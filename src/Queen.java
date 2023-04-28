import java.util.ArrayList;

public class Queen extends Piece{
    public Queen (int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Queen clone(Board board) {
        return new Queen(getRow(), getCol(), getColor(), board);
    }

    public void findMoves() {
        int row = getRow();
        int col = getCol();
        ArrayList<Location> moves = new ArrayList<>();
        findDiagonals(moves, 1, 1, row, col);
        findDiagonals(moves, -1, -1, row, col);
        findDiagonals(moves, -1, 1, row, col);
        findDiagonals(moves, 1, -1, row, col);
        findHorizontals(moves, 1, 0, row, col);
        findHorizontals(moves, 0, 1, row, col);
        findHorizontals(moves, -1, 0, row, col);
        findHorizontals(moves, 0, -1, row, col);
        setMoves(moves);
    }

    public void findDiagonals(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
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
        findDiagonals(moves, dRow, dCol, row + dRow, col + dCol);
    }

    public void findHorizontals(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
        if (getBoard().getBoard()[row][col].hasPiece()) {
            if (getBoard().canMove(getColor(), row, col)) {
                moves.add(new Location(row, col));
                return;
            }
            return;
        }
        if (dRow != 0) {
            if (row == 7 && dRow > 0) {
                moves.add(new Location(row, col));
                return;
            }
            if (row == 0 && dRow < 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        else {
            if (col == 7 && dCol > 0) {
                moves.add(new Location(row, col));
                return;
            }
            if (col == 0 && dCol < 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        findHorizontals(moves, dRow, dCol, row + dRow, col + dCol);
    }
}
