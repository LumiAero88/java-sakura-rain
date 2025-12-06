import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

public class SakuraRain extends JPanel implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PETAL_COUNT = 200; 
    private static final Color BG_COLOR = new Color(20, 32, 52);

    private static final Color[] PETAL_COLORS = {
        new Color(255, 220, 235), 
        new Color(255, 190, 215), 
        new Color(255, 160, 190)  
    };

    private Timer timer;
    private ArrayList<Petal> petals;

    public SakuraRain() {
        this.setBackground(BG_COLOR);
        
        petals = new ArrayList<>();
        for (int i = 0; i < PETAL_COUNT; i++) {
            petals.add(new Petal());
        }

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        g2d.setColor(new Color(255, 255, 240, 30));
        g2d.fillOval(600, 50, 80, 80);
        g2d.setColor(new Color(255, 255, 240, 220));
        g2d.fillOval(610, 60, 60, 60);

        for (Petal p : petals) {
            p.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Petal p : petals) {
            p.update();
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Real Sakura Rain 🌸");
        SakuraRain panel = new SakuraRain();
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class Petal {
        float x, y;
        float speedY, speedX;
        float size;     
        float angle, spinSpeed;
        float swayAngle, swaySpeed;
        Color color;
        Shape petalShape; 

        public Petal() {
            reset(true);
        }

        void reset(boolean initial) {
            Random rand = new Random();
            x = rand.nextInt(WIDTH);
            y = initial ? rand.nextInt(HEIGHT) : -30;
            
            size = 10 + rand.nextInt(8); 

            speedY = 1.0f + rand.nextFloat() * 1.5f;
            speedX = 0;
            
            angle = rand.nextFloat() * 360;
            spinSpeed = (rand.nextFloat() - 0.5f) * 0.05f;

            swayAngle = rand.nextFloat() * 100;
            swaySpeed = 0.02f + rand.nextFloat() * 0.03f;

            color = PETAL_COLORS[rand.nextInt(PETAL_COLORS.length)];
            
            petalShape = createSakuraShape(size);
        }
        
        private Shape createSakuraShape(float s) {
            GeneralPath p = new GeneralPath();
            p.moveTo(0, s); 
            p.curveTo(-s*0.8, s*0.5, -s, -s*0.5, 0, -s*0.3);
            p.moveTo(0, -s*0.3);
            p.curveTo(s, -s*0.5, s*0.8, s*0.5, 0, s);
            p.closePath();
            return p;
        }

        void update() {
            y += speedY;
            swayAngle += swaySpeed;
            float sway = (float) Math.sin(swayAngle);
            x += sway * 1.0f; 
            
            angle += spinSpeed;
            angle += sway * 0.02f;

            if (y > HEIGHT + 30) {
                reset(false);
            }
        }

        void draw(Graphics2D g2d) {
            AffineTransform old = g2d.getTransform();

            g2d.translate(x, y);
            g2d.rotate(angle);
            
            double scaleX = 1.0; 
            double scaleY = 0.8 + Math.abs(Math.sin(swayAngle)) * 0.2;
            g2d.scale(scaleX, scaleY);

            g2d.setColor(color);
            g2d.fill(petalShape); 
            
            g2d.setTransform(old);
        }
    }
}