package Blocks;

class SBlock extends Block{
    private int rotation = 1;
    SBlock() {
        cords[0] = new int[]{3,1}; cords[1] = new int[]{4,0}; cords[2] = new int[]{4,1}; cords[3] = new int[]{5,0};
        name = "S";
    }
    public void rotate() {
        cords = rotationCords();
        rotation = rotation + Integer.signum(rotation);
        if (rotation == -3) {rotation = 1;}
        else if (rotation == 3) {rotation = -1;}
    }
    public int[][] rotationCords() {
        int[][] rotationCords = new int[4][2];
        int[][][] rotating = new int[][][]{new int[][]{new int[]{1,-1},new int[]{1,1},new int[]{0,0},new int[]{0,2}},new int[][]{new int[]{1,1},new int[]{-1,1},new int[]{0,0},new int[]{-2,0}}};
        for (int n=0;n<4;n++) {
            rotationCords[n][0] = cords[n][0] +Integer.signum(rotation)*rotating[Math.abs(rotation)-1][n][0];
            rotationCords[n][1] = cords[n][1] +Integer.signum(rotation)*rotating[Math.abs(rotation)-1][n][1];
        }
        return rotationCords;
    }
}
