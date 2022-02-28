import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JComponent {
    Random rand = new Random();
    int[] preview = new int[4];
    final static int GAMEX = 10;
    final static int GAMEY = 20;
    final static int xScale = 50;
    final static int yScale = 50;
    final static Color border = new Color(119, 119, 119);

    //objects
    PieceManager pieceManager = new PieceManager();

    //threads

    public Game() {
        for (int i = 0 ; i < preview.length ; i++) {
            preview[i] = rand.nextInt(7);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
        drawBackground(g);
    }

    public void drawBackground(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (int i = 0 ; i < GAMEY ; i++) {
            draw.setColor(border);

        }
    }

    public static void drawSquare(Point point, Color mainColor, Color topColor, Color sideColor, Color bottomColor, Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        draw.setColor(mainColor);
        draw.fillRect(convert(point.x, "x"), convert(point.x, "y"), xScale, yScale);

        draw.setColor(sideColor);
        draw.fillRect(convert(point.x, "x"), convert(point.y, "y"), xScale * (5 / 33), yScale);
        draw.fillPolygon();
    }

    //converts cords to pixel pos
    public static int convert(int x, String y) {
        if (y.equals("x"))
            return x * xScale;
        return x * yScale;
    }

    public void nextFrame() {repaint();}

    public void stopThread() {
    }
}
