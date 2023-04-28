public class Location {
    private int row;
    private int col;
    private int toRow;
    private int toCol;

    public Location (int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Location(Location location, int row, int col) {
        this.toRow = location.getRow();
        this.toCol = location.getCol();
        this.row = row;
        this.col = col;
    }

    public Location (int row, int col, int toRow, int toCol) {
        this.row = row;
        this.col = col;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }
}