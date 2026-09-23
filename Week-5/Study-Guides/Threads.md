# Java Multithreading & Concurrency

## Table of Contents

* [1. High-Level Overview](#1-high-level-overview)
* [2. The Core Building Blocks: Threads & Runnables](#2-the-core-building-blocks-threads--runnables)
    * [2.1 The `Thread` Class](#21-the-thread-class)
    * [2.2 The `Runnable` Interface](#22-the-runnable-interface)
    * [2.3 Comparison: Thread vs. Runnable](#23-comparison-thread-vs-runnable)
* [3. Thread Lifecycle: The States of a Thread](#3-thread-lifecycle-the-states-of-a-thread)
* [4. Multithreading Fundamentals](#4-multithreading-fundamentals)
* [5. Synchronization & Thread Safety](#5-synchronization--thread-safety)
* [6. Concurrency Pitfalls: Deadlock & Livelock](#6-concurrency-pitfalls-deadlock--livelock)
    * [6.1 Deadlock](#61-deadlock)
    * [6.2 Livelock](#62-livelock)
* [7. Classic Problem: Producer-Consumer](#7-classic-problem-producer-consumer)

***

## 1. High-Level Overview

**Multithreading** is a Java feature that allows concurrent execution of two or more parts of a program to maximize the utilization of the CPU. Each part of such a program is called a **Thread**. While a single process has a single flow of control, a multithreaded process has multiple threads of execution running within the same memory space.

[↑ Back to Table of Contents](#table-of-contents)
***

*To understand how we manage multiple flows of execution, we must first look at the two primary ways to define a task in Java.*

## 2. The Core Building Blocks: Threads & Runnables

In Java, you can create a thread in two main ways: by extending the `Thread` class or by implementing the `Runnable` interface.

### 2.1 The `Thread` Class

The `Thread` class is the most basic way to create a thread. You create a class that extends `Thread` and override its `run()` method, placing the work you want done inside `run()`. To actually start the thread, you instantiate your subclass and call `start()` on it — never call `run()` directly. Calling `run()` directly would simply execute the method on the current thread; calling `start()` launches a brand-new thread of execution that invokes `run()` concurrently. When `run()` returns, that thread terminates.

Because it descends from a class, this approach consumes Java's single-inheritance slot. Your thread class cannot extend any other useful class, and the thread logic is tightly coupled to the runner — the task and the thread that executes it are one and the same object. This works for simple cases but becomes rigid as soon as you want to reuse or reassign work.

### 2.2 The `Runnable` Interface

`Runnable` is a functional interface that represents a unit of work — a task — independent of how it is executed. It declares a single `run()` method, so it can also be written as a lambda. You implement `run()` to define the task, then hand that `Runnable` to a `Thread` constructor. The thread owns the execution; the `Runnable` owns the work.

This cleanly separates the **task** from the **runner**. Your class stays free to extend anything else, and the same `Runnable` can be reused across many threads or submitted to an executor. Because a `Runnable` has no intrinsic awareness of threads, it is also far easier to test — you can invoke its `run()` directly in a unit test without spinning up real threads.

### 2.3 Comparison: Thread vs. Runnable

| Feature | `Thread` Class | `Runnable` Interface |
| :--- | :--- | :--- |
| **Inheritance** | Uses up the single inheritance slot in Java. | Allows the class to extend another class. |
| **Object Orientation** | Represents a thread object and a task combined. | Separates the **task** (Runnable) from the **runner** (Thread). |
| **Flexibility** | Less flexible; harder to reuse tasks. | Highly flexible; ideal for Thread Pools and ExecutorServices. |
| **Best Practice** | Generally avoided for complex systems. | **Recommended approach** for most professional development. |

> [!TIP]
> Use `Runnable` whenever possible. It keeps your code decoupled and allows your class to extend other useful classes.

[↑ Back to Table of Contents](#table-of-contents)
***

*Once a thread is created, it doesn't just run immediately; it moves through various stages of existence.*

## 3. Thread Lifecycle: The States of a Thread

A thread in Java always exists in one of the following states (defined in `Thread.State`):

1.  **NEW:** A thread that has been created but not yet started via `start()`.
2.  **RUNNABLE:** A thread that is executing in the JVM. It may be currently running or waiting for resource allocation (like CPU time) from the operating system.
3.  **BLOCKED:** A thread that is waiting for a monitor lock to enter a `synchronized` block/method.
4.  **WAITING:** A thread that is waiting indefinitely for another thread to perform a particular action (e.g., via `Object.wait()` or `Thread.join()`).
5.  **TIMED_WAITING:** A thread that is waiting for another thread to perform an action for up to a specified waiting time (e.g., via `Thread.sleep(ms)` or `Object.wait(ms)`).
6.  **TERMINATED:** A thread that has finished its execution.

The exact state observed right after `start()` is scheduling-dependent: the thread may still be `RUNNABLE`, or may already have progressed further by the time you check. The `NEW`, `TIMED_WAITING`, and `TERMINATED` states are the reliable ones to demonstrate, since they are tied to definite events (construction, a timed wait, and completion) rather than to how quickly the scheduler happened to run the thread.

Two methods orchestrate this flow. `start()` moves a thread from `NEW` into `RUNNABLE` in the background while the calling thread keeps going, which is how several threads run at once. `join()` then makes the calling thread pause until a specific worker finishes, so the caller can act only once the work it depends on is done.

[↑ Back to Table of Contents](#table-of-contents)
***

*With the lifecycle understood, let's explore the broader concept of managing these threads simultaneously.*

## 4. Multithreading Fundamentals

**Multithreading** is the execution of multiple threads concurrently. 

*   **Concurrency vs. Parallelism:** 
    *   **Concurrency** is about *dealing* with many things at once (interleaving execution).
    *   **Parallelism** is about *doing* many things at once (simultaneous execution on multiple CPU cores).
*   **Context Switching:** The process of the CPU switching from one thread to another. While necessary, excessive context switching can lead to performance degradation.
*   **Race Condition:** A situation where multiple threads access and modify shared data concurrently, and the final outcome depends on the timing/order of execution.

[↑ Back to Table of Contents](#table-of-contents)
***

*Since threads share memory, we need a way to prevent them from corrupting shared data.*

## 5. Synchronization & Thread Safety

To prevent **Race Conditions**, Java provides **Synchronization** mechanisms to ensure that only one thread can access a shared resource at a time.

A **lock** gives a thread exclusive access to the block it guards. Only one thread may hold a given lock at a time, and any other thread that tries to enter the same guarded region blocks until the lock is released.

### 5.1 The `synchronized` Keyword

You can synchronize code at the **Method level** or the **Block level**.

**Synchronized Method:** Locking at the method level guards the entire method against concurrent access. The whole object is locked for the duration of the call, so no two threads can be inside that method on the same instance at once. Choose method-level synchronization when the entire body is one critical section and it stays short — it is the simplest to write, read, and reason about.

**Synchronized Block:** Locking at the block level wraps only a specific portion of a method. Choose block-level synchronization when only part of the method needs protection, so the rest of the work can still run in parallel; when you want to lock on a specific object other than the current instance; or when you want to keep the lock held for a shorter time. A shorter hold reduces contention, the time other threads waste waiting for the lock. The trade-off is a little more verbosity and more places to get the guard wrong.

Beyond `synchronized`, the `java.util.concurrent` package offers explicit `Lock` objects, the most common being `ReentrantLock`. A `Lock` plays the same guard role as a `synchronized` block, but it is an explicit object that adds capabilities `synchronized` lacks — most notably a non-blocking `tryLock()` that returns immediately instead of waiting, timed acquisition, and interruptible waiting. Because there is no automatic release, every `lock()` must be paired with an `unlock()`, typically in a `finally` block. Choosing between `synchronized` and a `Lock` is usually a trade of simplicity for control.

### 5.2 Volatile Keyword

The `volatile` keyword ensures that a variable is always read from and written to **main memory**, rather than being cached in a thread's local CPU cache. This guarantees **visibility** but does *not* guarantee atomicity (e.g., `count++` is not safe with just `volatile`).

Visibility and atomicity are distinct concerns. A `volatile` read or write is atomic in itself, but a compound operation such as read-modify-write — where a thread reads a value, computes on it, and writes the result back — is not protected as a whole. Two threads can still interleave between the read and the write and clobber each other's result. So `volatile` fixes the case where one thread writes and another should see it promptly, but it does *not* fix a shared counter that many threads increment; that needs synchronization.

[↑ Back to Table of Contents](#table-of-contents)
***

*While synchronization solves one problem, improper use can lead to new, more dangerous issues.*

## 6. Concurrency Pitfalls: Deadlock & Livelock

### 6.1 Deadlock

**Deadlock** occurs when two or more threads are blocked forever, each waiting for the other to release a resource. A classic scenario has two threads and two locks: thread A acquires lock 1 then waits for lock 2, while thread B acquires lock 2 then waits for lock 1. Each thread holds a lock the other needs, and neither can proceed — they are mutually stuck.

The defining symptom is that neither thread is doing any work: each is `BLOCKED`, waiting on a monitor it will never get. The program does not crash and shows no error; it simply stops making progress and hangs. Because the failure is silent and looks identical to a slow program, particularly with more threads and more locks, deadlocks are notoriously difficult to debug once they appear in real systems. The standard preventive measure is to acquire locks in a consistent, global order so that the circular wait can never form.

> [!WARNING]
> Deadlocks can be extremely difficult to debug because they don't cause crashes; they just cause the application to "hang."

### 6.2 Livelock

**Livelock** is similar to deadlock, but the threads are not blocked. Instead, they are constantly changing their state in response to each other, making no actual progress. A good analogy is two people meeting in a narrow hallway who both try to be polite and step aside: they both move left, then both move right at the same time, endlessly re-blocking each other even though both are actively "trying to help."

The key difference from deadlock is the threads' state. In deadlock the threads are `BLOCKED`, parked on a monitor and consuming no CPU. In livelock the threads remain active and `RUNNABLE`, burning CPU cycles while continuously repositioning — yet still accomplishing nothing. This active-busy behavior is what makes livelock distinct: the threads are not stuck in one place, they are circling.

An important variant uses non-blocking lock acquisition. A `ReentrantLock`'s `tryLock()` attempts to grab the lock but returns `false` immediately rather than waiting if it is already held. Threads that keep trying to acquire each other's lock this way can fall into a livelock: each briefly holds its own lock, fails to grab the other's, backs off, and retries — all in step, so they keep colliding. Because these are active busy loops rather than blocked threads, they must cap their attempts, or the livelock would spin forever.

> [!TIP]
> In deadlock, threads sit in `BLOCKED`/`WAITING` states, doing nothing; in livelock, threads stay active in the `RUNNABLE` state, consuming CPU cycles without doing useful work.

[↑ Back to Table of Contents](#table-of-contents)
***

*Finally, let's look at how these concepts are applied to solve a fundamental design problem.*

## 7. Classic Problem: Producer-Consumer

The **Producer-Consumer Problem** is a classic synchronization challenge where two types of threads share a common, fixed-size buffer.

*   **Producers:** Generate data and put it into the buffer.
*   **Consumers:** Take data out of the buffer and process it.

### The Challenges:
1.  **Buffer Overflow:** The producer must not add data if the buffer is full.
2.  **Buffer Underflow:** The consumer must not try to take data if the buffer is empty.
3.  **Mutual Exclusion:** Only one thread (producer or consumer) should modify the buffer at any given time.

### The Classic Solution: `wait()` and `notifyAll()`

The traditional solution coordinates threads through the intrinsic monitor with `Object.wait()` and `Object.notifyAll()`, invoked inside `synchronized` code. The mechanics are deliberately manual:

When a producer finds the buffer full, it calls `wait()`, which releases the monitor lock it holds so a consumer can enter, and parks the producer until it is woken. The producer resumes only after it has been notified and has reacquired the lock. Consumers do the mirror image: when the buffer is empty, a consumer calls `wait()` to release the lock and wait for a producer. Every path that makes room or makes data available calls `notifyAll()`, which wakes every thread waiting on that monitor so whichever side is now unblocked gets a chance to run.

Two details are essential to getting this right. First, `wait()` and `notifyAll()` are only legal while holding the intrinsic lock of the object they are called on, which is why they must live inside `synchronized` code. Second, the condition must be checked in a loop rather than a single `if`: after a thread wakes, it must re-check whether the buffer is still full (or still empty), because a `notifyAll()` can wake a thread for a reason other than the exact condition it was waiting on. Rechecking in a loop guards against such spurious or unhelpful wakes.

### The Modern Solution: `ArrayBlockingQueue`

Because coordinating `wait()`/`notifyAll()` by hand is easy to get subtly wrong, the `java.util.concurrent` package provides `ArrayBlockingQueue`, a bounded, thread-safe queue. The queue itself enforces all three rules internally: its `put()` blocks when the buffer is full, so there is no overflow; its `take()` blocks when it is empty, so there is no underflow; and all the locking is handled inside the class. This collapses the entire manual `wait()`/`notifyAll()` machinery into two method calls.

> [!TIP]
> In modern Java, it is highly recommended to use `java.util.concurrent.ArrayBlockingQueue` instead of manual `wait/notify`, as it handles all this complexity safely and efficiently.

[↑ Back to Table of Contents](#table-of-contents)
