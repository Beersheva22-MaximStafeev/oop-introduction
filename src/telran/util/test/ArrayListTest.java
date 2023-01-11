package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Iterator;
import telran.util.*;

class ArrayListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}

}