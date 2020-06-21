import javax.swing.*;
import java.awt.Graphics;

public class Gilbert extends JFrame {
    static SimpleGraphics graphics;
    static long timer=0;

    public Gilbert(int size){
        setTitle("Gilbert");
        setSize(size,size);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Gilbert gilbert = new Gilbert(900);
        graphics = new SimpleGraphics(gilbert.getGraphics());
        p=8;                                                    //порядок
        u=(int)(850/Math.pow(2,p));
        if (u<=1) u=2;
        timeSet();
        d(p);
        timeSet();
    }

    public void paint(Graphics g) {
    }
    static int p; // порядок
    static int u; // длина штриха

    static void a(int i)
    {
        if (i > 0)
        {
            d(i-1);
            graphics.lineRel(u,0);
            a(i-1);
            graphics.lineRel(0, u);
            a(i - 1);
            graphics.lineRel(-u, 0);
            c(i - 1);
        }
    }

    static void b(int i)
    {
        if (i > 0)
        {
            c(i - 1);
            graphics.lineRel(-u, 0);
            b(i - 1);
            graphics.lineRel(0, -u);
            b(i - 1);
            graphics.lineRel(u, 0);
            d(i - 1);
        }
    }

    static void c(int i)
    {
        if (i > 0)
        {
            b(i - 1);
            graphics.lineRel(0, -u);
            c(i - 1);
            graphics.lineRel(-u, 0);
            c(i - 1);
            graphics.lineRel(0, u);
            a(i - 1);
        }
    }
    static void d(int i)
    {
        if (i > 0)
        {
            a(i - 1);
            graphics.lineRel(0, u);
            d(i - 1);
            graphics.lineRel(u, 0);
            d(i - 1);
            graphics.lineRel(0, -u);
            b(i - 1);
        }
    }

    static class SimpleGraphics {
        private Graphics g = null;
        private int x = 20, y = 35;

        public SimpleGraphics(Graphics g) {
            this.g = g;
        }

        public void lineRel(int deltaX, int deltaY) {
            g.drawLine(x, y, x + deltaX, y + deltaY);
            x += deltaX;
            y += deltaY;
        }
    }

    public static void timeSet(){
        if (timer==0) timer=System.nanoTime();
        else {
            double val = (double)(System.nanoTime()-timer);
            System.out.println(val/1000000000);
            timer=0;
        }
    }
}
