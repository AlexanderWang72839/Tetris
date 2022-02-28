import javax.swing.*;

public class Main {
    public static  void main(String[] args) {
        JFrame window = new JFrame();
        Game game = new Game();
        KeyListener listener = new KeyListener();

        final int FPS = 60;
        int currentFrame = 0;

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(game);
        window.setTitle("Tetris");
        window.addKeyListener(listener);
        window.setVisible(true);
        window.pack();

        while (true) {
            currentFrame++;
            game.nextFrame();
            try {
                Thread.sleep(1000/FPS);
            } catch (Exception e) {}
        }

        //Game.stopThread();
    }
}
