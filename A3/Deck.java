import java.lang.reflect.Array;
import java.util.*;

/* finish all of the constructors and methods given here */
/* do NOT add any public attributes or methods          */
/* add any protected/private attributes you need         */
public class Deck{
  /**crate the array of deck,the size of the deck,
   * the number of the cards that have been moved
   * and crate of list of cards
   */
  private Card[] deck;
  private int deckSize = 52;
  private int cardMoved = 0;
  private List<Card>cards;
  private int cardLeftinDeck = 0;
  /**Build a Deck without Jokers*/
  public Deck(){
    deck = new StandardCard[deckSize];
    int counter = 0;
    for(int i=2; i<=14; i++){
      for (int j=0; j<4; j++){
        deck[counter] = new StandardCard(i,Card.SUITS[j]);
        counter++;
      }
    }
    //Mix the sequence of the deck
    Collections.shuffle(Arrays.asList(deck));
   // ArrayList n =new ArrayList();
   // n.addAll(Arrays.asList(deck));
  }
  /**Build a Deck with certain numbers of Jokers
   * @param nun_jokers is the number of Jokers in the deck*/
  public Deck(int nun_jokers){
    deckSize+=nun_jokers;
    deck = new StandardCard[deckSize];
    int counter = 0;
    for(int i=2; i<=14; i++){
      for (int j=0; j<4; j++){
        deck[counter] = new StandardCard(i,Card.SUITS[j]);
        counter++;
      }
    }
    for(int i=deckSize-1; i>=counter;i--){
      deck[i]=new StandardCard(1,"None");
    }
  }
  /**Get cards from the deck
   * @param num_cards is the number of cards to be get*/
  public List<Card> getCards(int num_cards){
    cards = new LinkedList<>();
    //temp int to record the card moved
    int tempCardMoved =cardMoved;
    if((cardMoved+num_cards)>deckSize){
      //remind the user that there is no enough cards in the deck
      System.out.print("No enough card! Only get: ");
      //getting cards from the deck
      for(int i=cardMoved;i<(deckSize);i++){
        cards.add(deck[i]);
        deck[cardMoved] = null;
        cardMoved++;
      }
      return cards;
    }
    //getting cards from the deck
    for(int i=cardMoved;i<(tempCardMoved+num_cards);i++){
      cards.add(deck[i]);
      deck[cardMoved] = null;
      cardMoved++;
    }
    return cards;
  }
  /**Get single card form the deck*/
  public Card getCard(){
    Card tempCardMoved = deck[cardMoved];
    deck[cardMoved] = null;
    cardMoved++;
    return tempCardMoved;
  }
  /**Mover cards back to the deck
   * @param c is the card to be added*/
  public void addCard(Card c){
    for(int i=cardMoved; i<deckSize; i++){
      deck[i-1]=deck[i];
    }
    deck[deckSize-1] = c;
  }
  public int gerLeft(){
    return deckSize - cardMoved;
  }
  /**Main method for testing*/
  /*
  public static void main(String[] args){
    Deck deck = new Deck();
    System.out.println(deck.getCards(4));
    System.out.println(deck.getCards(10));
    System.out.println(deck.getCard());
    System.out.println(deck.getCards(3));
    Card c = new StandardCard(3,"Diamond");
    deck.addCard(c);
    System.out.println(deck.getCards(3));
  }
  */
}