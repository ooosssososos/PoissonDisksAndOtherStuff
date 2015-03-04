import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        g.clearRect(0,0,this.getWidth(),this.getHeight());
        g.drawLine(0,0,50,50);
        g.drawImage(Main.img,0,0,null);
        if(Main.img2 != null)
        g.drawImage(Main.img2,Main.img.getWidth(),0,null);
        repaint();
    }
}
