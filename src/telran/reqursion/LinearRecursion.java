package telran.reqursion;

public class LinearRecursion {
	public static long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}
	
	public static long pow(int a, int b) {
		long res = 0;
		if (b < 0) {
			throw new IllegalArgumentException();
		} else if (b == 0) {
			res = 1;
		} else {
			res = a * pow(a, b - 1);
		}
		return res;
	}
	
	public static long power(int a, int b) {
		// code cannot use cycles and *, / operators
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		long res = 1;
		if (b > 0) {
			res = sumForPower(pow(a, b-1), a);
		}
		return res;
	}
	
	private static long sumForPower(long pow, int a) {
		long res = 1;
		if (a < 0) {
			res = -sumForPower(pow, -a);
		} else if (a == 0) {
			res = 0;
		} else {
			res = pow + sumForPower(pow, a - 1);
		}
		return res;
	}

	public static long sum(int[] ar) {
		return sum(0, ar); 
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}
	
	public static long square(int x) {
		// write function 
		// no cycles; 
		// no *, / operators; 
		// no additional functions
		// no static fields
		long res = 0;
		if (x < 0) {
			res = square(-x);
		}
		if (x > 0) {
			res = x + x - 1 + square(x-1);
		}
		return res;
	}
	
	public static void reverse(int[] ar) {
		// no cycles
		// no static fields
		// method reversing given array
		reverse(0, ar.length - 1, ar);
	}

	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}
	}

	private static void swap(int[] ar, int index1, int index2) {
		int tmp = ar[index1];		
		ar[index1] = ar[index2];
		ar[index2] = tmp;
	}
	
	public static boolean isSubstring(String string, String substring) {
		// write function 
		// returns true if a given 'substr' is indeed the
		//substring of a given `string`
		/* Challenges: 
		 * 1. To apply only following methods of the class String: 
		 * charAt(int ind);
		 * String substring(int ind);
		 * int length();
		 * 2. No cycles;
		*/
		return isSubstring(0, string, substring);
	}

	private static boolean isSubstring(int i, String string, String substring) {
		return i + substring.length() <= string.length() ? 
				equals(i, 0, string, substring) || isSubstring(i + 1, string, substring) : 
				false;
	}

	private static boolean equals(int i, int j, String string, String substring) {
		return j < substring.length() ? 
				string.charAt(i + j) == substring.charAt(j) && equals(i, j + 1, string, substring) :
				true;
	}
}
