package Window;
import java.awt.*;
import javax.swing.*;
import Blocks.Block;

public class Window extends JFrame {
    static Color I_COLOR = new Color(0,162,232);
    static Color J_COLOR = new Color(0,18,154);
    static Color L_COLOR = new Color(255,127,39);
    static Color O_COLOR = new Color(255,201,14);
    static Color S_COLOR = new Color(34,177,76);
    static Color T_COLOR = new Color(234,63,247);
    static Color Z_COLOR = new Color(237,28,36);
    static Color BACKGROUND_COLOR = new Color(9,68,102);
    static int WIDTH = 660;
    static int HEIGHT = 1000;
    static int TABLE_WIDTH = 450;
    static int TABLE_HEIGHT = 934;
    static int SIDE_WIDTH = (Window.WIDTH-Window.TABLE_WIDTH)/2;
    static int BORDER = 5;
    private Table table = new Table();
    private Header header = new Header();
    private LeftPanel left = new LeftPanel();
    private RightPanel right = new RightPanel();
    public Window() {
        add();
        resize();
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void resize() {
        header.setPreferredSize(new Dimension(WIDTH,HEIGHT-TABLE_HEIGHT));
        left.setPreferredSize(new Dimension((WIDTH - TABLE_WIDTH)/2,TABLE_HEIGHT));
        right.setPreferredSize(new Dimension((WIDTH - TABLE_WIDTH)/2,TABLE_HEIGHT));
        table.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
    }
    private void add() {
        Container pane = getContentPane();
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(header).addGroup(layout.createSequentialGroup().addComponent(left).addComponent(table).addComponent(right)));
        layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createSequentialGroup().addComponent(header).addGroup(layout.createParallelGroup().addComponent(left).addComponent(table).addComponent(right)));
        layout.setVerticalGroup(vGroup);
    }
    public void setNext(Block[] next) {
        String[] blocks = new String[3];
        for (int n=0;n<3;n++) {
            blocks[n] = next[n].getName();
        }
        right.setIcon(blocks);
    }
    public void colorTable(String[][] blocks) {
        Color[][] colors = new Color[21][10];
        for (int row=1;row<22;row++) {
            for (int col=0;col<10;col++) {
                if (blocks[row][col].equals("I")) {colors[row-1][col] = I_COLOR;}
                else if (blocks[row][col].equals("J")) {colors[row-1][col] = J_COLOR;}
                else if (blocks[row][col].equals("L")) {colors[row-1][col] = L_COLOR;}
                else if (blocks[row][col].equals("O")) {colors[row-1][col] = O_COLOR;}
                else if (blocks[row][col].equals("S")) {colors[row-1][col] = S_COLOR;}
                else if (blocks[row][col].equals("T")) {colors[row-1][col] = T_COLOR;}
                else if (blocks[row][col].equals("Z")) {colors[row-1][col] = Z_COLOR;}
                else {colors[row-1][col] = Color.BLACK;}
            }
        }
        table.colorTable(colors);
    }
    public void setHold(Block hold) {
        left.setIcon(hold.getName());
    }
    public void setScore(int val) {
        header.setScore(val);
    }
}
