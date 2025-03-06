from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from transformers import pipeline
import os
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

app = FastAPI()

# Load a pre-trained model for text generation (e.g., GPT-2)
generator = pipeline('text-generation', model='gpt-2')

class ResumeInput(BaseModel):
    resume_text: str
    job_description: str = None

class ResumeScore(BaseModel):
    score: float
    feedback: str

@app.post("/score-resume/", response_model=ResumeScore)
async def score_resume(resume_input: ResumeInput):
    """
    AI-powered resume scoring based on industry standards.
    """
    # Example scoring logic (replace with actual AI model logic)
    score = 0.8  # Placeholder score
    feedback = "Your resume looks good, but consider adding more relevant keywords."

    return ResumeScore(score=score, feedback=feedback)

@app.post("/tailor-resume/", response_model=str)
async def tailor_resume(resume_input: ResumeInput):
    """
    Dynamically tailor the resume based on the job description.
    """
    if not resume_input.job_description:
        raise HTTPException(status_code=400, detail="Job description is required for tailoring.")

    # Example AI model logic to tailor the resume
    prompt = f"Tailor this resume for the job description:\n\nResume:\n{resume_input.resume_text}\n\nJob Description:\n{resume_input.job_description}\n\nTailored Resume:"
    tailored_resume = generator(prompt, max_length=500, num_return_sequences=1)[0]['generated_text']

    return tailored_resume

@app.get("/")
def read_root():
    return {"message": "Hello from FastAPI AI endpoint!"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)