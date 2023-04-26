import java.util.ArrayList;
public class Bishop extends Piece{

    public Bishop (int row, int col, int color, Board board) {
        super(row, col, color, board);
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

    public Bishop clone(Board board) {
        return new Bishop(getRow(), getCol(), getColor(), board);
    }

}
