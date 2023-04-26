public class Knight extends Piece{

    public Knight (int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Knight clone(Board board) {
        return new Knight(getRow(), getCol(), getColor(), board);
    }
}
