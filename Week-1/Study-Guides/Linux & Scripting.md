# Linux & Scripting Fundamentals

## 1. High-Level Overview
While AI and LLMs operate at the highest level of abstraction, the environments where they are developed, deployed, and scaled almost universally run on **Linux**. This module provides the foundational knowledge of the Linux Operating System, the command-line interface (CLI), and the ability to automate repetitive tasks through **Shell Scripting**.

---

## 2. Introduction to the Linux OS

**Linux** is an open-source, Unix-like operating system kernel. Unlike Windows or macOS, which are monolithic consumer products, Linux is modular. It is composed of the **Kernel** (the core that manages hardware) and a **Distribution** (or "Distro"), which bundles the kernel with various tools, libraries, and desktop environments.

### 2.1 The Linux Architecture
To understand Linux, one must understand its hierarchical structure:
*   **The Kernel:** The bridge between software and hardware (CPU, Memory, Disk).
*   **The Shell:** The command-line interpreter that acts as the interface between the user and the kernel.
*   **The File System:** A single, unified tree structure where everything (including hardware devices) is treated as a **file**.

### 2.2 The Linux File System Hierarchy
In Linux, there is no `C:\` drive. Everything starts from the **Root Directory**, denoted by a single forward slash (`/`).

| Directory | Purpose |
| :--- | :--- |
| `/bin` & `/usr/bin` | Essential user command binaries (e.g., `ls`, `cp`). |
| `/etc` | System-wide configuration files. |
| `/home` | Personal directories for individual users. |
| `/root` | The home directory for the System Administrator (Superuser). |
| `/var` | Variable data, such as system logs and databases. |
| `/tmp` | Temporary files (often cleared upon reboot). |

***

*Understanding the file structure is the first step; the next is learning how to navigate and manipulate that structure using the command line.*

## 3. Basic Linux Commands

The **CLI (Command Line Interface)** is the primary way to interact with Linux servers. Proficiency in these commands is non-negotiable for DevOps and AI Engineering.

### 3.1 Navigation & File Management
| Command | Action | Example |
| :--- | :--- | :--- |
| `pwd` | **Print Working Directory** (Where am I?) | `pwd` |
| `ls` | **List** files and directories | `ls -la` (List all, including hidden) |
| `cd` | **Change Directory** | `cd /etc/nginx` |
| `mkdir` | **Make Directory** | `mkdir my_project` |
| `touch` | Create an empty file | `touch config.yaml` |
| `cp` | **Copy** files or directories | `cp file.txt file_backup.txt` |
| `mv` | **Move** or rename files | `mv old_name.txt new_name.txt` |
| `rm` | **Remove** (Delete) files | `rm -rf folder_name` (**Caution: Recursive/Force**) |

### 3.2 Inspection & Permissions
*   **`cat`**: Displays the entire content of a file in the terminal.
*   **`less`**: Opens a file for interactive reading (allows scrolling).
*   **`grep`**: Searches for specific text patterns within files.
    *   *Example:* `cat logs.txt | grep "ERROR"`
*   **`chmod`**: Changes file permissions (Read, Write, Execute).
    *   *Example:* `chmod +x script.sh` (Makes a script executable).
*   **`sudo`**: **SuperUser Do**. Executes a command with administrative privileges.

***

*Mastering individual commands allows you to perform tasks manually, but the true power of Linux is unlocked when you combine these commands into automated sequences known as scripts.*

## 4. Shell Scripting Fundamentals

A **Shell Script** is a text file containing a series of commands that the shell executes in sequence. It is used to automate system administration, deployments, and data processing pipelines.

### 4.1 Anatomy of a Script
Every shell script should begin with a **Shebang** (`#!`). This tells the OS which interpreter to use to run the file.

**Basic Script Template (`myscript.sh`):**
```bash
#!/bin/bash

# This is a comment: A script to back up a directory

SOURCE="/home/user/data"
DEST="/home/user/backups"

echo "Starting backup of $SOURCE..."

# Create destination if it doesn't exist
mkdir -p "$DEST"

# Perform the copy
cp -r "$SOURCE" "$DEST"

echo "Backup completed successfully at $(date)"
```

### 4.2 Core Scripting Concepts
To move beyond simple command lists, scripts utilize programming logic:

1.  **Variables:** Storing data for reuse.
    *   `NAME="AI_Assistant"`
    *   Access via: `echo $NAME`
2.  **Conditionals (If/Else):** Making decisions based on states.
    ```bash
    if [ -f "config.json" ]; then
        echo "Config file exists."
    else
        echo "Error: Config file missing!"
    fi
    ```
3.  **Loops:** Repeating actions.
    ```bash
    # Loop through all .txt files in the current directory
    for file in *.txt; do
        echo "Processing $file"
    done
    ```
4.  **Exit Codes:** Every command returns a number (0–255) upon completion.
    *   `0` = Success.
    *   `Non-zero` = Failure.
    *   *Usage:* `if [ $? -eq 0 ]; then ...` (Checks if the previous command succeeded).

> [!IMPORTANT]
> **The Golden Rule of Scripting:** **Idempotency**. A well-written script should be able to run multiple times without causing errors or unintended side effects (e.g., a script that creates a directory should check if it exists first rather than failing).