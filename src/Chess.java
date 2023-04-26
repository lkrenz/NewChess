import javax.swing.*;
import java.util.Stack;

public class Chess {
    private Stack<Board> boards;
    private ChessView window;

    public Chess() {
        this.boards = new Stack<Board>();
        boards.push(new Board());
        window = new ChessView(new ImageIcon("Resources/chessboard.png").getImage());
    }

    public void startGame() {
        boolean gameOver = false;
        String winner;
        while (!gameOver) {
            if (!whiteCanMove()) {
                if (boards.peek().isWhiteChecked()) {
                    winner = "Black";
                    break;
                }
                winner = "Stalemate";
                break;
            }
            whiteMove();
            if (!blackCanMove()) {
                if (boards.peek().isBlackChecked()) {
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
        if (!boards.peek().getWhitePieces().contains(move)) {
            return false;
        }
        boards.add(new Board(boards.peek().getBoard()));
        boards.peek().makeMove(move);
        if (boards.peek().isWhiteChecked()) {
            return false;
        }
        return true;
    }

    public boolean blackCanMove() {
        return false;
    }

    public static void main(String[] args) {
        Chess game = new Chess();
    }
}
