package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.jupiter.api.*;
import telran.util.*;

public class HashSetTest extends SetTest {
	private static final int N_RUNS = 10;
	private static final int N_NUMBERS = 100000;
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new HashSet<>(4, 0.75f);
		super.setUp();
	}
	
	@Test
	void testHashSetIterator() {
		Predicate<Integer> allPredicate = n -> true;
		HashSet<Integer> set = new HashSet<>();
		fillSet(set, new Integer[] {0, 16, 32, 48, 512, 128});
		set.removeIf(allPredicate);
		assertTrue(set.isEmpty());
		for (int i = 0; i < N_RUNS; i++) {
			Integer[] bigArray = getRandomArray();
			fillSet(set, bigArray);
			set.removeIf(allPredicate);
			assertTrue(set.isEmpty());
			
		}

	}

	private Integer[] getRandomArray() {
		Integer[] result = new Integer[N_NUMBERS];
		Random random = new Random();
		for (int i = 0; i < N_NUMBERS; i++) {
			result[i] = random.nextInt();
		}

		return result;
	}

	private void fillSet(HashSet<Integer> hashSet, Integer[] numbers) {
		for (Integer num : numbers) {
			hashSet.add(num);
		}
	}

	@Test
	void testHashSetIterator1() {
		int tt;
		HashSet<Integer> set = new HashSet<>();
		Integer[] ar = new Integer[] {0, 1,2,3,4,5,6,7};
		fillSet(set, ar);
		Iterator<Integer> it = set.iterator();
		it.next();
		it.next();
		Iterator<Integer> it1 = set.iterator();
		it1.next();
		it1.next();
		it.remove();
		tt = it1.next();
		assertEquals(ar[2],tt);
//		set.removeIf((n) -> n.equals(ar[2]));
		tt = it.next();
		assertEquals(ar[2],tt);
	}
}
