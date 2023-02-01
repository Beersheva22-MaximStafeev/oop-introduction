package telran.util.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.*;

import telran.util.ArrayList;

public class StreamTests {
	static ArrayList<Integer> list;
	static Integer[] numbers = {10, 20, 3, 8, 100, 200, -10, -5};
	
	@BeforeAll
	static void setUp() {
		list = new ArrayList<>();
		Arrays.stream(numbers).forEach(list::add);
	}
	
	@Test
	void sortTest() {
		Integer[] expected = {-10, -5, 3, 8, 10, 20, 100, 200};
		// sorting
		assertArrayEquals(expected, list.stream().sorted().toArray(Integer[]::new));
	}
	
	@Test
	void sumNegative() {
		// find sum of negative numbers
		assertEquals(-15, list.stream().filter(el -> el < 0).mapToInt(n -> n).sum());
	}
	
	@Test
	void stringsArrayTest() {
		String[] expected = {"10", "20", "3", "8", "100", "200", "-10", "-5"};
		assertArrayEquals(expected, 
				list.stream()
				.map(el -> el.toString())
				.toArray(String[]::new)
				);
	}
	
	@Test
	void stringTest() {
		String expected = "10, 20, 3, 8, 100, 200, -10, -5";
		assertEquals(expected, list.stream().map(n -> n.toString()).
				collect(Collectors.joining(", "))
				);
	}
	
	@Test
	void sportLotoTest() {
		new Random().ints(1, 50).distinct().limit(7).forEach(n -> System.out.print(n + "; "));
		System.out.println();
	}
	
	@Test
	void HW28() {
		Integer[] ar = new Random().ints(0, numbers.length).distinct().limit(numbers.length).map(n -> list.get(n)).boxed().toArray(Integer[]::new);
		Arrays.stream(ar).forEach(el -> System.out.print(el + "; "));
		System.out.println();
		
		String[] inAr = {"aa", "bb", "cc", "dd"};
		String[] sufAr = new Random().ints(0, inAr.length).distinct().limit(inAr.length)
				.mapToObj(n -> inAr[n]).toArray(String[]::new);
		Arrays.stream(sufAr).forEach(el -> System.out.print(el + "; "));
		System.out.println();
	}
}
