import java.util.Arrays;

public class Sort{

	public static void selectionSort(int[] input){
		int lowest;

		for (int i=0; i<input.length; i+=1) {
			lowest = i;	
			for (int j=i+1; j<input.length; j+=1) {
				if(input[j] < input[lowest]) lowest = j;
			}
			swap(input, i, lowest);
		}

	}

	public static void swap(int[] input, int index1, int index2){
		int temp = input[index1];
		input[index1] = input[index2];
		input[index2] = temp;
	}

	public static void main(String[] args) {
		int[] example = {1, 5, 2, 4, 6, 9, 3, 3, 1};
		System.out.println("Presort: " + Arrays.toString(example));		
		selectionSort(example);
		System.out.println("postsort: " + Arrays.toString(example));		
	}
}