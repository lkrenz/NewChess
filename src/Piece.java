import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.*;
public class Piece {
    private Location location;
    private int color;
    private ArrayList<Location> controlled;
    private ArrayList<Location> moves;
    private Board board;
    private Image image;

    public Piece(int row, int col, int color, Board board, Image image) {
        this.color = color;
        this.location = new Location(row, col);
        this.board = board;
        this.image = image;
    }
    public boolean canPassant() {
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public Image getImage() {
        return image;
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
    public void setMoves(ArrayList<Location> moves) {
        this.moves = moves;
    }

    public void getControlled(ArrayList<Location> attacks) {
        if (controlled != null) {
            for (Location l : controlled) {
                attacks.add(l);
            }
        }
        else {
            for (Location l : moves) {
                attacks.add(l);
            }
        }
    }

    public void getMoves(ArrayList<Location> attacks) {
        for (Location l : moves) {
            attacks.add(new Location(getRow(), getCol(), l));
        }
    }

    public ArrayList<Location> getMoves() {
        return moves;
    }

    public void setControlled(ArrayList<Location> controlled) {
        this.controlled = controlled;
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

    public void findMoves() {}

    public void setControlled() {
        this.controlled = moves;
    }
}
