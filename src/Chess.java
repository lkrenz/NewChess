import javax.swing.*;
import java.awt.*;

public class Chess {
    private Board board;
    private Board checkBoard;
    private ChessView window;
    private String winner;

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
            if (board.checkWhiteCastle(move)) {
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
            }
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
        return board.isPromotion(move);
    }

    public String getWinner() {
        return winner;
    }

    public boolean checkBlackMove(Location move) {
        board.findBlackMoves();
        if (board.getBlackMoves().size() == 0) {
            window.setGameOver(true);
            if (board.isBlackChecked()) {
                winner = "Black";
            }
            winner = "Stalemate";
            return false;
        }
        if (!board.blackHas(move)) {
            if (board.checkBlackCastle(move)) {
                window.setBoardStatus(Math.abs(window.getBoardStatus() - 1));
            }
            return false;
        }
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

    public void move(Location l, int status) {
        if (status == 1) {
            if (checkWhiteMove(l)) {
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
