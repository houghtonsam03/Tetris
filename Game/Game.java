package Game;
import Window.Window;
import java.awt.event.*;
import java.util.*;
import Blocks.Block;

public class Game implements KeyListener{
    static String[] blocks = new String[]{"I","J","L","O","S","T","Z"}; // Possible blocks
    private Window window; // Window for game visualization
    private String[][] tetrisTable = new String[22][10]; // Tetris game table (22 rows, 10 columns)
    private Block currentBlock; // Current block being controlled
    private Block holdBlock; // Current hold block
    private Block[] nextBlocks = new Block[3]; // Next blocks to be spawned
    private Boolean holdUsed = false; // Flag to check if hold was used
    private Boolean dropping = false; // Flag to check if block is dropping
    private Boolean gameover = false; // Gameover flag
    private int speed = 500; // Speed in milliseconds
    private int score = 0; // Current score
    private int speedChange = 50; // Speed change value
    private int minSpeed = 100; // Minimum speed value
    Game() {
        window = new Window();
        window.addKeyListener(this);
        Random rand = new Random();
        for (int n=0;n<3;n++) {
            nextBlocks[n] = Block.Factory(blocks[rand.nextInt(blocks.length)]);
        }
        initTable();
        spawn();
        try {play();}
        catch (InterruptedException e) {}
    } 
    private void play() throws InterruptedException {
        while (!gameover) {
            gravity();
            update();
            Thread.sleep(speed);
        }
        System.out.println("Game Over!");
    }
    private void initTable() {
        for (int row=0;row<22;row++) {
            for (int col=0;col<10;col++) {
                tetrisTable[row][col] = "";
            }
        }
    }
    private void spawn() {
        pickFromNext();
        holdUsed = false; dropping = false;
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = currentBlock.getName();
        }
    }
    private void spawn(String block) {
        currentBlock = Block.Factory(block);
        holdUsed = false; dropping = false;
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = currentBlock.getName();
        }
    }
    private void pickFromNext() {
        Random rand = new Random();
        currentBlock = nextBlocks[0];
        for (int n=0;n<3;n++) {
            if (n == 2) {nextBlocks[n] = Block.Factory(blocks[rand.nextInt(blocks.length)]);}
            else {nextBlocks[n] = nextBlocks[n+1];}
        }
    }
    private void gravity() {
        if (isEmptyUnder(currentBlock)) {
            moveDown();
        }
        else if (!isGameover()) {
            tetris();
            spawn();
        } else {gameover = isGameover();}
    }
    private void moveDown() {
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = "";
        }
        currentBlock.moveDown();
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = currentBlock.getName();
        }
    }
    private void moveHorizontally(int change) {
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = "";
        }
        currentBlock.moveHorizontally(change);
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = currentBlock.getName();
        }
    }
    private void hold() {
        if (holdUsed) {return;} // If hold was already used, do nothing
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = "";
        }
        if (holdBlock != null) {
            Block tmpBlock = holdBlock;
            holdBlock = currentBlock;
            spawn(tmpBlock.getName());
        }
        else {
            holdBlock = currentBlock;
            spawn();
        }
        holdUsed = true;
    }
    private void drop() {
        while (isEmptyUnder(currentBlock)) {
            moveDown();
        }
        dropping = true;
    }
    private void rotate() {
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = "";
        }
        currentBlock.rotate();
        for (int[] cord : currentBlock.getCords()) {
            tetrisTable[cord[1]][cord[0]] = currentBlock.getName();
        }
    }
    private Boolean isEmptyUnder(Block block) {
        int[][] bottomCords = getBottomCords(block.getCords().clone());
        Boolean empty = true;
        try {
            for (int[] cord : bottomCords) {
                if (tetrisTable[cord[1]+1][cord[0]] != "") {
                    empty=false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            empty=false;
        }
        return empty;
    }
    private Boolean isEmptyToLeft(Block block) {
        int[][] leftCords = getLeftCords(block.getCords().clone());
        Boolean empty = true;
        try {
            for (int[] cord : leftCords) {
                if (tetrisTable[cord[1]][cord[0]-1] != "") {
                    empty=false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            empty=false;
        }
        return empty;
    }
    private Boolean isEmptyToRight(Block block) {
        int[][] rightCords = getRightCords(block.getCords().clone());
        Boolean empty = true;
        try {
            for (int[] cord : rightCords) {
                if (tetrisTable[cord[1]][cord[0]+1] != "") {
                    empty=false;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            empty=false;
        }
        return empty;
    }
    private int[] findTetrisRows() {
        ArrayList<Integer> rowArray = new ArrayList<Integer>();
        for (int row=0;row<22;row++) {
            if (!Arrays.asList(tetrisTable[row]).contains("")) {rowArray.add(row);}
        }
        int[] rows = new int[rowArray.size()];
        for (int n=0;n<rowArray.size();n++) {
            rows[n] = rowArray.get(n);
        }
        return rows;
    }
    private void tetris() {
        int[] rows = findTetrisRows();
        ArrayList<String[]> rowArray = new ArrayList<String[]>();
        for (int n=0;n<rows.length;n++) {
            rowArray.add(new String[]{"","","","","","","","","",""});
        }
        for (int row=0;row<22;row++) {
            Boolean hasTetris = false;
            for (int r :rows) {
                if (r == row) {hasTetris = true;}
            }
            if (!hasTetris) {rowArray.add(tetrisTable[row]);}
        }
        String[][] new_table = new String[22][10];
        for (int row=0;row<22;row++) {
            for (int col=0;col<10;col++) {
                new_table[row][col] = rowArray.get(row)[col];
            }
        }
        tetrisTable = new_table;
        int tetrisAmount = rows.length;
        if (tetrisAmount == 1) {addScore(100);}
        else if (tetrisAmount == 2) {addScore(300);}
        else if (tetrisAmount == 3) {addScore(500);}
        else if (tetrisAmount == 4) {addScore(800);}
    }
    private void addScore(int sc) {
        score += sc;
        speedUp(); // Speed up if score is high enough
    }
    private void speedUp() {
        int k = (500-speed)/speedChange; // What thousand is the score in
        if (score >= (k+1)*1000 && speed-speedChange >= minSpeed) {speed += -speedChange;} // Speed up
    }
    private Boolean isGameover() {
        Boolean over = false;
        for (int col=0;col<10;col++) {
            if (tetrisTable[1][col] != "" ) {
                over = true;
            }
        }
        return over;
    }
    private int[][] getBottomCords(int[][] cords) {
        Arrays.sort(cords,new DownSort());
        ArrayList<int[]> result = new ArrayList<int[]>();
        int old_col = cords[0][0];
        int old_row = cords[0][1];
        for (int n=0;n<4;n++){
            int[] cord = cords[n];
            if (cord[0] != old_col ) {
                result.add(new int[]{old_col,old_row});
                old_col = cord[0];
                old_row = cord[1];
            }
            else {old_row = cord[1];}
        }
        result.add(new int[]{cords[3][0],cords[3][1]});
        int[][] bottomCords = new int[result.size()][2];
        for (int n=0;n<result.size();n++) {
            bottomCords[n] = result.get(n);
        }
        return bottomCords;
    }
    private int[][] getLeftCords(int[][] cords) {
        ArrayList<int[]> cordArray = new ArrayList<int[]>();
        Arrays.sort(cords,new LeftSort());
        for (int[] cord : cords) {
            cordArray.add(cord);
        }
        cordArray.add(new int[]{-1,-1});
        ArrayList<int[]> result = new ArrayList<int[]>();
        int old_col = cordArray.get(0)[0];
        int old_row = cordArray.get(0)[1];
        for (int[] cord : cordArray) {
            if (cord[1] == old_row) {
                old_col = cord[0];
            }
            else {
                result.add(new int[]{old_col,old_row});
                old_row = cord[1];
            }
        }
        int[][] leftCords = new int[result.size()][2];
        for (int n=0;n<result.size();n++) {
            leftCords[n] = result.get(n);
        }
        return leftCords;
    }
    private int[][] getRightCords(int[][] cords) {
        ArrayList<int[]> cordArray = new ArrayList<int[]>();
        Arrays.sort(cords,new RightSort());
        for (int[] cord : cords) {
            cordArray.add(cord);
        }
        cordArray.add(new int[]{-1,-1});
        ArrayList<int[]> result = new ArrayList<int[]>();
        int old_col = cordArray.get(0)[0];
        int old_row = cordArray.get(0)[1];
        for (int[] cord : cordArray) {
            if (cord[1] == old_row) {
                old_col = cord[0];
            }
            else {
                result.add(new int[]{old_col,old_row});
                old_row = cord[1];
            }
        }
        int[][] rightCords = new int[result.size()][2];
        for (int n=0;n<result.size();n++) {
            rightCords[n] = result.get(n);
        }
        return rightCords;
    }
    private Boolean canRotate(Block block) {
        ArrayList<int[]> noneCurrent = new ArrayList<int[]>();
        int[][] new_cords = block.rotationCords();
        for (int[] cords : new_cords) {
            if (!currentBlock.isAtPos(cords[1], cords[0])) {noneCurrent.add(cords);}
        }
        Boolean rotatable = true;
        try {
            for (int[] cords : noneCurrent) {
                if (tetrisTable[cords[1]][cords[0]] != "") {rotatable = false;}
            }
        }
        catch (Exception e) {rotatable = false;}
        return rotatable;
    }
    private void update() {
        window.setScore(score);
        window.setNext(nextBlocks);
        window.colorTable(tetrisTable);
        try {window.setHold(holdBlock);}
        catch (Exception e) {}
        window.setVisible(true);
    }
    public void writeTable() {
        for (int row=0;row<22;row++) {
            for (int col=0;col<10;col++) {
                String l = tetrisTable[row][col];
                if (l == "") {l = "X";}
                System.out.print(l+" ");
            }
            System.out.println();
        }
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
        if (gameover || dropping) {return;}
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && isEmptyToLeft(currentBlock)) {moveHorizontally(-1);}
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && isEmptyToRight(currentBlock)) {moveHorizontally(1);}
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && isEmptyUnder(currentBlock)) {moveDown();}
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {hold();}
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {drop();}
        else if (e.getKeyCode() == KeyEvent.VK_UP && canRotate(currentBlock)) {rotate();}
        update();
    }
    public static void printCords(int[][] cords) {
        for (int[] cord : cords) {
            for (int v : cord) {
                System.out.print(v+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}