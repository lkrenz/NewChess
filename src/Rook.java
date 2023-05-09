import java.awt.*;
import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int row, int col, int color, Board board, Image image) {
        super(row, col, color, board, image);
    }

    public Rook clone(Board board) {
        return new Rook(getRow(), getCol(), getColor(), board, getImage());
    }

    public void findMoves() {
        int row = getRow();
        int col = getCol();
        ArrayList<Location> moves = new ArrayList<>();
        getMoves(moves, 1, 0, row, col);
        getMoves(moves, -1, 0, row, col);
        getMoves(moves, 0, 1, row, col);
        getMoves(moves, 0, -1, row, col);
        setMoves(moves);
    }

    public void getMoves(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
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
        getMoves(moves, dRow, dCol, row + dRow, col + dCol);
    }

    public void resetControlled() {
        findMoves();
    }
}
