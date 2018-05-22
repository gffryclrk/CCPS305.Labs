import java.util.Arrays;

public class Search{

	public static int binarySearch(int[] in, int search, int start, int end){
		
		int middle = start + (end - start)/2;
		if(middle < 0 || middle > in.length-1) return -1;
		if(in[middle] == search) return middle;
		else if(in[middle] < search) return binarySearch(in, search, middle+1, end);
		else if(in[middle] > search) return binarySearch(in, search, start, middle-1);
		else return -1;
	}

	public static void main(String[] args) {
		int[] example = {4, 5, 9, 2, 3, 1, 5, 6, 5, 1};
		System.out.println(Arrays.toString(example));
		Sort.selectionSort(example);
		System.out.println(Arrays.toString(example));
		System.out.println(binarySearch(example, 9, 0, example.length-1));
		System.out.println(example[binarySearch(example, 2, 0, example.length-1)]);
		System.out.println(binarySearch(example, 100, 0, example.length-1));

	}
}