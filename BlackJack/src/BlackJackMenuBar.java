import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class BlackJackMenuBar extends JMenuBar {

    private Color darkRed = new Color(65, 1, 5);
    private Color darkGreen = new Color(16, 65, 51);
    private Color darkBlue = new Color(23, 51, 131);
    private Color grey = new Color(61,65,63);
    private Color gold = new Color(185, 149, 0);
    private Color darkGold = new Color(123, 90, 0);
    private Color darkestGold = new Color(87, 66,0);


    /**
     * BlackJackMenuBar constructor
     * @param game the Game class that the menubar will be added to
     */
    public BlackJackMenuBar(Game game){
        //Build game menu
        JMenu gameMenu = new JMenu();
        gameMenu.setText("Game");
        JMenuItem exitItem = new JMenuItem();
        exitItem.setText("Close");
        JMenuItem saveItem = new JMenuItem();
        saveItem.setText("Save");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.saveFile();
                game.dispose();
            }
        });
        gameMenu.add(exitItem);
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.saveFile();
            }
        });
        gameMenu.add(saveItem);
        add(gameMenu);

        //Build color menu
        JMenu colorMenu = new JMenu();
        colorMenu.setText("Color Schemes");
        JMenuItem greenItem = new JMenuItem("Green");
        colorMenu.add(greenItem);
        JMenuItem redItem = new JMenuItem("Red");
        colorMenu.add(redItem);
        JMenuItem blueItem = new JMenuItem("Blue");
        colorMenu.add(blueItem);
        JMenuItem darkItem = new JMenuItem("Midnight");
        colorMenu.add(darkItem);

        greenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pCardPanel.setBackground(darkGreen);
                game.dCardPanel.setBackground(darkGreen);
                game.buttonBar.setBackground(darkRed);
                game.infoPanel.setBackground(darkRed);
                game.actionBar.setBackground(grey);
                game.hitButton.setBackground(grey);
                game.standButton.setBackground(grey);
                game.doubleDownButton.setBackground(grey);
                game.playerDefaultLabel.setForeground(Color.WHITE);
                game.playerNameLabel.setForeground(Color.WHITE);
                game.balanceDefaultLabel.setForeground(Color.WHITE);
                game.playerBalanceLabel.setForeground(Color.WHITE);

                game.playerNameLabel2.setForeground(Color.WHITE);
                game.playerValueLabel.setForeground(Color.WHITE);
                game.dealerValueLabel.setForeground(Color.WHITE);
                game.dealerLabel.setForeground(Color.WHITE);
            }
        });
        redItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pCardPanel.setBackground(darkRed);
                game.dCardPanel.setBackground(darkRed);
                game.buttonBar.setBackground(darkGreen);
                game.infoPanel.setBackground(darkGreen);
                game.actionBar.setBackground(grey);
                game.hitButton.setBackground(grey);
                game.standButton.setBackground(grey);
                game.doubleDownButton.setBackground(grey);
                game.playerDefaultLabel.setForeground(Color.WHITE);
                game.playerNameLabel.setForeground(Color.WHITE);
                game.balanceDefaultLabel.setForeground(Color.WHITE);
                game.playerBalanceLabel.setForeground(Color.WHITE);

                game.playerNameLabel2.setForeground(Color.WHITE);
                game.playerValueLabel.setForeground(Color.WHITE);
                game.dealerValueLabel.setForeground(Color.WHITE);
                game.dealerLabel.setForeground(Color.WHITE);
            }
        });
        blueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pCardPanel.setBackground(darkBlue);
                game.dCardPanel.setBackground(darkBlue);
                game.infoPanel.setBackground(darkestGold);
                game.buttonBar.setBackground(darkestGold);
                game.actionBar.setBackground(new Color(40, 130, 245));
                game.hitButton.setBackground(grey);
                game.standButton.setBackground(grey);
                game.doubleDownButton.setBackground(grey);

                game.playerDefaultLabel.setForeground(Color.WHITE);
                game.playerNameLabel.setForeground(Color.WHITE);
                game.balanceDefaultLabel.setForeground(Color.WHITE);
                game.playerBalanceLabel.setForeground(Color.WHITE);

                game.playerNameLabel2.setForeground(Color.WHITE);
                game.playerValueLabel.setForeground(Color.WHITE);
                game.dealerValueLabel.setForeground(Color.WHITE);
                game.dealerLabel.setForeground(Color.WHITE);


            }
        });
        darkItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pCardPanel.setBackground(Color.BLACK);
                game.dCardPanel.setBackground(Color.BLACK);
                game.infoPanel.setBackground(Color.BLACK);
                game.buttonBar.setBackground(Color.BLACK);

                game.actionBar.setBackground(darkGold);
                game.hitButton.setBackground(darkGold);
                game.standButton.setBackground(darkGold);
                game.doubleDownButton.setBackground(darkGold);
                game.playerDefaultLabel.setForeground(darkGold);
                game.playerNameLabel.setForeground(darkGold);
                game.balanceDefaultLabel.setForeground(darkGold);
                game.playerBalanceLabel.setForeground(darkGold);

                game.playerNameLabel2.setForeground(Color.LIGHT_GRAY);
                game.playerValueLabel.setForeground(Color.LIGHT_GRAY);
                game.dealerValueLabel.setForeground(Color.LIGHT_GRAY);
                game.dealerLabel.setForeground(Color.LIGHT_GRAY);
            }
        });

        add(colorMenu);

        //Sound Option
        JMenu sound = new JMenu("Sound");
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(e -> game.stopSound());
        sound.add(pause);
        JMenuItem resume = new JMenuItem("Resume");
        resume.addActionListener(e->game.playSound());
        sound.add(resume);
        add(sound);



    }

}
