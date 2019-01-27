import java.util.ArrayList;

public class PeachThief extends Player{

    /** the PeachThief is created at the diagonal position of home, says a 3*3 grid, home is [0,0], PeachThief is [2,2] */
    public PeachThief(World w){
        super(w,"PeachThief",w.locations[w.locations.length-1][w.locations[0].length-1],new ArrayList<Peach>(),50, RGB.YELLOW);
    }

    @Override
    public void PeachThiefPlay(){

        /** PeachThief cannot steal peach from the PeachGrove */
        if(!this.location.getDescription().equals("The PeachGrove")) {

            /** PeachThief can still pick up peaches on the road */
            if(this.location.peachesAtLocation.size() != 0){
                this.peaches.addAll(this.location.peachesAtLocation);
                System.out.println("The PeachThief just picked up " + this.location.peachesAtLocation.size() + " peaches on the road.");
                this.location.peachesAtLocation.clear();
            }
        }

        /** creating a random number between 0 and 1 */
        double probability = Math.random();

        /** stealing peaches when he meets any other players, not at home */
        if(this.location.getPlayers().size()>1 && !this.location.equals(this.location.getPlayers().get(0).getWorld().getHome())){

            /** getting the players at this location */
            for(Player pp : this.location.getPlayers()){

                /** PeachThief cannot steal his own peaches */
                if(!pp.toString().equals("PeachThief") && !(pp.peaches.size() == 0)) {
                    int peachStolen = 0;

                    /** the probability is 75% */
                    while (probability<=0.75 && pp.peaches.size()>0) {
                        if (peachStolen == 0) {

                            /** eating the first peach he got */
                            if (pp.peaches.get(0).bad) this.health -= pp.peaches.get(0).ripeness;
                            else this.health += pp.peaches.get(0).ripeness;
                            pp.peaches.remove(0);
                            peachStolen++;
                        } else {

                            /** saving the rest peaches */
                            this.peaches.add(pp.peaches.get(0));
                            pp.peaches.remove(0);
                            peachStolen++;
                            probability = Math.random(); //creating a new random number for the next steal
                        }
                    }

                    /** finish stealing */
                    System.out.println("The PeachThief just stole " + peachStolen + " peaches from " + pp.getName() + ". ");
                }
            }
        }
    }
}
