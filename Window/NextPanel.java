package Window;
import java.awt.*;
import javax.swing.*;

class NextPanel extends JPanel {
    private int nextSize = 3;
    private JPanel[] nextArray = new JPanel[nextSize];
    NextPanel() {
        for (int i = 0; i < nextSize;i++) {
            JPanel next = new JPanel();
            next.add(new JLabel());
            next.setBackground(Color.BLACK);
            nextArray[i] = next;
        }
        add();
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,Window.BORDER));
        resize();
    }
    private void add() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        for (JPanel next : nextArray) {
            hGroup.addComponent(next);
            vGroup.addComponent(next);
        }
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
    }
    public void setIcons(String[] blocks) {
        for (int i=0;i<3;i++) {
            ((JLabel)nextArray[i].getComponent(0)).setIcon(new ImageIcon("./Images/"+blocks[i]+".png"));
        }
    }
    private void resize() {
        for (JPanel next : nextArray) {
            next.setPreferredSize(new Dimension(Window.SIDE_WIDTH,100));
        }
    }
}
