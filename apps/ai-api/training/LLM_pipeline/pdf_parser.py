import PyPDF2
import re
import json

def extract_text_from_pdf(pdf_path):
    with open(pdf_path, 'rb') as file:
        reader = PyPDF2.PdfReader(file)
        text = "\n".join(page.extract_text() or '' for page in reader.pages)
    return text

def split_resume_sections(text):
    # Common resume section headers
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
    
# Example usage
if __name__ == "__main__":
    # Example usage
    pdf_path = "path/to/your/resume.pdf"
    output_path = "resume.json"
    generate_resume_json(pdf_path, output_path)
