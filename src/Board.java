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

    public ArrayList<Location> getBlackControls() {
        return blackControls;
    }

    public ArrayList<Location> getWhiteControls() {
        return whiteControls;
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

    public void drawBlackBoard(Graphics g) {
        return;
    }

    public boolean checkMove(Location from, Location too) {
        return false;
    }

    public boolean isWhiteChecked() {
        if (blackControls.indexOf(whiteKing) != -1)
            return true;
        return false;
    }

    public boolean isBlackChecked() {
        if (whiteControls.indexOf(blackKing) != -1)
            return true;
        return false;
    }

    public
}
