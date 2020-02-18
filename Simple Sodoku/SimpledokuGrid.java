/*
package simpledoku_solution;

import java.util.*;


public class SimpledokuGrid 
{
	private int				nCellsPerSide;
	private int[][]			values;
	
	
	public SimpledokuGrid(int nCellsPerSide)
	{
		//sets the sides equal to the number indicated
		this.nCellsPerSide = nCellsPerSide;
		//initiates a matrix square of the number of sides
		values = new int[nCellsPerSide][nCellsPerSide];
	}
	
	
	// This is called a "copy constructor". A copy ctor has 1 arg, which is the same type as
	// the current class. The ctor should make the new instance look just like the "source"
	// instance. CAUTION: The source and the current grid should each have their own "values"
	// array. So don't say "this.values = source.values". You have to create a new values
	// array, and copy numbers from source.values into the new array, one at a time.
	public SimpledokuGrid(SimpledokuGrid source)
	{
		//sets the number of cells equal to the number of cells in the source
		this.nCellsPerSide = source.nCellsPerSide;
		
		//sets the values of the matrix square to the number of cells
		this.values = new int[source.nCellsPerSide][source.nCellsPerSide];
		
		//goes though vertical values
		for(int i = 0; i < source.nCellsPerSide; i++) {
			//goes through the horizontal values
			for(int j = 0; j< source.nCellsPerSide; j++) {
				//inserts values from the source into the new values matrix 
				this.values[i][j] = source.values[i][j];
			}
		}
	}	
	
	
	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal().
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> ints)
	{
		//System.out.println("ints.size(): " + ints.size());
		//this calls the 0th index to the second to last index in ints
		for(int i = 0; i < ints.size() - 1; i++) {
			// this calls the index from i to the last index of ints
			for (int j = i+1; j < ints.size(); j++) {
				//System.out.println("first for loop:" + i + " vs " + j);
				//this compares the index to each other
				//if the indexes are equal to each other then it will check if the value is equal to zero
				if (ints.get(i).equals(ints.get(j))) {
					//if the value is not zero then it would return true
					if(!ints.get(i).equals(0)) {
						//System.out.print(i+" vs " + j);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	public boolean isLegal()
	{
		// Check all rows. For each row, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		
		//first index for the nth row
		for(int i = 0; i < nCellsPerSide; i++) {
			//create a temporary Int ArrayList
			ArrayList<Integer> ints = new ArrayList<Integer> (nCellsPerSide);
			//second index for the nth index in each row
			for(int j = 0; j < nCellsPerSide; j++) {
				//stores the values into the ArrayList
				ints.add(values[i][j]);
			}
			//checks if it non zero repeats
			if (containsNonZeroRepeats(ints) == true) {
				return false;
			}
		}
			
		
		


		// Check all columns. For each column, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		
		//first index for the nth index of each row (the column)
		for(int i = 0; i < nCellsPerSide; i++) {
			//creates a temporary ArrayList
			ArrayList<Integer> ints = new ArrayList<Integer> (nCellsPerSide);
			//second index for each row
			for(int j = 0; j < nCellsPerSide; j++) {
				//inserts the values
				ints.add(values[j][i]);
			}
			//checks if it has non zero repeats
			if (containsNonZeroRepeats(ints) == true) {
				return false;
			}
		}

		
		// Check top-left to bottom-right diagonal.
			//create an ArrayList
			ArrayList<Integer> newarr = new ArrayList<Integer> (nCellsPerSide);
			//for top-left to bottom-right diagonally, the index would be the same
			for(int i = 0; i < nCellsPerSide; i++) {
					//adding the values into the ArrayList
					newarr.add(values[i][i]);
				}
			//checks if it has non zero repeats
			if (containsNonZeroRepeats(newarr) == true) {
				return false;
			}
		
		
		// Check top-right to bottom-left diagonal. 
			//creates a new ArryaList
			ArrayList<Integer> newarray = new ArrayList<Integer> (nCellsPerSide);
			//inserts value from the matrix from top right to bottom left diagonal
			for(int i = 0; i < nCellsPerSide; i ++) {
				newarray.add(values[i][nCellsPerSide - i - 1]);
			}
			//checks if ArrayList has non zero repeats
			if (containsNonZeroRepeats(newarray) == true) {
				return false;
			}
			
		
		// If we haven't returned yet, this grid must be legal.
		return true;
	}
	
	// Returns true if all members of the values[][] array are non-zero.
	public boolean isFull()
	{
		//counter for row
		for(int i = 0; i < nCellsPerSide; i++) {
			//counter for nth value in row
			for(int j = 0; j < nCellsPerSide; j++) {
				//checks the value at index
				if (values[i][j] == 0) {
					return false;
				}
			}
		}
		//if there are no zeros then it is true
		return true;
	}	
	
	
	
	// Returns the Evaluation corresponding to the state of this grid. The return will be
	// Evaluation.ABANDON, Evaluation.ACCEPT, or Evaluation.CONTINUE. To determine what to
	// return, call isLegal() and isFull().
	public Evaluation evaluate()
	{
		if (!isLegal())
			return Evaluation.ABANDON;
		else if (isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;
	}
	
	
	// Returns a size=2 array of ints that index the next empty cell in the values[][] array.
	// The 2 ints in the returned array are the first and second indices into the values[][] array.
	// Returns null if this grid is full.
	private int[] getIndicesOfNextEmptyCell()
	{
		int[] xy = new int[2];
		
		for (xy[0]=0; xy[0]<nCellsPerSide; xy[0]++)
			for (xy[1]=0; xy[1]<nCellsPerSide; xy[1]++)
				if (values[xy[0]][xy[1]] == 0)
					return xy;
		
		return null;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list containing nCellsPerSide grids that look like the 
	// current grid, except the empty member contains 1, 2, 3 .... nCellsPerSide. 
	// 
	//
	// Example: if this grid = 12300
	//                         00000
	//                         00000
	//                         00000
	//                         00000
	//
	// Then the returned array list contains:
	//
	//      12310        12320        12330        12340        12350
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//
	ArrayList<SimpledokuGrid> generateNextGrids()
	{		
		int[] indicesOfNext = getIndicesOfNextEmptyCell();
		ArrayList<SimpledokuGrid> nextGrids = new ArrayList<SimpledokuGrid>();
		// Generate some grids and put them in nextGrids. Be sure that every
		// grid is a separate object.
		
		//if everything is filled then it doesn't need to generate more test grids
		if(this.isFull()) {
			return null;
		}
		else {
			//for loop for making the number of test grids and the values to assign them to
			for (int i = 1; i <= this.nCellsPerSide; i++) {
				//create a newgrid that contains the same values as the "this" Simpledoku
				SimpledokuGrid newgrid = new SimpledokuGrid(this);
				//using the values from indicesOfNext and inserting a new number
				newgrid.values[indicesOfNext[0]][indicesOfNext[1]] = i;
				//add the newgrid to the ArrayList
				nextGrids.add(newgrid);
		
			}
	}
		//nextGrids is returned and occur again and again till solve() is satisfied
		return nextGrids;
	}
	
	
	// Use this for debugging if it's helpful.
	public String toString()
	{
		String s = "";
		for (int j=0; j<nCellsPerSide; j++)
		{
			for (int i=0; i<nCellsPerSide; i++)
			{
				if (values[j][i] == 0)
					s += ".";
				else
					s += ("" + values[j][i]);
			}
			s += "\n";
		}
		return s;
	}

	
	}
	*/



