import java.util.ArrayList;
public class Bishop extends Piece{

    public Bishop (int row, int col, int color) {
        super(row, col, color);
    }

    public void makeMove(Location location) {
        return;
    }

    public ArrayList<Location> findControlled() {
        return null;
    }

    public boolean checkMove (Location location) {
        return false;
    }
}
