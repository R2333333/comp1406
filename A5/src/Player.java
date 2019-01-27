import java.util.List;
import java.util.Random;

/** A Player in the game
 *
 * Each member of your team should implement their own
 * unique Player subtype. Your group should also have a human player.
 */

public class Player{
    protected World        world;    // world that player lives in
    protected String       name;     // name of player
    protected Location     location; // where in the world the player is
    protected List<Peach>  peaches;  // peaches
    protected int          health;   // health of player
    protected int     defaulthealth;  //the maximum health of the player
    protected RGB          colour;   // colour of player (if graphics is used)
    protected boolean missionComplete = false;
    protected boolean groveTooLarge = false;
    protected List<Location> groveFound ;
    protected int fallPit = 0;

    /** Creates a player in the game
     *
     * @param w is the world that the player lives in
     * @param name is the name of the player
     * @param location is where in the world the player is
     * @param peaches is a list of peaches the player starts with
     * @param health is the health of the player (which may or may not be relevant in your game)
     * @param rgb is the colour of the player
     */
    public Player(World w, String name, Location location, List<Peach> peaches, int health, RGB rgb){
        this.world = w;
        this.name = name;
        this.location = location;
        location.getPlayers().add(this);
        this.peaches = peaches;
        this.health = health;
        this.colour = rgb;
        this.defaulthealth = health;
    }

    /** Getter for a player's world */
    public World        getWorld(){ return world; }

    /** Getter for a player's name */
    public String       getName(){ return name; }

    /** Getter for a player's location */
    public Location     getLocation(){ return location; }

    /** Getter for a player's peach */
    public Peach  getPeach(){ return peaches == null ? null: peaches.remove(0); }

    /** Getter for a player's health */
    public int getHealth(){ return health; }


    /** This is the logic of the player.
     * It defines what they should do when given a chance to do somerthing
     */
    public void play(){

        /** eating peaches when health is between 0 to 10 */
        while ( health < 10 && health > 0){
            if(this.peaches.size() > 0){
                if(this.peaches.get(0).bad){

                    /** bad peach reduce health */
                    this.health = this.health - this.peaches.get(0).ripeness;
                    System.out.println("Ate a bad peach, health reduced by " + this.peaches.get(0).ripeness);
                }else {

                    /** good peach increase health */
                    this.health = this.health + this.peaches.get(0).ripeness;
                    System.out.println("Ate a good peach, health increased by " + this.peaches.get(0).ripeness);
                }
                this.peaches.remove(0);
            }else {

                /** calling for help if player has no more peach */
                System.out.println("   Awaiting for the helper at " + this.getLocation().getPosition() + "\n");
                this.getHelp();
                return;
            }
        }

        /** die and respawn */
        if(health <= 0){
            this.setHealth(this.defaulthealth);
            for(int i = 0; i < this.location.peopleAtLocation.size(); i++){
                if(this.location.peopleAtLocation.get(i).getName().equals(this.getName())){
                    this.location.peopleAtLocation.remove(i);
                }
            }
            this.location = this.getWorld().getHome();
            System.out.println(this + " has no health, and he was reborn at home");
            System.out.println(" ");
        }

        /** call Helper method */
        if(this.getName().equals("Helper")){
            System.out.println("Helper is at home." + "\n");
            this.helperPlay();
            return;
        }

        /** call PeachHunter method */
        if(this.getName().equals("PeachHunter")){
            this.peachHunterPlay();
            if(missionComplete) {
                System.out.println("PeachHunter get sufficient peaches, returning home.");
                System.out.println(" ");
                this.goHome();
                missionComplete = false;
                return;
            }else if(groveTooLarge){
                System.out.println("PeachHunter going to the peach grove!");
                this.gopeachgrove();
                if(groveFound.size() == 0) { groveTooLarge = false;}
                return;
            }

        }

        /** random movement for players */
        Random random = new Random();
        int x = random.nextInt(4)+1;
        switch (x){
            case 1: if(this.getLocation().getPosition().getX() > 0 ) {
                move(Directions.UP);
                break;
            }
            case 2: if(this.getLocation().getPosition().getX() < this.getWorld().locations.length - 1) {
                move(Directions.DOWN);
                break;
            }
            case 3: if(this.getLocation().getPosition().getY() > 0 ) {
                move(Directions.LEFT);
                break;
            }
            case 4: if(this.getLocation().getPosition().getY() < this.getWorld().locations[0].length - 1 ) {
                move(Directions.RIGHT);
                break;
            }
            default: move(Directions.LEFT);
        }

        /** if a player was transferred from the PeachPit, his health is set to half of default health */
        if(this.getLocation().equals(this.getWorld().getHome())){
            if(this.fallPit > 0){
                this.setHealth(this.defaulthealth / 2);
                System.out.println(this+"'s health is reduced by half." + "\n");
                this.fallPit = 0;
                return;
            }
            else{
                this.setHealth(this.defaulthealth);
                System.out.println(this+" is completely healed at home!" + "\n");
            }
        }

        /** calling PeachThief method */
        if(this.getName().equals("PeachThief")){
            this.PeachThiefPlay();
            return;
        }
    }


