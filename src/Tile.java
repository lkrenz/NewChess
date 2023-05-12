import java.util.ArrayList;

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

    // Removes and returns this tile's piece
    public Piece removePiece() {
        Piece piece1 = piece;
        this.piece = null;
        return piece1;
    }

    // Returns true if this tile has a piece
    public boolean hasPiece() {
        return piece != null;
    }

    // Resets and returns the tile's piece's moves
    public void getMoves(ArrayList<Location> moves) {
        piece.findMoves();
        piece.getMoves(moves);
    }

    // Resets and returns the tile's piece's moves
    public void getControlled(ArrayList<Location> controls) {
        piece.resetControlled();
        piece.addControlled(controls);
    }

    // Moves the piece to location l
    public void movePiece(Location l) {
        piece.move(l);
    }
}
