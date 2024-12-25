package Window;
import javax.swing.*;
import java.awt.*;
class LeftPanel extends JPanel{
    private int textHeight = 50;
    private int panelHeight = 105;
    private JPanel textPanel = new JPanel();
    private HoldPanel holdPanel = new HoldPanel();
    private JPanel emptyPanel = new JPanel();
    LeftPanel() {
        setBackground(Window.BACKGROUND_COLOR);
        add();
        resize();
    }
    private void add() {
        JLabel textLabel = new JLabel("Hold",SwingConstants.CENTER); textLabel.setFont(new Font("MONOSPACED", Font.BOLD, 25)); textLabel.setForeground(Color.WHITE); 
        textPanel.add(textLabel);
        textPanel.setBackground(Window.BACKGROUND_COLOR); emptyPanel.setBackground(Window.BACKGROUND_COLOR);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
        hGroup.addComponent(textPanel).addComponent(holdPanel).addComponent(emptyPanel);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(textPanel).addComponent(holdPanel).addComponent(emptyPanel);
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);

    }
    private void resize() {
        textPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,textHeight));
        holdPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,panelHeight));
        emptyPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,Window.TABLE_HEIGHT-textHeight-panelHeight));
    }
    public void setIcon(String block) {
        holdPanel.setIcon(block);
    }
}
