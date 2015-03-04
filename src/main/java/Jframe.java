import javax.swing.*;
import java.awt.*;

public class Jframe extends JFrame {

    public Jframe(){
        PoissonDiskPanel f = new PoissonDiskPanel();
        f.setSize(1920,1000);
        this.getContentPane().add(BorderLayout.CENTER, f);
        this.setSize(1920,1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



}
