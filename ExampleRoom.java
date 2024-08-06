import java.util.*;

public class ExampleRoom {
    public static void main(String[] args)
     {
        // Initialize a robot with a maze layout and starting position
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

        int[][] maze = new int[10][10]; // Initialize a 2D array to represent the maze
        boolean[][] visited = new boolean[10][10]; // Keep track of visited cells

        solveMaze(robot, 'A', maze, visited); //start solving the maze

        // Print the resulting maze perameters
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

            // Mark the current cell as visited
            visited[x][y] = true;

            // Mark the current cell as part of the path 0 if not
            maze[x][y] = 1;


            //explore east direction
            if (robot.check('E') && previousDirection != 'E') {
                robot.go('E');
                solveMaze(robot, 'W', maze, visited);
                robot.go('W'); // Backtrack
            }
            if (robot.check('S') && previousDirection != 'S') {
                robot.go('S');
                solveMaze(robot, 'N', maze, visited);
                robot.go('N'); // Backtrack
            }
            if (robot.check('W') && previousDirection != 'W') {
                robot.go('W');
                solveMaze(robot, 'E', maze, visited);
                robot.go('E'); // Backtrack
            }
            if (robot.check('N') && previousDirection != 'N') {
                robot.go('N');
                solveMaze(robot, 'S', maze, visited);
                robot.go('S'); // Backtrack
            }
        }
    }
}
