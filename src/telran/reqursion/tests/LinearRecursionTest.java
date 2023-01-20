package telran.reqursion.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.reqursion.LinearRecursion.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LinearRecursionTest {
	
	@Test
	void isSubstringTest() {
		String pattern = "6vBQOu59mwfwuu88zrKgmOktueboGTBuiakeAR24R2h5QllXcfdWZJN0KmCgWb1e";
		for (int i = 0; i < pattern.length(); i++) {
			for (int j = i; j <= pattern.length(); j++) {
				String substring = pattern.substring(i, j);
				assertTrue(isSubstring(pattern, substring));
				assertFalse(isSubstring(pattern, "?" + substring));
				assertFalse(isSubstring(pattern, substring + "?"));
				assertFalse(isSubstring(pattern, substring.length() > 1 ? substring.substring(0, 1) + "?" + substring.substring(2) : "??"));
			}
		}
	}
	
	@Test
	void reverseTest() {
		makeReverseTest(new int[] {}, new int[] {});
		makeReverseTest(new int[] {1, 2}, new int[] {2, 1});
		makeReverseTest(new int[] {1, 2, 3}, new int[] {3, 2, 1});
		makeReverseTest(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
		makeReverseTest(new int[] {1, 2, 3, 4, 5}, new int[] {5, 4, 3, 2, 1});
	}
	
	void makeReverseTest(int[] expected, int[] ar) {
		reverse(ar);
		assertArrayEquals(expected, ar);
	}
	
	@Test
	void squareTest() {
		for (int i = 1; i < 10; i++) {
			assertEquals(i*i, square(i));
		}
	}
	
	@Test
	void sumTest() {
		int[] array = {1, 2, 3, 6, 5, 4};
	    assertEquals(21, sum(array));
	    assertEquals(0, sum(new int[] {}));
	}
	
	@Test
	void powTest() {
		assertEquals(1024, pow(2, 10));
		assertEquals(1, pow(23213, 0));
		assertEquals(125, pow(5, 3));
		assertEquals(-64, pow(-4, 3));
		assertThrowsExactly(IllegalArgumentException.class, () -> pow(-1, -1));
	}
	
	@Test
	void powerTest() {
		assertEquals(1024, power(2, 10));
		assertEquals(1, power(23213, 0));
		assertEquals(125, power(5, 3));
		assertEquals(-64, power(-4, 3));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(-1, -1));
	}
	
	@Test
	void factorialTest() {
		assertEquals(1, factorial(0));
		assertEquals(1, factorial(1));
		assertEquals(2, factorial(2));
		assertEquals(6, factorial(3));
		assertEquals(24, factorial(4));
		assertEquals(24, factorial(-4));
		assertEquals(120, factorial(5));
	}
	
	@Test
	@Disabled
	void fTest() {
		f(6);
	}
	
	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
	}
	
	@Test
	@Disabled
	void deepnesTest() {
		// outputs 13 lines!!!
		deepnes(1);
	}
	
	void deepnes(long l) {
		try {
			deepnes(l + 1);
		} catch (StackOverflowError e) {
			System.out.println(l);
		}
		
	}
	
	@Test
	@Disabled
	void deepnes1Test() {
		// outups only one line
		long d = deepnes1(1);
		System.out.printf("Max deep is: %d%n", d);
	}

	private long deepnes1(long i) {
		try {
			return deepnes1(i + 1);
		} catch (StackOverflowError e) {
			return i;
		}
	}
}
