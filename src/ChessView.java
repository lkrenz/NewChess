import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.awt.*;
public class ChessView extends JFrame implements MouseListener, MouseMotionListener {
    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600,
                                BOARD_DIMENSIONS = 400;
    private Image boardImage;
    public ChessView(Image boardImage) {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.boardImage = boardImage;
        repaint();
    }

    public void drawBoard(Graphics g) {
        g.drawImage(boardImage, 200, 100, BOARD_DIMENSIONS, BOARD_DIMENSIONS, this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawBoard(g);
    }

    public Location getMove() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
