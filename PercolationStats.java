/**
 * @author Sadanand Kulkarni
 *
   Assignment details :
   
 * By repeating this computation experiment T times and averaging the results, we obtain a more accurate estimate of the percolation threshold. Let xt be the fraction of open sites in computational experiment t. The sample mean μ provides an estimate of the percolation threshold; the sample standard deviation σ measures the sharpness of the threshold.

    Estimating the sample mean and variance 
    Assuming T is sufficiently large (say, at least 30), 
    the following provides a 95% confidence interval for the percolation threshold:
    95% confidence interval for percolation threshold 

To perform a series of computational experiments, create a data type PercolationStats with the following API.

    public class PercolationStats {
       public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
       public double mean()                     // sample mean of percolation threshold
       public double stddev()                   // sample standard deviation of percolation threshold
       public double confidenceLo()             // returns lower bound of the 95% confidence interval
       public double confidenceHi()             // returns upper bound of the 95% confidence interval
       public static void main(String[] args)   // test client, described below
    }

The constructor should throw a java.lang.IllegalArgumentException if either N ≤ 0 or T ≤ 0. 
 */
public class PercolationStats {

	     //private int size = 0;
	     private double[] means; // Storing std mean values
	     private double T;
	     
	     public PercolationStats(int N, int T){    // perform T independent computational experiments on an N-by-N grid
	    	 //size = (N * N);
	    	 
	    	 if (N <= 0 || T <= 0)
	             throw new java.lang.IllegalArgumentException();
	    	 
	    	 this.T= T;
	    	 means = new double[T];
	      }
		   public double mean() {                     // sample mean of percolation threshold
			   return StdStats.mean(means);
		   }
		   public double stddev() {
			   return StdStats.stddev(means);
		   }	    
		   public double confidenceLo(){             // returns lower bound of the 95% confidence interval
			  return( StdStats.mean(means) - ((1.96 * StdStats.stddev(means)) / Math.sqrt(T)));
		   }	   
		   public double confidenceHi() {             // returns upper bound of the 95% confidence interval
			   return( StdStats.mean(means) + ((1.96 * StdStats.stddev(means)) / Math.sqrt(T)));
		   }
		   
		   
		   //confidenceLoVar = meanVar - ((1.96*stddevVar) / Math.sqrt(T));
	       //confidenceHiVar = meanVar + ((1.96*stddevVar) / Math.sqrt(T));
		   
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);         // N-by-N percolation system
        int T = Integer.parseInt(args[1]);        // T number of operations

        // TODO : add check for N and T,
        if (N <= 0 || T <= 0)
           throw new java.lang.IllegalArgumentException();

        PercolationStats percstat = new PercolationStats(N, T);	
        int a = 1;  // Grid indexes starts with 1 
        int b = 1;
        int opensites = 0;
        
        for ( int i = 0; i < T; i++) {
        	Percolation perc = new Percolation(N);
        	opensites = 0;
        	while (!perc.percolates()){
        		a = StdRandom.uniform(1, N+1);  // for uniform API, N is exclusive hence pass N+1;
            	b = StdRandom.uniform(1, N+1);
            	
            	if (!perc.isOpen(a, b)){
        			opensites ++;
        			perc.open(a, b);
        		}else
        			continue;  // site is already open
        	}
        	   
        	    double mean = (double)opensites/ (double) (N*N); 
    			//System.out.println(" mean is " + mean);
    			//System.out.println(" number of open sites are " + opensites);
    			percstat.means[i] = mean;  // It should have values of all T operations
    		 

        } // end of For..

 		System.out.println(" Mean is                   ="  + percstat.mean());
 		System.out.println(" stddev is                 ="  + percstat.stddev());
 		System.out.println(" 95% confidence interval   ="  + percstat.confidenceLo() +"," + percstat.confidenceHi());
 		//System.out.println(" ConfHigh ="  + percstat.confidenceHi());

	}// end of main...

}
