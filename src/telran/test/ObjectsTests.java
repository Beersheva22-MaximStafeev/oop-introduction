package telran.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import telran.coders.*;

public class ObjectsTests {
	@Test
	void RandomizersTest() {
		int min = 5;
		int max = 10;
		int[] rnds = new int[max + 1];
		for (int i = 0; i < 10000 * (max - min + 1); i++) {
			int r = Randomizers.getRandomNumber(min, max);
			rnds[r]++;
			assertTrue(r >= min && r <= max);
		}
		for (int i = min; i <= max; i++) {
			System.out.print("" + i + ":" + rnds[i] + " ");
		}
	}

	@Test
	@Disabled
	void BaseCipherTest2() {
		BaseCipher baseCipher = new BaseCipher(2);
		for (int j = 0; j < 1000; j++) {
			String test = baseCipher.cipher(j);
			System.out.println(test);
			assertEquals(j, baseCipher.decipher(test));
		}
	}

	@Test
	@Disabled
	void BaseCipherTest3() {
		BaseCipher baseCipher = new BaseCipher(3);
		for (int j = 0; j < 1000; j++) {
			String test = baseCipher.cipher(j);
			System.out.println(test);
			assertEquals(j, baseCipher.decipher(test));
		}
	}
	
	@Test
	void BaseCipherTest() {
		for (int i = 2; i < 100; i++) {
			BaseCipher baseCipher = new BaseCipher(i);
			for (int j = 0; j < 10000; j++) {
				String test = baseCipher.cipher(j);
				assertEquals(j, baseCipher.decipher(test));
			}
		}
	}
	
	@Test
	void BaseCipherTestBad() {
		int base = 10;
		BaseCipher baseCipher = new BaseCipher(base);
		String encrypted = baseCipher.cipher(124);
		assertEquals(1244, baseCipher.decipher(encrypted + encrypted.charAt(2)));
		assertEquals(-1, baseCipher.decipher(encrypted + (char) 129));
	}
}
