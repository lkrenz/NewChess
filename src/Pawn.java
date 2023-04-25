import java.util.ArrayList;
public class Pawn extends Piece {
    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    public ArrayList<Location> findControls() {
        return null;
    }

    public Pawn clone() {
        return new Pawn(getRow(), getCol(), getColor());
    }
}
