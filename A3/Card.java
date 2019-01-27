import java.util.Random;

public abstract class Card implements Comparable<Card>{
  
  /* handy arrays for ranks and suits    */
  /* do NOT change these                 */
  public final static String[] RANKS = { "None", "Joker", 
    "2", "3", "4", "5", "6", "7", "8", "9", "10", 
    "Jack", "Queen", "King", "Ace"};
  public final static String[] SUITS = { "Diamonds", 
    "Clubs", "Hearts", "Spades", "NONE"};
  protected String suit;
  protected String rank;
 /** creates a card with specified suit and rank
  * 
  * @param suit is the suit of the card (must be a string from Card.SUITS)
  * @param rank is the rank of the card (must be a string from Card.RANKS)
  */
  public Card(String suit, String rank){
      this.suit=suit;
      this.rank=rank;
  }
  
  /** the numerical representation of the rank of the current card
  *  <p>
  * ranks have the numerical values
  *  Joker = 1,  
	*  2 = 2, 3 = 3, ..., 10 = 10
  *  Jack = 11, Queen = 12, King = 13, Ace = 14
  *  
  * @return the numerical rank of this card 
  */
  public abstract int getRank();
 
 /** the string representation of the rank of the current card 
  *
  * @return the string representation of the rank of this card (must be a string from Card.RANKS) 
  */
 public abstract String getRankString();
  
 
 /** the suit of the current card 
  *
  * @return the suit of this card (must be a string from Card.SUITS) 
  */
 public abstract String getSuit();

 /**Main method for testing purpose*/
  /*
  public static void main(String[] args){
      Card c = new StandardCard("Queen","Diamonds");
      System.out.println(c);
      System.out.println(c.getRankString());
      System.out.println(c.getRank());
      System.out.println(c.getSuit());
      Card d = new StandardCard("4","Spades");
      System.out.println(c.compareTo(d));
      System.out.println(d.compareTo(c));
      Card e = new StandardCard("Jack","Spades");
      System.out.println((d.compareTo(e)));
      System.out.println((e.compareTo(e)));
      Card j = new StandardCard(1,"NONE");
      System.out.println(j);
      System.out.println(j.getRankString());
      System.out.println(j.getRank());
      System.out.println(j.getSuit());
      System.out.println((e.compareTo(j)));
      //d.compareTo(c);
  }*/
  
  @Override
  public final String toString(){
    // outputs a string representation of a card object
    int r = getRank();
    if( r >= 2 && r <= 14 ){
      return r + getSuit().substring(0,1);
    }
		if (r == 1){
		  return "J";
	  }
    return "invalid card";
  }
  
}

