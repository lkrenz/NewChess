import java.util.ArrayList;
public class Bishop extends Piece{

    public Bishop (int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public void makeMove(Location location) {
        return;
    }

    public boolean checkMove (Location location) {
        return false;
    }

    // Adds all possible moves to new arraylist and returns it to super.
    public void findMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        int row = getRow();
        int col = getCol();
        addMoves(moves, 1, 1, row, col);
        addMoves(moves, 1, -1, row, col);
        addMoves(moves, -1, 1, row, col);
        addMoves(moves, -1, -1, row, col);
        setMoves(moves);
    }


    // Recursively moves diagonally until hits edge of board or another piece, adding each location to moves.
    public void addMoves(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
        if (getBoard().getBoard()[row][col].hasPiece()) {
            if (getBoard().canMove(getColor(), row, col)) {
                moves.add(new Location(row, col));
            }
            return;
        }
        if (dRow > 0) {
            if (row == 7) {
                moves.add(new Location(row, col));
                return;
            }
        }
        else {
            if (row == 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        if (dCol > 0) {
            if (col == 7) {
                moves.add(new Location(row, col));
                return;
            }
        }
        else {
            if (col == 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        moves.add(new Location(row, col));
        addMoves(moves, dRow, dCol, row + dRow, col + dCol);
    }

    public Bishop clone(Board board) {
        return new Bishop(getRow(), getCol(), getColor(), board);
    }

}
