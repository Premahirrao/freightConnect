# freightConnect

An Android application project for connecting freight services.

---

## How to Push Your Android Studio Project to This Repository

Follow the steps below to connect your local Android Studio project to this GitHub repository and push your code.

### Prerequisites

- [Git](https://git-scm.com/downloads) installed on your machine
- [Android Studio](https://developer.android.com/studio) with your project open
- A GitHub account with access to this repository

---

### Option 1 — Using Android Studio's Built-in VCS (Recommended)

1. **Open your project** in Android Studio.
2. Go to **VCS → Enable Version Control Integration** (if not already done) and select **Git**.
3. Go to **VCS → Git → Remotes** (or **File → Settings → Version Control → Git → Remotes**).
4. Click **+** and add the remote URL:
   ```
   https://github.com/Premahirrao/freightConnect.git
   ```
5. Go to **VCS → Commit** (or press `Ctrl+K` / `Cmd+K`), stage your files, and write a commit message.
6. Go to **VCS → Git → Push** (or press `Ctrl+Shift+K` / `Cmd+Shift+K`) to push to GitHub.

---

### Option 2 — Using the Terminal / Command Line

Open a terminal inside your Android Studio project root folder and run the following commands:

```bash
# 1. Initialize Git (skip if already initialized)
git init

# 2. Add the remote repository
git remote add origin https://github.com/Premahirrao/freightConnect.git

# 3. Stage all your files
git add .

# 4. Create your first commit
git commit -m "Initial commit"

# 5. Push to GitHub (use 'main' or 'master' depending on your default branch)
git push -u origin main
```

> **Tip:** If you get an error like `rejected – non-fast-forward`, the remote already has commits. Run `git pull origin main --allow-unrelated-histories` first, then push again.

---

### Keeping Sensitive Files Out of Version Control

A `.gitignore` file is already included in this repository. It automatically excludes:
- Build outputs (`build/`, `*.apk`, `*.aab`)
- Gradle cache (`.gradle/`)
- IDE-specific files (`.idea/`, `*.iml`)
- Local SDK paths (`local.properties`)
- Firebase config (`google-services.json`)

If you have a **signing keystore** or other secrets, make sure they are listed in `.gitignore` before your first push.

---

### Useful Git Commands

| Command | Description |
|---|---|
| `git status` | See which files are staged/unstaged |
| `git add .` | Stage all changed files |
| `git commit -m "message"` | Commit staged files |
| `git push` | Push commits to GitHub |
| `git pull` | Fetch and merge latest changes |
| `git log --oneline` | View recent commits |
