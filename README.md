# Maze-Runner

Description: This project simulates a robot navigating through a grid-based maze using Java's Swing and Graphics2D libraries. The Robot class provides functionality for the robot to explore the maze, detect doors, and move through them.

## 🔗 Features
- **Robot Movement**: The robot physically navigates the maze, visualizing the process.
- **Maze Representation**:
    - `x` denotes traversable rooms.
    - Empty spaces (` `) are walls.
- **Pathfinding Algorithms**:
    - **BFS**: Guarantees the shortest path.
    - **DFS**: Explores deeply before backtracking, finding any valid path.
- **Final Maze Output**: Marks the solved path with `'*'` in the command line interface.


## 🔗 Technologies:
- **Java**: Primary programming language.
- **Object-Oriented Design**: Uses a `Robot` class to abstract navigation.

## Getting started:

To get a local copy up and running follow these simple example steps.

**Prerequisites**

- Have java JDK installed on your system
- Clone the repo
```
- git clone https://github.com/danielcoblentz/Maze-Runner
```

- The program includes three solution methods: Backtracking, DFS (Depth-First Search), and BFS (Breadth-First Search). By default, the program runs the backtracking solution. To test the other methods, simply comment out or remove the backtracking solution in the code, uncomment the desired method, and run the program.
