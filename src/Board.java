import java.util.LinkedList;
import java.util.List;

/**
 * @author yuriyganusyak
 */
public class Board {
    private int[][] boardArray;
    private Board parent;

    public Board(int[][] argument) {
        int dimension = argument.length;
        boardArray = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardArray[i][j] = argument[i][j];
            }
        }
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<Board>();
        Board up = moveUp();
        if (!equals(up)) {
            neighbors.add(up);
        }
        Board down = moveDown();
        if (!equals(down)) {
            neighbors.add(down);
        }
        Board left = moveLeft();
        if (!equals(left)) {
            neighbors.add(left);
        }
        Board right = moveRight();
        if (!equals(right)) {
            neighbors.add(right);
        }
        return neighbors;
    }


    public int dimension() {
        return boardArray.length;
    }

    public boolean equals(Object that) {
        if (that == null) return false;

        if (that == this) return true;

        if (toString().equals(that.toString())) {
            return true;
        }
        return false;
    }

    public String toString() {
        String result = dimension() + "%n";
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray.length; j++) {
                if (boardArray[i][j] < 10) {
                    result = result + " ";
                }
                result = result + boardArray[i][j] + " ";
            }
            result = result + "%n";
        }
        return String.format(result);
    }

    private int distancesFromCorrect(int x, int y) {
        int numberAtPosition = boardArray[x][y];
        int dd = dimension() * dimension();
        int index = (numberAtPosition - 1 + dd) % dd;
        int row = index / dimension();
        int column = index % dimension();
        int difference = Math.abs(row - x) + Math.abs(column - y);
        return difference;
    }

    public int manhattan() {
        int counter = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (boardArray[i][j] != 0) {
                    counter += distancesFromCorrect(i, j);
                }
            }
        }
        return counter;
    }

    public int hamming() {
        int counter = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int column = 0; column < dimension(); column++) {
                if (boardArray[row][column] != row * dimension() + column + 1) {
                    if (boardArray[row][column] > 0) counter++;
                }
            }
        }
        return counter;
    }

    public Board twin() {
        int[][] twinBoard = new int[dimension()][dimension()];

        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                twinBoard[i][j] = boardArray[i][j];
            }
        }
        if (isZero(0, 0) || isZero(0, 1)) {
            int temp = twinBoard[1][1];
            twinBoard[1][1] = twinBoard[1][0];
            twinBoard[1][0] = temp;
        } else {
            int temp = twinBoard[0][1];
            twinBoard[0][1] = twinBoard[0][0];
            twinBoard[0][0] = temp;
        }
        return new Board(twinBoard);
    }

    private Board moveLeft() {
        int [][]newBoard = copyOf(boardArray);
        int row = zeroCoords()[0];
        int column = zeroCoords()[1];
        if (column < dimension() - 1) {
            int temp = newBoard[row][column + 1];
            newBoard[row][column + 1] = newBoard[row][column];
            newBoard[row][column] = temp;
        }
        Board result = new Board(newBoard);
        return result;
    }

    private int[] zeroCoords() {
        int[] result = new int[2];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (isZero(i, j)) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }

    private Board moveRight() {
        int [][]newBoard = copyOf(boardArray);
        int row = zeroCoords()[0];
        int column = zeroCoords()[1];
        if (column > 0) {
            int temp = newBoard[row][column - 1];
            newBoard[row][column - 1] = newBoard[row][column];
            newBoard[row][column] = temp;
        }
        Board result = new Board(newBoard);
        return result;
    }

    private int[][] copyOf(int[][] argument) {
        int[][] newBoard = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                newBoard[i][j] = boardArray[i][j];
            }
        }
        return newBoard;
    }

    private Board moveUp() {
        int [][]newBoard = copyOf(boardArray);
        int row = zeroCoords()[0];
        int column = zeroCoords()[1];

        if (row < dimension() - 1) {
            int temp = newBoard[row + 1][column];
            newBoard[row + 1][column] = newBoard[row][column];
            newBoard[row][column] = temp;
        }
        Board result = new Board(newBoard);
        return result;
    }

    private Board moveDown() {
        int [][]newBoard = copyOf(boardArray);
        int row = zeroCoords()[0];
        int column = zeroCoords()[1];
        if (row > 0) {
            int temp = newBoard[row - 1][column];
            newBoard[row - 1][column] = newBoard[row][column];
            newBoard[row][column] = temp;
        }
        return new Board(newBoard);
    }

    private boolean isZero(int i, int j) {
        return (boardArray[i][j] == 0);
    }

    public boolean isGoal() {
        return (manhattan() == 0);
    }
}
