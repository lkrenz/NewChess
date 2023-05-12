import javax.swing.*;
import java.awt.*;

public class Chess {
    private final Board board;
    private final ChessView window;
    private String winner;

    // Creates a new instance of a basic chess game
    public Chess() {
        this.board = new Board();
        window = new ChessView(new ImageIcon("Resources/Chessboard.png").getImage(), this);
        board.setWindow(window);
    }

    // Checks whether or not white can carry out a certain move
    public boolean checkWhiteMove(Location move) {
        board.findWhiteMoves();
        if (!board.whiteHas(move)) {
            if (board.checkWhiteCastle(move)) {
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
            }
            return false;
        }
        // Makes the move and checks whether or not white is in check
        Piece p = null;
        if (board.getBoard()[move.getToRow()][move.getToCol()].hasPiece()) {
            p = board.getBoard()[move.getToRow()][move.getToCol()].getPiece();
        }
        board.makeWhiteMove(move);
        if (board.isWhiteChecked()) {
            board.undoWhiteMove(move, p);
            return false;
        }
        board.undoWhiteMove(move, p);
        return true;
    }

    // Returns true if the selected move is a promotion
    public boolean isPromotion(Location move) {
        return board.isPromotion(move);
    }

    public String getWinner() {
        return winner;
    }

    // Returns true if black can make the inputted move
    public boolean checkBlackMove(Location move) {
        board.findBlackMoves();
        if (!board.blackHas(move)) {
            if (board.checkBlackCastle(move)) {
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
            }
            return false;
        }
        // Makes the inputted move and checks if it would put them in check
        Piece p = null;
        if (board.getBoard()[move.getToRow()][move.getToCol()].hasPiece()) {
            p = board.getBoard()[move.getToRow()][move.getToCol()].getPiece();
        }
        board.makeBlackMove(move);
        if (board.isBlackChecked()) {
            board.undoBlackMove(move, p);
            return false;
        }
        board.undoBlackMove(move, p);
        return true;
    }

    public void drawPieces(Graphics g) {
        board.drawPieces(g);
    }

    // Takes user input to complete a move
    public void move(Location l, int status) {
        if (status == 1) {
            if (checkWhiteMove(l)) {
                // Checks if the move would put black into checkmate
                board.makeWhiteMove(l);
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
                if (board.isBlackCheckMated()) {
                    window.setGameOver(true);
                    winner = "white";
                }
            }
        }
        else {
            if (checkBlackMove(l)) {
                board.makeBlackMove(l);
                // Checks if the move would put white into checkmate
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
                if (board.isWhiteCheckMated()) {
                    window.setGameOver(true);
                    winner = "black";
                }
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
