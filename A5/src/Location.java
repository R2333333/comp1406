import java.util.ArrayList;
import java.util.List;

public class Location{
    protected Position position;
    protected String description = "Nothing special about this location.";

    protected List<Player> peopleAtLocation = null;
    protected List<Peach>  peachesAtLocation = null;
    //protected ArrayList<String> herosBeenHere;

    public Location(Position p, String description, List<Player> people, List<Peach> peaches){
        this.position = p;
        this.description = description;
        this.peopleAtLocation = people;
        this.peachesAtLocation = peaches;
    }

    /** getter for position */
    public Position getPosition(){ return position; }

    /** getter for description */
    public String getDescription(){ return description; }

    /** getter for players */
    public List<Player> getPlayers(){ return peopleAtLocation; }

    /** getter for a Peach */
    public Peach getPeach(){return peachesAtLocation.remove(0); }

    /** check number pf peaches in location */
    public int numberOfPeaches(){
        return peachesAtLocation == null ? 0 : peachesAtLocation.size();
    }

    /** adding a peach to the location */
    public void addPeach(Peach p){ peachesAtLocation.add(p); }

    /** allows the location to do something to a player when entering the location */
    public void enter(Player p){
        p.setLocation(this);
        peopleAtLocation.add(p);
        System.out.println("   " + p.getName() + " just entered location " + position + "\n");

        /**entering the bearDens */
        for(BearsDen b: this.getPlayers().get(0).getWorld().bearDens){
            if(p.location.getPosition().equals(b.position)){b.setbearsden();}
        }

        /** entering the PeachPit */
        for(PeachPit a: this.getPlayers().get(0).getWorld().peachPits){
            if(p.location.getPosition().equals(a.position)){a.setPeachPit();}
        }

        /** at home */
        if(p.location.equals(p.getWorld().home)){ p.getWorld().home.atHome(); }
    }

    /** remove a player from a room */
    public void exit(Player p){
        peopleAtLocation.remove(p);
        System.out.println("   " + p.getName() + " just left location " + position);
    }
    public void setbearsden(){}

    public void atHome(){}
    /* ONLY for Home subclass */
    public void callForHelp(Player p, Location location){
    }

    @Override
    public String toString(){
        return description + position.toString();
    }

    public void setPeachPit(){}


}