from transformers import AutoTokenizer, AutoModelForCausalLM

# Load the fine-tuned model and tokenizer
model_name = "./fine_tuned_model"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForCausalLM.from_pretrained(model_name)

# Use the model for inference
def tailor_resume(resume_text, job_description):
    prompt = f"Resume: {resume_text}\nJob Description: {job_description}\nTailored Resume:"
    inputs = tokenizer(prompt, return_tensors="pt", max_length=512, truncation=True)
    outputs = model.generate(**inputs, max_length=512, num_return_sequences=1)
    tailored_resume = tokenizer.decode(outputs[0], skip_special_tokens=True)
    return tailored_resume