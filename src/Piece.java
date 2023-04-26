import java.util.ArrayList;
import java.awt.*;
public class Piece {
    private Location location;
    private int color;
    private ArrayList<Location> controlled;
    private Board board;

    public Piece(int row, int col, int color, Board board) {
        this.color = color;
        this.location = new Location(row, col);
        this.board = board;
    }
    public boolean canPassant() {
        return false;
    }

    public Location getLocation() {
        return location;
    }

    public int getRow() {
        return location.getRow();
    }

    public int getCol() {
        return location.getCol();
    }

    public int getColor() {
        return color;
    }

    public boolean isFirstMove() {
        return false;
    }

    public void checkMove() {
        return;
    }

    public void getControlled () {
        return;
    }

    public void castle() {
        return;
    }

    public void draw(Graphics g) {
        return;
    }

    public Piece clone(Board board) {
        return null;
    }
}
