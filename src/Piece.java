import java.util.ArrayList;
import java.awt.*;
public class Piece {
    private Location location;
    private final int color;
    private ArrayList<Location> controlled;
    private ArrayList<Location> moves;
    private final Board board;
    private final Image image;

    // Creates a new piece instance
    public Piece(int row, int col, int color, Board board, Image image) {
        this.color = color;
        this.location = new Location(row, col);
        this.board = board;
        this.image = image;
        this.moves = new ArrayList<>();
        this.controlled = null;
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

    public void resetControlled() {
        System.out.println("Called");
    }

    public boolean isFirstMove() {
        return false;
    }

    public void setMoves(ArrayList<Location> moves) {
        this.moves = moves;
    }

    // Adds the pieces controlled squares to attacks
    public void addControlled(ArrayList<Location> attacks) {
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

    // Adds the pieces possible moves to attacks
    public void getMoves(ArrayList<Location> attacks) {
        for (Location l : moves) {
            attacks.add(new Location(getRow(), getCol(), l));
        }
    }
    public void setControlled(ArrayList<Location> controlled) {
        this.controlled = controlled;
    }

    public void move(Location l) {
        this.location = l;
    }

    public void findMoves() {}
}
