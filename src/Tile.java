public class Tile {
    private Piece piece;

    public Tile() {
        this.piece = null;
    }

    public Tile(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece removePiece() {
        Piece piece1 = piece;
        this.piece = null;
        return piece1;
    }

    public void draw(Graphics g) {
        return;
    }

    public Tile clone() {
        return new Tile();
    }
}
