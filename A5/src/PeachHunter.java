import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeachHunter extends Player {
    //the maximum quantity of peaches a PeachHunter can carry
    private int peachCanCarry = 100;

    public PeachHunter(World w){
        super(w,"PeachHunter",w.getHome(),new ArrayList<Peach>(),100,RGB.RED);
    }

    @Override
    public void peachHunterPlay(){

        //peach can be carried depends on the health
        if(this.getHealth() < 50){
            peachCanCarry = 25;
        }else {
            peachCanCarry = 100;
        }

        /** picking up peaches **/
        int peachSize = this.peaches.size();

        /** checking if the PeachHunter is at home */
        if(!this.getLocation().equals(this.getWorld().getHome())){

            /** checking if the PeachHunter can pick all the peaches at once */
            if(peachSize < peachCanCarry){
                if(this.location.peachesAtLocation.size() <= (peachCanCarry - peachSize) ) {
                    this.peaches.addAll(this.location.peachesAtLocation);
                    if(this.location.peachesAtLocation.size() > 0){
                        System.out.println("   PeachHunter picked up " + this.location.peachesAtLocation.size() + " peaches.");

                        /** remember this location and come back*/
                        if(groveTooLarge && this.location.equals(this.groveFound.get(0))) {
                            groveTooLarge = false;
                            this.groveFound.remove(0);
                        }

                    }else {
                        System.out.println(" "); //do nothing if there is no peach
                    }

                    /** no more peaches left */
                    this.location.peachesAtLocation.clear();
                }else{

                    /** PeachHunter cannot pick all once, but he can pick to his maximum */
                    int count = 0; //to konw how many peaches got
                    for(int i = 0; i < peachCanCarry - peachSize; i++){
                        this.peaches.add(this.location.peachesAtLocation.get(0));
                        count++;
                        this.location.peachesAtLocation.remove(0);
                    }
                    if(count > 0){
                        System.out.println("PeachHunter pick up " + count + " peaches.");
                        System.out.println(" ");
                        if(this.location.description.equals("This is a peach grove.")) {
                            this.groveFound.add(this.location);
                            groveTooLarge = true;
                        }
                    }else {
                        System.out.println(" ");
                    }
                }
            }

            /** check if the peaches he carrys over the maximum */
            if(peachSize > peachCanCarry){
                int count = 0;
                for (int i = 0; i < peachSize - peachCanCarry; i++){
                    this.location.peachesAtLocation.add(this.peaches.get(0));
                    count++;
                    this.getPeach();
                }
                if(count > 0){
                    System.out.println("PeachHunter drop " + count + " peaches.");
                    System.out.println(" ");
                }else {
                    System.out.println(" ");
                }
            }
            if(peachSize >= 50 || this.peaches.size() == peachCanCarry){
                this.missionComplete = true;
            }
        }
    }
}
