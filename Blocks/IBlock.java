package Blocks;

class IBlock extends Block{
    private int rotation = 0;
    IBlock() {
        cords[0] = new int[]{3,1}; cords[1] = new int[]{4,1}; cords[2] = new int[]{5,1}; cords[3] = new int[]{6,1};
        name = "I";
    }
    public void rotate() {
        cords = rotationCords();
        rotation++;
        if (rotation == 4) {rotation = 0;}
    }
    public int[][] rotationCords() {
        int[][] rotationCords = new int[4][2];
        int[][] rotating = new int[][]{new int[]{2,-1,-1,1},new int[]{1,2,-1,-1},new int[]{-2,1,1,-1},new int[]{-1,-2,1,1}};
        for (int n=0;n<4;n++) {
            rotationCords[n][0] = cords[n][0] + rotating[rotation][0] + n*rotating[rotation][2];
            rotationCords[n][1] = cords[n][1] + rotating[rotation][1] + n*rotating[rotation][3];
        }
        return rotationCords;
    }
}
