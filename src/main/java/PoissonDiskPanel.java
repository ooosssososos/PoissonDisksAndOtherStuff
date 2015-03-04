import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PoissonDiskPanel extends JPanel {
    final static double r = 100;
    final static int ITERATIONS = 10;
    BufferedImage prev = new BufferedImage(1920,1000,BufferedImage.TYPE_4BYTE_ABGR);
    QuadTree qt = new QuadTree();
    ArrayList<QNode> ac = new ArrayList<QNode>();
    boolean first = true;
    public void paintComponent(Graphics gz) {
        if(first == true){
            gz.clearRect(0,0,1920,1000);
            first = false;
        }
        Graphics2D g = (Graphics2D) gz;

        drawDot(insertPnt(),g);
        g.dispose();
        // repaint panel with new modified paint
        try{
            Thread.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }

        repaint();
    }

    Point2D getRandInDisk(int x, int y) {
        double dx = 0;
        double dy = 0;
        double dr = 0;
       do{
        dr = Math.random() * r + r;
           double radang = Math.random() * 2 * Math.PI;
        dx = Math.sin(radang) * dr;
           dy = Math.cos(radang) * dr;
           //System.out.println("attempting " + dr + "," + dx + "," + dy);
       }while(x + dx < 0 || x + dx > 1920 || y + dy < 0 || y + dy > 1000);
        return new Point2D.Double(x + dx, y + dy);
    }

    public void addNode(int a, int b) {

        ac.add(qt.addNode(a, b));
    }

    public void addNode(double a, double b) {
        addNode((int) a, (int) b);
    }

    public QNode insertPnt() {
        if (qt.size() == 0) {
            qt.addNode((int) Math.random() * WIDTH, (int) Math.random() * HEIGHT);
            ac.add(qt.root);
        }
        if (ac.size() != 0) {
            double rand = Math.random() * ac.size();
            QNode s = ac.get((int)Math.floor(rand));
            for (int i = 0; i < ITERATIONS; i++) {
                Point2D prc = getRandInDisk((int) s.x, (int) s.y);
                if (qt.getClosest((int) prc.getX(), (int) prc.getY()) > r) {
                    System.out.println("Closest: " + qt.getClosest((int) prc.getX(), (int) prc.getY()));
                    addNode(prc.getX(), prc.getY());
                    System.out.println(prc.getX() + "    " + prc.getY());
                    return ac.get(ac.size()-1);
                }
                if (i == ITERATIONS - 1) {
                    // remove from active set
                    ac.remove(s);
                }
            }
        }
        return null;
    }

    public void drawDot(QNode a, Graphics g) {
        if(a != null){
            g.fillOval((int) a.x ,(int) a.y, 10, 10);
        }
    }

}
