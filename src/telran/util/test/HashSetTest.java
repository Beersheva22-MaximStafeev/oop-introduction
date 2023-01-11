package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class HashSetTest extends SetTest {
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new HashSet<>(4, 0.75f);
		super.setUp();
	}
	
}
