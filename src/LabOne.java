import java.util.*;

public class LabOne {

	public double sumLessMin(double[] nums) {
		double sum = 0;
		int min, max;
		min = max = 0;
		// for(double n: nums) {
		for (int i = 0; i < nums.length; i+= 1){
			// Decided to keep the index here instead of the value itself
			// Of course, the value could also be stored. 
			if(nums[i] < nums[min]) min = i;
			if(nums[i] > nums[max]) max = i;
			sum += nums[i];
		}
		// Return the sum of all numbers less the smallest.
		return sum - nums[min];
		
	}
	
	public void switchArray(int[] nums) {
		// This method changes the array in place

		System.out.println("Q2: Input array before switch: " + Arrays.toString(nums));
		
		float a1 = 0; // average 1 (first half)
		float a2 = 0; // average 2 (second half)
		int tmp;
		
		for(int i = 0; i < (nums.length / 2); i+=1) {
//			tmp = nums[nums.length - 1 - i];
			tmp = nums[i];
			nums[i] = nums[(nums.length / 2) + i];
			nums[(nums.length / 2) + i] = tmp;
			a1 += nums[i];
			a2 += nums[(nums.length / 2) + i];			
		}
		System.out.println("Input array after switch: " + Arrays.toString(nums));

		System.out.println("Average 1st Half: " + a1 / (nums.length / 2));
		System.out.println("Average 2nd Half: " + a2 / (nums.length / 2));
	}

	public void arrayEvenSort(int[] nums){
		// This method also re-arranges the array in place
		int oi = -1;
		int tmp;

		for (int i=0; i<nums.length; i+=1) {
			if(nums[i] % 2 != 0 && oi < 0) oi = i;
			if(nums[i] % 2 == 0 && oi >= 0){
				tmp = nums[oi];
				nums[oi] = nums[i];
				nums[i] = tmp;
				oi = i;
			}
		}
	}

	public static void catChar(char[][] c1, char[][] c2){
		// StringBuilder[] rs = new StringBuilder("")[2];
		for (int i = 0; i < c1[0].length; i+=1) {
			StringBuilder s1 = new StringBuilder("");
			StringBuilder s2 = new StringBuilder("");
			for (int j = 0; j < c1.length; j+=1) {
				s1.append(c1[j][i]);
				s2.append(c2[j][i]);
			}
			System.out.println("Column " + i + ": " + s1.toString() + s2.toString());
		}
	}

	public static int[][] multiplyMatrix(int[][] m1, int[][] m2){
		// Note that not all matrices can be multiplied
		// There should be some error checking to ensure that
		// the dimensions correspond to matrices that can be multiplied.
		// m1 dimensions: m x n
		// m2 dimensions: n x o
		// result dimensions: m x o
		int[][] result = new int[m1.length][m1[0].length];

		for(int i=0; i<m1.length; i+=1){
			for(int j=0; j<m2[i].length; j+=1){
				int dp = 0;
				for(int h=0; h<m2[i].length; h+=1){
					dp += m1[j][h] * m2[h][i];
				}
				result[j][i] = dp;
			}
		}

		return result;

	}
	public static String twoDeeArrayToString(int[][] a) {
		StringBuilder sb = new StringBuilder("");
		for(int i=0; i<a.length; i+=1) {
			for(int j = 0; j<a[i].length; j+=1) {
				sb.append(a[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		LabOne lo = new LabOne();
		
		// Q1
		double[] q1 = { 8, 7 , 8.5, 9.5, 7, 4, 10 };
		System.out.println("Q1: Sum all but minimum value: " + lo.sumLessMin(q1));

		// Q2
		int[] q2 = {9,13,21,4,11,7,1,3};		
		lo.switchArray(q2);

		// Q3
		int[] q3 = {1,4,14,2,1,3,5,6,23};
		System.out.println("Q3: Input array before arrayEvenSort: " + Arrays.toString(q3));
		lo.arrayEvenSort(q3);
		System.out.println("Input array after arrayEvenSort: " + Arrays.toString(q3));

		// Q4
		char[][] q4_1 = {
			{'a','b','c','d','e'},
			{'f','g','h','i','j'},
			{'k','l','m','n','o'},
			{'p','q','r','s','t'},
			{'u','v','w','x','y'}
		};
		
		char[][] q4_2 = {
			{'z','y','x','w','v'},
			{'t','s','r','q','p'},
			{'o','n','m','l','k'},
			{'j','i','h','g','f'},
			{'e','d','c','b','a'}
		};
		System.out.println("Q4: catChar\n");
		catChar(q4_1, q4_2);
		
		// Q5
		int[][] q5_1 = {
			{1,2,3},
			{4,5,6},
			{7,8,9}
		};
		int[][] q5_2 = {
			{3,1,3},
			{0,2,1},
			{7,8,9}
		};
		String q5_s = twoDeeArrayToString(multiplyMatrix(q5_1, q5_2));
		System.out.println("Q5: Array Multiplication\n" + q5_s);

	}


}
