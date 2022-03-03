import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JComponent {
    Random rand = new Random();
    final static int GAMEX = 10;
    final static int GAMEY = 20;
    final static int xScale = 50;
    final static int yScale = 50;

    final static Color mainBorder = new Color(119, 119, 119);
    final static Color topBorder = new Color(215, 215, 215);
    final static Color sideBorder = new Color(83, 83, 83);
    final static Color bottomBorder = new Color(35, 35, 35);

    public static boolean running;

    int[] preview = new int[4];
    Point[] edges = new Point[GAMEY * 2];

    //objects
    PieceManager pieceManager = new PieceManager();

    //threads
    Thread pieceManagerThread;

    public Game() {
        this.setPreferredSize(new Dimension(convert(GAMEX, "x"), convert(GAMEY, "y")));
        this.setDoubleBuffered(true);

        running = true;

        //assign threads
        pieceManagerThread = new Thread(pieceManager);

        //start threads
        pieceManagerThread.start();

        for (int i = 0 ; i < preview.length ; i++) {
            preview[i] = rand.nextInt(7);
        }

        for (int i = 0 ; i < GAMEY ; i++) {
            edges[i] = new Point(0, i);
            edges[i + GAMEY] = new Point(GAMEX - 1, i);
        }

        //print array
        for (Point i : edges)
            System.out.println(i);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
        draw.setColor(new Color(0, 0, 0));
        //draw.fillRect(0, 0, convert(GAMEX, "x"), convert(GAMEY, "y"));
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
        draw.fillRect(convert(point.x, "x"), convert(point.y, "y"), xScale, yScale);

        draw.setColor(sideColor);
        draw.fillRect(convert(point.x, "x"), convert(point.y, "y"), (int)(xScale * (5.0 / 33)), yScale);
        draw.fillRect(convert(point.x + 1, "x") - (int)(xScale * (5.0 / 33)), convert(point.y, "y"), (int)(xScale * (5.0 / 33)), yScale);

        draw.setColor(topColor);
        draw.fillRect(convert(point.x, "x") + (int)(xScale * (5.0 / 33)), convert(point.y, "y"), xScale - (int)(xScale * (10.0 / 33)), (int)(yScale * (5.0 / 33)));

        draw.setColor(bottomColor);
        draw.fillRect(convert(point.x, "x") + (int)(xScale * (5.0 / 33)), convert(point.y + 1, "y") - (int)(yScale * (5.0 / 33)), xScale - (int)(xScale * (10.0 / 33)), (int)(yScale * (5.0 / 33)));

        draw.setColor(topColor);
        draw.fillPolygon ( //upper left
                new int[] {
                        convert(point.x, "x"),
                        convert(point.x, "x") + (int)(xScale * (5.0 / 33)),
                        convert(point.x, "x") + (int)(xScale * (5.0 / 33))
                },
                new int[] {
                        convert(point.y, "y"),
                        convert(point.y, "y"),
                        convert(point.y, "y") + (int)(yScale * (5.0 / 33))
                },
                3
        );

        draw.setColor(topColor);
        draw.fillPolygon ( //upper right
                new int[] {
                        convert(point.x + 1, "x") - 1,
                        convert(point.x + 1, "x") - 1 - (int)(yScale * (5.0 / 33)),
                        convert(point.x + 1, "x") - 1 - (int)(yScale * (5.0 / 33))
                },
                new int[] {
                        convert(point.y, "y"),
                        convert(point.y, "y"),
                        convert(point.y, "y") + (int)(yScale * (5.0 / 33))
                },
                3
        );

        draw.setColor(bottomColor);
        draw.fillPolygon ( //lower left
                new int[] {
                        convert(point.x, "x"),
                        convert(point.x, "x") + (int)(xScale * (5.0 / 33)),
                        convert(point.x, "x") + (int)(xScale * (5.0 / 33))
                },
                new int[] {
                        convert(point.y + 1, "y"),
                        convert(point.y + 1, "y"),
                        convert(point.y + 1, "y") - 1 - (int)(yScale * (5.0 / 33))
                },
                3
        );

        draw.setColor(bottomColor);
        draw.fillPolygon ( //lower right
                new int[] {
                        convert(point.x + 1, "x") - 1,
                        convert(point.x + 1, "x") - 1 - (int)(yScale * (5.0 / 33)),
                        convert(point.x + 1, "x") - 1 - (int)(yScale * (5.0 / 33))
                },
                new int[] {
                        convert(point.y + 1, "y"),
                        convert(point.y + 1, "y"),
                        convert(point.y + 1, "y") - 1 - (int)(yScale * (5.0 / 33))
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
