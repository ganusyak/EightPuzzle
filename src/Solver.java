import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuriyganusyak on 10/9/15.
 */
public class Solver {
    private Board[] moves = new Board[100];
    public Solver (Board board) {
        moves[0] = board;
    }
              // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable() {
        return true;
    }        // is the initial board solvable?
    public int moves() {
        return 10;
    }              // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution() {
        List<Board> neighbors = new LinkedList<Board>();
        return neighbors;
    }    // sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args) {

    }




}
