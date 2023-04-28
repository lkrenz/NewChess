import java.util.ArrayList;

public class Knight extends Piece{

    public Knight (int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Knight clone(Board board) {
        return new Knight(getRow(), getCol(), getColor(), board);
    }

    public void findMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        int row = getRow();
        int col = getCol();
        if (row + 2 < 7) {
            if (col + 1 < 7)
                moves.add(new Location(row + 2, col + 1));
            if (col - 1 > 0)
                moves.add(new Location(row + 2, col - 1));
        }
        if (row - 2 > 0) {
            if (col + 1 < 7)
                moves.add(new Location(row - 2, col + 1));
            if (col - 1 > 0)
                moves.add(new Location(row - 2, col - 1));
        }
        if (col + 2 < 7) {
            if (row + 1 < 7)
                moves.add(new Location(row + 1, col + 2));
            if (row - 1 > 0)
                moves.add(new Location(row - 1, col + 2));
        }
        if (col - 2 > 0) {
            if (row + 1 < 7)
                moves.add(new Location(row + 1, col - 2));
            if (row - 1 > 0)
                moves.add(new Location(row - 1, col - 2));
        }
        setMoves(moves);
    }
}
