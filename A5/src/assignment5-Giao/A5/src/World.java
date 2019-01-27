import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class World{
    protected Location[][] locations;
    protected Location     home = new Home();
    protected List<BearsDen> bearDens = new ArrayList<>();
    protected List<Player> players; // all players in the world
    protected List<PeachPit> peachPits = new ArrayList<>();
    private int worldSize;

    public World(int worldSize){
        this.worldSize = worldSize;
        locations = new Location[worldSize][worldSize];
        for(int r=0; r<worldSize; r+=1){
            for(int c=0; c<worldSize; c+=1){
                locations[r][c] = new EmptyLocation(new Position(r,c), "Nothing here to see.");
            }
        }
        locations[0][0] = home;

        /** test, adding peaches on the road to know if players can pick them up */
        for(int i=0; i<20; i++){
            locations[3][7].peachesAtLocation.add(new Peach(10,false));
            locations[2][1].peachesAtLocation.add(new Peach(10,true));
            locations[1][4].peachesAtLocation.add(new Peach(10,false));
            locations[5][9].peachesAtLocation.add(new Peach(10,true));
            locations[6][4].peachesAtLocation.add(new Peach(10,false));
            locations[0][1].peachesAtLocation.add(new Peach(10,false));
            locations[1][1].peachesAtLocation.add(new Peach(10,true));
            locations[1][0].peachesAtLocation.add(new Peach(10,false));
            locations[3][1].peachesAtLocation.add(new Peach(10,false));
            locations[4][1].peachesAtLocation.add(new Peach(10,true));
            locations[5][6].peachesAtLocation.add(new Peach(10,false));
            locations[4][0].peachesAtLocation.add(new Peach(10,false));
            locations[7][3].peachesAtLocation.add(new Peach(10,true));
            locations[8][2].peachesAtLocation.add(new Peach(10,false));
            locations[4][6].peachesAtLocation.add(new Peach(10,false));
        }


        players = new ArrayList<Player>();
    }

    public List<Player> getPlayers(){ return players; }
    public Location[][] getWorld(){ return locations; }
    public Location getHome(){ return home; }

    public List<Location> getLocations(){
        List<Location> list = new ArrayList<Location>(locations.length*locations[0].length);
        for (Location[] array : locations) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    /* keep a list of all players in the world. Each time a helper is created be
     * sure to add them to this list
     */
    public World addPlayer(Player p){
        players.add(p);
        return this;
    }

    public World addBearDen(int x, int y){
        bearDens.add(new BearsDen(new Position(x,y)));
        locations[x][y] = bearDens.get(bearDens.size()-1);
        return this;
    }

    public World addPeachPit(int x, int y){
        peachPits.add(new PeachPit(new Position(x,y)));
        locations[x][y] = peachPits.get(peachPits.size()-1);
        return this;
    }

    public boolean move(Player p, int direction){
        Location loc = p.getLocation(); // player's current location
        int x = loc.getPosition().getX();
        int y = loc.getPosition().getY();
        Location newLocation = null;
        //
        switch(direction){
            case Directions.UP:
                newLocation = locations[x-1][y];
                break;
            case Directions.DOWN:
                newLocation = locations[x+1][y];
                break;
            case Directions.LEFT:
                newLocation = locations[x][y-1];
                break;
            case Directions.RIGHT:
                newLocation = locations[x][y+1];
                break;
            default: break;
        }
        loc.exit(p);
        newLocation.enter(p);


        return true;
    }


}
