package multiplication;


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Multiply {

	// Primary Example used:
	//  123 	<---- arrayOfDigits1
	//x 1234	<---- arrayOfDigits2
	//=    492 					
	//    3690					
	//   24600		[productMatrix]
	//+ 123000					
	//= 151782	<---- arrayOfDigits3
	
	//This works for base 10. To work for another base, change baseValue
	
	public static int baseValue = 10;
	
	
	public static int[] doMultiply(int[] arrayOfDigits1, int[] arrayOfDigits2)
	{
		/*Fuction can be broken up into steps:
			Part 1: Calculating 2D Array of products (productMatrix).
				Part 1; Step 1: Setting up dimensions, variables, and outer (array2) loop.
				Part 1; Step 2: Setting up each row's unused least significant digits.
				Part 1; Step 3: Setting up each row's used digits (from product).
				Part 1; Step 4: Extra case: adding the remaining carry to next column.
				Part 1; Step 5: Setting up each row's unused most significant digits.
			Part 2: Calculating Sum of 2D array (productMatrix).
		*/
	
		//********************Part 1: Calculating 2D Array of products. 
				// Original Example: 123x1234  = 
				//	0000492 
				//	0003690
				//	0024600
				//	0123000
				// 4 rows, 7 columns.
		
		
		//***Part 1; Step 1: Setting up dimensions, variables, and outer (array2) loop:
		int rowSize = arrayOfDigits2.length;
		int columnSize = arrayOfDigits1.length + arrayOfDigits2.length + 1;
		int[][] productMatrix = new int[rowSize][columnSize];
		//Reasoning for dimensions:	
			//row size == # of rows == number of products == arrayOfDigits2.length
			//column size can be variable. 
			//	Ex1: 100 x 99 = 9900 (4 rows)
			//	Ex2: 999 x 99 = 98901 (5 rows).
			// Solution = set as upperbound (arrayOfDigits1.length + arrayOfDigits2.length) 		
		int columnOffset = 0;
		int columnToPlaceDigit = 0;
		for(int array2Index = arrayOfDigits2.length-1; array2Index >= 0 ; array2Index--)
		{	
			int productCarry = 0;
			columnToPlaceDigit = (columnSize - 1) - columnOffset;
			//***Part 1; Step 2: Setting up each row's unused least significant digits.
				//fill least significant digits with 0.
				// 0024600 <- the rightmost 2 0's
				// 0123000 <- the rightmost 3 0's
			for(int leastSigFill = (columnSize-1); leastSigFill > columnToPlaceDigit; leastSigFill--)
			{
				productMatrix[(rowSize-1)-array2Index][leastSigFill] = 0;
			}
			
			//***Part 1; Step 3: Setting up each row's used digits (from product).
				//aka Storing the actual digits (ie:492, 369, 246, 123)
			
			
			for (int array1Index = arrayOfDigits1.length - 1; array1Index >= 0; array1Index--)
			{	
				int product = arrayOfDigits1[array1Index] * arrayOfDigits2[array2Index] + productCarry;
				int digitToAdd = product % baseValue; 
				productCarry = product/baseValue;
				columnToPlaceDigit = columnToPlaceDigit - ((arrayOfDigits1.length - 1)- array1Index);
				productMatrix[(rowSize-1)-array2Index][columnToPlaceDigit] = digitToAdd;
				
				
				//***Part 1; Step 4: Extra case: adding the remaining carry to next column.
				//Now if the last product had a carry digit, we need to add that to the next column.
				if(array1Index == 0)
				{
					if(product < baseValue)
					{
						columnToPlaceDigit++;
						productMatrix[(rowSize-1)-array2Index][columnToPlaceDigit] = product;
						productCarry = 0;
						columnToPlaceDigit--;
					}
					else
					{
						
						columnToPlaceDigit++;
						productMatrix[(rowSize-1)-array2Index][columnToPlaceDigit] = product%baseValue;
						columnToPlaceDigit--;
						productMatrix[(rowSize-1)-array2Index][columnToPlaceDigit] = product/baseValue;
						columnToPlaceDigit--;
					}
				}
			}
	
			//***Part1; Step 5: Setting up each row's unused most significant digits.
			//Now fill the rest of the row's more significant digits with 0's
			while(columnToPlaceDigit >= 0)
			{
				productMatrix[(rowSize-1)-array2Index][columnToPlaceDigit] = 0;
				columnToPlaceDigit--;
			}
			columnOffset++;
			
			
		}
		
		//******** Interlude: Printing the matrix
		for(int i = 0; i < rowSize; i++)
		{
			System.out.println();
			for(int k = 0; k < columnSize; k++)
			{System.out.print(productMatrix[i][k]);}
			
		}
		
		//********************Part 2: Calculating sum of 2D array (sum of products)
		int[] result = new int[columnSize];
		
		int carryOver = 0;
		for (int columnIndex = columnSize -1; columnIndex >= 0; columnIndex--)
		{
			int runningSumOfColumn = 0;
			for(int rowIndex = 0; rowIndex < rowSize; rowIndex++)
			{
				runningSumOfColumn += productMatrix[rowIndex][columnIndex];
			}
			result[columnIndex] = (runningSumOfColumn+carryOver)%baseValue;
			//at this point the length can be variable. If there are many products/rows, this number can balloon.
			carryOver = runningSumOfColumn/baseValue;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		//FIRST TEST
		int[] array1 = new int[3];
		array1[0] = 1; array1[1] = 2; array1[2] = 3;
		
		int[]array2 = new int[4];
		array2[0] = 1; array2[1] = 2; array2[2] = 3; array2[3] = 4;
		
		int[] result = doMultiply(array1, array2);
		System.out.println();
		for(int i = 0; i < result.length; i++)
		{
			System.out.print(result[i]);
		}
		
		System.out.println();
		
		//SECOND TEST
		array1[0] = 1; array1[1] = 2; array1[2] = 3;
		array2[0] = 9; array2[1] = 9; array2[2] = 9; array2[3] = 9;
		result = doMultiply(array1, array2);
		System.out.println();
		for(int i = 0; i < result.length; i++)
		{
			System.out.print(result[i]);
		}
	}
	
}
