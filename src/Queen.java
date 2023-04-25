public class Queen extends Piece{
    public Queen (int row, int col, int color) {
        super(row, col, color);
    }

    public Queen clone() {
        return new Queen(getRow(), getCol(), getColor());
    }
}
