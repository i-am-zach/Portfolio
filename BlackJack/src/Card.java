import javax.swing.*;
import java.awt.*;

public class Card
    {
    String rank;
    String suit;
    Image image;
    ImageIcon imageIcon;
    Icon icon;

    /**
     * Constructor for Card
     * @Param rank, the card's rank i.e. Jack or Eight
     * @Param suit, the card's suit
     */
    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;

        try{
            int value = Integer.parseInt(rank);
            if(value == 1){
                this.rank = "Ace";
            } else if(value == 2){
                this.rank = "Two";
            } else if(value == 3){
                this.rank = "Three";
            } else if(value == 4){
                this.rank = "Four";
            } else if(value == 5){
                this.rank = "Five";
            } else if(value == 6){
                this.rank = "Six";
            } else if(value == 7){
                this.rank = "Seven";
            } else if(value == 8){
                this.rank = "Eight";
            } else if(value == 9){
                this.rank = "Nine";
            } else if(value == 10){
                this.rank = "Ten";
            } else if(value == 11){
                this.rank = "Jack";
            } else if(value == 12){
                this.rank = "Queen";
            } else if(value == 13){
                this.rank = "King";
            }

        } catch(Exception ex) {

        }

        this.suit = this.suit.substring(0,1).toUpperCase() + this.suit.substring(1).toLowerCase();
        this.rank = this.rank.substring(0,1).toUpperCase() + this.rank.substring(1).toLowerCase();
        if(!this.suit.substring(suit.length()-1, suit.length()).equals("s")){
            this.suit+= "s";
        }

        imageIcon = new ImageIcon(getClass().getResource(getFileName()));
        image = imageIcon.getImage();
        icon = imageIcon;

    }
    /**
     * Getter method for the file name of the card's image
     *
     * @Return, the file's name
     */
    public String getFileName(){
        int value = this.getValue();
        String fName = "cardImages/";

        if(rank.equals("Ace")){
            fName += "A";
        } else if(value == 2) {
            fName += "2";
        } else if(value == 3) {
            fName += "3";
        }  else if(value == 4) {
            fName += "4";
        } else if(value == 5) {
            fName += "5";
        } else if(value == 6) {
            fName += "6";
        } else if(value == 7) {
            fName += "7";
        }  else if(value == 8) {
            fName += "8";
        } else if(value == 9) {
            fName += "9";
        } else if(this.rank.equals("Ten")) {
            fName += "10";
        } else if(this.rank.equals("Jack")){
            fName += "J";
        } else if(this.rank.equals("Queen")){
            fName += "Q";
        } else if(this.rank.equals("King")){
            fName += "K";
        }

        if(suit.equals("Hearts")){
            fName+="H";
        } else if(suit.equals("Diamonds")) {
            fName+="D";
        } else if(suit.equals("Spades")){
            fName+="S";
        } else if(suit.equals("Clubs")) {
            fName+="C";
        }

        fName+=".png";

        if(rank.equalsIgnoreCase("Null")){
            fName = "cardImages/red_back.png";
        }

        return fName;
    }

    /**
     * Getter method for the card's image
     *
     * @return the card's image
     */

public Image getImage(){
    return image;
}

public ImageIcon getImageIcon(){
    return imageIcon;
}

public Icon getIcon(){
    return icon;
}

    /**
     * Getter method for the card's rank
     *
     * @Return the card's rank
     */

    public String getRank(){
        return rank;
    }

    /**
     * Getter method for the card's suit
     *
     * @Return the card's suit
     */

    public String getSuit(){
        return suit;
    }


    /**
     * Turns the rank of a card into a value in the form of an integer
     *
     * @return the interger form of the rank
     * (Ace is defaulted to 11)
     */

    public int getValue(){
        if(rank.equalsIgnoreCase("Two") || rank.equalsIgnoreCase("2")){
            return 2;
        }
        else if(rank.equalsIgnoreCase("three") || rank.equalsIgnoreCase("3") ) {
            return 3;
        }
        else if(rank.equalsIgnoreCase("four") || rank.equalsIgnoreCase("4")) {
            return 4;
        }
        else if(rank.equalsIgnoreCase("five") || rank.equalsIgnoreCase("5") ) {
            return 5;
        }
        else if(rank.equalsIgnoreCase("six") || rank.equalsIgnoreCase("6")) {
            return 6;
        }
        else if(rank.equalsIgnoreCase("seven") || rank.equalsIgnoreCase("7") ) {
            return 7;
        }
        else if(rank.equalsIgnoreCase("eight") || rank.equalsIgnoreCase("8")) {
            return 8;
        }
        else if(rank.equalsIgnoreCase("nine") || rank.equalsIgnoreCase("9") ) {
            return 9;
        }
        else if(rank.equalsIgnoreCase("ten") || rank.equalsIgnoreCase("10")) {
            return 10;
        }
        else if(rank.equalsIgnoreCase("jack") || rank.equalsIgnoreCase("11") ) {
            return 10;
        }
        else if(rank.equalsIgnoreCase("queen") || rank.equalsIgnoreCase("12")) {
            return 10;
        }
        else if(rank.equalsIgnoreCase("king") || rank.equalsIgnoreCase("13") ) {
            return 10;
        }
        else if(rank.equalsIgnoreCase("ace") || rank.equalsIgnoreCase("1")){
            return 11;
        }
        return 0;
    }

    public void lowAce(){
        if(this.rank.equals("Ace")){

        }
    }

    /**
     * The Card class turned into a string
     *
     * @return the string
     */

    public String toString(){
        return rank + " of " + suit;
    }

    /**
     * Method that tells whether or not the card is an ace
     *
     * @return, true if ace, false if not an ace
     */

    public boolean isAce(){
        return this.rank.equalsIgnoreCase("Ace");
    }

    /**
     * The method to determine if two card classes are equal
     *
     * @param other, the other card to be compared to
     *
     * @return whether the card's rank and suit are equal
     */



    public boolean equals(Card other){
        return this.getRank().equalsIgnoreCase(other.getRank()) && this.getSuit().equalsIgnoreCase(other.getSuit());
    }
}
