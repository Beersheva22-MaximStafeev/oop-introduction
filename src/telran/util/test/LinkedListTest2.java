package telran.util.test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

class LinkedListTest2 extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList2<>();
		super.setUp();
	}
	
	@Test
	void addTest() {
		List<String> list2 = new LinkedList<>();
		list2.add("asfas");
		list2.add("dfdf");
		Iterator<String> it = list2.iterator();
		int i = 0;
		while (it.hasNext()) {
			assertTrue(it.next().equals(list2.get(i++)));
		}
	}
	
}