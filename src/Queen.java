public class Queen extends Piece{
    public Queen (int row, int col, int color, Board board) {
        super(row, col, color, board);
    }

    public Queen clone(Board board) {
        return new Queen(getRow(), getCol(), getColor(), board);
    }
}
