import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackerMazeGenerator {

    private static final int WALL = 0;
    private static final int PASSAGE = 1;

    public static void main(String[] args) {
        int rows = 13;
        int cols = 23;

        int[][] maze = generateMaze(rows, cols);
        printMaze(maze);
    }
    private static int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];
        Stack<int[]> stack = new Stack<>();
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = WALL;
            }
        }

        int startCol = random.nextInt(cols);
        stack.push(new int[]{0, startCol});
        maze[0][startCol] = PASSAGE;

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int currentRow = current[0];
            int currentCol = current[1];
            int[][] neighbors = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            boolean foundNeighbor = false;

            for (int i = neighbors.length - 1; i > 0; i--) {
                int index = random.nextInt(i + 1);
                int[] temp = neighbors[index];
                neighbors[index] = neighbors[i];
                neighbors[i] = temp;
            }

            for (int[] neighbor : neighbors) {
                int newRow = currentRow + 2 * neighbor[0];
                int newCol = currentCol + 2 * neighbor[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && maze[newRow][newCol] == WALL) {
                    maze[currentRow + neighbor[0]][currentCol + neighbor[1]] = PASSAGE;
                    maze[newRow][newCol] = PASSAGE;
                    stack.push(new int[]{newRow, newCol});
                    foundNeighbor = true;
                    break;
                }
            }

            if (!foundNeighbor) {
                stack.pop();
            }
        }

        return maze;
    }
    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}