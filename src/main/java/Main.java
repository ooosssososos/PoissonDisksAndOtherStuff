import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

public class Main extends JFrame{

    static double RM = 0;
    static double RG = 0;
    static double RB = 0;

    protected static BufferedImage img;
    protected static BufferedImage img2;
    public Main() throws Exception{
        JPanel disp = new DisplayPanel();
        disp.setSize(500,500);
        this.getContentPane().add(disp);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        JSlider rslid = new JSlider(JSlider.HORIZONTAL, 0,100,25);
        rslid.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ev){
                RM = ((JSlider)ev.getSource()).getValue();
                try{
                updateImg();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        JSlider gslid = new JSlider(JSlider.HORIZONTAL, 0,100,25);
        gslid.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ev){
                RG = ((JSlider)ev.getSource()).getValue();
                try{
                    updateImg();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        JSlider bslid = new JSlider(JSlider.HORIZONTAL, 0,100,25);
        bslid.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ev){
                RB = ((JSlider)ev.getSource()).getValue();
                try{
                    updateImg();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        this.getContentPane().add(BorderLayout.PAGE_END, rslid);
        this.getContentPane().add(BorderLayout.SOUTH,gslid);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().add(BorderLayout.SOUTH,bslid);
        this.setSize(1920,1000);
        this.setVisible(true);
    }

    public static void updateImg() throws Exception{

        File f = new File("C:\\Users\\oscar\\Desktop\\ImgProc\\dem.jpg");
        File fo = new File("C:\\Users\\oscar\\Desktop\\ImgProc\\demproc.jpg");
        img = ImageIO.read(f);
        if(img2 == null)
        img2 = deepCopy(img);
        int width = img.getWidth();

        int height = img.getHeight();
        for(int i = 0 ; i < height; i ++){
            for(int o = 0; o < width; o++){
                Color c = new Color(img.getRGB(o,i));
                double r = c.getRed();
                double g = c.getGreen();
                double b = c.getBlue();
                Color ne = new Color((int)(r*RM/100),(int)(g*RG/100),(int)(b*RB/100));
                img.setRGB(o,i,ne.getRGB());
            }
        }

    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


}
