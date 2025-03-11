from LLM_pipeline.pdf_parser import generate_resume_json
from DataAugmentor import ResumeDataAugmentor, GroqLLMClient

from transformers import pipeline
import requests 
import json
import os 
import argparse
from groq import Groq

groq_key = "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"
gemini_key = "AIzaSyCeEcNoSDQkjQjQxrUCHJjTM4mQFO7zv6E"

class ResumeAnalyser:
    def __init__(self, api_key):
        self.api_key = api_key
        self.client = GroqLLMClient(api_key = api_key)
        self.augmentor = ResumeDataAugmentor(self.client)
    
    def analyse_resume(self, pdf_path, output_path):
        resume_json = generate_resume_json(pdf_path, output_path)
        resume_feedback = {}
        for key, value in resume_json.items():
            print(f"Processing section {key}...")
            if value == "":
                print("Empty section, skipping...")
                continue
            feedback = self.augmentor.resume_comment(key, value)
            print(f"==== {key} ====")
            print(value)
            print(feedback)
            print("\n")
            resume_feedback[key] = feedback
        
        return resume_feedback

if __name__ == "__main__":
    pdf_path = "/Users/trongphan/Desktop/LinkediNTU/apps/ai-api/training/data/PhanResumeML.pdf"
    analyser = ResumeAnalyser(groq_key)
    resume_feedback = analyser.analyse_resume(pdf_path, "/Users/trongphan/Desktop/LinkediNTU/apps/ai-api/training/data/processed_resume/phan_rem.json")
    output_path = "/Users/trongphan/Desktop/LinkediNTU/apps/ai-api/training/data/feedback/phan_feedback.json"
    with open(output_path, "w") as f:
        json.dump(resume_feedback, f, indent=4)
    print(f"Feedback written to {output_path}")
    
# run this script to get the feedback of the resume
# cd /LinkediNTU/apps/ai-api/training/
# python -m LLM_pipeline.resume_analyse

    

    
        



