import java.util.*;

public class ExampleRoom {
    public static void main(String[] args)
     {
        // Recurive backtracking solution for solving any maze but not the shortest path
        // initialize a robot with a maze layout and starting position
        Robot robot = new Robot("xx xx  xxx\n"
        +               "xx x      \n"
        +               "x   xxx x \n"
        +               "xx xx x xx\n"
        +               " xxxx x x \n"
        +               " x   xxx x\n"
        +               "xxxxx x xx\n"
        +               "xxxxxxxxxx\n"
        +               "xx    x   \n"
        +               "x xxxxxxxx\n", 0, 0);

        int[][] maze = new int[10][10]; // Create a 2D array to represent the maze
        boolean[][] visited = new boolean[10][10]; // keep track of visited cells

        solveMaze(robot, 'A', maze, visited); //start solving the maze

        // print the resulting maze perameters
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
//recurisve method to solve the maze using backtracking
    private static void solveMaze(Robot robot, char previousDirection, int[][] maze, boolean[][] visited) {
        int x = robot.x;
        int y = robot.y;

        //win condition + once met terminate the program
        if(x==9 && y==9) {
            robot.say("maze fin.");
            return; // terminate the program
        }

//check if the current position is within the maze bounds and if the cell has not been visited
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && !visited[x][y]) {

            // mark the current cell as visited
            visited[x][y] = true;

            // mark the current cell as part of the path 0 if not
            maze[x][y] = 1;


            //recursive backtracking to solve the maze
            if (robot.check('E') && previousDirection != 'E') {
                robot.go('E');
                solveMaze(robot, 'W', maze, visited);
                robot.go('W'); // backtrack
            }
            if (robot.check('S') && previousDirection != 'S') {
                robot.go('S');
                solveMaze(robot, 'N', maze, visited);
                robot.go('N'); // backtrack
            }
            if (robot.check('W') && previousDirection != 'W') {
                robot.go('W');
                solveMaze(robot, 'E', maze, visited);
                robot.go('E'); // backtrack
            }
            if (robot.check('N') && previousDirection != 'N') {
                robot.go('N');
                solveMaze(robot, 'S', maze, visited);
                robot.go('S'); // backtrack
            }
        }
    }
}


//BFS solution
// import java.util.*;
//use the same maze as before(one of five)
// public class MazeSolverWithRobot {
//     public static void main(String[] args) {
    // initialize a robot with a maze layout and starting position
//         String mazeStr =
//             "xx xx  xxx\n" +
//             "xx x      \n" +
//             "x   xxx x \n" +
//             "xx xx x xx\n" +
//             " xxxx x x \n" +
//             " x   xxx x\n" +
//             "xxxxx x xx\n" +
//             "xxxxxxxxxx\n" +
//             "xx    x   \n" +
//             "x xxxxxxxx\n";

//         Robot robot = new Robot(mazeStr, 0, 0);
//         char[][] maze = parseMaze(mazeStr);

//         // solve maze using BFS 
//         List<int[]> path = bfs(robot, maze);

//         if (path != null) {
//             System.out.println("Shortest path found. Traversing...");
//             traversePath(robot, path); // Visualize robot traversal
//             markPath(maze, path); // Mark path in the maze
//             System.out.println("Final Maze with Shortest Path:");
//             printMaze(maze); // Print final maze
//             robot.say("maze complete!");
//         } else {
//             System.out.println("No valid path found");
//         }
//     }


//     public static List<int[]> bfs(Robot robot, char[][] maze) {
//         int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // E, S, W, N
//         char[] dirChars = {'E', 'S', 'W', 'N'};

//         int rows = maze.length, cols = maze[0].length;
//         boolean[][] visited = new boolean[rows][cols];
//         Queue<Node> queue = new LinkedList<>();

//         //initialize BFS
//         List<int[]> initialPath = new ArrayList<>();
//         initialPath.add(new int[]{robot.x, robot.y});
//         queue.add(new Node(robot.x, robot.y, initialPath));
//         visited[robot.x][robot.y] = true;

//         while (!queue.isEmpty()) {
//             Node current = queue.poll();
//             int x = current.x, y = current.y;

//             // Check if we reached the target
//             if (x == rows - 1 && y == cols - 1) {
//                 return current.path;
//             }

//             // Explore neighboring rooms relative to the current location
//             for (int i = 0; i < directions.length; i++) {
//                 int newX = x + directions[i][0];
//                 int newY = y + directions[i][1];
//                 char direction = dirChars[i];

//                 if (isValidMove(maze, visited, newX, newY)) {
//                     visited[newX][newY] = true;

//                     // add new position to path
//                     List<int[]> newPath = new ArrayList<>(current.path);
//                     newPath.add(new int[]{newX, newY});
//                     queue.add(new Node(newX, newY, newPath));
//                 }
//             }
//         }
//         return null; //no path found
//     }

//     private static boolean isValidMove(char[][] maze, boolean[][] visited, int x, int y) {
//         return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length &&
//                maze[x][y] == 'x' && !visited[x][y];
//     }

//     private static char[][] parseMaze(String mazeStr) {
//         String[] rows = mazeStr.split("\n");
//         char[][] maze = new char[rows.length][];
//         for (int i = 0; i < rows.length; i++) {
//             maze[i] = rows[i].toCharArray();
//         }
//         return maze;
//     }



