package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.*;

import telran.util.ArrayList;
import telran.util.List;
import telran.util.Map;
import telran.util.Map.Entry;

abstract class MapTest {
	Map<Integer, String> map;
	
	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertEquals("Two", map.get(2));
		assertEquals("Three", map.get(3));
		assertNull(map.get(1000));
		assertNull(map.get(-1));
		map.put(146, null);
		assertNull(map.get(146));
	}

	@Test
	void putTest() {
		assertEquals("One", map.put(1, "Ahad"));
		assertEquals("Ahad", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
		assertNull(map.put(146, null));
		assertNull(map.put(146, null));
	}
	
	@Test
	void putIfAbsentTest() {
		assertEquals("One", map.putIfAbsent(1, "Ahad"));
		assertEquals("One", map.putIfAbsent(1, "Ahad"));
		assertNull(map.putIfAbsent(4, "Four"));
		assertEquals("Four", map.putIfAbsent(4, "Ahad"));
		assertEquals("Four", map.putIfAbsent(4, "Ahad"));
	}
	
	@Test
	void getOrDefaultTest() {
		assertEquals("One", map.getOrDefault(1, "PP"));
		assertEquals("Two", map.getOrDefault(2, "PP"));
		assertEquals("PP", map.getOrDefault(100, "PP"));
	}
	
	@Test
	void contanisKeyTest() {
		assertTrue(map.contanisKey(1));
		assertTrue(map.contanisKey(2));
		assertTrue(map.contanisKey(3));
		assertFalse(map.contanisKey(4));
	}
	
	@Test
	void containsValue() {
		assertTrue(map.containsValue("One"));
		assertTrue(map.containsValue("Two"));
		assertTrue(map.containsValue("Three"));
		assertFalse(map.containsValue("Four"));
		assertFalse(map.containsValue(null));
		map.put(146, null);
		assertTrue(map.containsValue(null));
	}
	
	@Test
	void valuesTest() {
		String[] values = map.values().toArray(new String[0]);
		Arrays.sort(values);
		assertArrayEquals(new String[] {"One", "Three", "Two"}, values);
	}

	@Test
	void keySetTest() {
		Integer[] keys = map.keySet().toArray(new Integer[0]);
		Arrays.sort(keys);
		assertArrayEquals(new Integer[] {1, 2, 3}, keys);
	}
	
	@Test
	void entrySetTest() {
		List<Integer> keys = new ArrayList<>();
		List<String> values = new ArrayList<>();
		Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
		Entry<Integer, String> entry = null;
		while (it.hasNext()) {
			entry = it.next();
			keys.add(entry.getKey());
			values.add(entry.getValue());
		}
		Integer[] keysAr = keys.toArray(new Integer[0]);
		Arrays.sort(keysAr);
		assertArrayEquals(new Integer[] {1, 2, 3}, keysAr);
		String[] valuesAr = values.toArray(new String[0]);
		Arrays.sort(valuesAr);
		assertArrayEquals(new String[] {"One", "Three", "Two"}, valuesAr);
	}
}
