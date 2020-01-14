public class Player
{
    private Hand hand;
    private String name;
    private Shoe shoe;
    private double bankroll;
    private boolean hasShoe;

    /**
     * Paramiterized consturctor with name, bankroll and hand
     *
     * @param name, the player's name
     * @param bankroll, the player's bankroll
     * @param hand, the player's hand
     */

    public Player(String name, double bankroll, Hand hand){
        this.name = name;
        this.bankroll = bankroll;
        this.hand = hand;
    }

    /**
     * Hand paramitzered construstor
     *
     * @param hand, the player's hand
     */

    public Player(Hand hand){
        this.hand = hand;
    }

    public Hand getHand(){
        return hand;
    }

    /**
     * Adds a card the player's hand
     *
     * @param c, the player's card to be added
     *
     * @return bust, show's if they are able to continue
     */

    public boolean hit(Card c){
        if(!bust()){
            hand.addCard(c);
        }
        return bust();
    }


    /**
     * hit method to be used in tandom with addCard or a shoe
     */

    public Card hit(){
        if(hasShoe && !bust()){
            Card card = shoe.draw();
            hand.addCard(card);
            return card;
        }
        return new Card("Null", "Spades");
    }

    /**
     * Adds a card to the player's hand
     *
     * @param c, the card to be added
     */

    public void addCard(Card c){
        hand.addCard(c);
    }


    /**
     * Returns false to show they do not want to continue
     *
     * @return false ^^
     */

    public boolean stand(){
        return false;
    }

    /**
     * @return whether of not the player is above 21 cards
     * true if over 21
     * false if 21 or under
     */

    public boolean bust(){
        return hand.getValue()>21;
    }

    /**
     * Attaches a shoe to the deck
     */

    public void setShoe(Shoe shoe){
        hasShoe = true;
        this.shoe = shoe;
    }

    /**
     * @return, the value of the player's hand
     */

    public int getValue(){
        return hand.getValue();
    }

    public boolean hasBlackJack(){
        return this.getValue() == 21 && hand.numCards() == 2;
    }

    public boolean canDoubleDown(){
        return hand.numCards() == 2 && hand.getCards().get(0).getRank().equals(hand.getCards().get(1).getRank());
    }

    public String toString(){
        String cheese = "";
        cheese += "Name: " + name;
        cheese += "\tBankroll: " + bankroll;
        cheese+= "\nHand: " + hand;
        cheese+= "\nValue: " + getValue() + "\tBlackjack: " + hasBlackJack() + "\tDouble down: " + canDoubleDown();
        return cheese;
    }
}
