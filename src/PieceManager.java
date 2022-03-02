import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PieceManager extends JComponent implements Runnable {
    Random rand = new Random();
    private Piece[] order = new Piece[4];

    public PieceManager() {
        for (int i = 0 ; i < order.length ; i++) {
            order[i] = new Piece(rand.nextInt(7) + 1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {


        for (int i = 1 ; i < order.length ; i++) {

        }
    }

    @Override
    public void run() {

    }
}
