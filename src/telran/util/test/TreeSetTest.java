package telran.util.test;

import org.junit.jupiter.api.*;

import telran.util.*;

public class TreeSetTest extends SetTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}

}
