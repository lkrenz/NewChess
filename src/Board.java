import java.awt.*;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
public class Board {
    private Tile[][] board;
    private ArrayList<Location> blackControls;
    private ArrayList<Location> whiteControls;
    private ArrayList<Location> whiteMoves;
    private ArrayList<Location> blackMoves;
    private ArrayList<Location> whitePieces;
    private ArrayList<Location> blackPieces;

    public Board() {
        this.board = new Tile[8][8];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        board[0][0] = new Tile(new Rook(0,0, 0, this));
        blackPieces.add(new Location(0,0));
        board[0][7] = new Tile(new Rook(0,7,0, this));
        blackPieces.add(new Location(0,7));
        board[0][1] = new Tile(new Knight(0,1, 0, this));
        blackPieces.add(new Location(0,1));
        board[0][6] = new Tile(new Knight(0,6, 0, this));
        blackPieces.add(new Location(0,6));
        board[0][2] = new Tile(new Bishop(0,2,0, this));
        blackPieces.add(new Location(0,2));
        board[0][5] = new Tile(new Bishop(0,5,0, this));
        blackPieces.add(new Location(0,5));
        board[0][3] = new Tile(new Queen(0, 3, 0, this));
        blackPieces.add(new Location(0,3));
        board[0][4] = new Tile(new King(0,4,0, this));
        blackPieces.add(0, new Location(0, 4));
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Tile(new Pawn(1, i, 0, this));
            blackPieces.add(new Location(1, i));
            board[6][i] = new Tile(new Pawn(6, i, 1, this));
            whitePieces.add(new Location(6, i));
        }
        for (int i = 2; i < 6;i ++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile();
            }
        }
        board[7][0] = new Tile(new Rook(7,0, 1, this));
        blackPieces.add(new Location(7,0));
        board[7][7] = new Tile(new Rook(7,7,1, this));
        blackPieces.add(new Location(7,7));
        board[7][1] = new Tile(new Knight(7,1, 1, this));
        blackPieces.add(new Location(7,1));
        board[7][6] = new Tile(new Knight(7,6, 1, this));
        blackPieces.add(new Location(7,6));
        board[7][2] = new Tile(new Bishop(7,2,1, this));
        blackPieces.add(new Location(7,2));
        board[7][5] = new Tile(new Bishop(7,5,1, this));
        blackPieces.add(new Location(7,5));
        board[7][4] = new Tile(new Queen(7, 3, 1, this));
        blackPieces.add(new Location(7,3));
        board[7][3] = new Tile(new King(7,4,1, this));
        blackPieces.add(new Location(7,4));
    }

    public Board(Tile[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = board[i][j].clone(this);
            }
        }
    }

    public void findBlackControls() {
        blackControls = new ArrayList<Location>();
        for (Location l : blackPieces) {
            board[l.getRow()][l.getCol()].getControlled(blackControls);
        }
    }

    public void findWhiteControls() {
        whiteControls = new ArrayList<>();
        for (Location l : whitePieces) {
            board[l.getRow()][l.getCol()].getControlled(whiteControls);
        }
    }

    public ArrayList<Location> getBlackControls() {
        return blackControls;
    }

    public ArrayList<Location> getWhiteControls() {
        return whiteControls;
    }

    public void findWhiteMoves() {
        whiteMoves = new ArrayList<>();
        for (Location l : whitePieces) {
            board[l.getRow()][l.getCol()].getMoves(whiteMoves);
        }
    }

    public void findBlackMoves() {
        blackMoves = new ArrayList<>();
        for (Location l : blackPieces) {
            board[l.getRow()][l.getCol()].getControlled(blackMoves);
        }
    }

    public ArrayList<Location> getWhiteMoves() {
        return whiteMoves;
    }

    public ArrayList<Location> getBlackMoves() {
        return blackMoves;
    }

    public ArrayList<Location> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Location> getBlackPieces() {
        return blackPieces;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void makeMove(Location location) {
        board[location.getToRow()][location.getToCol()].setPiece(board[location.getRow()][location.getCol()].removePiece());
    }

    public void drawWhiteBoard(Graphics g) {
        return;
    }

    public boolean canMove(int color, int row, int col) {
        if (board[row][col].hasPiece()) {
            if (board[row][col].getPiece().getColor() != color) {
                return true;
            }
            return false;
        }
        return true;
    }

    public void drawBlackBoard(Graphics g) {
        return;
    }

    public boolean checkMove(Location from, Location too) {
        return false;
    }

    public boolean isWhiteChecked() {
        if (blackControls.indexOf(whitePieces.get(0)) != -1)
            return true;
        return false;
    }

    public boolean isBlackChecked() {
        if (whiteControls.indexOf(blackPieces.get(0)) != -1)
            return true;
        return false;
    }


}
