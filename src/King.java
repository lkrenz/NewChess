import java.util.ArrayList;
public class King extends Piece{

    public King(int row, int col, int color) {
        super(row, col, color);
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

    public King clone() {
        return new King(getRow(), getCol(), getColor());
    }
}
