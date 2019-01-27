import java.util.ArrayList;

public class Helper extends Player {
    private Location toBeHelp;
    private boolean toBeHelpposition = false;
    private boolean gavepeach = false;

    public Helper(Player player){
        super(player.getWorld(),"Helper",player.getWorld().getHome(),new ArrayList<Peach>(),100000,RGB.GREEN);
        toBeHelp = player.location; //the location of a player who needs help

        /** offering 300 good peaches to the Helper*/
        for(int i =0; i < 300; i++){
            this.peaches.add(new Peach(10,false));
        }
    }

    @Override
    public void helperPlay(){

        /** on the way to the player */
        if(!gavepeach){
            if(this.location.getPosition().getX() < toBeHelp.getPosition().getX()){
                move(Directions.DOWN);
            }else if(this.location.getPosition().getY() < toBeHelp.getPosition().getY()){
                move(Directions.RIGHT);
            }

            if (!this.location.getPosition().equals(toBeHelp.getPlayers().get(0).location.getPosition())) {
                System.out.println("Helper is on the way to help " + toBeHelp.getPlayers().get(0).getName() );
            }else {
                System.out.println("Helper arrives!");
            }
        }

        /** when Helper arrives*/
        if(!toBeHelpposition) {

            /** giving peaches to help player*/
            if (this.location.getPosition().equals(toBeHelp.getPlayers().get(0).location.getPosition())) {
                this.toBeHelp.getPlayers().get(0).peaches.add(this.peaches.get(0));
                this.peaches.remove(0);
                System.out.println("Helper gave a good peach to " + this.toBeHelp.getPlayers().get(0).getName());

                /** help finished*/
                gavepeach = true;
                toBeHelpposition = true;
            }
        }else {

            /** Helper way back home */
            if(gavepeach){
                this.peaches.clear();
                if(this.location.getPosition().getX() > 0){
                    move(Directions.UP);
                }else if(this.location.getPosition().getY() > 0){
                    move(Directions.LEFT);
                }

                /** Helper arrives at home */
                if(!(this.location.getPosition().getX() == 0 && this.location.getPosition().getY() == 0)){
                    System.out.println("Helper mission complete! Going back home");
                }
            }
        }
    }
}


