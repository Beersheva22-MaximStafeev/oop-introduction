package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte[] ar;
	
	@Test
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
	void maxMemoryTest1() {
		MemoryOperationsPseudo memoryOperationsPseudo = new MemoryOperationsPseudo();
		for (int test = 520093080; test < 520093980; test++) {
			memoryOperationsPseudo.setMaxValue(test);
			assertEquals(test, memoryOperationsPseudo.getMaxAvialableMemory());
		}
	}

}
