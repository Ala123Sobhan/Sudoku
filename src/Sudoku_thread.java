import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Author- Ala Sobhan
 * Course- CMP 426
 * Sudoku Validator
 * */
public class Sudoku_thread implements Runnable {
	static int [] [] board = {            // 9x9 Sudoku board
		    {6, 2, 4, 5, 3, 9, 1, 8, 7},
		    {5, 1, 9, 7, 2, 8, 6, 3, 4},
		    {8, 3, 7, 6, 1, 4, 2, 9, 5},
		    {1, 4, 3, 8, 6, 5, 7, 2, 9},
		    {9, 5, 8, 2, 4, 7, 3, 6, 1},
		    {7, 6, 2, 3, 9, 1, 4, 5, 8},
		    {3, 7, 1, 9, 5, 6, 8, 4, 2},
		    {4, 9, 6, 1, 8, 2, 5, 7, 3},
		    {2, 8, 5, 4, 7, 3, 9, 1, 6},
		    };
	
	//validity arrays for row, column and subgrids
	static int [] row_validity = new int[9];
	static int [] col_validity = new int [9];
	static int [] grids_validity = new int [9];
	
	static int k =0, c=0, g=0;
	
	// nine 3x3 subgrids
	static int [][] arr_1 = new int[3][3];
	static int [][] arr_2 = new int[3][3];
	static int [][] arr_3 = new int[3][3];
	static int [][] arr_4 = new int[3][3];
	static int [][] arr_5 = new int[3][3];
	static int [][] arr_6 = new int[3][3];
	static int [][] arr_7 = new int[3][3];
	static int [][] arr_8 = new int[3][3];
	static int [][] arr_9 = new int[3][3];
	
	//count contains the subgrid count
	static int count = 0; 
	
	public int check_valid_rows(int row, int [][] grid)
	{
		
		int [] grid_row = grid[row];
		Set<Integer>set = new HashSet<Integer>(); //set will contain 1-9 unique numbers
		
	    for (int v : grid_row) {
	    	  if (v <= 0 || v > 9){
	  	       // System.out.println( "Invalid value" );
	  	        return -1; //returns -1 if invalid
	  	      }
	    	  else if (v!= 0){
	  	        if (set.add(v) == false) { //repeated values
	  	         return 0;  //returns 0 if repeated values
	  	        }
	  	      }
	    	
	    }
		return 1; //returns 1 if valid
	}
	
	public int check_valid_cols(int col, int [][]grid)
	{
		  Set<Integer>set = new HashSet<Integer>(); //set will contain 1-9 unique numbers
		  
		  for(int i =0; i<9; i++)
		  {
			  if (grid[i][col] <= 0 || grid[i][col] > 9){
			    //  System.out.println( "Invalid value" );
			      return -1; //return -1 if invalid
			    }
			  else if (grid[i][col] != 0){
			      if (set.add(grid[i][col]) == false) { //repeated values
			      return 0; //return 0 if repeated values
			      }
			    }
		  }
		
		return 1; //returns 1 if valid
		
	}
	
   public int check_grid_validity(int [][] arr)
   {
	   Set<Integer>set = new HashSet<Integer>(); //set will contain 1-9 unique numbers
       
		 for(int r = 0; r< 3; r++)
		 {
			 for(int c= 0; c<3; c++)
			 {
				 if (arr[r][c] <= 0 || arr[r][c] > 9){
			            System.out.println( "Invalid (the grid contains invalid value)" );
			            return -1; //returns -1 if invalid
			        }
				 
				 else if (arr[r][c] != 0){
			            if (set.add(arr[r][c]) == false) {//repeated values
			              System.out.println("Invalid");
			             return 0; //return 0 if repeated values
			            }
			          }
				 
			 }
		 }
		 System.out.println("Valid");
		 return 1; //valid- sub-grid has 1-9 without repetition
   }
		
	
	public void validator(int [] row_validity, int [] col_validity, int[] grids_validity) {
		
		for(int i =0; i<9; i++)
		{
			
			if(row_validity[i] < 1 || col_validity[i] < 1) //checking the row and column validity
			{
				 System.out.println( "The Sudoku solution is invalid!" ); 
			        return;
			}
			
		}
		
		for(int i=0; i<9; i++) {
			
			if(grids_validity[i] < 1)  //checking for nine subgrids validity
		     {
				  //System.out.println(grids_validity[i]);
			      System.out.println( "The Sudoku solution is invalid!" );
			      return;
		    }
	   }
		
		 //all the rows, columns and subgrids are valid 
		 System.out.println( "The Sudoku solution is valid!" );
		
		
	}
	
