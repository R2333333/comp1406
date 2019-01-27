import java.util.Arrays;
import java.util.Collections;

public class MinDistance{
  
  
  public static int distance(byte target, byte[] numbers) {
    /* purpose: determine the minimal distance between any
     *          two instances of target in the numbers array
     * input  : target is the number we are looking for
     *          numbers is an array of numbers
     * output : the method returns
     *           o) the minimal distance between any two instances
     *              of target in the numbers array, if there are
     *              at least two instances of target present, or
     *           o) -2 if target is not in the numbers array, or
     *           o) -1 if target appears only once in the numbers array
     */
    int j = 0;
    boolean notin = true;
    int once = 0;//count the numbers the target occurs
    for (int i = 0; i < numbers.length; i++) {
      //determine the whether the array has the target
      if (numbers[i] == target) {
        notin = false;
        once++;
      }
    }
    if (notin) return -2;
    if (once < 2) return -1;
    int[] position = new int[once];
   //count the position of target in the array
    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i] == target) {
        position[j] = i;
        j++;
      }
    }
    //calculate the distance of target elements
    int[] dist = new int[once-1];
    for (int i=0; i<position.length-1; i++){
      dist[i] = position[i+1] - position[i] -1;
    }
    //rearrange the array in order to get the min distance
    Arrays.sort(dist);
    return dist[0];
  }
  
	/*
	  main method can be used for basic test cases. Modify the method to add
    more test cases. Your main method will NOT be graded. But, be sure it does
    cause your class to not compile.		
	*/
  public static void main(String[] args){
    byte[] N = {5 , 3 , 7 , 2 , 3 , 4 , 4 , 7 , 3 , -1 , 3 , 4 , 7 , 5};
    System.out.println( distance((byte)5, N));
    System.out.println( distance((byte)4, N));
    System.out.println( distance((byte)7, N));
    System.out.println( distance((byte)3, N));
    System.out.println( distance((byte)2, N));
    System.out.println( distance((byte)13, N));
  }
}