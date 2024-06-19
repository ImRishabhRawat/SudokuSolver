import java.util.Arrays;
import java.util.Random;

public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private static final int MAX_TRIES = 50;

    private int[][] board;

    public SudokuSolver() {
        board = new int[SIZE][SIZE];
    }

    public void generatePuzzle() {
        Random random = new Random();
        solve();

        int numEmpty = SIZE * SIZE / 2; // Remove half of the numbers
        for (int i = 0; i < numEmpty; i++) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            while (board[x][y] == EMPTY) {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            }
            board[x][y] = EMPTY;
        }
    }

    public void solve() {
        if (solveHelper()) {
            System.out.println("Solution:");
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }

    private boolean solveHelper() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (solveHelper()) {
                                return true;
                            }
                            board[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuSolver sudoku = new SudokuSolver();
        sudoku.generatePuzzle();

        System.out.println("Generated Puzzle:");
        sudoku.printBoard();

        System.out.println("\nSolving...");
        sudoku.solve();
    }
}
