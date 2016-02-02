/**
 * 
 */
package com.example.problem;

/**
 * @author Pankaj Dighe
 * 
 * Type: Dynamic Programming
 * Problem: http://www.spoj.com/problems/MCOINS/
 * 
 * 
 */
public class McoinPropblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		findWinner(3, 4, 10);
	}

	//M has not been used in this implementation
	public static void findWinner(int L, int K, int M) {

		boolean[] a = new boolean[257146];
		a[1] = true;

		for (int i = 1; i <= 88888; i++) {

			if (i - 1 >= 0 && a[i - 1] == false) {
				a[i] = true;
			} else if (i - L >= 0 && a[i - L] == false) {
				a[i] = true;
			} else if (i - K >= 0 && a[i - K] == false) {
				a[i] = true;
			}

		}
		
		int find=12;
		
		if(a[find])
		System.out.println("A");
		else
			System.out.println("B");

	}

}
