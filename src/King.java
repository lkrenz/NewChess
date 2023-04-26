import java.util.ArrayList;
public class King extends Piece{

    public King(int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public boolean checkMove(Location location) {
        return false;
    }

    public void makeMove(Location location) {
        return;
    }
    public ArrayList<Location> findControlled() {
        return null;
    }

    public King clone(Board board) {
        return new King(getRow(), getCol(), getColor(), board);
    }
}
