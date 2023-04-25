import java.util.Stack;

public class Chess {
    private Stack<Board> boards;
    private ChessView window;

    public Chess() {
        this.boards = new Stack<Board>();
        boards.push(new Board());
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
        return;
    }

    public void blackMove() {
        return;
    }

    public boolean blackCanMove() {
        return false;
    }
}
