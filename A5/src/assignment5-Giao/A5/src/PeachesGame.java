import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PeachesGame{
    public static void main(String[] args) {
        World w = new World(10);
        Player p = new Player(w, "cat", w.home, new ArrayList<Peach>(), 50, RGB.YELLOW);
        Player q = new Player(w, "dog", w.home, new ArrayList<Peach>(), 100, RGB.BLUE);
        Player thief = new PeachThief(w);
        Player hunter = new PeachHunter(w);
        w.addPlayer(p).addPlayer(q).addPlayer(hunter).addBearDen(9,9).addBearDen(1,1).addPeachPit(2,2).addPeachPit(4,2).addPeachPit(5,5).addBearDen(7,7).addPeachPit(4,4);
        w.addPlayer(thief);


        System.out.println("Home : " + w.getHome());
        System.out.println("Players at Home : " + w.getHome().getPlayers());
        //System.out.println("World : " + p.getWorld());
        System.out.println("Location of all players in world");
        for (Player pp : w.getPlayers()) {
            System.out.print("Play(s)" + pp.getLocation().getPlayers() + " is(are) at ");
            System.out.println(pp.getLocation());

        }
        System.out.println(" ");


        p.peaches.add(new Peach(8, false));
        p.peaches.add(new Peach(7, false));
        q.peaches.add(new Peach(10, true));
        q.peaches.add(new Peach(5, false));
        System.out.println("Move some players in world");

        //hunter.move(Directions.DOWN);
        //hunter.health = 6;

        System.out.println("Location of all players in world");

        for (Player pp : w.getPlayers()) {
            System.out.println(pp.getLocation());
            System.out.println(pp.getLocation().getPlayers());
        }
        System.out.println(" ");

        int count = 1;
        while (w.getHome().numberOfPeaches() < 100) {

            System.out.println(" ");
            System.out.println("===================================================================================");
            System.out.println("ROUND " + count + ":");
            count++;
            System.out.println("POSITION AND STATUS FOR EACH PLAYER: ");
            System.out.println("-----------------------------------------------------------------------------------");
            for(int i =0; i < w.getPlayers().size(); i++){
                if (w.getPlayers().get(i).getLocation().peachesAtLocation.size() > 0 && w.getPlayers().get(i).getLocation().description.equals("Nothing here to see.")) {
                    System.out.println("   " + w.getPlayers().get(i).getName() + ": " + "Found "+w.getPlayers().get(i).getLocation().peachesAtLocation.size()+" peaches at" +w.getPlayers().get(i).getLocation().getPosition() + " Peaches carrying: " + w.getPlayers().get(i).peaches.size() + " HP: " + w.getPlayers().get(i).health );
                }else{
                    System.out.println("   " + w.getPlayers().get(i).getName() + ": " + w.getPlayers().get(i).getLocation() + " Peaches player has: " + w.getPlayers().get(i).peaches.size() + " HP: " + w.getPlayers().get(i).health );
                }
            }
            System.out.println(" ");
            System.out.println("PLAYERS NEXT MOVE:");
            System.out.println("-----------------------------------------------------------------------------------");
            for(int i =0; i < w.getPlayers().size(); i++){
                System.out.println(w.getPlayers().get(i).name.toUpperCase() + ":");
                w.getPlayers().get(i).play();
                System.out.println("-----------------------------------------------------------------------------------");
            }
            System.out.println("                                     ||||||                                        ");
            System.out.println("                                     ||||||                                        ");
            System.out.println("                                     ||||||                                        ");
            System.out.println("                                  || |||||| ||                                     ");
            System.out.println("                                    ||||||||                                       ");
            System.out.println("                                      ||||                                         ");
            System.out.println("                                       ||                                          ");

            if (w.getHome().numberOfPeaches() >= 100) {
                System.out.println("***********************************************************************************");
                System.out.println("       MISSION COMPLETE! Home now have " + w.getHome().numberOfPeaches() + " peaches!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("***********************************************************************************");
                return;
            }

            if(count > 500){
                System.out.println("***********************************************************************************");
                System.out.println("PeachThief wins the game! He has " + thief.peaches.size() + " peaches! Home can't have enough peaches!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("***********************************************************************************");
                return;
            }
        }
    }

}