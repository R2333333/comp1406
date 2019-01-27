import java.lang.reflect.Array;
import java.util.Arrays;

public class SudokuChecker{
 
	/** sample valid game */
  public static byte[][] example1 = new byte[][]{
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,4},
    {2,8,7,4,1,9,6,3,5},
    {3,4,5,2,8,6,1,7,9}};

  /** sample invalid game */
  public static byte[][] example2 = new byte[][]{
    {5,3,4,6,7,8,9,1,2},
    {6,7,2,1,9,5,3,4,8},
    {1,9,8,3,4,2,5,6,7},
    {8,5,9,7,6,1,4,2,3},
    {4,2,6,8,5,3,7,9,1},
    {7,1,3,9,2,4,8,5,6},
    {9,6,1,5,3,7,2,8,3},
    {2,8,7,4,1,9,6,2,6},
    {3,4,5,2,8,6,1,8,8}};

	/** checks if row 'row' is OK in the grid */	
  public static boolean checkRow(int row, byte[][] grid){
      int[] tast = new int[grid[0].length];//crate a temporary array used for check the numbers
      for(int i=0; i<grid[0].length;i++) {
          tast[i] = grid[row][i];
      }
      Arrays.sort(tast);//sort the array in order to check the completeness of 1 to 9
      for (int i=0; i<grid.length;i++) {
          if (tast[i]!=i+1) {
              return false;
          }
      }
    return true;
  }

	/** checks if column 'col' is OK in the grid */
  public static boolean checkColumn(int col, byte[][] grid){
      int[] tast = new int[grid.length];//same function as in above method
      for(int i=0; i<grid.length;i++) {
          tast[i] = grid[i][col];
      }
      Arrays.sort(tast);//same as above
      for (int i=0; i<grid.length;i++) {
          if (tast[i]!=i+1) {
              return false;
          }
      }
      return true;
  }

	/** checks if the subregion 'region' is OK in the grid */
  public static boolean checkSubregion(int region, byte[][] grid){
      int[] tast = new int[grid.length];

    for(int i=0; i<3;i++) {//divide each region into three rows
        if (region%3==0) {//to check which col the region starts
            tast[i] = grid[region][i];//first row in the region
            tast[i + 3] = grid[region+1][i];//second row
            tast[i + 6] = grid[region+2][i];//third row
        }else if (region%3==1){
            tast[i] = grid[region-1][i];
            tast[i+3] = grid[region][i];
            tast[i+6] = grid[region+1][i];
        }else if (region%3==2){
            tast[i] = grid[region-2][i];
            tast[i+3] = grid[region-1][i];
            tast[i+6] = grid[region][i];
        }
      }
      Arrays.sort(tast);
      for (int i=0; i<grid.length;i++) {
          if (tast[i]!=i+1) {
              return false;
          }
      }
      return true;
  }


  public static boolean check(byte[][] grid){
    if (grid.length!=grid[0].length) return false;
   for(int row=0; row<9; row+=1){                    // check the rows
      if( !checkRow(row, grid) ){
        return false;
      }
   }
   for(int col=0; col<9; col+=1){                    // check the rows
      if( !checkColumn(col, grid) ){
        return false;
      }
   }
   for(int subregion=0; subregion<9; subregion+=1){  // check the subregions
      if( !checkSubregion(subregion, grid) ){
        return false;
      }
   }

		// if we get this far then we conclude that the grid
		// must be valid (because if it was not, we would have
		// returned false somewhere above)
    return true;
  }
    



  public static void main(String[] args){

    System.out.print("exmample1 | expected output is true  | actual output is ");
    System.out.println(check(example1));

    System.out.print("exmample2 | expected output is false | actual output is ");
    System.out.println(check(example2));
    }
  }
