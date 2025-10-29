public class PairInt {
    private int x;
    private int y;

    // Constructor
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Create a copy of this PairInt
    public PairInt copy() {
        return new PairInt(this.x, this.y);
    }

    // Equals method to compare two PairInt objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PairInt other = (PairInt) obj;
        return this.x == other.x && this.y == other.y;
    }

    // String representation
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