package simpledoku_solution;

import java.util.*;


public class SimpledokuGrid 
{
	private int				nCellsPerSide;
	private int[][]			values;
	
	
	public SimpledokuGrid(int nCellsPerSide)
	{
		this.nCellsPerSide = nCellsPerSide;
		values = new int[nCellsPerSide][nCellsPerSide];
	}
	
	
	// This is called a "copy constructor". A copy ctor has 1 arg, which is the same type as
	// the current class. The ctor should make the new instance look just like the "source"
	// instance. CAUTION: The source and the current grid should each have their own "values"
	// array. So don't say "this.values = source.values". You have to create a new values
	// array, and copy numbers from source.values into the new array, one at a time.
	public SimpledokuGrid(SimpledokuGrid source)
	{
		 nCellsPerSide = source.nCellsPerSide;
		 values = new int[nCellsPerSide][nCellsPerSide];
		 for (int j=0; j< source.nCellsPerSide; j++)
			{	
				for (int i=0; i< source.nCellsPerSide; i++) {
					this.values[i][j] = source.values[i][j];
				}
			}

	}	
	
	
	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal().
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> ints)
	{
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i: ints)
			if (i != 0  &&  set.contains(i))
				return true;
			else
				set.add(i);
		return false;
	}
	
	public boolean isLegal()
	{
		// Check all rows. For each row, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		
		for (int i=0; i< nCellsPerSide; i++)
		{	
			ArrayList<Integer> valsInRow = new ArrayList<Integer>();
			for (int j=0; j< nCellsPerSide; j++) {
				valsInRow.add(values[i][j]);
			}
			if (containsNonZeroRepeats(valsInRow)) {
				return false;
			}
		}


		// Check all columns. For each column, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		
		for (int i=0; i<nCellsPerSide; i++)
		{
			ArrayList<Integer> valsInCol = new ArrayList<Integer>();
			for (int j=0; j<nCellsPerSide; j++) {
				valsInCol.add(values[j][i]);
			}
			if (containsNonZeroRepeats(valsInCol))
			{
				return false;
			}
		}
		
		
		// Check top-left to bottom-right diagonal. 
		
		ArrayList<Integer> valsInDiag = new ArrayList<Integer>();
		for (int i=0; i<nCellsPerSide; i++) {
			valsInDiag.add(values[i][i]);
		if (containsNonZeroRepeats(valsInDiag)) {
			return false;
		}
		}
		
		
		// Check top-right to bottom-left diagonal. 
		
		ArrayList<Integer> valsInOtherDiag = new ArrayList<Integer>();
		for (int i=0; i< nCellsPerSide; i++) {
			valsInOtherDiag.add(values[i][nCellsPerSide-i-1]);
		if (containsNonZeroRepeats(valsInOtherDiag)) {
			return false;
		}
		}
		
		// If we haven't returned yet, this grid must be false.//must be legal
		return true;
	}

	

	
	// Returns true if all members of the values[][] array are non-zero.
	public boolean isFull()
	{	
		for (int i=0; i<nCellsPerSide; i++) {
			for (int j=0; j<nCellsPerSide; j++) {
				if (values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
		

	}	
	
	
	
	// Returns the Evaluation corresponding to the state of this grid. The return will be
	// Evaluation.ABANDON, Evaluation.ACCEPT, or Evaluation.CONTINUE. To determine what to
	// return, call isLegal() and isFull().
	public Evaluation evaluate()
	{
		if (!isLegal())
			return Evaluation.ABANDON;
		else if (isFull())
			return Evaluation.ACCEPT;
		else
			return Evaluation.CONTINUE;
	}
	
	
	// Returns a size=2 array of ints that index the next empty cell in the values[][] array.
	// The 2 ints in the returned array are the first and second indices into the values[][] array.
	// Returns null if this grid is full.
	private int[] getIndicesOfNextEmptyCell()
	{
		int[] xy = new int[2];
		
		for (xy[0]=0; xy[0]<nCellsPerSide; xy[0]++)
			for (xy[1]=0; xy[1]<nCellsPerSide; xy[1]++)
				if (values[xy[0]][xy[1]] == 0)
					return xy;
		
		return null;
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list containing nCellsPerSide grids that look like the 
	// current grid, except the empty member contains 1, 2, 3 .... nCellsPerSide. 
	// 
	//
	// Example: if this grid = 12300
	//                         00000
	//                         00000
	//                         00000
	//                         00000
	//
	// Then the returned array list contains:
	//
	//      12310        12320        12330        12340        12350
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//      00000        00000        00000        00000        00000
	//
	ArrayList<SimpledokuGrid> generateNextGrids()
	{		
		int[] indicesOfNext = getIndicesOfNextEmptyCell();
		ArrayList<SimpledokuGrid> nextGrids = new ArrayList<SimpledokuGrid>();
		// Generate some grids and put them in nextGrids. Be sure that every
		// grid is a separate object
		for (int x = 1; x <= nCellsPerSide; x++) {
			SimpledokuGrid grid = new SimpledokuGrid(this); 
			grid.values[indicesOfNext[0]][indicesOfNext[1]] = x; 
			nextGrids.add(grid);
		}
		
		return nextGrids;
	} 
	
	
	// Use this for debugging if it's helpful.
	public String toString()
	{
		String s = "";
		for (int j=0; j<nCellsPerSide; j++)
		{
			for (int i=0; i<nCellsPerSide; i++)
			{
				if (values[j][i] == 0)
					s += ".";
				else
					s += ("" + values[j][i]);
			}
			s += "\n";
		}
		return s;
	}
}
