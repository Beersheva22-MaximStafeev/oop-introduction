package telran.memory;

public class MemoryOperations {
	public static int getMaxAvialableMemory() {
//		return 400 * 1024 * 1024; //Integer.MAX_VALUE; // 495 - good, 496 - not good
		int l = 0;
		int r = Integer.MAX_VALUE;
		int res = l + (r - l) / 2;
		byte[] ar = null;
		while (l < r) {
			try {
				ar = new byte[res];
				l = res + 1;
			} catch (Throwable e) {
				r = res - 1;
			}
			ar = null;
			res = l + (r - l) / 2;
		}
		return res;
	}
}
