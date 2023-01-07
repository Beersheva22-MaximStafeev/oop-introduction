package telran.memory;

public class MemoryOperationsPseudo {
	private int maxValue = 520093680;
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	private void makeThrow(int test) throws Exception {
		if (test > maxValue) {
			throw new Exception();
		}
	}
	
	public int getMaxAvialableMemory() {
//		return 400 * 1024 * 1024; //Integer.MAX_VALUE; // 495 - good, 496 - not good
		int l = 0;
		int r = Integer.MAX_VALUE;
		int res = l + (r - l) / 2;
//		byte[] ar = null;
		while (l <= r) {
			try {
				makeThrow(res);
				l = res + 1;
			} catch (Throwable e) {
				r = res - 1;
			}
//			ar = null;
			res = l + (r - l) / 2;
		}
		return res - 1;
	}
}
