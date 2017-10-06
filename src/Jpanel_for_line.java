import javax.swing.*;
import java.awt.*;

public class Jpanel_for_line extends JPanel {
    public int xx = 10;
    public int yy = 100;




    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(0, 0, yy, xx);
        xx += 1;
        yy+=2;
    }
}
