package Window;
import javax.swing.*;
import java.awt.*;
class Table extends JPanel {
    private int rows = 21;
    private int columns = 10;
    private JPanel[][] panels = new JPanel[rows][columns];
    Table() {
        setLayout(new GridLayout(rows,columns,1,1));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));;
        setBackground(Color.DARK_GRAY);
        for (int row=0;row<rows;row++) {
            for (int col=0;col<columns;col++) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.BLACK);
                panels[row][col] = panel;
                add(panel);
            }
        }
    }
    public void colorTable(Color[][] colors) {
        for (int row=0;row<rows;row++) {
            for (int col=0;col<columns;col++) {
                panels[row][col].setBackground(colors[row][col]);
            }
        }
    }
}
