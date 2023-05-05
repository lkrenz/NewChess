import java.awt.*;
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

    public Piece removePiece() {
        Piece piece1 = piece;
        this.piece = null;
        return piece1;
    }

    public boolean hasPiece() {
        if (piece != null) {
            return false;
        }
        return false;
    }

    public void draw(Graphics g) {
        return;
    }

    public Tile clone(Board board) {
        if (piece != null) {
            return new Tile(piece.clone(board));
        }
        return new Tile();
    }

    public void getMoves(ArrayList<Location> moves) {
        piece.findMoves();
        piece.getMoves(moves);
    }

    /**
     *  Need to fix this, ask Mr. Blick about sub
     * @param controls
     */
    public void getControlled(ArrayList<Location> controls) {
        if (piece instanceof King || piece instanceof Pawn) {
            piece.setControlled();
        }
        else {
            piece.findMoves();
        }
        piece.getControlled(controls);
    }

    public void movePiece(Location l) {
        System.out.println(hasPiece() + "");
        piece.move(l);
    }
}
