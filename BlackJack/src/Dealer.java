import java.util.ArrayList;
public class Dealer
{
    private Hand hand;
    private Shoe shoe;
    private ArrayList<Player> players;

    public Dealer(){
        hand = new Hand();
        shoe = new Shoe(2);
        players = new ArrayList<Player>();
    }

    public void addPlayer(Player player){
        player.setShoe(shoe);
        players.add(player);
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void setShoeSize(int shoeSize){
        shoe = new Shoe(shoeSize);
    }

    public Shoe getShoe(){
        return shoe;
    }

    public Hand getHand(){
        return hand;
    }

    public Card hit(){
        if(hand.getValue()<17){
            Card drawn = shoe.draw();
            hand.addCard(drawn);
            return drawn;
        }
        return new Card("Null", "Spades");
    }

    public void setShoe(Shoe shoe){
        this.shoe = shoe;
    }

    public void shuffle(){
        shoe.shuffle();
    }

    public Card deal(Player player){
        return player.hit();
    }

    public void roundOver(){
        for(int i = 0; i < players.size(); i++){
            players.get(i).getHand().clear();
        }
        this.getHand().clear();
    }

    public String toString(){
        return new Player("Dealer", Double.MAX_VALUE, hand).toString();
    }
}
