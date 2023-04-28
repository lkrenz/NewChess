import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Rook clone(Board board) {
        return new Rook(getRow(), getCol(), getColor(), board);
    }

    public void findMoves() {
        int row = getRow();
        int col = getCol();
        ArrayList<Location> moves = new ArrayList<>();
        getMoves(moves, 1, 0, row, col);
        getMoves(moves, -1, 0, row, col);
        getMoves(moves, 0, 1, row, col);
        getMoves(moves, 0, -1, row, col);
        setMoves(moves);
    }

    public void getMoves(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
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
        getMoves(moves, dRow, dCol, row + dRow, col + dCol);
    }
}
