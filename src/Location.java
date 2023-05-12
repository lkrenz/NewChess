public class Location {
    private final int row;
    private final int col;
    private final int toRow;
    private final int toCol;

    // Creates a new location, combining the row and col into one data type
    public Location (int row, int col) {
        this.row = row;
        this.col = col;
        this.toRow = -1;
        this.toCol = -1;
    }

    // Creates a new location with the inputted location as toRow and toCol
    public Location(int row, int col, Location location) {
        this.toRow = location.getRow();
        this.toCol = location.getCol();
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return row + ", " + col + " -----> " + toRow + ", " + toCol;
    }

    // Creates a new location with all inputted values
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

    public boolean equals(Location l) {
        if (this.row == l.getRow() && this.col == l.getCol() && this.toRow == l.getToRow() && this.toCol == l.getToCol()) {
            return true;
        }
        return false;
    }
}