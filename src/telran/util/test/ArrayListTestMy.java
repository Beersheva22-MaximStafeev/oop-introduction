package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telran.coders.Randomizers;

public class ArrayListTestMy {
	telran.util.ArrayList<String> arT = new telran.util.ArrayList<>();
	java.util.ArrayList<String> arJ = new java.util.ArrayList<>();
	
	String getRandomEl() {
		return ((Integer) Randomizers.getRandomNumber((int)'A', (int)'Z')).toString() + Randomizers.getRandomNumber(100000, 999999);
	}
	
	void add100Rnd() {
		for (int index = 0; index < 49; index++) {
			String element = getRandomEl();
			arT.add(element);
			arJ.add(element);
		}
		arT.add(null);
		arJ.add(null);
		for (int index = 0; index < 49; index++) {
			String element = getRandomEl();
			arT.add(element);
			arJ.add(element);
		}
	}
	
	void testArrays() {
		assertArrayEquals(arJ.toArray(new String[0]), arT.toArray(new String[0]));
		assertEquals(arJ.size(), arT.size());
		assertEquals(arJ.isEmpty(), arT.isEmpty());
		for (int index = -10; index < arJ.size() + 10; index++) {
			assertEquals(arJ.isEmpty(), arT.isEmpty());
			boolean wasErrorT = false;
			boolean wasErrorJ = false;
			String arTGet = "";
			String arJGet = "";
			try {
				arTGet = arT.get(index);
			} catch (Exception e) {
				wasErrorT = true;
			}
			try {
				arJGet = arJ.get(index);
			} catch (Exception e) {
				wasErrorJ = true;
			}
			assertEquals(wasErrorJ, wasErrorT);
			if (!wasErrorJ && !wasErrorT) {
				assertEquals(arJGet, arTGet);
			}
			
		}
		assertEquals(arJ.isEmpty(), arT.isEmpty());
	}
	
	@Test
	void arrayTests() {
		add100Rnd();
		testArrays();
		
		for (int i = 0; i < arJ.size() / 2; i++) {
			String el = arJ.get(Randomizers.getRandomNumber(0, arJ.size()-1));
			arJ.remove(el);
			arT.remove(el);
			testArrays();
		}
		
		for (int i = 0; i < 100; i++) {
			String el = getRandomEl();
			assertEquals(arJ.remove(el), arT.remove(el));
			testArrays();
		}

		for (int i = 0; i < arJ.size() / 10; i++) {
			String el = arJ.get(Randomizers.getRandomNumber(0, arJ.size()-1));
			assertEquals(arJ.remove(el), arT.remove(el));
			testArrays();
		}
		String el = null;
		assertEquals(arJ.remove(el), arT.remove(el));
		testArrays();

		for (int i = 0; i < arJ.size() / 2; i++) {
			int index = Randomizers.getRandomNumber(0, arJ.size()-1);
			arJ.remove(index);
			arT.remove(index);
			testArrays();
		}
		
		add100Rnd();
		for (int i = -10; i < arJ.size() * 2; i++) {
			boolean wasErrorJ = false;
			boolean wasErrorT = false;
			try {
				arJ.remove(i);
			} catch (Exception e) {
				wasErrorJ = true;
			}
			try {
				arT.remove(i);
			} catch (Exception e) {
				wasErrorT = true;
			}
			assertEquals(wasErrorJ, wasErrorT);
			testArrays();
		}
		
		add100Rnd();
		for (int i = 0; i < arJ.size(); i++) {
			int index = Randomizers.getRandomNumber(0, arJ.size()-1);
			String el1 = arJ.get(index);
			arJ.removeIf(e -> e == null ?  true : e.equals(el1));
			arT.removeIf(e -> e == null ?  true : e.equals(el1));
			testArrays();
		}
		add100Rnd();
		arJ.removeIf(e -> true);
		arT.removeIf(e -> true);
		testArrays();

		add100Rnd();
		assertEquals(arJ.contains(null), arT.contains(null));
		for (int i = 0; i < arJ.size(); i++) {
			int index = Randomizers.getRandomNumber(0, arJ.size()-1);
			String el1 = arJ.get(index);
			assertEquals(arJ.contains(el1), arT.contains(el1));
			String el2 = getRandomEl();
			assertEquals(arJ.contains(el2), arT.contains(el2));
		}
		
		int size = arJ.size();
		for (int i = 0; i < size; i++) {
			arJ.add(arJ.get(i));
			arT.add(arT.get(i));
			testArrays();
		}
		
		for (int i = arJ.size() - 1; i >= 0; i--) {
			assertEquals(arJ.lastIndexOf(arJ.get(i)), arT.lastIndexOf(arT.get(i)));
		}
		
		for (int i = -10; i < arJ.size() + 10; i++) {
			String el3 = getRandomEl();
			boolean wasErrorJ = false;
			boolean wasErrorT = false;
			try {
				arJ.set(i, el3);
			} catch (Exception e) {
				wasErrorJ = true;
			}
			try {
				arT.set(i, el3);
			} catch (Exception e) {
				wasErrorT = true;
			}
			assertEquals(wasErrorJ, wasErrorT);
			testArrays();
		}
		
		add100Rnd();
		size = arJ.size();
		for (int i = -10; i < size + 10; i++) {
			String el3 = getRandomEl();
			boolean wasErrorJ = false;
			boolean wasErrorT = false;
			try {
				arJ.add(i, el3);
			} catch (Exception e) {
				wasErrorJ = true;
			}
			try {
				arT.add(i, el3);
			} catch (Exception e) {
				wasErrorT = true;
			}
			assertEquals(wasErrorJ, wasErrorT);
			testArrays();
		}
		el = getRandomEl();
		arJ.add(size, el);
		arT.add(size, el);
		testArrays();
	}
	
