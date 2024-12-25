package Blocks;

class OBlock extends Block{
    OBlock() {
        cords[0] = new int[]{4,0}; cords[1] = new int[]{5,0}; cords[2] = new int[]{5,1}; cords[3] = new int[]{4,1};
        name = "O";
    }
    public void rotate() {
        cords = rotationCords();
    }
    public int[][] rotationCords() {
        int[][] newCords = new int[4][2];
        int[] lastCords = cords[3];
        for (int i = 0; i < newCords.length;i++) {
            newCords[i] = lastCords;
            lastCords = cords[i];
        }
        return newCords;
    }
}
