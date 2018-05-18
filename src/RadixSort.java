/*
 * CCPS305 RadixSort Lab 04
 * Implementation based on example from Java Foundations 4th Edition
 */

import java.util.*;
import java.io.*;

public class RadixSort{
	
	public static void radixSort(int r, List<Integer[]> v) {
		
		
		Queue<Integer[]>[] queues = (java.util.LinkedList<Integer[]>[])(new java.util.LinkedList[r]);
		for(int i = 0; i < queues.length; i+=1){
			queues[i] = (Queue<Integer[]>)(new java.util.LinkedList<Integer[]>());
		}

		int num;
		int d = v.get(0).length-1; // Assumes all vector input arrays are of same length

		for (int pos = 0; pos <= d; pos+=1) {
			for (int scan = 0; scan < v.size(); scan+=1) {
				Integer[] get = v.get(scan);
				int dig = get[d-pos];
				queues[dig].offer(get);
			}
			
			
			
			num = 0;
			for (int digitVal=0; digitVal < queues.length; digitVal += 1) {
				while(!(queues[digitVal].isEmpty())){
					v.set(num, queues[digitVal].remove());
					num+=1;
				}
			}
			
			/* Uncomment to output each step (interesting) */
			// System.out.println("\n\nPos " + pos);
			// v.forEach(x -> System.out.println(Arrays.toString(x)));
			
		}

	}

	public static void main(String[] args) {
		/*
		 * For this lab I decided to read the vectors from a source textfile (RadixInput.txt)
		 * The output is to std. out but you could just as easily change this to another
		 * textfile.
		 * Instead of Strings in the lecture slides example I decided to work with Integer arrays
		 * to make things a little more interesting. The main method does all the pre-processing
		 * to set up the data in such a way: Reads a line of space separated integers and 
		 * puts them into an integer array that is then passed to the RadixSort method. 
		 */

		try{
			Scanner in = new Scanner(new File("RadixInput.txt"));

			int k = Integer.parseInt(in.nextLine());
			int RANGE = 10; // base 10: 0, 1, 2 , ... , 9
			
			List<Integer[]> li = new ArrayList<>();
			
			/* I was experimenting with different sized input files
			 * and so set up this code to read all the lines of the input
			 * (as opposed to just the number of lines dictated by the integer
			 * in the first line). If you wanted to only read k lines, you could
			 * change the loop below to only run k times.   
			 */
			while(in.hasNext()){
				String[] split = in.nextLine().split(" ");
				Integer[] ints = new Integer[split.length];
				for(int i = 0; i<split.length; i+=1) {
					ints[i] = Integer.parseInt(split[i]);
				}
				
				li.add(ints);

			}
			System.out.println("Input:");
			System.out.println(k);
			li.forEach(x -> System.out.println(Arrays.toString(x)));
			
			radixSort(RANGE, li);
			System.out.println("\nOutput:");
			li.forEach(x -> System.out.println(Arrays.toString(x)));
			
			in.close();

		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}


	}
}