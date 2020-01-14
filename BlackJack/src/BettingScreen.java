import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BettingScreen extends JFrame{
    private JPanel container;
    private JPanel chipPanel;
    private JLabel redChip;
    private JLabel blueChip;
    private JLabel greenChip;
    private JLabel blackChip;
    private JLabel whiteChip;
    private JLabel betLabel;
    private JLabel topLabel;
    private JButton submitButton;
    private JButton resetButton;

    private double bet = 0;
    private final int cr = 75;

    private String name;
    private double balance;

    public static void main(String[] args) {
        new BettingScreen("Zach", 1000.0);
    }

    /**
     * Constructor for BettingScreen
     * @param game The game class that will get connected to the BettingScreen Class
     */

    public BettingScreen(Game game){
        super("Place bet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,280);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        add(container);

        this.name = game.getName();
        this.balance = game.getBalance();
        game.setVisible(false);

        JLabel[] chips = {whiteChip, redChip, blueChip, greenChip, blackChip};
        resizeIcon(whiteChip, cr, cr, "chipImages/whiteChip.png");
        whiteChip.setToolTipText("$1");
        resizeIcon(redChip, cr, cr, "chipImages/redChip.png");
        redChip.setToolTipText("$5");
        resizeIcon(blueChip, cr, cr, "chipImages/blueChip.png");
        blueChip.setToolTipText("$10");
        resizeIcon(greenChip, cr, cr, "chipImages/greenChip.png");
        greenChip.setToolTipText("$25");
        resizeIcon(blackChip, cr, cr, "chipImages/blackChip.png");
        blackChip.setToolTipText("$100");


        for(int i = 0; i < chips.length; i++){
            chips[i].setText("");
            chips[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getSource().equals(whiteChip)){
                        if(1 + bet <= balance) {
                            bet += 1;
                        }
                    } else if(e.getSource().equals(redChip)){
                        if(5 + bet <= balance) {
                            bet += 5;
                        }
                    } else if(e.getSource().equals(blueChip)){
                        if(10 + bet <= balance) {
                            bet += 10;
                        }
                    } else if(e.getSource().equals(greenChip)){
                        if(25 + bet <= balance) {
                            bet += 25;
                        }
                    } else if(e.getSource().equals(blackChip)){
                        if(100 + bet <= balance) {
                            bet += 100;
                        }
                    }
                    betLabel.setText("Bet: $" + Double.toString(bet));
                }
            });
        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bet > 0) {
                    dispose();
                    game.setBet(bet);
                    game.setVisible(true);
                } else {
                    betLabel.setText("You must enter a bet");
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bet = 0;
                betLabel.setText("Bet:");
            }
        });
        setVisible(true);
    }

    /**
     * Constructor that is independent of a game class (used before the game frame is created)
     *
     * @param name the Player's name (needs to be hosted for the game construction)
     * @param balance the player's balance(needs to be hosted for the game construction)
     */

    public BettingScreen(String name, double balance){
        super("Place bet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,280);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        add(container);

        this.name = name;
        this.balance = balance;

        JLabel[] chips = {whiteChip, redChip, blueChip, greenChip, blackChip};
        resizeIcon(whiteChip, cr, cr, "chipImages/whiteChip.png");
        whiteChip.setToolTipText("$1");
        resizeIcon(redChip, cr, cr, "chipImages/redChip.png");
        redChip.setToolTipText("$5");
        resizeIcon(blueChip, cr, cr, "chipImages/blueChip.png");
        blueChip.setToolTipText("$10");
        resizeIcon(greenChip, cr, cr, "chipImages/greenChip.png");
        greenChip.setToolTipText("$25");
        resizeIcon(blackChip, cr, cr, "chipImages/blackChip.png");
        blackChip.setToolTipText("$100");


        for(int i = 0; i < chips.length; i++){
            chips[i].setText("");
            chips[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getSource().equals(whiteChip)){
                        if(1 + bet <= balance) {
                            bet += 1;
                        }
                    } else if(e.getSource().equals(redChip)){
                        if(5 + bet <= balance) {
                            bet += 5;
                        }
                    } else if(e.getSource().equals(blueChip)){
                        if(10 + bet <= balance) {
                            bet += 10;
                        }
                    } else if(e.getSource().equals(greenChip)){
                        if(25 + bet <= balance) {
                            bet += 25;
                        }
                    } else if(e.getSource().equals(blackChip)){
                        if(100 + bet <= balance) {
                            bet += 100;
                        }
                    }
                    betLabel.setText("Bet: $" + Double.toString(bet));
                }
            });
        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bet > 0) {
                    dispose();

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    double width = screenSize.getWidth();
                    double height = screenSize.getHeight();

                    Game game = new Game(name, balance, bet);
                    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    game.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    game.setUndecorated(true);
                    game.setTitle("BlackJack");
                    game.setJMenuBar(new BlackJackMenuBar(game));
                    game.setVisible(true);
                } else {
                    betLabel.setText("You must enter a bet");
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bet = 0;
                betLabel.setText("Bet:");
            }
        });
        setVisible(true);
    }

    /**
     * ImameIcon resizer method
     *
     * @param jl the JLabel to set the icon to
     * @param width the new width
     * @param height the new height
     * @param src the image source in string form
     */

    public void resizeIcon(JLabel jl, int width, int height, String src){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(src));
        Image img = imageIcon.getImage();
        Image newimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(newimg);
        jl.setIcon(icon);
    }

}
