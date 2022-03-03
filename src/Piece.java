import javax.swing.*;
import java.awt.*;

public class Piece extends JComponent implements Runnable {
    //I - cyan
    private static final Point[] I = {
            new Point(4, 0),
            new Point(5, 0),
            new Point(6, 0),
            new Point(7, 0)};
    //Square - yellow
    private static final Point[] SQUARE = {
            new Point(4, 0),
            new Point(5, 0),
            new Point(4, 1),
            new Point(5, 1)};
    //T - purple
    private static final Point[] T = {
            new Point(5, 0),
            new Point(4, 1),
            new Point(5, 1),
            new Point(6, 1)};
    //S - lime
    private static final Point[] S = {
            new Point(6, 0),
            new Point(5, 0),
            new Point(5, 1),
            new Point(4, 1)};
    //Z - red
    private static final Point[] Z = {
            new Point(4, 0),
            new Point(5, 0),
            new Point(5, 1),
            new Point(6, 1)};
    //L - orange
    private static final Point[] L = {
            new Point(6, 0),
            new Point(6, 1),
            new Point(5, 1),
            new Point(4, 1)};
    //J - blue
    private static final Point[] J = {
            new Point(4, 1),
            new Point(4, 1),
            new Point(5, 1),
            new Point(6, 0)};

    private Point[] points;
    private Color mainColor, topColor, sideColor, bottomColor;
    private boolean alive;



    public Piece(int type) {
        alive = true;

        switch (type) {
            case 1:
                points = I.clone();
                mainColor = new Color(0, 240, 240);
                topColor = new Color(167, 255, 255);
                sideColor = new Color(0, 216, 216);
                bottomColor = new Color(0, 107, 107);
                break;

            case 2:
                points = SQUARE.clone();
                mainColor = new Color(240, 240, 0);
                topColor = new Color(255, 255, 167);
                sideColor = new Color(216, 216, 0);
                bottomColor = new Color(107, 107, 0);
                break;

            case 3:
                points = T.clone();
                mainColor = new Color(160, 0, 240);
                topColor = new Color(223, 158, 255);
                sideColor = new Color(144, 1, 216);
                bottomColor = new Color(78, 0, 118);
                break;

            case 4:
                points = S.clone();
                mainColor = new Color(0, 240, 0);
                topColor = new Color(165, 255, 156);
                sideColor = new Color(0, 216, 0);
                bottomColor = new Color(0, 107, 0);
                break;
            case 5:
                points = Z.clone();
                mainColor = new Color(240, 0, 0);
                topColor = new Color(240, 100, 100);
                sideColor = new Color(216, 0, 0);
                bottomColor = new Color(107, 0, 0);
                break;

            case 6:
                points = L.clone();
                mainColor = new Color(240, 160, 0);
                topColor = new Color(255, 226, 167);
                sideColor = new Color(216, 144, 0);
                bottomColor = new Color(118, 79, 1);
                break;

            case 7:
                points = J.clone();
                mainColor = new Color(0, 0, 240);
                topColor = new Color(167, 167, 255);
                sideColor = new Color(0, 0, 216);
                bottomColor = new Color(0, 0, 107);
                break;

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (Point point : points) {
            this.drawSquare(point, g);
        }
    }

    @Override
    public void run() {
        while (Game.running && alive) {
            //piece moving down
            //crash detection
            //loose detection
            //turning and rotating
        }

        while (Game.running) {
            //transfer pieces to death chunk
            //save processiong power some how
        }
    }

    public void drawSquare(Point point, Graphics g) {
        Game.drawSquare(point, mainColor, topColor, sideColor, bottomColor, g);
    }

    public void rotateClockwise() {}

    public void rotateCounterClockwise() {}

    public void moveRight() {
        for (Point point : points) {
            point.x += 1;
        }
    }

    public void moveLeft() {
        for (Point point : points) {
            point.x -= 1;
        }
    }

    public void moveDown() {
        for (Point point : points) {
            point.y -= 1;
        }
    }

    public int convert(int x, String y) {
        return Game.convert(x, y);
    }

    public void nextFrame() {repaint();}
}