//     private static void traversePath(Robot robot, List<int[]> path) {
//         for (int i = 1; i < path.size(); i++) {
//             int[] current = path.get(i - 1);
//             int[] next = path.get(i);

//             char direction = getDirection(current, next);
//             robot.go(direction); // Move robot to next position
//         }
//     }



//     private static char getDirection(int[] current, int[] next) {
//         int dx = next[0] - current[0];
//         int dy = next[1] - current[1];

//         if (dx == 0 && dy == 1) return 'E';
//         if (dx == 1 && dy == 0) return 'S';
//         if (dx == 0 && dy == -1) return 'W';
//         if (dx == -1 && dy == 0) return 'N';

//         throw new IllegalArgumentException("Invalid movement from " + Arrays.toString(current) + " to " + Arrays.toString(next));
//     }

//     private static void markPath(char[][] maze, List<int[]> path) {
//         for (int[] step : path) {
//             maze[step[0]][step[1]] = '*';
//         }
//     }

//     private static void printMaze(char[][] maze) {
//         for (char[] row : maze) {
//             System.out.println(new String(row));
//         }
//     }

//     //node class for BFS
//     static class Node {
//         int x, y;
//         List<int[]> path;

//         Node(int x, int y, List<int[]> path) {
//             this.x = x;
//             this.y = y;
//             this.path = path;
//         }
//     }
// }




//DFS solution(not guarenteed to find the shortest path):
// import java.util.*;
// initialize a robot with a maze layout and starting position
// public class MazeSolverWithDFS {
//     public static void main(String[] args) {
//         String mazeStr =
//             "xx xx  xxx\n" +
//             "xx x      \n" +
//             "x   xxx x \n" +
//             "xx xx x xx\n" +
//             " xxxx x x \n" +
//             " x   xxx x\n" +
//             "xxxxx x xx\n" +
//             "xxxxxxxxxx\n" +
//             "xx    x   \n" +
//             "x xxxxxxxx\n";

//         Robot robot = new Robot(mazeStr, 0, 0);
//         char[][] maze = parseMaze(mazeStr);

//         //solve the maze using DFS
//         List<int[]> path = new ArrayList<>();
//         boolean found = dfs(robot, maze, 0, 0, path, new boolean[maze.length][maze[0].length]);



//         if (found) { (for user to see the maze and curernt steps)
//             System.out.println("path found with DFS -- Traversing...");
//             traversePath(robot, path); // visualize and create the traversal
//             markPath(maze, path); // mark path in the maze
//             System.out.println("final maze with DFS Path:"); 
//             printMaze(maze); // print final maze into the command interface
//             robot.say("maze complete");
//         } else {
//             System.out.println("no valid path found");
//         }
//     }




//     public static boolean dfs(Robot robot, char[][] maze, int x, int y, List<int[]> path, boolean[][] visited) {
//         // boundary and validity checks
//         if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] != 'x' || visited[x][y]) {
//             return false;
//         }

//         // mark cell as visited
//         visited[x][y] = true;
//         path.add(new int[]{x, y});

//         // check if we reached the bottom-right corner (target destination, win condition)
//         if (x == maze.length - 1 && y == maze[0].length - 1) {
//             return true;
//         }

//         // try moving in all directions (E, S, W, N)
//         if (robot.check('E') && dfs(robot, maze, x, y + 1, path, visited)) {
//             robot.go('E'); // move robot East
//             return true;
//         }
//         if (robot.check('S') && dfs(robot, maze, x + 1, y, path, visited)) {
//             robot.go('S'); // move robot South
//             return true;
//         }
//         if (robot.check('W') && dfs(robot, maze, x, y - 1, path, visited)) {
//             robot.go('W'); // move robot West
//             return true;
//         }
//         if (robot.check('N') && dfs(robot, maze, x - 1, y, path, visited)) {
//             robot.go('N'); // move robot North
//             return true;
//         }

//         // backtrack if no path found
//         path.remove(path.size() - 1);
//         return false;
//     }

//     private static char[][] parseMaze(String mazeStr) {
//         String[] rows = mazeStr.split("\n");
//         char[][] maze = new char[rows.length][];
//         for (int i = 0; i < rows.length; i++) {
//             maze[i] = rows[i].toCharArray();
//         }
//         return maze;
//     }



//     private static void traversePath(Robot robot, List<int[]> path) {
//         for (int i = 1; i < path.size(); i++) { // start from 1 since 0 is the starting position
//             int[] current = path.get(i - 1);
//             int[] next = path.get(i);

//             char direction = getDirection(current, next);
//             robot.go(direction); // move robot to next position in the maze
//         }
//     }

//     private static char getDirection(int[] current, int[] next) {
//         int dx = next[0] - current[0];
//         int dy = next[1] - current[1];

//         if (dx == 0 && dy == 1) return 'E';
//         if (dx == 1 && dy == 0) return 'S';
//         if (dx == 0 && dy == -1) return 'W';
//         if (dx == -1 && dy == 0) return 'N';

//         throw new IllegalArgumentException("Invalid movement from " + Arrays.toString(current) + " to " + Arrays.toString(next));
//     }

//     private static void markPath(char[][] maze, List<int[]> path) {
//         for (int[] step : path) {
//             maze[step[0]][step[1]] = '*';
//         }
//     }

//     private static void printMaze(char[][] maze) {
//         for (char[] row : maze) {
//             System.out.println(new String(row));
//         }
//     }
// }
