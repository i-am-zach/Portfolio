import java.util.ArrayList;

public class Hand
{
    private ArrayList<Card> cards;
    private int numAces;

    /**
     * Default constructor for Hand
     */

    public Hand(){
        cards = new ArrayList<Card>();
        numAces = 0;
    }

    /**
     * Accessor method to the arraylist of cards
     *
     * @return the arraylist of cards
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * Setter method to add a card to the hand
     */

    public void addCard(Card card){
        cards.add(card);
        if(card.isAce()){
            numAces++;
        }
    }

    /**
     * Empties the hand
     */
    public void clear(){
        cards.clear();
        numAces = 0;
    }

    /**
     * Acessor method to get the number of cards
     */

    public int numCards(){
        return cards.size();
    }

    public int getNumAces(){
        return numAces;
    }

    public boolean canSplit(){
        if(numCards() == 2){
            if(cards.get(0).getRank().equals(cards.get(1).getRank())){
                return true;
            }
        }
        return false;
    }

    public boolean isFiveCardCharlie(){
        return numCards() >= 5;
    }

    public int getValue(){
        int value = 0;

        for(Card c: cards){
            if(!c.getRank().equalsIgnoreCase("Ace")){
                value+= c.getValue();
            }
        }
        for(int i = 0; i < numAces; i++){
            if(value < 11){
                value+= 11;
            } else {
                value+= 1;
            }
        }
        return value;
    }

    public String toString(){
        String cheese = "";
        for(Card c: cards){
            cheese += c.toString() + "\t";
        }
        return cheese;
    }

}
