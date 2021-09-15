package a01;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Creates and iterates through objects of type Percolation to
 *  provide an estimate of the percolation threshold.
 * 
 * @author Cannon J & Ethan Moore
 *
 */
public class PercolationStats {
	private int count;
	private Percolation perc;
	private int T;
	private double[] pValues;
	
	/**
	 * Requires two arguments, N and T. N is used to create a new object
	 * of type Percolation of size N by N. T is the number of times the
	 * simulation will be run. It randomly opens squares in the grid until
	 * the system percolates. It counts how many blocks are opened to
	 * generate statistics about it.
	 * 
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) throw new IllegalArgumentException("N & T must be greater than 0");
		this.T = T;
		this.pValues = new double[T];
		this.count = 0;
		
		for (int i = 0; i < T; i++) {
			this.perc = new Percolation(N);
			while (!perc.percolates()) {
				int x = StdRandom.uniform(N);
				int y = StdRandom.uniform(N);
				if (!perc.isOpen(x, y)) {
					perc.open(x, y);
					count++;
				}
			}
			double pStar = (double)count / (N * N);
			count = 0;
			pValues[i] = pStar;
		}
	}
	
	/**
	 * @return sample mean of percolation threshold
	 */
	public double mean() {
		return StdStats.mean(pValues);
	}
	
	/**
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev() {
		return StdStats.stddev(pValues);
	}
	
	/**
	 * @return low endpoint of 95% confidence interval
	 */
	public double confidenceLow() {
		double mean = StdStats.mean(pValues);
		double stddev = StdStats.stddev(pValues);
		return mean - (1.96 * stddev) / Math.sqrt(T);
	}
	
	/**
	 * @return  high endpoint of 95% confidence interval
	 */
	public double confidenceHigh() {
		double mean = StdStats.mean(pValues);
		double stddev = StdStats.stddev(pValues);
		return mean + (1.96 * stddev) / Math.sqrt(T);
	}
	
	public static void main(String[] args) {
		PercolationStats percStats = new PercolationStats(200, 100);
		
		System.out.println("mean: " + percStats.mean());
		System.out.println("stddev: " + percStats.stddev());
		System.out.println("confidenceLow: " + percStats.confidenceLow());
		System.out.println("confidenceHigh: " + percStats.confidenceHigh());
	}
}

