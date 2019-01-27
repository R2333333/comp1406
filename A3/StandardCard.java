public class StandardCard extends Card{
    private String suit;
    private int rankNum;
    private String rankString;

    public StandardCard(String rank, String suit){
        super(rank,suit);
        for(int i=0; i<SUITS.length; i++){
            if(SUITS[i]==suit){
                this.suit=suit;
            }
        }
        for (int i=0; i<RANKS.length; i++){
            if(RANKS[i]==rank){
                rankNum = i;
                rankString = RANKS[i];
            }
            if(rankNum==1) {this.suit = SUITS[4];}
        }

        // purpose: creates a card with given rank and suit
        // preconditions: suit must be a string found in Card.SUITS
        // rank must be a string found in Card.RANKS
        // Note: If the rank is Card.RANKS[15] then any
        // valid suit from Card.SUITS can be given
        // but the card's suit will be set to Card.SUITS[4]

    }

    public StandardCard(int rank, String suit) {
        super(String.valueOf(rank),suit);
        for(int i=0; i<SUITS.length; i++){
            if(SUITS[i]==suit){
                this.suit=suit;
            }
        }
        for (int i=0; i<RANKS.length; i++){
            if(i==rank){
                rankNum = i;
                rankString = RANKS[i];
            }
            if(rankNum==1) {this.suit = SUITS[4];}
        }
        // purpose: creates a card with the given rank and suit
    // preconditions: suit must be a string found in Card.SUITS
    // rank is an integer satisfying 1 <= rank <= 14, where
    // 1 for joker, 2 for 2, 3 for 3, .., 10 for 10
    // 11 for jack, 12 for queen, 13 for king, 14 for ace
    // Note: as with the other constructor, if a joker is created, any valid suit can be passed
    // but the card's suit will be set to Card.SUITS[4]

    }

    @Override
    public int compareTo(Card o) {
        int suitNum1 = 0;
        int suitNum2 = 0;
        for(int i=0; i<SUITS.length;i++){
            if(SUITS[i]==this.getSuit()) {suitNum1 = i;}
            if(SUITS[i]==o.getSuit()) {suitNum2 = i;}
        }
        if(this.rankNum==1 && o.getRank()!=1){return 1;}
        if(this.rankNum!=1 && o.getRank()==1){return -1;}
        if(suitNum1>suitNum2){ return 1; }
        else if(suitNum1<suitNum2){ return -1;}
        else if(this.getRank()<o.getRank()){ return -1;}
        else if(this.getRank()>o.getRank()){ return 1;}
        return 0;
    }
    @Override
    public int getRank(){ return rankNum; }
    @Override
    public String getRankString(){
        return rankString;
    }
    @Override
    public String getSuit(){
        return suit;
    }
}
