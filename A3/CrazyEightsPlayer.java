public class CrazyEightsPlayer extends Player{
    private Card topDisPile;
    private Deck deck;
    //ask use to take card repeatly or not
    private boolean repeatlyTakeCard = false;
    //Ask for user's desired suit
    private String suitDesired = "Diamond";

    public CrazyEightsPlayer(Hand hand){
        super(hand);
    }
    /**for playing the card
     * @param top_of_discard_pile is the card on the top of the discard
     * @param deck is the deck used for playing the game*/
    @Override
    public Card play(Card top_of_discard_pile, Deck deck){
        topDisPile = top_of_discard_pile;
        this.deck = deck;
        //an int to determine if the player plays a card
        int tempHand = hand.numberOfCards();
        //finding 8 and ask the user's desired suit
        for(int i=0; i<hand.cards.size(); i++) {
            if (hand.cards.get(i).getRank() == 8) {
                hand.remove(hand.cards.get(i));
                //a possible interact like scanner can ba used here
                hand.add(new StandardCard(8, suitDesired));
            }
        }
        //playing the cards one the player's hand that can be played
        for(int i=0; i<hand.cards.size(); i++) {

            if (hand.cards.get(i).getSuit().equals(topDisPile.getSuit())) {
                topDisPile = hand.cards.get(i);
                hand.remove(hand.cards.get(i));
            } else if (hand.cards.get(i).getRank() == topDisPile.getRank()) {
                topDisPile = hand.cards.get(i);
                hand.remove(hand.cards.get(i));
            }else if (this.deck.gerLeft()==0){ return null;}
        }
        //take cards from the deck
        while (repeatlyTakeCard) {
            if (tempHand == hand.numberOfCards()) {
                hand.add(this.deck.getCard());
                if(hand.cards.get(hand.numberOfCards()-1).equals(topDisPile)){
                    topDisPile = hand.cards.get(hand.numberOfCards()-1);
                    hand.cards.remove(hand.numberOfCards()-1);
                    break;
                }
            }
        }
        return topDisPile;
    }
}
