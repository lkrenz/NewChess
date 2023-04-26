public class Rook extends Piece{

    public Rook(int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Rook clone(Board board) {
        return new Rook(getRow(), getCol(), getColor(), board);
    }
}
