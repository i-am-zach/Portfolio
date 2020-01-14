import java.util.ArrayList;
/**
 * Deck.java
 *
 * @author:
 * Assignment #:
 *
 * Brief Program Description:
 *
 *
 */
public class Deck{
    private ArrayList<Card> cards;
    private ArrayList<Card> discards;
    private int numCards, numDiscards;
    /**
     * Default constructor for the Deck class
     * Creates a Stack object of cards with all 52 cards in correct order
     */
    public Deck(){
        cards = new ArrayList<Card>();
        discards = new ArrayList<Card>();
        numCards = 0;
        numDiscards = 0;

        fillDeck(this.cards);
    }

    /**
     * Paramitizered constructor for a card class
     *
     * @Param createDeck, will fill the deck with 52 cards if true, else it will be left empty
     */

    public Deck(boolean createDeck){
        cards = new ArrayList<Card>();
        discards = new ArrayList<Card>();
        numCards = 0;
        numDiscards = 0;

        if(createDeck){
            fillDeck(this.cards);
        }

    }

    /**
     * Method that will add a card to the deck
     *
     * @param card, the card to be added
     */
    public void addCard(Card card){
        cards.add(card);
        numCards++;
    }

    /**
     * Adds a card to the deck at a specific index
     *
     * @Param index, the index to be added at
     * @Param card, the card to be added
     */

    public void addCard(int index, Card card){
        if(index < cards.size()){
            cards.add(index, card);
        } else {
            cards.add(card);
        }
        numCards++;
    }


    /**
     * Returns the top card from the deck and moves it to the discard pile
     */

    public Card draw(){
        discards.add(cards.get(0));
        numDiscards++;
        numCards--;
        return cards.remove(0);
    }

    public int findCard(Card c){
        return cards.indexOf(c);
    }

    /**
     * Returns the arraylist of cards
     *
     * @returns the arraylist of cards
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * Returns the arraylist of discards
     *
     * @returns the arraylist of discards
     */

    public ArrayList<Card> getDiscards(){
        return discards;
    }

    /**
     * Returns the numbr of cards in the deck
     *
     * @Return number of cards in the deck
     */

    public int numCards(){
        return cards.size();
    }

    /**
     * Returns the numbr of discards in the deck
     *
     * @Return number of discards in the deck
     */

    public int numDiscards(){
        return discards.size();
    }

    public void shuffle(){
        ArrayList<Integer> randNums = new ArrayList<Integer>();
        ArrayList<Card> copy = new ArrayList<Card>();
        for(int i = 0; i < cards.size(); i++){
            randNums.add(i);
        }
        while(randNums.size() > 0)
        {
            int randIndex = randNums.remove((int)(Math.random()*randNums.size()));
            copy.add(cards.get(randIndex));
        }
        cards.clear();
        cards.addAll(copy);
    }

    /**
     * String version of the deck class
     *
     * @return the string version of the deck class
     */

    public String toString(){
        String fill = "Deck: ";
        int count = 0;
        for(Card card: cards){
            fill+=card.toString();
            if(count < cards.size()-1)
            {
                fill+=", ";
            }
            count++;
        }

        return fill;
    }

    public void fillDeck(ArrayList<Card> cards){
        for(int i = 0; i < 4; i++){
            String suit;
            if(i==0){
                suit = "spades";
            } else if(i == 1){
                suit = "diamonds";
            } else if(i == 2) {
                suit = "clubs";
            } else {
                suit = "hearts";
            }
            for(int j = 1; j<=13; j++){
                int value;
                //Ace - King or King - Ace ordering
                //Value represents the rank
                if(i>1){
                    value = 14-j;
                } else {
                    value=j;
                }
                //Creates Card with value of the value and the suit
                Card c = new Card(String.valueOf(value), suit);
                cards.add(c);
                numCards++;
            }
        }
    }

}
