import java.util.ArrayList;
public class Shoe
{
    private int numDecks;
    private ArrayList<Card> cards;
    private ArrayList<Card> discards;

    /**
     * Integer Paramiterized Constructor
     *
     * @param numDecks, the number of decks to be added in the shoe
     */
    public Shoe(int numDecks){
        this.numDecks = numDecks;
        cards = new ArrayList<Card>();
        discards = new ArrayList<Card>();


        ArrayList<Deck> decks = new ArrayList<Deck>();
        for(int i = 0; i < this.numDecks; i++){
            decks.add(new Deck());
            cards.addAll(decks.get(i).getCards());
        }
    }

    /**
     * Deck array paramiterized constructor
     *
     * @param decks, the array of decks to be added to the shoe
     */

    public Shoe(Deck[] decks){
        this.numDecks = decks.length;
        cards = new ArrayList<Card>();
        discards = new ArrayList<Card>();
        for(int i = 0; i < this.numDecks; i++){
            cards.addAll(decks[i].getCards());
        }
    }

    /**
     * Returns the top card from the deck and moves it to the discard pile
     */
    public Card draw(){
        discards.add(cards.get(0));
        return cards.remove(0);
    }

    /**
     * Return the index of the card
     *
     * @param c, the card to be searched for
     *
     * @return the index of the card
     */
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

    /**
     * Returns the number of decks in the shoe
     *
     * @return, the number of decks in the shoe
     */

    public int getNumDecks(){
        return numDecks;
    }

    /**
     * Shuffles the cards in the deck without adding the discards
     */

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
     * Adds all cards back to the deck and shuffles the deck
     */

    public void reshuffle(){
        cards.addAll(discards);
        discards.clear();
        shuffle();
    }

    /**
     * String version of the shoe class composed of all of the card's toStrings
     *
     * @return String version of the shoe class composed of all of the card's toStrings
     */

    public String toString(){
        String fill = "";
        if(numCards() > 0)
        {
            fill+="In deck:\n";
            for(int i = 0; i < this.numCards(); i++){
                fill+=cards.get(i).toString() + ", ";
            }
        }
        if(numDiscards() > 0){
            fill+="\nIn Discard:\n";
            for(int i = 0; i < this.numDiscards(); i++){
                fill+=discards.get(i).toString() + ", ";
            }
        }
        return fill;
    }

}
