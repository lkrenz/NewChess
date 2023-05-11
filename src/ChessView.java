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
    private boolean needPromotion;
    private boolean gameOver;
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
        needPromotion = false;
        gameOver = false;
        repaint();
    }

    public void drawBoard(Graphics g) {
        g.drawImage(boardImage, 200, 100, BOARD_DIMENSIONS, BOARD_DIMENSIONS, this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawBoard(g);
        game.drawPieces(g);
        if (needPromotion) {
            game.getBoard().drawOptions(g, boardStatus);
        }
        if (gameOver) {
            String winner = game.getWinner();
            g.drawRect(0,0,1000,1000);
            g.drawString(winner + " wins!", WINDOW_WIDTH - (winner.length() + 6) / 2, WINDOW_HEIGHT / 2);
        }
    }

    public void setNeedPromotion(boolean needPromotion) {
        this.needPromotion = needPromotion;
    }

    public Location getMove() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (needPromotion) {
            if (e.getX() >= 700 && e.getX() <= 750 && e.getY() >= 100 && e.getY() <= 400) {
                int choice = (e.getY() - 100) / 50 + 1;
                Location move = new Location(moveY, moveX, moveToY, moveToX);
                game.getBoard().promote(move, choice, boardStatus);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!needPromotion) {
            this.moveX = e.getX();
            this.moveY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (!needPromotion) {
                this.moveToX = e.getX();
                this.moveToY = e.getY();
                if (!(moveX <= 600 && moveX >= 200) || !(moveToX <= 600 && moveToX >= 200) || !(moveY <= 500 && moveY >= 100) || !(moveToY <= 500 && moveToY >= 100)) {
                    return;
                }
                moveX = (moveX - 200) / 50;
                moveToX = (moveToX - 200) / 50;
                moveY = (moveY - 100) / 50;
                moveToY = (moveToY - 100) / 50;
                Location move = new Location(moveY, moveX, moveToY, moveToX);
                if (game.isPromotion(move)) {
                    needPromotion = true;
                    repaint();
                }
                else {
                    game.move(new Location(moveY, moveX, moveToY, moveToX), boardStatus);
                }

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }
//    Need to share this info from the back end to here
//    public int getPromotion(int color) {
//        this.needPromotion = true;
//
//    }


    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
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

    public int getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(int boardStatus) {
        this.boardStatus = boardStatus;
    }
}
