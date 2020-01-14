import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends JFrame{
    public JPanel container;
    public JButton hitButton;
    public JButton standButton;
    public JButton doubleDownButton;
    public JPanel buttonBar;
    public JPanel actionBar;
    public JPanel pCardPanel;
    public JPanel dCardPanel;
    public JPanel infoPanel;
    public JLabel card1;
    public JLabel card2;
    public JLabel card3;
    public JLabel card4;
    public JLabel card5;
    public JLabel card6;
    public JLabel card7;
    public JLabel card8;
    public JLabel card9;
    public JLabel card10;
    public JLabel card11;
    public JLabel card12;
    public JLabel playerValueLabel;
    public JLabel dCard1;
    public JLabel dCard2;
    public JLabel dCard3;
    public JLabel dCard4;
    public JLabel dCard5;
    public JLabel dCard6;
    public JLabel dCard7;
    public JLabel dCard8;
    public JLabel dCard9;
    public JLabel dCard10;
    public JLabel dCard11;
    public JLabel dCard12;
    public JLabel dealerValueLabel;
    public JLabel playerNameLabel;
    public JLabel playerBalanceLabel;
    public JLabel playerNameLabel2;
    public JLabel dealerLabel;
    public JLabel playerDefaultLabel;
    public JLabel balanceDefaultLabel;

    private JLabel[] cards = {card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12};
    private int cardIndex = 2;

    private JLabel[] dCards = {dCard1, dCard2, dCard3, dCard4, dCard5, dCard6, dCard7, dCard8, dCard9, dCard10, dCard11, dCard12};
    private int dCardIndex = 1;

    final public static int cardWidth = 120;
    final public static int cardHeight = 200;

    private Dealer dealer;
    private Player player;

    private boolean playerWon = false;

    private String name;
    private double bet = 50, balance = 1000;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    protected Clip sound;
    protected boolean soundPlaying = false;

    public static void main(String[] args) {
        new IntroScreen();
        /*Game game = new Game("Zach", 1000.0);
        game.setBounds(200, 100, 800, 400);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setExtendedState(JFrame.MAXIMIZED_BOTH);
        game.setUndecorated(true);
        game.setTitle("BlackJack");
        game.setVisible(true); */

    }

    /**
     * Parameterized constructor for the game class
     *
     * @param theName the Player's name, will be displayed on the Frame
     * @param theBalance the Player's balance, this will be kept track of in the data.txt file along with displayed on the left corner
     * @param theBet the player's initial bet
     */

    public Game(String theName, Double theBalance, double theBet){
        initializeSound();
        playSound();

        name = theName;
        balance = theBalance;
        System.out.println("Name: " + name);
        playerNameLabel.setText(name);
        playerNameLabel2.setText(name);
        playerNameLabel2.setForeground(Color.WHITE);
        playerNameLabel2.setPreferredSize(new Dimension(screenSize.width, 1));
        playerNameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameLabel2.setVerticalAlignment(SwingConstants.CENTER);

        playerBalanceLabel.setText("$" + Double.toString(balance));

        /// Button styling /////////////////////////////////////
        hitButton.setForeground(Color.WHITE);
        standButton.setForeground(Color.WHITE);
        doubleDownButton.setForeground(Color.WHITE);
        //////////////////////////////////////////////////////

        /*//Prompt for name //////////////////////////////////////////////////////
        name = JOptionPane.showInputDialog(null, "What's your name?", "BlackJack", JOptionPane.OK_CANCEL_OPTION);
        playerNameLabel.setText(name);
        giveBalance();
        if(balance <= 0){
            JOptionPane.showMessageDialog(null, "It looks like you ran out of cash\nYou're gonna have to work it off(WIP)");
            balance+= 1000;

        }
        ///////////////////////////////////////////////////////////////////////////////

        playerNameLabel2.setText(name);
        playerNameLabel2.setForeground(Color.WHITE);
        playerNameLabel2.setPreferredSize(new Dimension(screenSize.width, 1));
        playerNameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameLabel2.setVerticalAlignment(SwingConstants.CENTER);

        dealerLabel.setForeground(Color.WHITE);
        dealerLabel.setPreferredSize(new Dimension(screenSize.width, 1));
        dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dealerLabel.setVerticalAlignment(SwingConstants.CENTER); */

        /*//Prompt for bet ///////////////////////////////////////////////////////////////
        try{
            bet = Double.parseDouble(JOptionPane.showInputDialog("Enter bet ($)"));
        } catch (Exception e){
            bet = Double.parseDouble(JOptionPane.showInputDialog("Enter bet ($)"));
        }*/
        bet = theBet;
        saveFile();

        balance -= bet;
        /////////////////////////////////////////////////////////////////////////////////

        //Sets up blackjack components ////////////////////////////////////////////////
        dealer = new Dealer();
        player = new Player(new Hand());

        dealer.addPlayer(player);
         //Deals first cards
        dealer.shuffle();
        dealer.deal(player);
        dealer.hit();
        dealer.deal(player);
        dealer.hit();

        
        //Check for blackjack///////////////////////////////////
        if(player.getValue() == 21){
            for(int i = 0; i < player.getHand().getCards().size(); i++){
                cards[i].setBorder(new LineBorder(Color.GREEN,3, true));
            }
        }
        if(dealer.getHand().getValue() == 21 && dealer.getHand().getCards().size() == 2){
            for(int i = 0; i < player.getHand().getCards().size(); i++){
                cards[i].setBorder(new LineBorder(Color.GREEN,3, true));
            }
        }
        ///////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////

        //Adding cards to the panel ////////////////////////////////////////////////
        for(int i = 0; i < 2; i++){
            Card temp  = player.getHand().getCards().get(i);
            ImageIcon icon = new ImageIcon(getClass().getResource(temp.getFileName()));
            icon = resizeIcon(icon, cardWidth, cardHeight);
            Icon newIcon = icon;
            cards[i].setIcon(icon);
        }

        for(int i = 0; i < dCards.length; i++){
            dCards[i].setText("");
        }

        Card tempCard = dealer.getHand().getCards().get(0);
        ImageIcon icon2 = new ImageIcon(getClass().getResource(tempCard.getFileName()));
        icon2 = resizeIcon(icon2, cardWidth, cardHeight);
        Icon newIcon2 = icon2;
        dCards[0].setIcon(icon2);

        ImageIcon backCard = new ImageIcon(getClass().getResource("cardImages/red_back.png"));
        backCard = resizeIcon(backCard, cardWidth, cardHeight);
        Icon icon3 = backCard;
        dCards[1].setIcon(icon3);
        //////////////////////////////////////////////////////////////

        //Visual balance displayer /////////////////////////////////////////////////
        playerBalanceLabel.setText("$" + Double.toString(balance));
        playerValueLabel.setText("Value: " + player.getHand().getValue());
        ////////////////////////////////////////////////////////////////////////

        //Hit button event /////////////////////////////////////////////
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Checks if the deck is at a normal amount ////////////////////////////
                if(dealer.getShoe().numCards() < 24){
                    dealer.getShoe().reshuffle();
                }
                if(e.getSource().equals(hitButton)){
                    doubleDownButton.setEnabled(false);

                    //Checks if the player is allowed to hit
                    if(player.getHand().getValue() < 21){
                        //Player dealt
                        Card drawn = dealer.deal(player);
                        cardFlipNoise();
                        //Updates value display
                        playerValueLabel.setText("Value: " + player.getHand().getValue());
                        //Index of card (number of cards - 1)
                        cardIndex = player.getHand().getCards().size()-1;
                        //Creates and sets icon ////////////////////////////
                        ImageIcon icon = new ImageIcon(Game.this.getClass().getResource(drawn.getFileName()));
                        icon = resizeIcon(icon, cardWidth, cardHeight);
                        Icon newIcon = icon;
                        cards[cardIndex].setIcon(newIcon);
                        ///////////////////////////////////////////////////////

                    //Visual components for special cases (blackjack/busting)////////////////////
                    if(player.getHand().getValue() > 21){
                        for(int i = 0; i < player.getHand().getCards().size(); i++){
                            cards[i].setBorder(new LineBorder(Color.RED,3, true));
                        }
                        standButton.doClick();
                    }
                    } else if(player.hasBlackJack()) {
                        for(int i = 0; i < player.getHand().getCards().size(); i++){
                            cards[i].setBorder(new LineBorder(Color.GREEN,3, true));
                        }
                    }
                    //////////////////////////////////////////////////////////////
                }
            }
        });

        // Stand button event ////////////////////////////////////////////////////////////
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(standButton)){

                    //Disables other buttons
                    standButton.setEnabled(false);
                    hitButton.setEnabled(false);
                    doubleDownButton.setEnabled(false);

                    //Creates and sets Icon
                    ImageIcon ic = new ImageIcon(getClass().getResource(dealer.getHand().getCards().get(1).getFileName()));
                    ic = resizeIcon(ic, cardWidth, cardHeight);
                    Icon newIcon = ic;
                    dCards[1].setIcon(newIcon);

                    //Hits while the dealer's less than 17, updates the Card Icons
                    while(dealer.getHand().getValue() < 17){
                        Card drawn = dealer.hit();
                        cardFlipNoise();
                        int cardIndex = dealer.getHand().getCards().size()-1;

                        ImageIcon icon = new ImageIcon(getClass().getResource(drawn.getFileName()));
                        icon = resizeIcon(icon, cardWidth, cardHeight);
                        Icon newIc = icon;
                        dCards[cardIndex].setIcon(newIc);
                    }

                    //Visual components for special cases /////////////////////////////////////

                    if(dealer.getHand().getValue() == 21){
                        dealer.getHand().getCards().trimToSize();
                        for(int i = 0; i < dealer.getHand().getCards().size(); i++){
                            dCards[i].setBorder(new LineBorder(Color.GREEN,3, true));
                        }
                    } else if(dealer.getHand().getValue() > 21){
                        dealer.getHand().getCards().trimToSize();
                        for(int i = 0; i < dealer.getHand().getCards().size(); i++){
                            dCards[i].setBorder(new LineBorder(Color.RED,3, true));
                        }
                    }
                    //Updates value aid
                    dealerValueLabel.setText("Value: " + dealer.getHand().getValue());
                    playerValueLabel.setText("Value: " + player.getHand().getValue());
                    //Calls method to update boolean that contains winner
                    determineWinner();
                    //Calls method to give winnings
                    giveWinnings();
                    //Calls dealer method to reset the hands
                    dealer.roundOver();
                    //Calls method to reset game for next round
                    reset();
                }
            }
        });
        //Double Down Button//////////////////////////////////////////////////////
        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource().equals(doubleDownButton)){
                    System.out.println("You clicked double down");
                    if(bet <= balance){
                        balance -= bet;
                        bet += bet;
                        playerBalanceLabel.setText("$" + balance);
                        dealer.deal(player);
                        cardFlipNoise();
                        playerValueLabel.setText("Value: " + player.getHand().getValue());
                        //Create and set image to card
                        ImageIcon ic = new ImageIcon(getClass().getResource(player.getHand().getCards().get(2).getFileName()));
                        ic = resizeIcon(ic, cardWidth, cardHeight);
                        Icon newIcon = ic;
                        cards[2].setIcon(newIcon);

                        standButton.doClick();
                    } else {
                        JOptionPane.showMessageDialog(null, "You don't have enough money to double down", "Error", JOptionPane.ERROR_MESSAGE);
                        doubleDownButton.setEnabled(false);
                    }
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////

        //Adds the panel with all the components to the frame
        add(container);
    }

    /**
     * Method to determine the winner of the round once the stand button is pressed or forcibly pressed (busting)
     *
     * @return whether of not the player won the round
     */

    public boolean determineWinner(){
        int playerScore = player.getHand().getValue();
        int dealerScore = dealer.getHand().getValue();
        int numPlayerCards = player.getHand().getCards().size();
        int numDealerCards = dealer.getHand().getCards().size();

        System.out.println("Player score: " + playerScore);
        System.out.println("Dealer score: " + dealerScore);

        //Player has blackjack
        if(playerScore == 21 && numPlayerCards == 2){
            playerWon = true;
        }
        //Dealer has blackjack
        else if(dealerScore == 21 && numDealerCards == 21){
            playerWon = false;
        }
        //Player bust
        else if(playerScore > 21){
            playerWon = false;
        }
        //Dealer bust
        else if(dealerScore > 21){
            playerWon = true;
        }
        //Player has five card charlie
        else if(playerScore <= 21 && numPlayerCards >= 5){
            playerWon = true;
        }
        //Dealer has five card charlie
        else if(dealerScore <= 21 && numDealerCards >= 5){
            playerWon = false;
        }
        //Player has higher score than dealer
        else if(playerScore >= dealerScore){
            playerWon = true;
        }
        //Dealer has higher score than player
        else if(dealerScore > playerScore) {
            playerWon = false;
        }
        System.out.println("Player won: " + playerWon);
        return playerWon;
    }

    /**
     * Method that resizes an imageicon
     *
     * @param icon the imageicon to resize
     * @param width the new width
     * @param height the new height
     * @return the resized imageicon
     */

    public ImageIcon resizeIcon(ImageIcon icon, int width, int height){
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    /**
     * Method that gives winnings to the player after each round
     */

    public void giveWinnings(){
        if(playerWon == true){
            if(player.hasBlackJack()){
                bet = bet * (3/2);
                JOptionPane.showMessageDialog(null, "You got Blackjack! 3:2 bet modifier");
            }
            balance+= bet*2;
            playerBalanceLabel.setText("$" + Double.toString(balance));
            JOptionPane.showMessageDialog(null, "You won! Earning: $" + bet*2);

        } else {
            playerBalanceLabel.setText("$" + Double.toString(balance));
            JOptionPane.showMessageDialog(null, "You lost $" + bet);
        }
    }

    /**
     * Method that resets the round and prepares the GUI components for the next round
     *
     */

    public void reset(){
        //Checks for invalid balance
        if(balance <= 1){
            JOptionPane.showMessageDialog(null, "It looks like you are low on cash, here take this\n*you gain 100 dollars*");
            balance+=100;
            playerBalanceLabel.setText("$" + Double.toString(balance));
        }

        //Resets card components
        saveFile();
        for(int i = 0; i < cards.length; i++){
            cards[i].setIcon(null);
            cards[i].setText("");
            cards[i].setBorder(null);
            dCards[i].setIcon(null);
            dCards[i].setText("");
            dCards[i].setBorder(null);
        }

        /*//Ask for new bet
        try{
            bet = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter bet ($)"));
            if(bet > balance){
                bet = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter bet (cannot be more than balance)"));
            }
        } catch(Exception e){
            int done = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if(done == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "Goodbye");
                saveFile();
                super.dispose();
            } else {
                try {
                    bet = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter bet (cannot be more than balance)"));
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Goodbye");
                    saveFile();
                    super.dispose();
                }
            }
        } */
        bet = 0;
        new BettingScreen(this);
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        doubleDownButton.setEnabled(true);

        player.getHand().getCards().clear();

        dealer.deal(player);
        dealer.hit();
        dealer.deal(player);
        dealer.hit();

        for(int i = 0; i < 2; i++){
            Card tempCard = player.getHand().getCards().get(i);
            ImageIcon ic = new ImageIcon(getClass().getResource(tempCard.getFileName()));
            ic = resizeIcon(ic, cardWidth, cardHeight);
            Icon newIcon = ic;
            cards[i].setIcon(ic);
        }

        for(int i = 0; i < 2; i++){
            Card tempCard = dealer.getHand().getCards().get(i);
            ImageIcon ic;
            if(i == 0) {
                ic = new ImageIcon(getClass().getResource(tempCard.getFileName()));
            } else {
                ic = new ImageIcon(getClass().getResource("cardImages/red_back.png"));
            }
            ic = resizeIcon(ic, cardWidth, cardHeight);
            Icon newIcon = ic;
            dCards[i].setIcon(ic);
        }

        playerBalanceLabel.setText("$" + Double.toString(balance));
        playerValueLabel.setText("Value: " + player.getHand().getValue());
        dealerValueLabel.setText("Value: ??");
    }

    /**
     * Method that will save a players progress and move it to a txt file
     *  Uses scanner to add a player's name and balance into a string form and onto a document
     *  Uses the WriteOrAppendString class to move the data to the document
     */

    public void saveFile(){

        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File("src/data.txt"));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            System.out.println(lines);
        } catch (Exception e){

        }
        String[] arr = lines.toArray(new String[0]);
        String tempName;

        Boolean newPlayer = true;
        for(int i = 0; i < arr.length; i++){
            int index = 0;
            while(true){
                if(arr[i].substring(index, index + 1).matches("[0-9]")){
                    tempName = arr[i].substring(0, index);
                    break;
                }
                index++;
            }
            if(tempName.equals(name)){
                newPlayer = false;
                arr[i] = name + balance;
            }
        }
        if(newPlayer) {

            try {
                WriteOrAppendString fileWriter = new WriteOrAppendString();
                File f = new File("src/data.txt");
                fileWriter.write(name + balance, f);
            } catch (IOException e) {

            }
        } else {
            try{
                WriteOrAppendString fileWriter = new WriteOrAppendString();
                File f = new File("src/data.txt");
                fileWriter.refresh(arr, f);
            } catch (IOException e){

            }
        }
    }

    /**
     * Method that will set the balance to the selected player's name
     */

    public void giveBalance(){
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File("src/data.txt"));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            //System.out.println(lines);
        } catch (Exception e){

        }
        String[] arr = lines.toArray(new String[0]);
        for(int i = 0; i < arr.length; i++){
            String tempName;
            int index = 0;
            while(true){
                if(arr[i].substring(index, index + 1).matches("[0-9]")){
                    tempName = arr[i].substring(0, index);
                    break;
                }
                index++;
            }
            //System.out.println(tempName);
            if(name.equals(tempName)){
                String temp = arr[i];
                int nameLength = name.length();
                temp = temp.substring(nameLength);
                System.out.println("Balance: " + temp);
                JOptionPane.showMessageDialog(null, "Welcome back " + name + "!\nYour balance: " + temp);
                balance = Double.parseDouble(temp);
                break;
            }
        }
    }

    /**
     * Setter method to set the player's bet and remove that amount from the balance
     *
     * @param bet the new bet
     */

    public void setBet(double bet){
        this.bet += bet;
        balance -= bet;
        playerBalanceLabel.setText("$" + Double.toString(balance));
    }

    /**
     * Getter method for the player's name
     * @return the player's name
     */

    public String getName(){
        return name;
    }

    /**
     * Getter method for the player's balance
     * @return the player's balance
     */

    public double getBalance(){
        return balance;
    }

    /**
     * Helper method to intialize a clip with a casino white noise .wav file
     */

    public void initializeSound() {
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(getClass().getResource("casinoNoise.wav")));
        } catch (UnsupportedAudioFileException e1) {
            System.out.println(e1);
        } catch (IOException e2) {
            System.out.println(e2);
        } catch (LineUnavailableException e3) {
            System.out.println(e3);
        }
    }

    /**
     * Method that checks if the sound is already being played and will play it if it is not
     */

    public void playSound(){
        if(!soundPlaying) {
            sound.start();
            soundPlaying = true;
        }
    }

    /**
     * Method that checks if sound is playing and will stop it if it is playing
     */

    public void stopSound(){
        if(soundPlaying) {
            sound.stop();
            soundPlaying = false;
        }
    }

    /**
     * Method to play a card flipping sound
     */

    public void cardFlipNoise(){
        try {
            Clip audioSound = AudioSystem.getClip();
            audioSound.open(AudioSystem.getAudioInputStream(getClass().getResource("cardFlip.wav")));
            audioSound.start();
        } catch (UnsupportedAudioFileException e1) {
            System.out.println(e1);
        } catch (IOException e2) {
            System.out.println(e2);
        } catch (LineUnavailableException e3) {
            System.out.println(e3);
        }
    }

}
