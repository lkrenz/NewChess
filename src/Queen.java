import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{

    // Creates a new queen instance
    public Queen (int row, int col, int color, Board board, Image image) {
        super(row, col, color, board, image);
    }

    // Recursively finds the possible moves for a queen
    @Override
    public void findMoves() {
        int row = getRow();
        int col = getCol();
        ArrayList<Location> moves = new ArrayList<>();
        addMoves(moves, 1, 1, row, col);
        addMoves(moves, -1, -1, row, col);
        addMoves(moves, -1, 1, row, col);
        addMoves(moves, 1, -1, row, col);
        addMoves(moves, 1, 0, row, col);
        addMoves(moves, 0, 1, row, col);
        addMoves(moves, -1, 0, row, col);
        addMoves(moves, 0, -1, row, col);
        setMoves(moves);
    }

    // Recursively adds dRow and dCol until it reaches a piece or boundary
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

    @Override
    public void resetControlled() {
        findMoves();
    }
}
