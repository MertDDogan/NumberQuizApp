# 🧠 NumberQuizApp - Java GUI Number Quiz

A simple but powerful number quiz game built using Java Swing.  
Users are shown a random number (1–20) and must type its English name (e.g., `four`, `twelve`). The app checks answers, tracks performance, and provides a fun, interactive interface.

---

## ✨ Features

- ✅ 20 unique questions (each number shown once)
- 🔁 Skip, Retry, and Quit options
- 🔡 Case-insensitive answer checking
- 📊 Live score tracking (Total, Correct, Wrong, Skipped)
- 🖼️ Visual tick/cross images
- 📈 Bar chart-style stats indicator
- 💾 Loads numbers from external file (`numbers.txt`)
- 🎨 Clean dark-themed GUI layout

---

## 🚀 How It Works

1. A random number appears on screen.
2. User types the English word for the number.
3. If correct ➜ ✅ "Correct!" form appears with tick.
4. If wrong ➜ ❌ "Wrong!" form appears with cross.
5. Retry generates new/same question depending on correctness.
6. Game ends after 20 total questions. Final stats shown.

---

## 🧪 Example Screenshots

| Input Form                     | Output (Correct/Wrong)          |
|-------------------------------|---------------------------------|
| `Enter the name of the number: 4` | ✅ **Correct** or ❌ **Wrong Answer** |
| Total: 2 | Correct: 1 | Wrong: 1 |
| Final Stats: `Correct: 14, Wrong: 3, Skipped: 3` |

---

## 🛠 Technologies Used

- **Java Swing** (UI)
- **File I/O** (`BufferedReader`, `FileReader`)
- **Event Handling** (`ActionListener`)
- **OOP Principles** (Encapsulation, Classes, Constructors)
- **Exception Handling** (Robustness)

---

## 🧠 Key Learning Highlights

- Java GUI component design (buttons, labels, text fields)
- Reading and using external resource files
- Managing form transitions between multiple screens
- Validating user input (case-insensitive checks)
- Visual user feedback through images and layout
- Debugging, test case coverage, and UI logic flow

---

## 📖 Project Purpose

This application was originally developed as part of the **FUPR POF2 Spring 2025 GUI Programming Assessment** at university.

- 📚 **Assessment Weight**: 30%
- 🧑‍💻 **Role**: Full development (planning, UI, backend, testing)
- 📂 **Structure**: Includes `InputForm`, `OutputForm`, `Main`, resource files, and full logic

Now, it is refined and presented as a **personal portfolio project** for GitHub.

---

## 🔗 Run the App

### Requirements:
- Java 8 or higher
- Any Java IDE (e.g., IntelliJ, Eclipse)

### Instructions:
1. Clone the repository:
   ```bash
   git clone https://github.com/MertDDogan/NumberQuizApp.git
   cd NumberQuizApp
