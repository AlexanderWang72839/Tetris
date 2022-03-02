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

    final static Color mainBorder = new Color(119, 119, 119);
    final static Color topBorder = new Color(215, 215, 215);
    final static Color sideBorder = new Color(83, 83, 83);
    final static Color bottomBorder = new Color(35, 35, 35);

    Point[] edges = new Point[GAMEY * 2];

    //objects
    PieceManager pieceManager = new PieceManager();

    //threads
    Thread pieceManagerThread;

    public Game() {
        pieceManagerThread = new Thread(pieceManager);

        for (int i = 0 ; i < preview.length ; i++) {
            preview[i] = rand.nextInt(7);
        }

        for (int i = 0 ; i < GAMEY ; i++) {
            edges[i] = new Point(0, i);
            edges[i + GAMEY] = new Point(GAMEX, i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
        draw.setBackground(new Color(0, 0, 0));
        this.drawBackground(g);
        pieceManager.paintComponent(g);
    }

    public void drawBackground(Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        for (Point point : edges) {
            drawSquare(point, mainBorder, topBorder, sideBorder, bottomBorder, g);
        }
    }

    public static void drawSquare(Point point, Color mainColor, Color topColor, Color sideColor, Color bottomColor, Graphics g) {
        Graphics2D draw = (Graphics2D)g;

        draw.setColor(mainColor);
        draw.fillRect(convert(point.x, "x"), convert(point.x, "y"), xScale, yScale);

        draw.setColor(sideColor);
        draw.fillRect(convert(point.x, "x"), convert(point.y, "y"), xScale * (5 / 33), yScale);

        draw.fillPolygon ( //upper left
                new int[] {
                        convert(point.x, "x"),
                        convert(point.x, "x") + xScale * (5 / 33),
                        convert(point.x, "x") + xScale * (5 / 33)
                },
                new int[] {
                        convert(point.y, "y"),
                        convert(point.y, "y"),
                        convert(point.y, "y") + yScale * (5 / 33)
                },
                3
        );

        draw.fillPolygon ( //upper right
                new int[] {
                        convert(point.x + 1, "x") - 1,
                        convert(point.x + 1, "x") - 1 - yScale * (5 / 33),
                        convert(point.x + 1, "x") - 1 - yScale * (5 / 33)
                },
                new int[] {
                        convert(point.y, "y"),
                        convert(point.y, "y"),
                        convert(point.y, "y") + yScale * (5 / 33)
                },
                3
        );

        draw.fillPolygon ( //lower left
                new int[] {
                        convert(point.x, "x"),
                        convert(point.x, "x") + xScale * (5 / 33),
                        convert(point.x, "x") + xScale * (5 / 33)
                },
                new int[] {
                        convert(point.y + 1, "y") - 1,
                        convert(point.y + 1, "y") - 1,
                        convert(point.y + 1, "y") - 1 - yScale * (5 / 33)
                },
                3
        );

        draw.fillPolygon ( //lower right
                new int[] {
                        convert(point.x + 1, "x") - 1,
                        convert(point.x + 1, "x") - 1 - yScale * (5 / 33),
                        convert(point.x + 1, "x") - 1 - yScale * (5 / 33)
                },
                new int[] {
                        convert(point.y + 1, "y") - 1,
                        convert(point.y + 1, "y") - 1,
                        convert(point.y + 1, "y") - 1 - yScale * (5 / 33)
                },
                3
        );

    }

    //converts cords to pixel pos
    public static int convert(int x, String y) {
        if (y.equals("x"))
            return x * xScale;
        return x * yScale;
    }

    public void nextFrame() {repaint();}

    public void stopThread() {}
}
