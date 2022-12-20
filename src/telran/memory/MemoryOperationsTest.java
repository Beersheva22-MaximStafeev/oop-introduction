package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte[] ar;
	
	@Test
	@Disabled
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvialableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flagException = false;
		try {
			ar = new byte[maxMemory+1];
		} catch (Throwable e) {
			flagException = true;
		}
		assertTrue(flagException);
	}

	
	@Test
	@Disabled
	void maxMemoryTest1() {
		MemoryOperationsPseudo memoryOperationsPseudo = new MemoryOperationsPseudo();
		for (int test = 520093080; test < 520093980; test++) {
			memoryOperationsPseudo.setMaxValue(test);
			assertEquals(test, memoryOperationsPseudo.getMaxAvialableMemory());
		}
	}
	
	@Test
	void standardMemoryMethods() {
		Runtime runtime = Runtime.getRuntime();
		System.out.printf("Maximal memory JVM may require from OS: %,d, " + 
				"current total JVM memory: %,d, " +
				"current free JVM memory: %,d", 
				runtime.maxMemory(), runtime.totalMemory(), runtime.freeMemory());
	}

}
