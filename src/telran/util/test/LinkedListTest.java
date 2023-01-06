package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.*;

import telran.util.*;

class LinkedListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	@Test
	void testLoop() {
		LinkedList<String> myList;
		for (int i = 0; i < 4; i++) {
			for (int j = i; j < 4; j++) {
				myList = initList();
				assertFalse(myList.hasLoop());
				myList.setNext(j, i);
				assertTrue(myList.hasLoop());
			}
		}
	}
	
	private LinkedList<String> initList() {
		LinkedList<String> myList = new LinkedList<>();
		assertFalse(myList.hasLoop());
		myList.add("prpr");
		assertFalse(myList.hasLoop());
		myList.add("wewe");
		assertFalse(myList.hasLoop());
		myList.add("cvcv");
		assertFalse(myList.hasLoop());
		myList.add("ahah");
		assertFalse(myList.hasLoop());
		return myList;
	}
	
	private void printList(LinkedList<String> myList, int max) {
		int i = 0;
		Iterator<String> it = myList.iterator();
		while (i < max && it.hasNext()) {
			System.out.printf("i: %d, s: %s\n", i++, it.next());
		}
		System.out.printf("--\n");
	}
}