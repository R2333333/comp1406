import java.util.ArrayList;

public class Home extends Location {

    public Home(){
        super(new Position(0,0),"is at the Home",new ArrayList<Player>(), new ArrayList<Peach>());
    }

    /** a method to determine what players can do at home */
    @Override
    public void atHome(){
        for(Player p: this.getPlayers()){
            if(p.peaches.size()>0) {

                /** Helper and PeachThief cannot place peaches at home*/
                if (!p.toString().equals("Helper") && !p.toString().equals("PeachThief")) {
                    Player newEnter = this.getPlayers().get(peopleAtLocation.size() - 1);
                    System.out.println(newEnter + " placed " + newEnter.peaches.size() + " peaches at home.");
                    while (newEnter.peaches.size() > 0) {
                        this.peachesAtLocation.add(newEnter.peaches.get(0));
                        newEnter.getPeach();
                    }
                }
            }
        }
    }

    @Override
    public String toString(){return description+", with "+peachesAtLocation.size()+" peaches at home.";}

    /** creating a Helper whenever a player calls for help */
    @Override
    public void callForHelp(Player p, Location location){
        boolean count = true;
        for(int i = 0; i < p.getWorld().getPlayers().size(); i++){
            if(p.getWorld().getPlayers().get(i).getName().equals("Helper")
                   && !p.getWorld().getPlayers().get(i).location.equals(p.getWorld().getPlayers().get(i).getWorld().getHome())){
                count = false;
            }
        }

        /** only create one Helper for a player at once */
        if(count){
            Player Helper = new Helper(p);
            p.getWorld().addPlayer(Helper);
        }
    }

}

