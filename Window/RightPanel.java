package Window;
import javax.swing.*;
import java.awt.*;
class RightPanel extends JPanel{
    private int textHeight = 50;
    private int panelHeight = 95*3+10;
    private JPanel textPanel = new JPanel();
    private NextPanel nextPanel = new NextPanel();
    private JPanel emptyPanel = new JPanel();
    RightPanel() {
        setBackground(Window.BACKGROUND_COLOR);
        add();
        resize();
    }
    private void add() {
        JLabel textLabel = new JLabel("Next",SwingConstants.CENTER); textLabel.setFont(new Font("MONOSPACED", Font.BOLD, 25)); textLabel.setForeground(Color.WHITE); 
        textPanel.add(textLabel);
        textPanel.setBackground(Window.BACKGROUND_COLOR); emptyPanel.setBackground(Window.BACKGROUND_COLOR);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup();
        hGroup.addComponent(textPanel).addComponent(nextPanel).addComponent(emptyPanel);
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addComponent(textPanel).addComponent(nextPanel).addComponent(emptyPanel);
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);

    }
    private void resize() {
        textPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,textHeight));
        nextPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,panelHeight));
        emptyPanel.setPreferredSize(new Dimension(Window.SIDE_WIDTH,Window.TABLE_HEIGHT-textHeight-panelHeight));
    }
    public void setIcon(String[] next) {
        nextPanel.setIcons(next);
    }
}
