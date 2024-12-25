package Blocks;

public abstract class Block {
    int[][] cords = new int[4][2];
    String name;
    int rotation;
    Block() {
    }
    public static Block Factory(String type) {
        if (type.equals("I")) {return new IBlock();}
        else if (type.equals("J")) {return new JBlock();}
        else if (type.equals("L")) {return new LBlock();}
        else if (type.equals("O")) {return new OBlock();}
        else if (type.equals("S")) {return new SBlock();}
        else if (type.equals("T")) {return new TBlock();}
        else if (type.equals("Z")) {return new ZBlock();}
        else {return null;}
    }
    public String getName() {
        return name;
    }
    public int[][] getCords() {
        return cords;
    }
    public Boolean isAtPos(int row, int col) {
        for (int[] cord : cords) {
            if (cord[0] == col && cord[1] == row) {
                return true;
            }
        }
        return false;
    }
    public void moveDown() {
        for (int n=0;n<4;n++) {
            cords[n][1] = cords[n][1]+1;
        }
    }
    public void moveHorizontally(int change) {
        for (int n=0;n<4;n++) {
            cords[n][0] = cords[n][0]+change;
        }
    }
    public void rotate() {}
    public int[][] rotationCords() {return null;}
}
