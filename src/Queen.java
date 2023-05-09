import java.awt.*;
import java.util.ArrayList;

public class Queen extends Piece{
    public Queen (int row, int col, int color, Board board, Image image) {
        super(row, col, color, board, image);
    }

    public Queen clone(Board board) {
        return new Queen(getRow(), getCol(), getColor(), board, getImage());
    }

    public void findMoves() {
        int row = getRow();
        int col = getCol();
        ArrayList<Location> moves = new ArrayList<>();
        findDiagonals(moves, 1, 1, row, col);
        findDiagonals(moves, -1, -1, row, col);
        findDiagonals(moves, -1, 1, row, col);
        findDiagonals(moves, 1, -1, row, col);
        findDiagonals(moves, 1, 0, row, col);
        findDiagonals(moves, 0, 1, row, col);
        findDiagonals(moves, -1, 0, row, col);
        findDiagonals(moves, 0, -1, row, col);
        setMoves(moves);
    }

//    public void findDiagonals(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
//        if (row < 0 || row > 7 || col < 0 || col > 7)
//            return;
//        if (getBoard().getBoard()[row][col].hasPiece()) {
//            if (getBoard().canMove(getColor(), row, col)) {
//                moves.add(new Location(row, col));
//            }
//            return;
//        }
//        if (dRow > 0) {
//            if (row == 7) {
//                moves.add(new Location(row, col));
//                return;
//            }
//        }
//        else {
//            if (row == 0) {
//                moves.add(new Location(row, col));
//                return;
//            }
//        }
//        if (dCol > 0) {
//            if (col == 7) {
//                moves.add(new Location(row, col));
//                return;
//            }
//        }
//        else {
//            if (col == 0) {
//                moves.add(new Location(row, col));
//                return;
//            }
//        }
//        moves.add(new Location(row, col));
//        findDiagonals(moves, dRow, dCol, row + dRow, col + dCol);
//    }

    public void findDiagonals(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7)
            return;
        if (dRow > 0) {
            if (row + dRow > 7) {
                return;
            }
        }
        else {
            if (row + dRow < 0) {
                return;
            }
        }
        if (dCol > 0) {
            if (col + dCol > 7) {
                return;
            }
        }
        else {
            if (col + dCol < 0) {
                return;
            }
        }
        if (getBoard().getBoard()[row + dRow][col + dCol].hasPiece()) {
            if (getBoard().canMove(getColor(), row + dRow, col + dCol)) {
                moves.add(new Location(row + dRow, col + dCol));
            }
            return;
        }
        moves.add(new Location(row + dRow, col + dCol));
        findDiagonals(moves, dRow, dCol, row + dRow, col + dCol);
    }

    public void findHorizontals(ArrayList<Location> moves, int dRow, int dCol, int row, int col) {
        if (getBoard().getBoard()[row][col].hasPiece()) {
            if (getBoard().canMove(getColor(), row, col)) {
                moves.add(new Location(row, col));
                return;
            }
            return;
        }
        if (dRow != 0) {
            if (row == 7 && dRow > 0) {
                moves.add(new Location(row, col));
                return;
            }
            if (row == 0 && dRow < 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        else {
            if (col == 7 && dCol > 0) {
                moves.add(new Location(row, col));
                return;
            }
            if (col == 0 && dCol < 0) {
                moves.add(new Location(row, col));
                return;
            }
        }
        findHorizontals(moves, dRow, dCol, row + dRow, col + dCol);
    }
}
