

public class LabOne {

	public int sumLessMin(int[] nums) {
		int sum = 0;
		int min = nums[0];
		for(int n: nums) {
			if(n < min) min = n;
			sum += n;
		}
		return sum - min;
		
	}

}
