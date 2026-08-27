# AI Orientation

## 1. Foundations of Intelligence

### 1.1 AI Introduction
**Artificial Intelligence (AI)** is the broad field of computer science dedicated to creating systems capable of performing tasks that typically require human intelligence, such as visual perception, speech recognition, decision-making, and language translation.

The goal of AI is to simulate cognitive functions. It is helpful to view AI as the "umbrella" term that encompasses all subsequent technologies in this note set.

### 1.2 ML Introduction
**Machine Learning (ML)** is a subset of AI that focuses on the use of data and algorithms to imitate the way that humans learn, gradually improving its accuracy without being explicitly programmed for every specific task.

> [!IMPORTANT]
> While AI is the concept of "intelligent machines," ML is the **methodology** of using statistical patterns to achieve that intelligence.

### 1.3 Learning Paradigms: Supervised vs. Unsupervised
To understand how machines learn, we must distinguish between the presence and absence of "ground truth" (labeled data).

| Feature | **Supervised Learning** | **Unsupervised Learning** |
| :--- | :--- | :--- |
| **Input Data** | Labeled (Input + Correct Output) | Unlabeled (Input only) |
| **Goal** | Predict outcomes for new data | Find hidden patterns or structures |
| **Common Tasks** | Classification, Regression | Clustering, Association, Dimensionality Reduction |
| **Analogy** | Learning with a teacher/answer key | Learning by finding similarities alone |

**Vs: Supervised vs. Unsupervised**
The fundamental difference lies in the **Feedback Loop**. In Supervised learning, the model is told when it is wrong. In Unsupervised learning, the model identifies structures based solely on the inherent properties of the data.

***

*Now that we have established the theoretical foundations of how machines learn from data, we can transition into the modern era of intelligence: Large Language Models.*

## 2. Large Language Models (LLMs)

### 2.1 The LLM Landscape
**Large Language Models (LLMs)** are a specialized subset of Deep Learning trained on massive datasets to understand and generate human-like text. They are typically based on the **Transformer Architecture**.

#### Model Taxonomy
*   **GPT (Generative Pre-trained Transformer):** Developed by OpenAI. Known for high-reasoning capabilities and widely used in consumer applications (ChatGPT).
*   **BERT (Bidirectional Encoder Representations from Transformers):** Developed by Google. Unlike GPT, it is "bidirectional," meaning it looks at words to the left and right of a token simultaneously, making it excellent for understanding context/sentiment.
*   **Claude:** Developed by Anthropic. Emphasizes "Constitutional AI" (safety and steerability) and features very large context windows.
*   **Llama:** Developed by Meta. A leading **Open Weights** model that allows developers to run powerful LLMs on their own infrastructure.

### 2.2 LLM Use Cases
LLMs are versatile tools that can be categorized by their functional utility:

1.  **Content Generation:** Drafting emails, essays, or creative stories.
2.  **Code Assistance:** Generating boilerplate, debugging, or translating logic between programming languages.
3.  **Summarization:** Distilling long documents or meeting transcripts into actionable bullet points.
4.  **Extraction & Transformation:** Converting unstructured text (an email) into structured data (a JSON object).
5.  **Reasoning & Logic:** Solving complex word problems or acting as an agent to execute multi-step tasks.

***

*Understanding what these models can do is essential, but to use them effectively in a production environment, we must master the art of interaction and the economics of scale.*

## 3. Operational Excellence

### 3.1 LLM Best Practices
To maximize the utility of an LLM, users should follow proven prompting frameworks.

*   **Zero-Shot Prompting:** Asking a question without any examples. (e.g., *"Translate this to French: Hello"*)
*   **Few-Shot Prompting:** Providing a few examples of the desired input/output format before the actual task. This significantly improves pattern adherence.
*   **Chain-of-Thought (CoT):** Instructing the model to *"think step-by-step."* This forces the model to allocate more computation to the reasoning process before arriving at an answer.

> [!TIP]
> **Golden Rule:** The quality of the output is directly proportional to the specificity and context provided in the prompt.

### 3.2 Cost and Token Considerations
LLMs do not process "words"; they process **Tokens**. A token is a chunk of characters (roughly 0.75 words in English).

#### The Economic Framework
Managing LLM implementation requires balancing performance against two primary constraints:

1.  **Context Window:** The maximum number of tokens a model can "remember" at one time. Exceeding this window causes the model to "forget" the beginning of the conversation.
2.  **Pricing Models:** Most providers charge based on:
    *   **Input Tokens:** The cost of the prompt you send.
    *   **Output Tokens:** The cost of the response generated (usually more expensive than input).

| Factor | Impact | Mitigation Strategy |
| :--- | :--- | :--- |
| **High Token Count** | Increased Latency & Cost | Summarize long contexts; use RAG (Retrieval Augmented Generation). |
| **Complex Reasoning** | Higher Output Token Count | Use smaller, cheaper models for simple tasks; save "frontier" models for logic. |