package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.coders.Randomizers;
import telran.util.*;

public class SetTest extends CollectionTest {

	Set<Integer> set;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));
	}

	private String getRandomEl() {
		return ((Integer) Randomizers.getRandomNumber((int) 'A', (int) 'Z')).toString()
				+ Randomizers.getRandomNumber(100000, 999999);
	}

	@Override
	@Test
	void testIterator() {
		int maxLen = 1000;
		String[] initArray = new String[maxLen];
		java.util.List<String> checkArray = new java.util.ArrayList<>();
		Set<String> testSet = new HashSet<>(4, 0.75f);
		String tst;
		int i = 0;

		while (i < maxLen) {
			tst = getRandomEl();
			if (!checkArray.contains(tst)) {
				assertTrue(testSet.add(tst));
				checkArray.add(tst);
				initArray[i++] = tst;
			} else {
				assertFalse(testSet.add(tst));
			}
		}

		String[] arrayInerator = new String[maxLen];
		int index = 0;
		Iterator<String> iterator = testSet.iterator();
		while (iterator.hasNext()) {
			arrayInerator[index++] = iterator.next();
		}

		assertEquals(testSet.size(), index);
		Arrays.sort(initArray);
		Arrays.sort(arrayInerator);
		assertArrayEquals(initArray, arrayInerator);
		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}

}
