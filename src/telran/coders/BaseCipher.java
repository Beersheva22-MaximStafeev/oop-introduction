package telran.coders;

public class BaseCipher {
	private String key = "";
	private final int MIN_ASCII_CODE = 33;
	private final int MAX_ASCII_CODE = 126;
	
	public BaseCipher(int length) {
		boolean[] usedChars = new boolean[MAX_ASCII_CODE + 1]; 
		int newSymbol;
		if (length < 2) {
			length = 2;
		}
		if (length > MAX_ASCII_CODE - MIN_ASCII_CODE + 1) {
			length = MAX_ASCII_CODE - MIN_ASCII_CODE + 1;			
		}
		while (key.length() < length) {
			do {
				newSymbol = Randomizers.getRandomNumber(MIN_ASCII_CODE, MAX_ASCII_CODE);
			} while (usedChars[newSymbol]);
			key += (char) newSymbol;
			usedChars[newSymbol] = true;
		}
	}
	
	public String cipher(int number) {
		String res = "";
		int base = key.length();
		do {
			res = key.charAt(number % base) + res;
			number /= base;
		} while (number > 0);
		return res;
	}
	
	public int decipher(String cipher) {
		int[] arrayOfValues = getArrayOfValues();
		int base = key.length();
		int pow = 1;
		int res = 0;
		if (!testCipherInKey(cipher)) {
			res = -1;
		} else {
			for (int i = cipher.length() - 1; i >= 0; i--) {
				res = res + pow * arrayOfValues[cipher.charAt(i)];
				pow *= base;
			}
		}
		return res;
	}
	
	private boolean testCipherInKey(String cipher) {
		boolean res = true;
		boolean[] keyChars = new boolean[MAX_ASCII_CODE + 1];
		for (int i = 0; i < key.length(); i++) {
			keyChars[key.charAt(i)] = true;
		}
		int i = 0;
		while (i < cipher.length() && res) {
			res = res && cipher.charAt(i) <= MAX_ASCII_CODE && cipher.charAt(i) >= MIN_ASCII_CODE && keyChars[cipher.charAt(i)];
			i++;
		}
		return res;
	}

	/**
	 * 
	 * @return values of chars in key 
	 */
	private int[] getArrayOfValues() {
		int[] res = new int[MAX_ASCII_CODE + 1];
		for (int i = 0; i < key.length(); i++) {
			res[key.charAt(i)] = i;
		}
		return res;
	}
	
}
