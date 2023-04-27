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

    // Adds all possible king moves then removes any that are controlled by the opposite color
    public void findMoves() {
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
        ArrayList<Location> checkArr;
        if (getColor() == 1) {
            checkArr = getBoard().getBlackControls();
        }
        else {
            checkArr = getBoard().getWhiteControls();
        }
        int i = 0;
        while (i < moves.size()) {
            if (checkArr.contains(moves.get(i))) {
                moves.remove(i);
                continue;
            }
            i++;
        }
        setMoves(moves);
    }
}
