public class Rook extends Piece{

    public Rook(int row, int col, int color) {
        super(row, col, color);
    }

    public Rook clone() {
        return new Rook(getRow(), getCol(), getColor());
    }
}
