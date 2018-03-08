# multiplication
There is only 1 file: multiplication.java

Explanation of file:

1 Function: public static int[] doMultiply(int[] arrayOfDigits1, int[] arrayOfDigits2)

1 Main method: serves as quick testing. Calls doMultiply with different arrays and prints result. 

Function can be broken up into steps:
  
Part 1: Calculating 2D Array of products (productMatrix).

	Part 1; Step 1: Setting up dimensions, variables, and outer (array2) loop.
	
	Part 1; Step 2: Setting up each row's unused least significant digits.
	
	Part 1; Step 3: Setting up each row's used digits (from product).
	
	Part 1; Step 4: Extra case: adding the remaining carry to next column.
	
	Part 1; Step 5: Setting up each row's unused most significant digits.
	
Part 2: Calculating Sum of 2D array (productMatrix).

Important Note = The function only accepts single digit integer elements per array entry. This is to simulate multiplication.
