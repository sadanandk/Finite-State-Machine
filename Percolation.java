
/**
 *Percolation. 
 *Given a composite systems comprised of randomly distributed insulating and metallic materials: 
 *what fraction of the materials need to be metallic so that the composite system is an electrical conductor? 
 *Given a porous landscape with water on the surface (or oil below), under what conditions will the water be 
 *able to drain through to the bottom (or the oil to gush through to the surface)? 
 *Scientists have defined an abstract process known as percolation to model such situations.

The model. We model a percolation system using an N-by-N grid of sites. 
Each site is either open or blocked. A full site is an open site that can be connected to an open site 
in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates 
if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites 
connected to the top row and that process fills some open site on the bottom row. 
(For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system 
that percolates has a metallic path from top to bottom, with full sites conducting. 
For the porous substance example, the open sites correspond to empty space through which water might flow, 
so that a system that percolates lets water fill open sites, flowing from top to bottom.)

Percolates 
 *
 */
/**
 * @author Sadanand Kulkarni
 *
 */
public class Percolation {

    private boolean[][] id;    // id[i] = parent of i
    private int count;   // number of components
    private int[][] lookup;
    private WeightedQuickUnionUF wquf;
	// template
	/*public class Percolation {
	   public Percolation(int N)              // create N-by-N grid, with all sites blocked
	   public void open(int i, int j)         // open site (row i, column j) if it is not already
	   public boolean isOpen(int i, int j)    // is site (row i, column j) open? isConnected
	   public boolean isFull(int i, int j)    // is site (row i, column j) full? 
	   public boolean percolates()            // does the system percolate? // is connected
	}*/
    
    /**
     * Initializes an empty union-find data structure with N isolated components 0 through N-1.
     * @throws java.lang.IllegalArgumentException if N < 0
     * @param N the number of objects
     */
    public Percolation(int N){
    	count = N; // Grid size.
    	int counter = 1; 
    	id = new boolean[N+1][N+1];  // Array of N* N
    	lookup = new int[N+1][N+1];
    	wquf = new WeightedQuickUnionUF(N*N + 1);
    	for (int i = 1; i <= N; i++) {
    		for (int j = 1; j<= N; j++){
    			id[i][j] = false;  //  false-blocked, true-open
    			lookup[i][j] = counter;
    			counter++;
    		}	
    	}

    }
    
    private boolean isValidIndices(int i, int j){
    	
    	if (((i-1) < 0) || 
    		((j-1) < 0) || 
    		(i > count) || 
    		( j > count))
    	 return false;	
    	 
    	return true;
    }
	
	public void open(int i, int j){
		
	if ( !isValidIndices(i, j))
		throw  new IndexOutOfBoundsException();
	
		 if (!id[i][j])
			 id[i][j]= true; // site is open
		 
		// Connecting with valid neighbor..
		// Right
		if ( isValidIndices(i, j+1) && id[i][j+1] && !wquf.connected(getDigit(i,j), getDigit(i, j+1)))
		   wquf.union(getDigit(i,j), getDigit(i, j+1));
		
		// Left
		if ( isValidIndices(i, j-1) &&  id[i][j-1] && !wquf.connected(getDigit(i,j), getDigit(i, j-1)))
		   wquf.union(getDigit(i,j), getDigit(i, j-1));	
		
		//Bottom
		if ( isValidIndices(i-1, j) &&  id[i-1][j] && !wquf.connected(getDigit(i,j), getDigit(i-1, j)))
		  wquf.union(getDigit(i,j), getDigit(i-1, j));
		
		// TOP
		if ( isValidIndices(i+1, j) &&  id[i+1][j] && !wquf.connected(getDigit(i,j), getDigit(i+1, j)))
			  wquf.union(getDigit(i,j), getDigit(i+1, j));

	}
	
	public boolean isOpen(int i, int j){
		if ( !isValidIndices(i, j))
			throw new IndexOutOfBoundsException();
	    
		if ( id[i][j])
		  return true;
		  
		return false;		
	}

	// Check full condition...
	public boolean isFull(int i, int j){
		if ( !isValidIndices(i,j))
		   throw new IndexOutOfBoundsException();
		
		if (isOpen(i,j)){
			int root = wquf.find(getDigit(i,j));
			for ( int k=1; k<= count; k++){
				  if (wquf.find(getDigit(1,k)) == root)		
					return true;
			}	
		}	
		return false;	
	}
	
	public boolean percolates(){
		// Check bottom rows...
		for ( int k=1; k <= count; k++){
			// check only last row
			if (isFull(count, k)){
				return true;
			}	
		}	
		return false;	
	}
	/**
	 * @param i
	 * @param j
	 * @return converted single digit
	 */
	private int getDigit(int i, int j){
	
		return lookup[i][j];
		//return 0;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Percolation test = new Percolation(20);
		System.out.println("Testing the lookup");
		for ( int i=1; i <=20; i++ ){
			for ( int j =1; j <=20; j++)
				System.out.println( "Testing the lookup " + test.getDigit(i, j));
			
		}

	}

}
