package telran.util.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.jupiter.api.*;

import telran.util.*;

public class RangeIteratorTest {
	
	Integer[] numbers = {1, 2, 3, 4, 5};
	
	@Test
	void RangeTest() {
		Range range = new Range(1, 6);
		ArrayList<Integer> list;
		
		// 1st method
		list = new ArrayList<>();
		Iterator<Integer> it = range.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		assertArrayEquals(numbers, list.toArray(new Integer[0]));

		// 2nd method 
		list = new ArrayList<>();
		for (Integer num: range) {
			list.add(num);
		}
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
		
		// 3rd
		list = new ArrayList<>();
		range.forEach(list::add);
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
		
	}
}
