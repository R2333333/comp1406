import java.util.Arrays;

public class MaxTemp{

  /** t1 and t2 are considered close enough if Math.abs(t1-t2) < EPSILON */
  public static final double EPSILON = 0.01;


  /* add attributes as you need */

  private double maxtemp = 0.00;
  private Temperature[] temper;
  private double count = 0.0;


  /* ----------------------------------------------------
   * constructor
   * ----------------------------------------------------
   */

  public MaxTemp(Temperature[] temperatures){
    // add your code here
    temper = temperatures;
  }


  /* ----------------------------------------------------
   * getter
   * ----------------------------------------------------
   */

  public double[] getMax(){
    // - returns an array of length 2 [max, count]
    //   where max is the maximum temperature (expressed in the Kelvin scale)
    //   of all Temperature objects passed to the constructor, and count
    //   is the number of times that temperature was present (in the input
    //   array of the constructor)
		//   If there are no temperatures then return the array [0.0, 0.0]
    if (temper.length==0) return new double[]{0.0, 0.0};
    double[] tempvalue = new double[temper.length];//make an array to compare the converted temperature
    for (int i=0; i< temper.length; i++){
      temper[i].setScale("K");
      tempvalue[i] = temper[i].getTemp();
    }
    Arrays.sort(tempvalue);
    maxtemp = tempvalue[tempvalue.length-1];
    for (int i=0;i<(tempvalue.length-1);i++){
      if (Math.abs(tempvalue[i+1]-tempvalue[i])<EPSILON){
       count++;
      }
    }
    double[] m = {maxtemp,count};
    //System.out.println(count);
    return m;

  }


  /* OPTIONAL - use your main method to test your code */
  public static void main(String[] args){
     // testing code here is optional
    Temperature m[] = {new Temperature(1001.12, "K"), new Temperature(-200.0, "F"), new Temperature(1001.11, "K")};
    MaxTemp n = new MaxTemp(m);
    System.out.println("["+n.getMax()[0]+" "+n.getMax()[1]+"]");
  }

}
