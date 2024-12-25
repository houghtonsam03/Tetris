package Window;
import java.awt.*;

import javax.swing.*;
class Header extends JPanel {
    private JLabel scoreLabel;
    Header() {
        scoreLabel = new JLabel(""); scoreLabel.setForeground(Color.WHITE); setScore(0); scoreLabel.setFont(new Font("MONOSPACED", Font.BOLD,40));
        add(scoreLabel);
        setBackground(Window.BACKGROUND_COLOR);
    }
    void setScore(int score) {
        scoreLabel.setText("Score: "+ Integer.toString(score));
    }
}