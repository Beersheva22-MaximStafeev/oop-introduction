package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.TreeSet;

public class TreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
	}
}
