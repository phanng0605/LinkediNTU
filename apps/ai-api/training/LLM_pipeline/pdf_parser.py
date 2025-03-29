import PyPDF2
import re
import json
from PyPDF2 import PdfReader
import google.generativeai as genai
from typing import Dict, Optional

def extract_text_from_pdf(pdf_path):
    with open(pdf_path, 'rb') as file:
        reader = PyPDF2.PdfReader(file)
        text = "\n".join(page.extract_text() or '' for page in reader.pages)
    return text

"""
Extract the resume content from the PDF file, but we hard code the section headers
"""
def split_resume_sections(text):
    # Common resume section headers
    # Maybe we have to hardcode this list for now, cause all the resume will have the same format and same header
    section_headers = [
        "Education", "Experience", "Work Experience", "Projects", 
        "Certifications", "Skills", "Awards", "Publications", "Interests"
    ]
    
    # Create a regex pattern to split text at these section headers
    pattern = r"(?i)(?=" + "|".join(section_headers) + r")"

    # Split text based on identified sections
    sections = re.split(pattern, text)

    # Organize into dictionary
    resume_dict = {}
    for section in sections:
        lines = section.strip().split("\n")
        if lines:
            header = lines[0].strip()
            content = "\n".join(lines[1:]).strip()
            resume_dict[header] = content
    
    return resume_dict

def generate_resume_json(pdf_path, output_path):
    extracted_text = extract_text_from_pdf(pdf_path)
    resume_sections = split_resume_sections(extracted_text)
    resume_storage = {}
    
    for section, content in resume_sections.items():
        resume_storage[section] = content
        print(f"==== {section} ====")
        print(content, "\n")
        
    with open(output_path, "w") as f:
        json.dump(resume_storage, f, indent=4)
    print(f"Written to {output_path}")
    return resume_storage

"""
Extract the resume content from the PDF file using Gemini AI to read pdf and extract the content
"""
def parse_resume_with_gemini(pdf_path: str, 
                           output_json_path: str,
                           gemini_api_key: str,
                           prompt: Optional[str] = None) -> Dict[str, str]:
    genai.configure(api_key=gemini_api_key)
    model = genai.GenerativeModel('gemini-2.0-flash-lite')
    text = extract_text_from_pdf(pdf_path)
    
    if prompt is None:
        prompt = """
        Analyze the following resume text and extract structured information. 
        Return the data in JSON format with these sections:
        - personal_info (name, email, phone, location, links)
        - summary (professional summary)
        - experience (list of jobs with company, title, dates, description)
        - education (list of degrees with institution, degree, dates)
        - skills (list of skills grouped by category)
        - certifications (if any)
        - projects (if any)
        
        For dates, use YYYY-MM format when possible.
        For descriptions, use bullet points when appropriate.
        
        Here is the resume text:
        """
    
    # Get response from Gemini
    response = model.generate_content(prompt + text)
    
    try:
        # Try to extract JSON from the response
        json_str = response.text.strip()
        if json_str.startswith("```json"):
            json_str = json_str[7:-3].strip()
        resume_data = json.loads(json_str)
    except json.JSONDecodeError:
        json_str = re.sub(r'^.*?\{', '{', json_str, flags=re.DOTALL)
        resume_data = json.loads(json_str)
    
    # Save to JSON file
    with open(output_json_path, 'w') as f:
        json.dump(resume_data, f, indent=2)
    
    return resume_data

def extract_text_from_pdf(pdf_path: str) -> str:
    text = ""
    with open(pdf_path, 'rb') as file:
        reader = PdfReader(file)
        for page in reader.pages:
            text += page.extract_text() + "\n"
    return text.strip()

def usage_extract_with_gemini(pdf_path, output_json_path):
    custom_prompt = """
    Extract the following information from this resume in JSON format:
    - contact_info (name, email, phone, LinkedIn)
    - work_experience (company, role, duration, achievements)
    - technical_skills (programming languages, tools)
    - education (degree, university, year)
    - projects (name, description, technologies)
    
    Make sure to:
    1. Parse dates consistently (MM/YYYY or YYYY-MM)
    2. Extract specific achievements with bullet points
    3. Group similar skills together
    """
    
    # Parse the resume
    resume_data = parse_resume_with_gemini(
        pdf_path=pdf_path,
        output_json_path=output_json_path,
        gemini_api_key=gemini_api_key,
        prompt=custom_prompt
    )
    
    print(f"Resume parsed successfully and saved to {output_json_path}")
    print("Sample extracted data:", json.dumps(resume_data, indent=2)[:500] + "...")
    
    
# Example usage
if __name__ == "__main__":
    # # Example usage
    pdf_path = "/Users/trongphan/Desktop/LinkediNTU/apps/ai-api/training/data/PhanResumeML.pdf"
    folder_path = "/Users/trongphan/Desktop/LinkediNTU/apps/ai-api/training/data/processed_resume/"
    output_path = folder_path+"phan_parsed_resume_gemini.json"
    # generate_resume_json(pdf_path, output_path)
    extract_with_gemini(pdf_path, output_path)
    