	@Test
	void testS() {
		telran.util.List<String> list = new telran.util.ArrayList<>();
		list.add("0");
		list.add("1");
		list.add(2, "2");
		assertEquals(3, list.size());
		assertEquals("0", list.get(0));
		assertEquals("1", list.get(1));
		assertEquals("2", list.get(2));
		for (int i = 3; i < 20; i++) {
			list.add(String.valueOf(i));
		}
		assertEquals(20, list.size());

		assertEquals(3, list.indexOf("3"));
		assertEquals(3, list.lastIndexOf("3"));

		list.add(20, "20");
		assertEquals(21, list.size());

		list.remove(0);
		assertEquals(20, list.size());
		assertEquals("1", list.get(0));
		assertEquals("20", list.get(19));
		list.remove(19);
		assertEquals(19, list.size());
		assertEquals("19", list.get(18));

		assertTrue(list.remove("19"));
		assertEquals(18, list.size());
		assertEquals("18", list.get(17));

		assertFalse(list.remove("100"));

		assertTrue(list.remove("10"));
		assertEquals(17, list.size());
		assertEquals("9", list.get(8));
		assertEquals("11", list.get(9));
		assertEquals("18", list.get(16));

		list.add("20");
		list.add("21");
		assertTrue(list.removeIf(s -> s.contains("1") || s.contains("8")));
		assertEquals(8, list.size());
		assertEquals("2", list.get(0));
		assertEquals("9", list.get(6));
		assertEquals("20", list.get(7));

		assertTrue(list.removeIf(s -> s.contains("2") && s.contains("0")));
		assertEquals(7, list.size());
		assertEquals("9", list.get(6));

		assertTrue(list.removeIf(s -> s.contains("2")));
		assertEquals(6, list.size());
		assertEquals("3", list.get(0));
		assertEquals("9", list.get(5));

		list.add(3, null);
		assertEquals(7, list.size());
		assertEquals(3, list.indexOf(null));
		assertEquals("5", list.get(2));
		assertEquals("6", list.get(4));

		list.add(0, "1000");
		list.add(1, "1000");
		assertEquals(9, list.size());
		assertEquals("1000", list.get(0));
		assertEquals("1000", list.get(1));
		assertEquals("3", list.get(2));
		assertEquals("9", list.get(8));
		assertEquals(0, list.indexOf("1000"));
		assertEquals(1, list.lastIndexOf("1000"));

		String[] expectedArray = {"1000", "1000", "3", "4", "5", null, "6", "7", "9"};
		String[] expectedArrayWithNulls = {"1000", "1000", "3", "4", "5", null, "6", "7", "9", null, null};
		
		assertArrayEquals(expectedArray, list.toArray(new String[0]));
		assertArrayEquals(expectedArrayWithNulls, list.toArray(new String[11]));
	}
}
