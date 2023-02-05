package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.TreeMap;

public class TreeMapTest extends MapTest {
	@Override
	@BeforeEach
	void setUp() throws Exception {
		map = new TreeMap<>();
		super.setUp();
	}
}

