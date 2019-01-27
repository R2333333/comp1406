import java.util.ArrayList;

public class BearsDen extends Location{

    /**create a list of store the player who give peaches to bear*/
    private ArrayList<String> visitors = new ArrayList<>();

    public BearsDen(Position p){
        super(p, "At a BearsDen! ",new ArrayList<Player>(), new ArrayList<Peach>());
    }

    public void setbearsden(){

        /**helper will not give peaches*/
        for(int i = 0; i < this.getPlayers().size(); i++){
            if(this.getPlayers().get(i).getName().equals("Helper")){
                return;
            }
        }

        /**check if there are some players in the bearsDen*/
        if(this.getPlayers().size() != 0){

            /**first time give peaches*/
            if (!visitors.contains(this.getPlayers().get(this.getPlayers().size() - 1).name + this.getPlayers().get(this.getPlayers().size() - 1).location.position.toString())) {

                /**player has less than 2 peaches, he is attacked*/
                if (this.getPlayers().get(this.getPlayers().size() - 1).peaches.size() < 2) {
                    this.getPlayers().get(this.getPlayers().size() - 1).health = this.getPlayers().get(this.getPlayers().size() - 1).health - 25;
                    System.out.println("This is a BearsDen! " + this.getPlayers().get(this.getPlayers().size() - 1)+": have less then 2 peaches, a bear attacked player and his health: "+ this.getPlayers().get(this.getPlayers().size() - 1).health + "\n");
                } else {

                    /**give peaches to bear*/
                    this.getPlayers().get(this.getPlayers().size() - 1).peaches.remove(0);
                    this.getPlayers().get(this.getPlayers().size() - 1).peaches.remove(0);
                    System.out.println("This is a BearsDen! " + this.getPlayers().get(this.getPlayers().size() - 1)+": gave the bear 2 peaches, and have "+ this.getPlayers().get(this.getPlayers().size() - 1).peaches.size()+" peaches left." + "\n");
                    visitors.add(this.getPlayers().get(this.getPlayers().size() - 1).name + this.getPlayers().get(this.getPlayers().size() - 1).location.position.toString());
                }
            }else {

                /**the bear won't attack since he got peaches before*/
                System.out.println("This is a BearsDen! The bear won't attack " + this.getPlayers().get(this.getPlayers().size() - 1).name + " because it remembers " + this.getPlayers().get(this.getPlayers().size() - 1).name + "\n");
            }
        }
    }
}

