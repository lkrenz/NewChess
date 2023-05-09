import javax.swing.*;
import java.awt.*;

public class Chess {
    private Board board;
    private Board checkBoard;
    private ChessView window;

    public Chess() {
        this.board = new Board();
        window = new ChessView(new ImageIcon("Resources/Chessboard.png").getImage(), this);
        board.setWindow(window);
    }

//    public void startGame() {
//        boolean gameOver = false;
//        String winner;
//        while (!gameOver) {
//            if (!whiteCanMove()) {
//                if (board.isWhiteChecked()) {
//                    winner = "Black";
//                    break;
//                }
//                winner = "Stalemate";
//                break;
//            }
//            whiteMove();
//            if (!blackCanMove()) {
//                if (board.isBlackChecked()) {
//                    winner = "White";
//                    break;
//                }
//                winner = "Stalemate";
//                break;
//            }
//            blackMove();
//        }
//    }

    public boolean whiteCanMove() {
        if (board.getWhiteMoves().size() > 0) {
            return true;
        }
        return false;
    }

    public boolean blackCanMove() {
        if (board.getBlackMoves().size() > 0) {
            return true;
        }
        return false;
    }

    public void makeMove(Location l) {
        board.makeMove(l);
    }

    public boolean checkWhiteMove(Location move) {
        board.findWhiteMoves();
        if (!board.whiteHas(move)) {
            return false;
        }
        Piece p = null;
        if (board.getBoard()[move.getToRow()][move.getToCol()].hasPiece()) {
            p = board.getBoard()[move.getToRow()][move.getToCol()].getPiece();
        }
        board.makeWhiteMove(move);
        if (board.isWhiteChecked()) {
            board.undoWhiteMove(move, p);
            System.out.println("Checked");
            return false;
        }
        board.undoWhiteMove(move, p);
        return true;
    }

    public boolean isPromotion(Location move) {
        if (board.getBoard().isPromotion(move)) {
            return true;
        }
        return false;
    }

//    public boolean checkBlackMove(Location move) {
//        if (!board.getBlackMoves().contains(move)) {
//            return false;
//        }
//        checkBoard = new Board(board);
//        checkBoard.makeMove(move);
//        if (checkBoard.isBlackChecked()) {
//            return false;
//        }
//        return true;
//    }

    public boolean checkBlackMove(Location move) {
        board.findBlackMoves();
        if (!board.blackHas(move)) {
            return false;
        }
        Piece p = null;
        if (board.getBoard()[move.getToRow()][move.getToCol()].hasPiece()) {
            p = board.getBoard()[move.getToRow()][move.getToCol()].getPiece();
        }
        board.makeBlackMove(move);
        if (board.isBlackChecked()) {
            undoMove(move, p);
            return false;
        }
        undoMove(move, p);
        return true;
    }

    public void undoMove(Location move, Piece p) {
        board.makeMove(new Location(move.getToRow(), move.getToCol(), move.getRow(), move.getCol()));
        if (p != null) {
            board.getBoard()[move.getToRow()][move.getToCol()].setPiece(p);
        }
    }

    public void drawPieces(Graphics g) {
        board.drawPieces(g);
    }

    public void move(Location l, int status) {
        if (status == 1) {
            if (checkWhiteMove(l)) {
                makeMove(l);
            }
        }
        else {
            if (checkBlackMove(l)) {
                makeMove(l);
            }
        }
        window.repaint();
    }

    public Board getBoard() {
        return board;
    }
    public static void main(String[] args) {
        Chess game = new Chess();
    }
}
