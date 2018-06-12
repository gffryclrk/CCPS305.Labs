public class RecursionLabs {
	public static int gcd(int p, int q){
		if(q <= 0) return p;
		else return gcd(q, (p % q));
	}

	public static void fibonacci(int n1, int n2, int end){
		// Instead of printing out, you could push this into
		// A collection or other data structure of your choosing.
		// Printing out seemed simplest for this case. 
		System.out.print(n1 + ",");
		if(n1 + n1 <= end) fibonacci(n2, n1+n2, end);
		else System.out.print(n2 + "\n");
	}

	public static boolean palindrome(String s){
		if(s.length() <= 1) return true;
		else return (s.charAt(0) == s.charAt(s.length() - 1)) && palindrome(s.substring(1, s.length()-1));
	}
	
	
	public static void main(String[] args) {
		System.out.println("54 & 24: " + gcd(54, 24));
		System.out.println("102 & 68: " + gcd(102,68));

		System.out.println("fibonacci");
		fibonacci(0,1,1000);

		System.out.println("Palindrome \"racecar\": " + palindrome("racecar"));
		System.out.println("Palindrome \"dogsandcats\": " + palindrome("dogsandcats"));
		
		/* 
		* If you wanted palindromes with spaces allowed, this is possible.
		* If you want to ignore the spaces this is also possible, simply trim
		* whitespace characters first. You could do this in your method first
		* and then call a private recursive helper method implemented the same
		* as above. 
		*/ 

		String s = "a but tuba";
		System.out.println("Palindrome \"a but tuba\": " + palindrome(s));
		System.out.println("Palindrome \"a but tuba\": " + palindrome(s.replaceAll("\\s+", "")));
	}
}