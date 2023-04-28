import java.util.ArrayList;
public class Pawn extends Piece {
    public Pawn(int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public ArrayList<Location> findControls() {
        return null;
    }

    public void findMoves() {
        ArrayList<Location> moves = new ArrayList<>();
        if (getColor() == 1) {
            if (getCol() > 0 && getBoard().getBoard()[getRow() - 1][getCol() - 1].hasPiece()) {
                moves.add(new Location(getRow() - 1, getCol() - 1));
            }
            if (getCol() < 7 && getBoard().getBoard()[getRow() - 1][getCol() + 1].hasPiece()) {
                moves.add(new Location(getRow() - 1, getCol() + 1));
            }
            if (!getBoard().getBoard()[getRow() - 1][getCol()].hasPiece()) {
                moves.add(new Location(getRow() - 1, getCol()));
                if (isFirstMove() && !getBoard().getBoard()[getRow() - 2][getCol()].hasPiece()) {
                    moves.add(new Location(getRow() - 2, getCol()));
                }
            }
        }
        else {
            if (getCol() > 0 && getBoard().getBoard()[getRow() + 1][getCol() - 1].hasPiece()) {
                moves.add(new Location(getRow() + 1, getCol() - 1));
            }
            if (getCol() < 7 && getBoard().getBoard()[getRow() + 1][getCol() + 1].hasPiece()) {
                moves.add(new Location(getRow() + 1, getCol() + 1));
            }
            if (!getBoard().getBoard()[getRow() + 1][getCol()].hasPiece()) {
                moves.add(new Location(getRow() + 1, getCol()));
                if (isFirstMove() && !getBoard().getBoard()[getRow() + 2][getCol()].hasPiece()) {
                    moves.add(new Location(getRow() + 2, getCol()));
                }
            }
        }
        setMoves(moves);
    }

    public Pawn clone(Board board) {
        return new Pawn(getRow(), getCol(), getColor(), board);
    }

    public void findControlled() {
        ArrayList<Location> attacks = new ArrayList<>();
        if (getCol() < 7) {
            attacks.add(new Location(getRow() + 1, getCol() + 1));
        }
        if (getCol() > 0) {
            attacks.add(new Location(getRow() + 1, getCol() + 1));
        }
        setControlled(attacks);
    }
}
