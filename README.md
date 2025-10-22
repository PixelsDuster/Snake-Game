# 🐍 Java Snake Game (Swing GUI)

A modern, refactored, and feature-enhanced take on the classic **Snake** game — built in **Java** using **Swing**.  
The game logic was originally inspired by a YouTube tutorial (link below), but this project significantly expands upon it with:

✅ **Full GUI with Menus & Navigation**  
✅ **Leaderboard with Persistent Scoring**  
✅ **Restart & Main Menu System**  
✅ **Code Restructuring & Clean Architecture**  
✅ **Use of Records (where suitable), Enums, and Documentation**  

---

## ✨ Features

| Feature | Description |
|--------|-------------|
| 🎮 Playable Snake game | Move the snake with arrow keys, eat food, grow, survive! |
| 🧭 Clean MVC-style structure | Separate config, model, UI, and game logic packages |
| 🏆 Leaderboard | Saves name + score in `Leaderboard.txt` |
| 🔁 Restart System | Restart instantly after game over |
| 🪟 Multi-Screen UI | Main menu → Game → Game Over → Leaderboard |
| 🧱 Configurable Settings | Central `GameConfig` for easy tweaks |
| 📚 Fully Documented | Javadoc comments throughout the code |

---

## 📂 Project Structure
```
Project
|--.vscode
|--bin
|--lib
|--src
|  |--config
|  |  |--GameConfig.java
|  |--game
|  |  |--App.java
|  |  |--SnakeGame.java
|  |--model
|  |  |--Direction.java
|  |  |--Food.java
|  |  |--Snake.java
|  |  |--Tile.java
|  |--UI
|  |  |--GameOverPanel.java
|  |  |--LeaderboardPanel.java
|  |  |--MainMenuPanel.java
|--Leaderboard.txt
```

---

## 🕹️ Controls

| Key | Action |
|------|--------|
| ⬆️ | Move Up |
| ⬇️ | Move Down |
| ⬅️ | Move Left |
| ➡️ | Move Right |
| R (on Game Over) | Restart |
| E (on Game Over / Leaderboard) | Back to Menu |

---

## 🚀 Getting Started

### **Requirements**
- Java **17+** (recommended)
- Any IDE (IntelliJ, VS Code, Eclipse) or run through terminal

### **Run the Game**
```bash
cd src
javac game/App.java
java game.App
```
Or run App.java directly from your IDE.

---

## 📑 How the Leaderboard Works

- After game over, you're prompted to enter your name
- Score is appended to `Leaderboard.txt` in this format:
```
Alice,12
Mark,7
Sofia,21
```
- The leaderboard screen automatically sorts scores **from highest to lowest**

---

## 🧠 Code Highlights

- **Loose coupling** using Runnables for panel navigation
- **Enum (`Direction`)** for safe snake movement
- **Javadoc everywhere** for easy understanding & maintainability
- **Separation of concerns** (UI vs Game Logic vs Model vs Config)
- **Collision checks** implemented cleanly using model classes

---

## 📺 Inspiration

Original logic reference video: [YouTube Video](https://www.youtube.com/watch?v=Y62MJny9LHg&t=1s)  
Original project on GitHub: [Original Project GitHub](https://github.com/ImKennyYip/snake-java)
> *This project is not a copy — the original code was restructured, modernized, expanded, and documented.*

---

## 🔧 Possible Future Improvements

- ✅ Sound effects & background music  
- ⏹ Pause menu  
- 🧪 Unit tests for model logic  
- 🎨 Theme customization (dark/light, neon, retro)  
- 🌍 Online leaderboard  

---

## 📜 License

This project is for educational and non-commercial use.
