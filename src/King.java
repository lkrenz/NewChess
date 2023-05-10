import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class King extends Piece{

    public King(int row, int col, int color, Board board, Image image) {
        super(row, col, color, board, image);
    }

    public boolean checkMove(Location location) {
        return false;
    }

    public void makeMove(Location location) {
        return;
    }

    public King clone(Board board) {
        return new King(getRow(), getCol(), getColor(), board, getImage());
    }

    // Adds all possible king moves then removes any that are controlled by the opposite color
    public ArrayList<Location> findControlled() {
        int row = getRow();
        int col = getCol();

        ArrayList<Location> moves = new ArrayList<>();
        // Checks to the right and right down
        if (row < 7) {
            moves.add(new Location(row + 1, col));
            if (col < 7) {
                moves.add( new Location(row + 1, col + 1));
            }
            if (col > 0) {
                moves.add(new Location(row + 1, col - 1));
            }
        }
        // Checks left and left up
        if (row > 0) {
            moves.add(new Location(row - 1, col));
            if (col > 0) {
                moves.add(new Location(row - 1, col - 1));
            }
            if (col < 7) {
                moves.add(new Location(row - 1, col + 1));
            }
        }
        if (col < 7) {
            moves.add(new Location(row, col + 1));
        }
        if (col > 0) {
            moves.add(new Location(row, col - 1));
        }
        return moves;
    }

    public void findMoves() {
        ArrayList<Location> moves = findControlled();
        ArrayList<Location> checkArr;
        if (getColor() == 1) {
            getBoard().findBlackControls();
            checkArr = getBoard().getBlackControls();
        }
        else {
            getBoard().findWhiteControls();
            checkArr = getBoard().getWhiteControls();
        }
        for (Location l : moves) {
            System.out.println(l);
        }
        int i = 0;
        while (i < moves.size()) {
            for (int j = 0; j < checkArr.size(); j++) {
                if (moves.get(i).equals(checkArr.get(j))) {
                    moves.remove(i);
                    i--;
                    break;
                }
            }
            i++;
        }
        setMoves(moves);
    }

    public void resetControlled() {
        setControlled(findControlled());
    }
}
