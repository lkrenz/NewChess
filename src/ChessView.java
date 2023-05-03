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
    private Chess game;
    private int moveX;
    private int moveY;
    private int moveToX;
    private int moveToY;
    private int boardStatus;
    public ChessView(Image boardImage, Chess game) {
        this.game = game;
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.setTitle("Chess");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.boardImage = boardImage;
        boardStatus = 1;
        repaint();
    }

    public void drawBoard(Graphics g) {
        g.drawImage(boardImage, 200, 100, BOARD_DIMENSIONS, BOARD_DIMENSIONS, this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawBoard(g);
        game.drawPieces(g);
    }


    public Location getMove() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.moveX = e.getX();
        this.moveY = e.getY();
        System.out.println("clicked");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.moveToX = e.getX();
        this.moveToY = e.getY();
        if (!(moveX <= 600 && moveX >= 200) || !(moveToX <= 600 && moveToX >= 200) || !(moveY <= 500 && moveY >= 100) || !(moveToY <= 500 && moveToY >= 100)) {
            return;
        }
        moveX = (moveX - 200) / 50 - 1;
        moveToX = (moveToX - 200) / 50 - 1;
        moveY = (moveY - 200) / 50 - 1;
        moveToY= (moveToY - 200) / 50 - 1;
        game.move(new Location(moveY, moveX, moveToY, moveToX), boardStatus);
        System.out.println("move detected");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void moveMade() {
        if (boardStatus == 1) {
            boardStatus = 0;
        }
        else {
            boardStatus = 1;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
