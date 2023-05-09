import java.awt.*;
import java.util.ArrayList;
public class Bishop extends Piece{

    public Bishop (int row, int col, int color, Board board, Image image) {
        super(row, col, color, board, image);
    }

    public void makeMove(Location location) {
        return;
    }

    public boolean checkMove (Location location) {
        return false;
    }

    // Adds all possible moves to new arraylist and returns it to super.
    public void findMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        int row = getRow();
        int col = getCol();
        addMoves(moves, 1, 1, row, col);
        addMoves(moves, 1, -1, row, col);
        addMoves(moves, -1, 1, row, col);
        addMoves(moves, -1, -1, row, col);
        setMoves(moves);
    }


    // Recursively moves diagonally until hits edge of board or another piece, adding each location to moves.
    public void addMoves(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7)
            return;
        if (dRow > 0) {
            if (row + dRow > 7) {
                return;
            }
        }
        else {
            if (row + dRow < 0) {
                return;
            }
        }
        if (dCol > 0) {
            if (col + dCol > 7) {
                return;
            }
        }
        else {
            if (col + dCol < 0) {
                return;
            }
        }
        if (getBoard().getBoard()[row + dRow][col + dCol].hasPiece()) {
            if (getBoard().canMove(getColor(), row + dRow, col + dCol)) {
                moves.add(new Location(row + dRow, col + dCol));
            }
            return;
        }
        moves.add(new Location(row + dRow, col + dCol));
        addMoves(moves, dRow, dCol, row + dRow, col + dCol);
    }

    public Bishop clone(Board board) {
        return new Bishop(getRow(), getCol(), getColor(), board, getImage());
    }

    public void resetControlled() {
        findMoves();
    }
}
