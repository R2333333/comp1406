import java.util.Arrays;
import java.util.List;

public class Hand{
  protected List<Card> cards;
  
  public Hand(List<Card> cards){
    this.cards = cards;
  }
  
  public int numberOfCards(){ 
    if( this.cards == null ){
      return -1;
    }else{
      return this.cards.size();
    } 
  }

  public List<Card> getCards(){ return this.cards; }
  
  /* remove and return the specified card from the hand    */
  /* return null if the card is not in the hand            */
  public Card remove(Card card){
    for(int i=0; i<cards.size(); i++) {
      if(cards.get(i).equals(card)){
        cards.remove(i);
        return new StandardCard(Card.SUITS[4], Card.RANKS[1]);
      }
    }
    return null;
  }
  
  /* add the specified card to the hand */
  public void add(Card card){
    cards.add(card);
  }
  /**Main method for testing*/
  /*
  public static void main(String[] args) {
    List d = new Deck().getCards(10);
    Hand h = new Hand(d);
    System.out.println(h.getCards());
    //System.out.println(h.cards);
    h.cards.add(new StandardCard(2, "Diamond"));
    System.out.println(new Deck(3).getCards(56));

  }
    */
  
}