import java.util.ArrayList;

public class MyImage extends Image {
    //make a boolean array to record the positions already traveled
    private boolean[][] traveled = new boolean[this.rows][this.cols];
    //make an array to record the satisfied position
    private ArrayList<Position> positions = new ArrayList<>();

    //private int count = 0;

    /**Make an image with certain rows and cols
     * @param rows is the row of the image
     * @param cols is the column of the image
     */
    public MyImage(int rows,int cols){ super(rows, cols); }

    /**Make an image with data input
     * @param data contains the pixels of the image which is t
     */
    public MyImage(boolean[][] data){ super(data); }

    /**Override method to find any black pixel in an picture*/
    @Override
    public Position find(){
        for(int r=0; r<this.rows; r+=1) {
            for (int c = 0; c < this.cols; c += 1) {
                if(this.pixels[r][c]){
                    return new Position(r,c);
                }
            }
        }
    return null;
    }

    /**Override method to find the size of connected component
     * of certain position in an image by using recursion part
     * recursion1 is the recursion part
     * @param p is the position to find connected component*/
    @Override
    public int sizeOfConnectedComponent(Position p){
        //reset the connect component array and the traveled map
        positions.clear();
        traveled = new boolean[this.rows][this.cols];

        return this.recursion1(p);
    }

    /**Override method to find if the image consists of only
     * two or one connected component*/
    @Override
    public boolean isConnectedWithoutHoles(){
        Position comp1;
        Position comp2 = new Position(0,0);
        // get one black pixel initially
        comp1 = this.find();
        boolean breakHelper = false;
        // check if the image has no black pixels
        if(comp1==null){return true;}
        // check if the image has no white pixels
        else if(this.sizeOfConnectedComponent(comp1)==this.cols*this.rows){return true;}
        // get another pixel with different color
        for(int r=0; r<this.rows; r++){
            for(int c= 0; c<this.cols; c++){
                if(pixels[r][c]!=pixels[comp1.getX()][comp1.getY()]){
                    comp2 = new Position(r,c);
                    breakHelper = true;
                    break;
                }
            }
            if(breakHelper){break;}
        }
        // compute if the these two connected component fills the whole image
        if(this.sizeOfConnectedComponent(comp1)+this.sizeOfConnectedComponent(comp2)==cols*rows){return true;}
        return false;
    }

    /**Override method to find any pin hole in a connected component
     * consists of given position p
     * @param p is the position belonging to the connected component to be check*/
    @Override
    public Position findPinHole(Position p){
        Position tempp;
        //create four boolean to check four different direction
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;

        //if a connected component containing less than 7 pixels,
        //it cannot make a hole inside
        if(this.sizeOfConnectedComponent(p)<7){return null;}

            for (int c=1; c<cols-1; c++){
                for (int r=1; r<rows-1;r++){

                    if(pixels[r][c]!=pixels[p.getX()][p.getY()]){
                        tempp = new Position(r,c);
                        positions = new ArrayList<>();
                        traveled = new boolean[this.rows][this.cols];
                        //recursion part for make the connected component array
                        this.recursion1(p);
                        //check four different position of of selected pixel
                        //to make sure it is completely surrounded by the
                        // connected component
                        for(int i=0; i<positions.size(); i++) {
                            if (positions.get(i).getX()==r-1 && positions.get(i).getY()==c) { left = true;}
                            if (positions.get(i).getX()==r+1 && positions.get(i).getY()==c) { right = true;}
                            if (positions.get(i).getX()==r && positions.get(i).getY()==c+1) { down = true; }
                            if (positions.get(i).getX()==r && positions.get(i).getY()==c-1) { up = true; }
                        }
                        if (up && down && left && right){ return tempp;}
                    }
                }
            }

        return null;
    }
    /**the recursion part for recursive method*/
    private int recursion1(Position p){
        //add the initial position p into the position array
        if(positions.size()==0){
            positions.add(p);
            traveled[p.getX()][p.getY()] = true;
        }
        //search right direction
        if(p.getX()<this.rows-1) {
            //check if the pixel has same color with right pixel
            if (pixels[p.getX()][p.getY()] == pixels[p.getX() + 1][p.getY()]) {
                //check if the position is already visited
                if (!traveled[p.getX() + 1][p.getY()]) {
                    positions.add(new Position(p.getX() + 1, p.getY()));
                    traveled[p.getX() + 1][p.getY()] = true;
                    recursion1(new Position(p.getX() + 1, p.getY()));
                }
            }
        }
        //search down direction, same steps with above
        if(p.getY()<this.cols-1) {
            if (pixels[p.getX()][p.getY()] == pixels[p.getX()][p.getY() + 1]) {

                if (!traveled[p.getX()][p.getY() + 1]) {
                    positions.add(new Position(p.getX(), p.getY() + 1));
                    traveled[p.getX()][p.getY() + 1] = true;
                    //count++;
                    recursion1(new Position(p.getX(), p.getY() + 1));
                }
            }
        }
        //search left direction
        if (p.getX() > 0) {
            if (pixels[p.getX()][p.getY()] == pixels[p.getX() - 1][p.getY()]) {

                if (!traveled[p.getX() - 1][p.getY()]) {
                    positions.add(new Position(p.getX() - 1, p.getY()));
                    traveled[p.getX() - 1][p.getY()] = true;
                    //count++;
                    recursion1(new Position(p.getX() - 1, p.getY()));
                }
            }
        }
        //search upper direction
        if (p.getY() > 0) {
            if (pixels[p.getX()][p.getY()] == pixels[p.getX()][p.getY() - 1]) {

                if (!traveled[p.getX()][p.getY() - 1]) {
                    positions.add(new Position(p.getX(), p.getY() - 1));
                    traveled[p.getX()][p.getY() - 1] = true;
                    //count++;
                    recursion1(new Position(p.getX(), p.getY() - 1));
                }
            }
        }
        return positions.size();
    }
}
