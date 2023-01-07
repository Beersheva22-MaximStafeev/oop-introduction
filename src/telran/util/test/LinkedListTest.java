package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

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
		myList = initList(false);
		for (int i = 0; i < myList.size(); i++) {
			for (int j = i; j < myList.size(); j++) {
				myList = initList(false);
				assertFalse(myList.hasLoop());
				myList.setNext(j, i);
				assertTrue(myList.hasLoop());
			}
		}
		myList = initList(true);
		for (int i = 0; i < myList.size(); i++) {
			for (int j = i; j < myList.size(); j++) {
				myList = initList(true);
				assertFalse(myList.hasLoop());
				myList.setNext(j, i);
				assertTrue(myList.hasLoop());
			}
		}
	}
	
	private LinkedList<String> initList(boolean addOne) {
		// for odd and even sizes
		LinkedList<String> myList = new LinkedList<>();
		for (int i = 0; i < 100; i++) {
			assertFalse(myList.hasLoop());
			myList.add("prpr");
			
		}
		assertFalse(myList.hasLoop());
		if (addOne) {
			myList.add("ohjk");
			assertFalse(myList.hasLoop());
		}
		return myList;
	}
	
//	private void printList(LinkedList<String> myList, int max) {
//		int i = 0;
//		Iterator<String> it = myList.iterator();
//		while (i < max && it.hasNext()) {
//			System.out.printf("i: %d, s: %s\n", i++, it.next());
//		}
//		System.out.printf("--\n");
//	}
}