import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Board {
    private Tile[][] board;
    private ArrayList<Location> blackControls;
    private ArrayList<Location> whiteControls;
    private ArrayList<Location> whiteMoves;
    private ArrayList<Location> blackMoves;
    private ArrayList<Location> whitePieces;
    private ArrayList<Location> blackPieces;
    private Image[] images;
    private ChessView window;

    public Board() {
        this.board = new Tile[8][8];
        this.images = new Image[12];
        instantiatePieces();
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        board[0][0] = new Tile(new Rook(0,0, 0, this, images[1]));
        blackPieces.add(new Location(0,0));
        board[0][7] = new Tile(new Rook(0,7,0, this, images[1]));
        blackPieces.add(new Location(0,7));
        board[0][1] = new Tile(new Knight(0,1, 0, this, images[2]));
        blackPieces.add(new Location(0,1));
        board[0][6] = new Tile(new Knight(0,6, 0, this, images[2]));
        blackPieces.add(new Location(0,6));
        board[0][2] = new Tile(new Bishop(0,2,0, this, images[3]));
        blackPieces.add(new Location(0,2));
        board[0][5] = new Tile(new Bishop(0,5,0, this, images[3]));
        blackPieces.add(new Location(0,5));
        board[0][3] = new Tile(new Queen(0, 3, 0, this, images[4]));
        blackPieces.add(new Location(0,3));
        board[0][4] = new Tile(new King(0,4,0, this, images[5]));
        blackPieces.add(0, new Location(0, 4));
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Tile(new Pawn(1, i, 0, this, images[0]));
            blackPieces.add(new Location(1, i));
            board[6][i] = new Tile(new Pawn(6, i, 1, this, images[6]));
            whitePieces.add(new Location(6, i));
        }
        for (int i = 2; i < 6;i ++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile();
            }
        }
        board[7][0] = new Tile(new Rook(7,0, 1, this, images[7]));
        whitePieces.add(new Location(7,0));
        board[7][7] = new Tile(new Rook(7,7,1, this, images[7]));
        whitePieces.add(new Location(7,7));
        board[7][1] = new Tile(new Knight(7,1, 1, this, images[8]));
        whitePieces.add(new Location(7,1));
        board[7][6] = new Tile(new Knight(7,6, 1, this, images[8]));
        whitePieces.add(new Location(7,6));
        board[7][2] = new Tile(new Bishop(7,2,1, this, images[9]));
        whitePieces.add(new Location(7,2));
        board[7][5] = new Tile(new Bishop(7,5,1, this, images[9]));
        whitePieces.add(new Location(7,5));
        board[7][3] = new Tile(new Queen(7, 3, 1, this, images[10]));
        whitePieces.add(new Location(7,3));
        board[7][4] = new Tile(new King(7,4,1, this, images[11]));
        whitePieces.add(0, new Location(7,4));
    }

    public void instantiatePieces() {
        images[0] = new ImageIcon("Resources/Black pawn.png").getImage();
        images[1] = new ImageIcon("Resources/Black rook.png").getImage();
        images[2] = new ImageIcon("Resources/Black horse.png").getImage();
        images[3] = new ImageIcon("Resources/Black bishop.png").getImage();
        images[4] = new ImageIcon("Resources/Black queen.png").getImage();
        images[5] = new ImageIcon("Resources/Black king.png").getImage();
        images[6] = new ImageIcon("Resources/White pawn.png").getImage();
        images[7] = new ImageIcon("Resources/White rook.png").getImage();
        images[8] = new ImageIcon("Resources/White horse.png").getImage();
        images[9] = new ImageIcon("Resources/White bishop.png").getImage();
        images[10] = new ImageIcon("Resources/White queen.png").getImage();
        images[11] = new ImageIcon("Resources/White king.png").getImage();
    }

    public void setWindow(ChessView window) {
        this.window = window;
    }
    public Board(Board b) {
        this.board = new Tile[8][8];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        this.whiteMoves = new ArrayList<>();
        this.blackMoves = new ArrayList<>();
        this.whiteControls = new ArrayList<>();
        this.blackControls = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = b.getBoard()[i][j].clone(this);
            }
        }
    }
    public void findBlackControls() {
        blackControls = new ArrayList<Location>();
        for (Location l : blackPieces) {
            board[l.getRow()][l.getCol()].getControlled(blackControls);
        }
    }

    public void findWhiteMoves() {
        whiteMoves = new ArrayList<>();
        findBlackControls();
        for (Location l : whitePieces) {
            board[l.getRow()][l.getCol()].getMoves(whiteMoves);
        }
    }

    public boolean checkBlackCastle(Location move) {
        if (!board[move.getRow()][move.getCol()].hasPiece()) {
            return false;
        }
        if (!(board[move.getRow()][move.getCol()].getPiece() instanceof King)) {
            return false;
        }
        if (move.getRow() != move.getToRow()) {
            return false;
        }
        if (Math.abs(move.getToCol() - move.getCol()) != 2) {
            return false;
        }
        if (move.getToCol() - move.getCol() > 0) {
            if (!board[move.getRow()][7].hasPiece()) {
                return false;
            }
            else {
                if (!(board[move.getRow()][7].getPiece() instanceof Rook)) {
                    return false;
                }
                if (!(board[move.getRow()][7].getPiece().isFirstMove())) {
                    return false;
                }
            }
        }
        else {
            if (!board[move.getRow()][0].hasPiece()) {
                return false;
            }
            else {
                if (!(board[move.getRow()][0].getPiece() instanceof Rook)) {
                    return false;
                }
                if (!(board[move.getRow()][0].getPiece().isFirstMove())) {
                    return false;
                }
            }
        }
        if (move.getToCol() > move.getCol()) {
            if (board[move.getRow()][move.getCol() + 1].hasPiece() || board[move.getRow()][move.getCol() + 2].hasPiece()) {
                return false;
            }
        }
        else {
            if (board[move.getRow()][move.getCol() - 1].hasPiece() || board[move.getRow()][move.getCol() - 2].hasPiece() || board[move.getRow()][move.getCol() - 3].hasPiece()) {
                return false;
            }
        }
        findWhiteControls();
        if (isBlackChecked()) {
            return false;
        }
        if (move.getToCol() > move.getCol()) {
            if (doesWhiteControl(move.getRow(), move.getCol() + 1)) {
                return false;
            }
            if (doesWhiteControl(move.getRow(), move.getCol() + 2)) {
                return false;
            }
            makeBlackMove(new Location(move.getRow(), move.getCol(), move.getRow(), move.getCol() + 2));
            makeBlackMove(new Location(move.getRow(), 7, move.getRow(), move.getCol() + 1));
        }
        else {
            if (doesWhiteControl(move.getRow(), move.getCol() - 1)) {
                return false;
            }
            if (doesWhiteControl(move.getRow(), move.getCol() - 2)) {
                return false;
            }
            makeBlackMove(new Location(move.getRow(), move.getCol(), move.getRow(), move.getCol() - 2));
            makeBlackMove(new Location(move.getRow(), 0, move.getRow(), move.getCol() - 1));
        }
        return true;
    }

    public boolean checkWhiteCastle(Location move) {
        if (!board[move.getRow()][move.getCol()].hasPiece()) {
            return false;
        }
        if (!(board[move.getRow()][move.getCol()].getPiece() instanceof King)) {
            return false;
        }
        if (move.getRow() != move.getToRow()) {
            return false;
        }
        if (Math.abs(move.getToCol() - move.getCol()) != 2) {
            return false;
        }
        if (move.getToCol() - move.getCol() > 0) {
            if (!board[move.getRow()][7].hasPiece()) {
                return false;
            }
            else {
                if (!(board[move.getRow()][7].getPiece() instanceof Rook)) {
                    return false;
                }
                if (!(board[move.getRow()][7].getPiece().isFirstMove())) {
                    return false;
                }
            }
        }
        else {
            if (!board[move.getRow()][0].hasPiece()) {
                return false;
            }
            else {
                if (!(board[move.getRow()][0].getPiece() instanceof Rook)) {
                    return false;
                }
                if (!(board[move.getRow()][0].getPiece().isFirstMove())) {
                    return false;
                }
            }
        }
        if (move.getToCol() > move.getCol()) {
            if (board[move.getRow()][move.getCol() + 1].hasPiece() || board[move.getRow()][move.getCol() + 2].hasPiece()) {
                return false;
            }
        }
        else {
            if (board[move.getRow()][move.getCol() - 1].hasPiece() || board[move.getRow()][move.getCol() - 2].hasPiece() || board[move.getRow()][move.getCol() - 3].hasPiece()) {
                return false;
            }
        }
        findBlackControls();
        if (isWhiteChecked()) {
            return false;
        }
        if (move.getToCol() > move.getCol()) {
            if (doesBlackControl(move.getRow(), move.getCol() + 1)) {
                return false;
            }
            if (doesBlackControl(move.getRow(), move.getCol() + 2)) {
                return false;
            }
            makeWhiteMove(new Location(move.getRow(), move.getCol(), move.getRow(), move.getCol() + 2));
            makeWhiteMove(new Location(move.getRow(), 7, move.getRow(), move.getCol() + 1));
        }
        else {
            if (doesBlackControl(move.getRow(), move.getCol() - 1)) {
                return false;
            }
            if (doesBlackControl(move.getRow(), move.getCol() - 2)) {
                return false;
            }
            makeWhiteMove(new Location(move.getRow(), move.getCol(), move.getRow(), move.getCol() - 2));
            makeWhiteMove(new Location(move.getRow(), 0, move.getRow(), move.getCol() - 1));
        }
        return true;
    }

    public void findBlackMoves() {
        blackMoves = new ArrayList<>();
        findWhiteControls();
        for (Location l : blackPieces) {
            board[l.getRow()][l.getCol()].getMoves(blackMoves);
        }
    }

    public boolean isWhiteCheckMated() {
        if (!isWhiteChecked()) {
            return false;
        }
        for (int i = 0; i < whiteMoves.size(); i++) {
            Piece p = null;
            Location move = whiteMoves.get(i);
            if (board[move.getToRow()][move.getToCol()].hasPiece()) {
                p = board[move.getToRow()][move.getToCol()].getPiece();
            }
            makeWhiteMove(move);
            if (!isWhiteChecked()) {
                undoWhiteMove(move, p);
                return false;
            }
            undoWhiteMove(move, p);
        }
        return true;
    }

    public boolean isBlackCheckMated() {
        if (!isBlackChecked()) {
            return false;
        }
        for (int i = 0; i < blackMoves.size(); i++) {
            Piece p = null;
            Location move = blackMoves.get(i);
            if (board[move.getToRow()][move.getToCol()].hasPiece()) {
                p = board[move.getToRow()][move.getToCol()].getPiece();
            }
            makeBlackMove(move);
            if (!isBlackChecked()) {
                undoBlackMove(move, p);
                return false;
            }
            undoBlackMove(move, p);
        }
        return true;
    }

    public boolean doesBlackControl(int row, int col) {
        for (int i = 0; i < blackControls.size(); i++) {
            if (blackControls.get(i).getRow() == row && blackControls.get(i).getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean doesWhiteControl(int row, int col) {
        for (int i = 0; i < whiteControls.size(); i++) {
            if (whiteControls.get(i).getRow() == row && whiteControls.get(i).getCol() == col) {
                return true;
            }
        }
        return false;
    }

    public void findWhiteControls() {
        whiteControls = new ArrayList<>();
        for (Location l : whitePieces) {
            board[l.getRow()][l.getCol()].getControlled(whiteControls);
        }
    }

    public boolean whiteHas(Location l) {
        for (Location move : whiteMoves) {
            if (move.equals(l)) {
                return true;
            }
        }
        return false;
    }

    public boolean blackHas(Location l) {
        for (Location move : blackMoves) {
            if (move.equals(l)) {
                return true;
            }
        }
        return false;
    }


    // Moves the piece on the board and removes an attacked piece if necessary
    public void makeMove(Location location) {
        Location moveTo = new Location(location.getToRow(), location.getToCol());
        if (board[moveTo.getRow()][moveTo.getCol()].hasPiece()) {
            if (board[moveTo.getRow()][moveTo.getCol()].getPiece().getColor() == 0) {
                for (int i = 0; i < blackPieces.size(); i++) {
                    if (moveTo.equals(blackPieces.get(i))) {
                        blackPieces.remove(i);
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < whitePieces.size(); i++) {
                    if (moveTo.equals(whitePieces.get(i))) {
                        whitePieces.remove(i);
                        break;
                    }
                }
            }

        }
        board[location.getToRow()][location.getToCol()].setPiece(board[location.getRow()][location.getCol()].removePiece());
    }

    // Changes the row and col stored in each piece
    public void makeWhiteMove(Location location) {
        Location moveOrigin = new Location(location.getRow(), location.getCol());
        board[moveOrigin.getRow()][moveOrigin.getCol()].movePiece(new Location(location.getToRow(), location.getToCol()));
        for (int i = 0; i < whitePieces.size(); i++) {
            if (whitePieces.get(i).equals(moveOrigin)) {
                whitePieces.set(i, new Location(location.getToRow(), location.getToCol()));
            }
        }
        makeMove(location);
    }

    public void makeBlackMove(Location location) {
        Location moveOrigin = new Location(location.getRow(), location.getCol());
        for (int i = 0; i < blackPieces.size(); i++) {
            if (blackPieces.get(i).equals(moveOrigin)) {
                board[moveOrigin.getRow()][moveOrigin.getCol()].movePiece(new Location(location.getToRow(), location.getToCol()));
                blackPieces.set(i, new Location(location.getToRow(), location.getToCol()));
            }
        }
        makeMove(location);
    }

    public void undoWhiteMove(Location move, Piece p) {
        Location moveTo = new Location(move.getToRow(), move.getToCol());
        board[move.getRow()][move.getCol()].setPiece(board[move.getToRow()][move.getToCol()].removePiece());
        for (int i = 0; i < whitePieces.size(); i++) {
            if (whitePieces.get(i).equals(moveTo)) {
                whitePieces.set(i, new Location(move.getRow(), move.getCol()));
            }
        }
        if (p != null) {
            getBoard()[move.getToRow()][move.getToCol()].setPiece(p);
            blackPieces.add(new Location(move.getToRow(), move.getToCol()));
        }
    }

    public void undoBlackMove(Location move, Piece p) {
        Location moveTo = new Location(move.getToRow(), move.getToCol());
        board[move.getRow()][move.getCol()].setPiece(board[move.getToRow()][move.getToCol()].removePiece());
        for (int i = 0; i < blackPieces.size(); i++) {
            if (blackPieces.get(i).equals(moveTo)) {
                blackPieces.set(i, new Location(move.getRow(), move.getCol()));
            }
        }
        if (p != null) {
            getBoard()[move.getToRow()][move.getToCol()].setPiece(p);
            whitePieces.add(new Location(move.getToRow(), move.getToCol()));
        }
    }

//    public void undoBlackMove(Location move, Piece p) {
//        makeMove(new Location(move.getToRow(), move.getToCol(), move.getRow(), move.getCol()));
//        if (p != null) {
//            getBoard()[move.getToRow()][move.getToCol()].setPiece(p);
//            blackPieces.add(new Location(move.getToRow(), move.getToCol()));
//        }
//    }

    public void drawWhiteBoard(Graphics g) {
        return;
    }

    public boolean canMove(int color, int row, int col) {
        Location moveTo = new Location(row, col);
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

    public void drawPieces(Graphics g) {
        for (Location l : whitePieces) {
            Image i = board[l.getRow()][l.getCol()].getPiece().getImage();
            g.drawImage(i,200 + l.getCol() * 50, 100 + l.getRow() * 50, 50, 50, window);
        }
        for (Location l : blackPieces) {
            Piece p = board[l.getRow()][l.getCol()].getPiece();
            g.drawImage(p.getImage(),200 + p.getCol() * 50, 100 + p.getRow() * 50, 50, 50, window);
        }
    }

    // Need to figure out how to share the information to the front end
//    public void promote(int row, int col, int color) {
//        int promo = window.getPromotion(int color);
//    }

    public boolean isPromotion(Location move) {
        findBlackMoves();
        if (board[move.getRow()][move.getCol()].getPiece().getColor() == 0) {
            findBlackMoves();
            if (!blackHas(move)) {
                return false;
            }
        }
        else {
            findWhiteMoves();
            if (!whiteHas(move)) {
                return false;
            }
        }
        if (move.getToRow() == 0 || move.getToRow() == 7) {
            if (board[move.getRow()][move.getCol()].getPiece() instanceof Pawn) {
                return true;
            }
        }
        return false;
    }

    public void promote(Location move, int choice, int status) {
        Piece p = null;
        if (choice == 1) {
            p = new Queen(move.getToRow(), move.getToCol(), status, this, images[4 + status * 6]);
        }
        else if (choice == 2) {
            p = new Rook(move.getToRow(), move.getToCol(), status, this, images[1 + status * 6]);
        }
        else if (choice == 3) {
            p = new Knight(move.getToRow(), move.getToCol(), status, this, images[2 + status * 6]);
        }
        else if (choice == 4) {
            p = new Bishop(move.getToRow(), move.getToCol(), status, this, images[3 + status * 6]);
        }

        board[move.getToRow()][move.getToCol()].setPiece(p);
        board[move.getRow()][move.getCol()].removePiece();
        Location moveOrigin = new Location(move.getRow(), move.getCol());
        Location moveDestination = new Location(move.getToRow(), move.getToCol());
        if (status == 1) {
            for (int i = 0; i < whitePieces.size(); i++) {
                if (moveOrigin.equals(whitePieces.get(i))) {
                    whitePieces.set(i, new Location(move.getToRow(), move.getToCol()));
                }
            }
            for (int i = 0; i < blackPieces.size(); i++) {
                if (moveDestination.equals(blackPieces.get(i))) {
                    blackPieces.remove(i);
                }
            }

        }
        else {
            for (int i = 0; i < blackPieces.size(); i++) {
                if (moveOrigin.equals(blackPieces.get(i))) {
                    blackPieces.set(i, new Location(move.getToRow(), move.getToCol()));
                }
            }
            for (int i = 0; i < whitePieces.size(); i++) {
                if (moveDestination.equals(whitePieces.get(i))) {
                    whitePieces.remove(i);
                }
            }
        }
        window.repaint();
        window.setNeedPromotion(false);
//        window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
        window.repaint();
    }

    public void drawOptions(Graphics g, int color) {
        g.drawImage(images[4 + color * 6], 700, 100, window);
        g.drawImage(images[1 + color * 6], 700, 150, window);
        g.drawImage(images[2 + color * 6], 700, 200, window);
        g.drawImage(images[3 + color * 6], 700, 250, window);
    }

    public boolean isWhiteChecked() {
        findBlackControls();
        for(Location l : blackControls) {
            if (l.equals(whitePieces.get(0))) {
                System.out.println("You checked");
                return true;
            }
        }
        return false;
    }

    public boolean isBlackChecked() {
        findWhiteControls();
        for(Location l : whiteControls) {
            if (l.equals(blackPieces.get(0))) {
                System.out.println("You checked");
                return true;
            }
        }
        return false;
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

}
