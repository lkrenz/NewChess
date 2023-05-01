import javax.swing.*;
import java.util.Stack;

public class Chess {
    private Board board;
    private Board checkBoard;
    private ChessView window;

    public Chess() {
        this.board = new Board();
        window = new ChessView(new ImageIcon("Resources/chessboard.png").getImage());
    }

    public void startGame() {
        boolean gameOver = false;
        String winner;
        while (!gameOver) {
            if (!whiteCanMove()) {
                if (board.isWhiteChecked()) {
                    winner = "Black";
                    break;
                }
                winner = "Stalemate";
                break;
            }
            whiteMove();
            if (!blackCanMove()) {
                if (board.isBlackChecked()) {
                    winner = "White";
                    break;
                }
                winner = "Stalemate";
                break;
            }
            blackMove();
        }
    }

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

    public void whiteMove() {
        Location move = window.getMove();
        while (!checkWhiteMove(move)) {
            move = window.getMove();
        }
    }

    public void blackMove() {
        return;
    }

    public boolean checkWhiteMove(Location move) {
        if (!board.getWhiteMoves().contains(move)) {
            return false;
        }
        checkBoard = new Board(board.getBoard());
        checkBoard.makeMove(move);
        if (checkBoard.isWhiteChecked()) {
            return false;
        }
        return true;
    }

    public boolean checkBlackMove(Location move) {
        if (!board.getBlackMoves().contains(move)) {
            return false;
        }
        checkBoard = new Board(board.getBoard());
        checkBoard.makeMove(move);
        if (checkBoard.isBlackChecked()) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Chess game = new Chess();
    }
}