    /** if PeachHunter gets enough peaches, he will go home and drop those peaches */
    public void goHome(){

        Random random = new Random();
        int x = random.nextInt(2)+1;
        switch (x) {
            case 1:
                if (this.getLocation().getPosition().getX() > 0) {
                    move(Directions.UP);
                    break;
                }
            case 2:
                if (this.getLocation().getPosition().getY() > 0) {
                    move(Directions.LEFT);
                    break;
                }
            default: move(Directions.UP);
        }
        if(this.getLocation().equals(this.getWorld().getHome())){
            this.setHealth(this.defaulthealth);
            System.out.println(this+" is completely healed at home!" + "\n");
        }
    }

    /** PeachHunter goes to PeachGrove */
    public void gopeachgrove(){
        Random random = new Random();
        int x = random.nextInt(2)+1;
        switch (x) {
            case 1:
                if (this.getLocation().getPosition().getY() < this.groveFound.get(0).getPosition().getY()) {
                    move(Directions.RIGHT);
                    break;
                }
            case 2:
                if (this.getLocation().getPosition().getY() < this.groveFound.get(0).getPosition().getX()) {
                    move(Directions.DOWN);
                    break;
                }
            default: if(this.getLocation().getPosition().getY() < this.groveFound.get(0).getPosition().getY()){
                move(Directions.RIGHT);
            }
        }
    }


    /** Moves a player from one location to a new location
     *
     * @param direction is the new location that the player will be moved to
     * @return true if the move was successful and false otherwise (e.g. when trying to move from one
     *         location to another that are not connected)
     */
    public boolean move(int direction){
        // move from current location to new location (if possible)
        world.move(this, direction);
        return false;
    }

    /** sets a player's current location
     *
     * @param location is the Location to set for the player
     */
    public void setLocation(Location location){
        this.location = location;
    }

    /** Setter for a player's health
     *
     * @param h is the new health of the player
     */
    public void setHealth(int h){
        this.health = h;
    }


    /** Allows for interaction with this player and another player
     * (presumably called from within the play method)
     *
     * @param p is a player that is interacting with this player
     */
    public void interact(Player p){
        // allows for some interaction with a player
    }

    /** ask for help when they need it */
    public void getHelp(){
        world.getHome().callForHelp(this, location);
    }

    @Override
    public String toString(){
        return name;
    }

    /** Two players are the same if they have the same name, location and health. */
    @Override
    public boolean equals(Object o){
        if( o instanceof Player){
            return this.name.equals( ((Player)o).name )
                    && this.location.equals( ((Player)o).location )
                    && this.health == ((Player)o).health;

        }else{
            return false;
        }
    }
    public void peachHunterPlay(){}
    public void helperPlay(){}
    public void PeachThiefPlay(){}

}
