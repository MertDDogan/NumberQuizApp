# NumberQuizApp – Java GUI Number Quiz

A Java Swing-based graphical application where users are challenged to identify the English names of randomly selected numbers between 1 and 20. This application features real-time validation, user input tracking, and form transitions to ensure a smooth and interactive experience.

---

## Features

- Handles 20 unique random questions
- Displays one number at a time
- Accepts answers through a JTextField input
- Case-insensitive input validation
- Retry, Skip, and Quit functionality
- Real-time score tracking (Total, Correct, Wrong, Skipped)
- Clean layout and user-friendly navigation
- Reads question data from external file
- Visual feedback using tick/cross images

---

## How It Works

1. The `Main` class launches the `InputForm` window.
2. A random number (1–20) is shown.
3. The user types the English word corresponding to the number.
4. The app validates the answer:
   - If correct, the user proceeds to the next question.
   - If incorrect, the same question is asked again.
5. User may also choose to skip the question.
6. After 20 total questions (correct, skipped, and wrong), final statistics are shown with an option to restart or exit.

---

## Technologies Used

- Java Swing (GUI components)
- Java I/O (FileReader, BufferedReader)
- Object-Oriented Programming
- ActionListener (event handling)
- Conditional logic and input validation

---

## Folder & File Structure

- `src/` – All Java classes: `InputForm.java`, `OutputForm.java`, `Main.java`
- `resources/` – Includes:
  - `numbers.txt` – list of numbers (1–20) in word form
  - `tick.png` – correct answer icon
  - `cross.png` – incorrect answer icon

All paths are handled **relatively** to ensure cross-platform compatibility.

---

## How to Run

### Requirements
- Java 8 or higher
- IDE such as IntelliJ or Eclipse

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/MertDDogan/NumberQuizApp.git
   cd NumberQuizApp
