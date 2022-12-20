package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;


class MyArraysTest {

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
		Integer numbers[] = {13,2, -8,47, 100, 10, 7};
		Integer expected[] = {-8, 2, 10, 100, 47, 13, 7};
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}	
}
