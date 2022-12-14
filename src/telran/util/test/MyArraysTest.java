package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;


class MyArraysTest {
	private static final int N_NUMBERS = 10000;
	private static final int N_RUNS = 1000;

	@Test
	@Disabled
	void simpleTest() {
		String[] strings = {"12", "1", "sdds", "f", "ad"};
		StringLengthComparator stringLengthComparator = new StringLengthComparator();
		MyArrays.sort(strings, stringLengthComparator);
		for (String s: strings) {
			System.out.println(s);
		}
	}

	@Test
	void binarySearchTest() {
		Integer[] ar = {1,5,2,5,3};
		Integer[] ar1 = {1,2,3,5,5};
		MyArrays.sort(ar, (a, b) -> a - b);
		assertArrayEquals(ar, ar1);
		assertEquals(-1, MyArrays.binarySearch(ar1, (Integer) 0, (o1, o2) -> o1 - o2));
		assertEquals(0, MyArrays.binarySearch(ar1, (Integer) 1, (o1, o2) -> o1 - o2));
		assertEquals(1, MyArrays.binarySearch(ar1, (Integer) 2, (o1, o2) -> o1 - o2));
		assertEquals(2, MyArrays.binarySearch(ar1, (Integer) 3, (o1, o2) -> o1 - o2));
		assertEquals(-4, MyArrays.binarySearch(ar1, (Integer) 4, (o1, o2) -> o1 - o2));
		assertEquals(3, MyArrays.binarySearch(ar1, (Integer) 5, (o1, o2) -> o1 - o2));
		assertEquals(-6, MyArrays.binarySearch(ar1, (Integer) 6, (o1, o2) -> o1 - o2));
	}
	
	@Test
	void evenOddTest() {
		Integer numbers[] = {13, 2, -8, 47, 100, 10, 7, -5, -3};
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7, -3, -5};
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}	
	
	@Test
	void filterTest() {
		Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
		String strings[] = {
				"ab", "abm", "abmb", "abmbc"	
			};
		int dividor = 2;
		String subStr = "m";
		Predicate<Integer> predEven = t -> t % dividor == 0;
		Predicate<String> predSubstr = s -> s.contains(subStr);
		String expectedStr[] = {
				 "abm", "abmb", "abmbc"	
			};
		Integer expectedNumbers[] ={2, -8, 100, 10};
		assertArrayEquals(expectedStr, MyArrays.filter(strings, predSubstr));
		assertArrayEquals(expectedNumbers, MyArrays.filter(numbers, predEven));
	}
	
	int evenOddCompare(Integer o1, Integer o2) {
		int remainder =  Math.abs(o1) % 2;
		int res = remainder - Math.abs(o2) %2;
		if (res == 0) {
			res = remainder != 0 ? Integer.compare(o2, o1) : Integer.compare(o1, o2);
		}
		return res;
	}
	
	@Test
	void containsTest() {
		String[] myAr = {"sf", "c", "rref", "abab", "dd"};
		assertTrue(MyArrays.contains(myAr, "c"));
		assertTrue(MyArrays.contains(myAr, "rref"));
		assertTrue(MyArrays.contains(myAr, "abab"));
		assertFalse(MyArrays.contains(myAr, null));
		myAr[myAr.length - 1] = null;
		assertTrue(MyArrays.contains(myAr, null));
		assertFalse(MyArrays.contains(myAr, "abadb"));
		assertFalse(MyArrays.contains(myAr, "abadb"));
		assertFalse(MyArrays.contains(myAr, "dd1"));
	}
	
	@Test
	void removeRepeatedTest() {
		Integer[] ar = {100, 10, 18, 10, 20, 18};
		Integer[] arExpected = {100, 10, 18, 20};
		assertArrayEquals(arExpected, MyArrays.removeRepeated(ar));
		String[] s1 = {"a", "dsf", "as", "a", "dsf", "cc"};
		String[] sE = {"a", "dsf", "as", "cc"};
		assertArrayEquals(sE, MyArrays.removeRepeated(s1));
	}
	
	@Test
	void joinFunctionalTest() {
		Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7};
		String expected = "13,2,-8,47,100,10,-7,7";
		assertEquals(expected, MyArrays.join(numbers, ","));
	}
	
	@Test
	void joinPerfomanceTest() {
		Integer[] largeArray = getLargeNumbersArray();
		for (int i = 0; i < N_RUNS; i++) {
			/*
			 * MyArrays.joinString(largeArray, ",");
			 * works 103,7 sec
			 * 
			 * MyArrays.join(largeArray, ",");
			 * works 0,7 sec
			 */
			MyArrays.join(largeArray, ",");
		}
	}

	private Integer[] getLargeNumbersArray() {
		Integer[] res = new Integer[N_NUMBERS];
		Arrays.fill(res, 1000);
		return res;
	}
}
