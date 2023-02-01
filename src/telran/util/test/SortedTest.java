package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Sorted;

public abstract class SortedTest extends SetTest{
	private static final int N_ELEMENTS = 100000;
	private static final int N_RUNS = 100000;
	private Random rnd = new Random();
	Sorted<Integer> sorted;
	@Override
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		 sorted = (Sorted<Integer>)collection;
	}

	@Override
	@Test
	void testIterator() {
		Integer expected [] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		Integer actual[] = new Integer[expected.length];
		int index = 0;
		for(Integer num: sorted) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		
	}
	//{-5, 10, 15, 100, 120, 134, 280  };
	@Test
	void floorTest() {
		assertEquals((Integer)100, sorted.floor(100));
		assertNull(sorted.floor(-10));
		assertEquals((Integer)15, sorted.floor(20));
		assertEquals((Integer)280, sorted.floor(300));
		assertEquals((Integer)100, sorted.floor(115));
	}
	@Test
	void ceilingTest() {
		assertEquals((Integer)100, sorted.ceiling(100));
		assertNull(sorted.ceiling(281));
		assertEquals((Integer)15, sorted.ceiling(13));
		assertEquals((Integer)10, sorted.ceiling(0));
		assertEquals((Integer)(-5), sorted.ceiling(-10));
		assertEquals((Integer)280, sorted.ceiling(150));
	}
	@Test
	void firstTest() {
		assertEquals((Integer)(-5),sorted.first());
	}
	@Test
	void lastTest() {
		assertEquals((Integer)280,sorted.last());
	}

	@Test
	void perfomanceTestSortedAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(i));
		runPerformanceTest(sorted);
	}
	
	private void runPerformanceTest(Sorted<Integer> sorted2) {
		for (int i = 0; i < N_RUNS; i++) {
			sorted.floor(rnd.nextInt());
		}
		
	}

	protected abstract Sorted<Integer> getSortedCollection();

	@Test
	void perfomanceTestRandomAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMENTS).forEach(i -> sorted.add(rnd.nextInt()));
		runPerformanceTest(sorted);
	}
}
