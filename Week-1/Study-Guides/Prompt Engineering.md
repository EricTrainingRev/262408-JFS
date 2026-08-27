# Prompt Engineering

## 1. High-Level Overview
**Prompt Engineering** is the discipline of optimizing the input provided to a Large Language Model (LLM) to elicit the most accurate, high-quality, and relevant responses. Rather than treating the model as a search engine, Prompt Engineering treats it as a **reasoning engine** that can be steered through specific linguistic structures and logical frameworks.

---

## 2. Structural Framework: Prompt Types

LLM interactions are not monolithic; the "shape" of the prompt changes based on the intended interaction model.

### 2.1 Primary Interaction Paradigms
*   **Instruction Prompting:** Direct commands where the user tells the model exactly what to do (e.g., *"Summarize this text"*).
*   **Completion Prompting:** Providing a prefix and letting the model "fill in the blanks" (e.g., *"The capital of France is..."*). This was the primary method for early models like GPT-3.
*   **Dialogue (Chat) Prompting:** A multi-turn interaction where the model maintains context across a conversation, utilizing `system`, `user`, and `assistant` roles.

### 2.2 The Spectrum of Examples: Zero-Shot vs. Few-Shot
The most effective way to guide a model's output format is to provide examples.

| Type | Definition | Example |
| :--- | :--- | :--- |
| **Zero-Shot** | Providing a task with no examples of the desired output. | *"Classify this review as Positive or Negative: The food was cold."* |
| **Few-Shot** | Providing one or more examples (shots) to establish a pattern before the final task. | *"Review: Great! -> Positive. Review: Bad. -> Negative. Review: Okay. ->"* |

> [!TIP]
> **Pro-Tip:** Use **Few-Shot** prompting when you need the model to adhere to a very specific, non-standard output format (like a custom JSON schema or a specific brand voice).

***

*While the number of examples provided helps with formatting, the internal logic of the model is best unlocked through structured reasoning techniques.*

## 3. Advanced Reasoning & Persona Engineering

### 3.1 Chain-of-Thought (CoT) Prompting
**Chain-of-Thought (CoT)** is a technique used to improve the performance of LLMs on complex tasks involving logic, arithmetic, or multi-step reasoning. Instead of jumping directly to an answer, the model is encouraged to "think out loud."

**Mechanism:**
By forcing the model to generate intermediate reasoning steps, you reduce the likelihood of "calculation errors" or logical leaps that lead to hallucinations.

**Implementation Patterns:**
1.  **Zero-Shot CoT:** Simply appending the phrase **"Let's think step by step"** to a prompt. This is a powerful "magic phrase" that triggers latent reasoning capabilities.
2.  **Manual CoT:** Providing an example in your few-shot prompt that explicitly shows the reasoning steps before the final answer.

**Comparison: Standard vs. CoT**
*   **Standard:** "Q: If I have 5 apples and eat 2, how many are left? A: 3"
*   **CoT:** "Q: If I have 5 apples and eat 2, how many are left? A: **You started with 5 apples. You ate 2. 5 minus 2 equals 3. The answer is 3.**"

### 3.2 Persona Engineering (Role Prompting)
**Persona Engineering** involves assigning a specific identity, expertise level, or personality to the LLM. This sets the "latent space" the model operates within, influencing tone, vocabulary, and depth of knowledge.

**Key Components of a Persona:**
*   **Role:** Who is the model? (e.g., *"You are a Senior DevOps Engineer"*).
*   **Audience:** Who is the model talking to? (e.g., *"Explain this to a 5-year-old"* vs. *"Explain this to a CTO"*).
*   **Tone/Style:** How should it speak? (e.g., *"Use a professional, concise, and clinical tone"*).

**Example of a Complex Prompt Construction:**
```text
[Persona] You are an expert Python Security Auditor.
[Task] Review the following code snippet for SQL injection vulnerabilities.
[Constraint] Provide your findings in a bulleted list. For every vulnerability found, 
             provide a 'Severity' rating (Low/Med/High).
[Format] Return the output in valid JSON format.
[Input] 
def get_user(username):
    query = "SELECT * FROM users WHERE name = '" + username + "'"
    ...
```

### 3.3 Constraints: The "Guardrails" of Prompting
Constraints are the boundaries you set to prevent the model from drifting into unwanted territory. Effective constraints include:
*   **Length Constraints:** *"Keep your response under 50 words."*
*   **Negative Constraints:** *"Do not mention any competitor products."*
*   **Format Constraints:** *"Only output the raw JSON; do not include conversational filler like 'Here is your JSON'."*
