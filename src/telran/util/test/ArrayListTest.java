package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import telran.coders.Randomizers;

public class ArrayListTest {
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
		assertArrayEquals(arT.toArray(new String[0]), arJ.toArray(new String[0]));
		assertEquals(arT.size(), arJ.size());
		assertEquals(arT.isEmpty(), arJ.isEmpty());
		for (int index = -10; index < arJ.size() + 10; index++) {
			assertEquals(arT.isEmpty(), arJ.isEmpty());
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
				assertEquals(arTGet, arJGet);
			}
			
		}
		assertEquals(arT.isEmpty(), arJ.isEmpty());
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
	
}
