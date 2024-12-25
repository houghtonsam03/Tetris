package Window;
import java.awt.*;
import javax.swing.*;

class HoldPanel extends JPanel {
    private JLabel icon = new JLabel();
    HoldPanel() {
        add(icon);
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,Window.BORDER));
        resize();
    }
    private void resize() {
        setPreferredSize(new Dimension(Window.SIDE_WIDTH,100));
    }
    public void setIcon(String block) {
        icon.setIcon(new ImageIcon("./Images/"+block+".png"));
    }
}

