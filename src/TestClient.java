import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Created by yuriyganusyak on 10/9/15.
 */
public class TestClient {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/yuriyganusyak/Developer/Coursera/AlgorithmsPt1/EightPuzzle/src/input.txt"));
        int sizeOfBoard = scanner.nextInt();
        int[][] boardArray = new int[sizeOfBoard][sizeOfBoard];
        for (int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                boardArray[i][j] = scanner.nextInt();
            }
        }
        Board board = new Board(boardArray);

        System.out.println(board);
        System.out.println(board.hamming());
        System.out.println(board.twin());
        System.out.println(board);
    }
}
