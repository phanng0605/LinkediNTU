import requests 
import json
import os 
import argparse

from GroqLLM import GroqLLMClient

class ResumeDataAugmentor: 
    def __init__(self, groq_client: GroqLLMClient):
        self.groq_client = groq_client

    def generate_resume(self, role: str) -> str:
        # """
        # Generate a realistic resume for a job applicant applying for a specific role.
        # Args:
        #     role (str): The role for which the resume is being generated.
        # Returns:
        #     str: The generated resume for the job applicant.
        # """
        prompt = f"Generate a realistic resume for a job applicant applying for the role of {role}."
        resume = self.groq_client.chat_completion(prompt)
        return resume.strip()

    def generate_tailored_resume(self, resume: str, job_description: str) -> str:
        # """
        # Generate a tailored resume based on a job description.
        # Args:
        #     resume (str): The original resume of the job applicant.
        #     job_description (str): The job description for which the resume is being tailored.
        # Returns:
        #     str: The tailored resume for the job applicant.
        # """
        prompt = (
            f"You are given the following resume from a job applicant: {resume}\n"
            f"The applicant submitted their resume for the following job description: {job_description}\n\n"
            f"Please generate an improved version of the resume that is tailored to the job description."
        )
    
        tailored_resume = self.groq_client.chat_completion(prompt)
        return tailored_resume.strip()
    
    def generate_job_description(self, role: str) -> str:
        # """
        # Generate a realistic job description for a specific role.
        # Args:
        #     role (str): The role for which the job description is being generated.
        # Returns:
        #     str: The generated job description for the role.
        # """
        prompt = f"Generate a realistic job description for the role of {role}."
        job_description = self.groq_client.chat_completion(prompt)
        return job_description.strip()

    def augment_data(self, resume: str, job_description: str) -> dict:
        tailored_resume = self.generate_tailored_resume(resume, job_description)
        return {
            "resume": resume,
            "job_description": job_description,
            "tailored_resume": tailored_resume,
        }

def main():
    parser = argparse.ArgumentParser(
        description="Generate resume data for a specific role using Groq."
    )
    parser.add_argument("--role", required=True, help="The job role for which to generate data.")
    parser.add_argument("--num", type=int, default=1, help="Number of resume data entries to generate.")
    parser.add_argument("--output", required=True, help="The output JSON filename (e.g., dataset.json).")
    args = parser.parse_args()
    
    groq_key = "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"
    groq_client = GroqLLMClient(api_key=groq_key)
    augmentor = ResumeDataAugmentor(groq_client)
    
    data_entries = []
    for i in range(args.num):
        # Generate resume and job description for the role.
        resume = augmentor.generate_resume(args.role)
        job_description = augmentor.generate_job_description(args.role)
        # Augment data to create a tailored resume.
        augmented_data = augmentor.augment_data(resume, job_description)
        data_entries.append(augmented_data)
    
    output_folder = "data"
    os.makedirs(output_folder, exist_ok=True)
    
    # Create the full output file path.
    output_file_path = os.path.join(output_folder, args.output)
    
    # Write the results to the specified output file as formatted JSON.
    with open(output_file_path, "w") as outfile:
        json.dump(data_entries, outfile, indent=2)
    
    print(f"Data successfully written to {output_file_path}")

if __name__ == "__main__":
    main()
