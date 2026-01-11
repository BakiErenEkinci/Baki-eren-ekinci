# Baki-eren-ekinci

<img width="718" height="750" alt="image" src="https://github.com/user-attachments/assets/22511e74-80ac-4ab1-97a0-b83dc829dc71" />


Piano Rhythm Game - README
This project is an interactive Piano Rhythm Game developed using Java Swing, where players press keys corresponding to falling notes to earn points.

Project Overview
The game features three difficulty levels and tracks player performance, such as wins, losses, and specific note errors, using an integrated SQLite database.

Key Features
Dynamic Gameplay: Notes flow from top to bottom, requiring the player to press 'A, S, D, F, G, H, J' at the correct time.

Note Varieties: Includes standard notes and long notes that must be held down for their duration.

Progressive Difficulty: Three distinct levels (Easy, Medium, Hard) with unique note sequences.

Statistical Tracking: Records win/loss ratios and identifies which specific notes the player misses most frequently.

Audio Feedback: Triggers high-fidelity audio files for each successful key press.

Technical Stack
Language: Java

Framework: Java Swing & AWT for the graphical user interface

Database: SQLite via JDBC for persistent data storage

Audio: javax.sound.sampled library for sound processing

File Structure and Responsibilities
Piyano1.java: The entry point of the application that initializes the database and launches the main frame.

GameFrame.java: The main window container for the application.

MainMenu.java: Handles level selection and navigation to the statistics panel.

GamePanel.java: The core game engine managing the falling logic, collision detection, and scoring.

DatabaseManager.java: Manages all SQL operations, including table creation and data updates.

StatsPanel.java: Displays player performance data and provides an option to reset statistics.

SoundPlayer.java: Handles the loading and playing of .wav audio files.

Box.java: A model class representing the properties of individual falling notes.

How to Play
Launch: Run the Piyano1 class.

Select Level: Choose a difficulty from the main menu.

Controls: Press the corresponding key when a note reaches the hit zone at the bottom of the screen:

Single Notes (Cyan): Press the key once.

Long Notes (Yellow): Hold the key for the entire length of the note.

Error Handling: If a note is missed, the game pauses, prompting the player to press the correct key to continue.

Statistics: Access the "Stats" menu to view your error counts per note and overall win/loss record.

Developer
Baki Eren Ekinci
