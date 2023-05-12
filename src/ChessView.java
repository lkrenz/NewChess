import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.awt.*;
public class ChessView extends JFrame implements MouseListener, MouseMotionListener {
    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600,
                                BOARD_DIMENSIONS = 400;
    private final Image boardImage;
    private final Chess game;
    private int moveX;
    private int moveY;
    private int moveToX;
    private int moveToY;
    private int boardStatus;
    private boolean needPromotion;
    private boolean gameOver;

    // Creates a new window instance and sets phase variables to false
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

    // Draws the board
    public void drawBoard(Graphics g) {
        g.drawImage(boardImage, 200, 100, BOARD_DIMENSIONS, BOARD_DIMENSIONS, this);
    }

    // Wipes the screen and draws the board and pieces or win display screen
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
            g.setColor(Color.black);
            g.setFont(g.getFont().deriveFont(100f));
            g.drawString(winner + " wins!", 50, 300);
        }
    }

    // Activates promotion phase of the game
    public void setNeedPromotion(boolean needPromotion) {
        this.needPromotion = needPromotion;
    }



    // Only activates if a promotion is needed to continue
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

    // Inputs the starting point of the player's move
    @Override
    public void mousePressed(MouseEvent e) {
        if (!needPromotion) {
            this.moveX = e.getX();
            this.moveY = e.getY();
        }
    }

    // Takes the end of the user's move then checks if it is promotion
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
        return;
    }

    // Activates the game over phase
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        return;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        return;
    }

    public int getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(int boardStatus) {
        this.boardStatus = boardStatus;
    }
}
