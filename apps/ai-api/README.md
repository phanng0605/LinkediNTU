# ai-api (FastAPI)

This directory contains the FastAPI app for AI or ML endpoints.

Structure:
- `app/`:
  - `main.py`: FastAPI entry point
  - `routers/`: Organize endpoints
  - `services/`: Business logic or AI inference code
- `requirements.txt`: Python dependencies (FastAPI, etc.)

Run locally with:
```bash
uvicorn app.main:app --host 0.0.0.0 --port 8000
```