	public void run() {
		
		if(Thread.currentThread().getName().equalsIgnoreCase("t1"))
		{
			System.out.println("Thread t1 is running to determine row validity:");
			
			//check rows 
			for(int i =0; i<9; i++)
			{
				row_validity[k] = check_valid_rows(i, board);
				System.out.print(row_validity[k]+" "); //print 1 for valid row, print -1 for invalid row
				k++;
			
			}
			System.out.println();
			return;
		}
		else if (Thread.currentThread().getName().equalsIgnoreCase("t2"))
		{
			System.out.println("Thread t2 is running to determine column validity:");
			
			//check columns
			for(int i =0; i <9; i++)
			{
				col_validity[c] = check_valid_cols(i, board);
				System.out.print(col_validity[c]+" "); //print 1 for valid column, print -1 for invalid column
				c++;
				
			}
			System.out.println();
			return;
			
			
		}
		else if(Thread.currentThread().getName().equalsIgnoreCase("t3")) 
		{
			
		  System.out.println("Thread t3 is running to determine subgrid-1 validity:");
		  grids_validity[g++] =	check_grid_validity(arr_1); //check validity for subgrid -1
		  return;
		  
		}
		else if(Thread.currentThread().getName().equalsIgnoreCase("t4"))
		{
			 System.out.println("Thread t4 is running to determine subgrid-2 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_2); //check validity for subgrid -2
			 return;
			
		}
		else if(Thread.currentThread().getName().equalsIgnoreCase("t5"))
		{
			 System.out.println("Thread t5 is running to determine subgrid-3 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_3); //check validity for subgrid -3
			 return;
		}
		
		else if(Thread.currentThread().getName().equalsIgnoreCase("t6"))
		{
			 System.out.println("Thread t6 is running to determine subgrid-4 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_4); //check validity for subgrid -4
			 return;
		}
		
		else if(Thread.currentThread().getName().equalsIgnoreCase("t7"))
		{
			 System.out.println("Thread t7 is running to determine subgrid-5 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_5); //check validity for subgrid -5
			 
			 return;
		}
		
		else if(Thread.currentThread().getName().equalsIgnoreCase("t8"))
		{
			 System.out.println("Thread t8 is running to determine subgrid-6 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_6); //check validity for subgrid -6
			 return;
		}
		
		else if(Thread.currentThread().getName().equalsIgnoreCase("t9"))
		{
			 System.out.println("Thread t9 is running to determine subgrid-7 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_7); //check validity for subgrid -7
			 return;
		}
		else if(Thread.currentThread().getName().equalsIgnoreCase("t10"))
		{
			 System.out.println("Thread t10 is running to determine subgrid-8 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_8); //check validity for subgrid -8
			 return;
		}
		else if(Thread.currentThread().getName().equalsIgnoreCase("t11"))
		{
			 System.out.println("Thread t11 is running to determine subgrid-9 validity:");
			 grids_validity[g++] =	check_grid_validity(arr_9); //check validity for subgrid -9
			 return;
		}
		
		else if(Thread.currentThread().getName().equalsIgnoreCase("t12")){
			System.out.println("Thread t12 is running to determine Sudoku Validity:");
			 validator(row_validity,col_validity, grids_validity); //sending all validity arrays to Sudoku Validator
			 return;
		}
		else {
			System.out.println("Wrong thread");
		}
			
		
	}	
	
	public static void copyArray(int [][] temp) {
		if(count == 0)
		{
			//arr_1 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_1[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_1));
		}
		else if(count == 1)
		{
            //arr_2 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_2[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_2));
		}
		else if(count == 2)
		{
			//arr_3 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_3[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_3));
		}
		else if(count == 3)
		{
			//arr_4 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_4[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_4));
		}
		else if(count == 4)
		{
			//arr_5 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_5[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_5));
		}
		else if(count == 5)
		{
			//arr_6 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_6[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_6));
		}
		else if(count == 6)
		{
			//arr_7 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_7[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_7));
		}
		else if(count == 7)
		{
			//arr_8 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_8[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_8));
		}
		else if(count == 8)
		{
			//arr_9 = temp.clone();
			for(int i =0; i<temp.length; i++)
			{
				arr_9[i] = temp[i].clone();
			}
			count++;
			System.out.println(Arrays.deepToString(arr_9));
		}
	}
  
	public static void zeroArray(int [][] temp)
	{
		for(int i =0; i<3; i++)
		{
			for(int j=0; j<3;j++)
			{
				temp[i][j] = 0;
			}
		}
	}
	public static void makeSub_Grids() {
		int [][] grid = new int [3][3];
	      
		   for(int i =0; i<9; i+=3)
			{
				for(int j=0; j<9; j+=3)
				{
				    int row=0 , col=0;
					 for(int r = i; r< i+3; r++)
					 {  
						 
						 for(int c= j; c<j+3; c++)
						 {
							
							 grid[row][col] = board[r][c];
							
							 col++;
							 //System.out.print(r+","+c+" "); 
						 }
						 col=0;
						 row++;
					  
					
					 }
					
					 copyArray(grid);
					 zeroArray(grid);
					 
				}
		   
		   
	   }
		
	}
   public static void main(String [] args) throws InterruptedException
   {
	   System.out.println("Making SubGrids:");
	   makeSub_Grids();
	    
	   Thread t1 = new Thread(new Sudoku_thread()); //to check row validity
	   Thread t2 = new Thread(new Sudoku_thread()); //to check column validitiy
	   Thread t3 = new Thread(new Sudoku_thread()); //to check subgrid-1 validity
	   Thread t4 = new Thread(new Sudoku_thread()); //to check subgrid-2 validity
	   
	   Thread t5 = new Thread(new Sudoku_thread()); //to check subgrid-3 validity
	   Thread t6 = new Thread(new Sudoku_thread()); //to check subgrid-4 validity
	   Thread t7 = new Thread(new Sudoku_thread()); //to check subgrid-5 validity
	   Thread t8 = new Thread(new Sudoku_thread()); //to check subgrid-6 validity
	   
	   Thread t9  = new Thread(new Sudoku_thread()); //to check subgrid-7 validity
	   Thread t10 = new Thread(new Sudoku_thread()); //to check subgrid-8 validity
	   Thread t11 = new Thread(new Sudoku_thread()); //to check subgrid-9 validity
	   Thread t12 = new Thread(new Sudoku_thread());  //to check Sudoku board validity
	   
	   t1.setName("t1");
	   t2.setName("t2");
	   t3.setName("t3");
	   
	   t4.setName("t4");
	   t5.setName("t5");
	   t6.setName("t6");
	
	   t7.setName("t7");
	   t8.setName("t8");
	   t9.setName("t9");
	   
	   t10.setName("t10");
	   t11.setName("t11");
	   t12.setName("t12");
	   
	 
	   
	 
	   
	   System.out.println("***************************Row Validity*******************************");
	   t1.start();
	  
	   t1.join(); //wait for this thread to die
	 
	   System.out.println("****************************Column Validity***************************");
       
	   
	   t2.start();
	   
	   t2.join();//wait for this thread to die
	 
	  
	   
	   System.out.println("***************************Sub Grids Validity*************************");
	   
	   t3.start();
	   t3.join();
	   
	   t4.start();
	   t4.join();
	
	   t5.start();
	   t5.join();
	   
	   t6.start();
	   t6.join();
	   
	   t7.start();
	   t7.join();
	   
	   t8.start();
	   t8.join();
	   
	   t9.start();
	   t9.join();
	   
	   t10.start();
	   t10.join();
	   
	   t11.start();
	   t11.join();
	 
	   
	   /* wait for all the threads to die before determining Sudoku validity*/
	
	   System.out.println("****************************************************************");
	   System.out.println("**************************VALIDITY******************************");
	   
	   t12.start();
	   
	   System.out.println("Add changes for jenkins execution build trigger poll SCM(source code management)");
	   
	   
}
	
}


