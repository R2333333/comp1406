import java.util.ArrayList;

public class PeachPit extends Location{

    /** this list is to stored how many times the player falls into the pit **/
    private ArrayList<String> pitVisitors = new ArrayList<>();


    public PeachPit(Position position){
        super(position,"is at peach pit. ",new ArrayList<Player>(), new ArrayList<Peach>());

        /** creating random quality and quantity peaches in the peach pit */
        for(int i =0; i < (int)(Math.random()*70); i++){
            boolean quality = false;
            if(Math.random()>0.5){ quality = true;}
            this.peachesAtLocation.add(new Peach((int)(Math.random()*10),quality));
        }
    }

    public void setPeachPit() {

        /** helper will not fall into the peach pit */
        for(int i = 0; i < this.getPlayers().size(); i++){
            if(this.getPlayers().get(i).getName().equals("Helper")){
                return;
            }
        }

        if (this.getPlayers().size() != 0) {

            /**checking if the player falls into the pit for the first time*/
            if (!pitVisitors.contains(this.getPlayers().get(this.getPlayers().size() - 1).name + this.getPlayers().get(this.getPlayers().size() - 1).location.position.toString())) {
                this.getPlayers().get(this.getPlayers().size() - 1).health = this.getPlayers().get(this.getPlayers().size() - 1).health / 2;

                /**the player will not be sent back home, but it will lose health*/
                System.out.println(this.getPlayers().get(this.getPlayers().size() - 1) + " falls into the peach pit, lost " + this.getPlayers().get(this.getPlayers().size() - 1).health + " health!" + "\n");
                pitVisitors.add(this.getPlayers().get(this.getPlayers().size() - 1).name + this.getPlayers().get(this.getPlayers().size() - 1).location.position.toString());
            } else {

                /**when the player is not the first time, it will be sent back home*/
                System.out.println(this.getPlayers().get(this.getPlayers().size() - 1) + " are instantly transported to the Home location" + "\n");

                /**PeachThief cannot place peaches at home*/
                if(!this.getPlayers().get(this.getPlayers().size()-1).getName().equals("PeachThief")) {
                    for (int i = 0; i < this.getPlayers().get(this.getPlayers().size() - 1).peaches.size(); i++) {
                        this.getPlayers().get(0).getWorld().getHome().addPeach(this.getPlayers().get(this.getPlayers().size() - 1).peaches.get(i));
                    }
                    System.out.println(this.getPlayers().get(this.getPlayers().size() - 1) + " placed " + this.getPlayers().get(this.getPlayers().size() - 1).peaches.size() + " peaches at home.");
                    this.getPlayers().get(this.getPlayers().size() - 1).peaches.clear();
                }

                /**initialize the starting location and health*/
                this.getPlayers().get(this.getPlayers().size() - 1).location = this.getPlayers().get(0).getWorld().getHome();
                this.getPlayers().get(this.getPlayers().size() - 1).health /= 2;
                this.getPlayers().get(this.getPlayers().size() - 1).fallPit ++;
                this.peopleAtLocation.remove(this.getPlayers().size() - 1);
            }
        }
    }

}
