import java.util.ArrayList;
public class Pawn extends Piece {
    public Pawn(int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public ArrayList<Location> findControls() {
        return null;
    }

    public ArrayList<Location> findMoves() {
        return null;
    }

    public Pawn clone(Board board) {
        return new Pawn(getRow(), getCol(), getColor(), board);
    }
}
