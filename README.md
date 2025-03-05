# LinkediNTU Monorepo

This repository houses multiple applications and packages for the **LinkediNTU** project. The goal is to provide a cohesive, modular codebase, using:

- **Next.js** (React) for the frontend  
- **Node/Express** (optional) for a separate backend API  
- **FastAPI** for AI/ML endpoints  
- **Shared packages** for utilities and UI components  

---

## Repository Structure

```markdown
.
├── apps/
│   ├── web-frontend/    # Next.js (React) app
│   ├── node-api/        # Optional Node/Express backend
│   └── ai-api/          # FastAPI AI/ML service
├── packages/
│   ├── shared-utils/    # Shared JS/TS utility functions
│   └── shared-ui/       # Reusable React UI components
├── scripts/             # CI/CD or build scripts
├── docs/                # Additional design docs, proposals, etc.
└── README.md            # This file
```
### 1. `apps/web-frontend` (Next.js)
- Contains your main React application.
- The `pages/`, `components/`, and `lib/` directories hold UI logic; `public/` holds static assets.
- A `package.json` manages frontend dependencies like React, Next.js, etc.

### 2. `apps/node-api` (Optional)
- A Node-based backend (e.g., Express, MongoDB) for APIs you don’t want to build into Next.js directly.
- Has its own `package.json` for backend dependencies.

### 3. `apps/ai-api` (FastAPI)
- A Python-based service for AI/ML or advanced features (e.g., resume scoring, text analysis).
- Managed with `requirements.txt`.
- `app/main.py` is the entry point for your FastAPI endpoints.

### 4. `packages/`
- **`shared-utils/`**: Common logic (utility functions, constants, etc.) shared by other apps.
- **`shared-ui/`**: Reusable React components or design elements used by the `web-frontend` or any other React app.

### 5. `scripts/` and `docs/`
- **`scripts/`**: Shell scripts, CI/CD tools, or deployment commands.
- **`docs/`**: Documentation, design specs, or internal proposals.

---

## Getting Started

Below are the typical steps to get each part of the monorepo up and running.

### 1. Cloning the Repo
```bash
git clone https://github.com/phanng0605/LinkediNTU
cd LinkediNTU
```

### 2. Installing Dependencies

#### Option A: Use a Workspace Manager (Recommended)
If using Yarn or PNPM:

1. In the **root** `package.json`, include:
   ```json
   {
     "private": true,
     "name": "LinkediNTU",
     "workspaces": [
       "apps/*",
       "packages/*"
     ]
   }
   ```
2. Run:
   ```bash
   yarn install
   ```
   or
   ```bash
   pnpm install
   ```
   This installs and links dependencies for all sub-packages automatically.

#### Option B: Manual Installation in Each Folder
- **`apps/web-frontend`:**  
  ```bash
  cd apps/web-frontend
  npm install
  ```
- **`apps/node-api` (optional):**  
  ```bash
  cd ../node-api
  npm install
  ```
- **`apps/ai-api` (FastAPI):**  
  ```bash
  cd ../ai-api
  python -m venv venv
  source venv/bin/activate
  pip install -r requirements.txt
  ```
- **`packages/shared-utils` / `packages/shared-ui`:**  
  ```bash
  cd packages/shared-utils
  npm install
  ```
  Same for `shared-ui`.

---

## Running Each Service

### 1. Next.js Frontend
```bash
cd apps/web-frontend
npm run dev
# or yarn dev
```
The app typically runs on `http://localhost:3000`.

### 2. Node.js API (Optional)
```bash
cd apps/node-api
npm start
# or yarn start
```
Access it at `http://localhost:5000` (or whichever port you set).

### 3. FastAPI (AI API)
```bash
cd apps/ai-api
uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload
```
Endpoints are available at `http://localhost:8000`.

---

## Using Shared Packages

### 1. Shared Utilities (`packages/shared-utils`)
For example:
```js
// packages/shared-utils/src/dateUtils.js
export function formatDate(date) {
  // ...
}
```
Export from `index.js`:
```js
export * from "./dateUtils";
```
Then, in your other apps:
```js
import { formatDate } from "@LinkediNTU/shared-utils";
```

### 2. Shared UI Components (`packages/shared-ui`)
```js
// packages/shared-ui/src/components/Button.jsx
import React from "react";
export default function Button({ children }) {
  return <button>{children}</button>;
}
```
Export from `index.js`:
```js
export { default as Button } from "./components/Button";
```
Then, in the Next.js app:
```js
import { Button } from "@LinkediNTU/shared-ui";
```

---

## Scripts and Deployment
- Place custom build or deployment scripts in `scripts/`.
- Reference them in your `package.json` scripts (e.g., `"build": "bash scripts/build.sh"`).

---

## Contributing
1. **Create a branch** off the main branch (e.g., `feature/add-resume-scorer`).
2. **Implement changes** and commit to your branch.
3. **Open a Pull Request** for review and testing.
4. Merge after approval.

---

## Additional Notes
- Keep secrets out of version control by using `.env` files (e.g., `apps/web-frontend/.env.local`).
- Configure ESLint/Prettier at the root or individually per package.
- Store architecture diagrams or extra docs under `docs/`.

Enjoy developing with the Next.js & FastAPI monorepo!
```
