package a01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[] grid;
	private int n;
	private WeightedQuickUnionUF uni;
	private WeightedQuickUnionUF uniIsFull;
	
	public Percolation(int n) {
		if (n <= 0) throw new IllegalArgumentException("Value cannot be less than or equal to 0");
		
		this.n = n;
		
		n = (n * n) + 2;
		uni = new WeightedQuickUnionUF(n);
		uniIsFull = new WeightedQuickUnionUF(n);
		
		grid = new boolean[n];
	}
	
	public void open(int i, int j) {
		if (i < 0 || i > (n - 1) || j < 0 || j > (n - 1)) throw new IndexOutOfBoundsException("Values are out of the range");
		
		int coords = return1DValue(i,j);
		grid[coords] = true;
		
		if (j == 0 || j == (this.n - 1)) {
			if (j == 0) {
				uni.union(0, return1DValue(i,j));
				uniIsFull.union(0, return1DValue(i,j));
			}
			else {
				uni.union((grid.length - 1), return1DValue(i,j));
			}
		}
		
		if (i - 1 >= 0) {
			if (isOpen(i - 1, j)) {
				uni.union(coords, return1DValue((i-1),j));
				uniIsFull.union(coords, return1DValue((i-1),j));
			}
		}
		if (i + 1 < n - 1) {
			if (isOpen(i + 1, j)) {
				uni.union(coords, return1DValue((i+1),j));
				uniIsFull.union(coords, return1DValue((i+1),j));
			}
		}
		if (j - 1 >= 0) {
			if (isOpen(i, j - 1)) {
				uni.union(coords, return1DValue(i,(j-1)));
				uniIsFull.union(coords, return1DValue(i,(j-1)));
			}
		}
		if (j + 1 <= n - 1) {
			if (isOpen(i, j+ 1)) {
				uni.union(coords, return1DValue(i,(j+1)));
				uniIsFull.union(coords, return1DValue(i,(j+1)));
			}
		}
	}
	
	public boolean isOpen(int i, int j) {
		if (i < 0 || i > (n - 1) || j < 0 || j > (n - 1)) throw new IndexOutOfBoundsException("Values are out of the range");
		return grid[return1DValue(i,j)];
	}
	
	public boolean isFull(int i, int j) {
		if (i < 0 || i > (n - 1) || j < 0 || j > (n - 1)) throw new IndexOutOfBoundsException("Values are out of the range");
//		return uniIsFull.connected(0, return1DValue(i,j));
		return uniIsFull.find(0) == uniIsFull.find(return1DValue(i,j));
	}
	
	public boolean percolates() {
		return (uni.find(0) == uni.find(grid.length - 1));
	}
	
	private int return1DValue(int x, int y) {
		if (x == 0 && y == 0) {
			return 1;
		}
		return (((this.n * x) + y) + 1);
	}
	
	public static void main(String[] args) {
		Percolation perc = new Percolation(5);
		System.out.println("Percolates? (everything closed): " + perc.percolates());
//		perc.open(2,0);
//		perc.open(2,1);
//		perc.open(2,2);
//		perc.open(2,3);
//		perc.open(2,4);
//		perc.open(0, 0);
//		perc.open(4, 4);
//		perc.open(0, 4);
//		System.out.println("4,4 is full? " + perc.isFull(4,4));
//		System.out.println("0,4 is full? " + perc.isFull(0,4));
//		System.out.println("2,2 is full? " + perc.isFull(2, 2));
//		System.out.println("2,4 is full? " + perc.isFull(2, 4));
		
//		System.out.println("is open? 2,1: " + perc.isOpen(2, 1));
//		System.out.println("is open? 6,6: " + perc.isOpen(6, 6));
		
//		perc.open(4, 4);
//		perc.open(4, 3);
//		perc.open(4,2);
//		perc.open(4, 1);
//		perc.open(4, 0);
//		perc.open(0, 0);
//		perc.open(0, 4);
//		System.out.println("Percolates?: " + perc.percolates());
//		System.out.println("Is full? " + perc.isFull(0, 4));
//		System.out.println("is open? " + perc.isOpen(0,4));
		
//		perc.open(3, 0);
//		perc.open(3, 1);
//		perc.open(3, 2);
//		perc.open(2, 3);
//		perc.open(3, 4);
//		perc.open(3,3);
//		
//		System.out.println("isFull?: " + perc.isFull(2, 3));
//		System.out.println("Percolates?: " + perc.percolates());
		
		perc.open(4, 4);
		perc.open(4, 3);
		perc.open(4,2);
		perc.open(4, 1);
		perc.open(4, 0);
		
		System.out.println("isFull?: " + perc.isFull(0, 0));
		
	}

}