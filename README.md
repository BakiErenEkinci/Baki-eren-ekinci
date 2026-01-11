# Baki-eren-ekinci

<img width="718" height="750" alt="image" src="https://github.com/user-attachments/assets/22511e74-80ac-4ab1-97a0-b83dc829dc71" />


# 🎹 Piano Game (Java Swing)

A simple piano game developed using **Java Swing**.  
The game allows playing piano notes via keyboard input and stores statistics using an **SQLite** database.

---

## 🚀 Features
- Java Swing graphical user interface
- Play piano notes using keyboard
- SQLite database for storing game statistics
- Local `.db` file usage

---

## 🛠 Requirements
- **Java JDK 17+** (JDK 8 also works on most systems)
- **NetBeans IDE** (recommended)
- Windows / Linux / macOS

---

## 📂 Project Structure

```
project-root/
│
├─ src/                 # Java source files
├─ lib/                 # External libraries
│   └─ sqlite-jdbc-3.x.x.jar
│
├─ piano_stats.db       # SQLite database file
├─ README.md
```

> ⚠️ The `sqlite-jdbc` JAR file must be located inside the `lib` folder.

---

## ▶️ How to Run
1. Download or clone the project from GitHub
2. Open NetBeans → File → Open Project
3. Go to Project Properties → Libraries
4. Make sure `lib/sqlite-jdbc-3.x.x.jar` is added
5. Click Run

---

## ❗ Common Error
```
java.sql.SQLException: No suitable driver found for jdbc:sqlite
```

### Solution
- Ensure the `sqlite-jdbc` JAR file is included in the project
- The JAR file must be committed to GitHub inside the `lib` folder

---

## 📌 Notes
- This project is created for educational purposes
- You are free to modify and improve it

---



## 👤 Developer
baki eren ekinci


