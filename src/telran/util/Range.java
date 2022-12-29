package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	int min;
	int max;
	
	private class RangeIterator implements Iterator<Integer> {

		int current = min;
		
		@Override
		public boolean hasNext() {
			return current < max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current++;
		}
		
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	private void checkMinMax(int min, int max) {
		if (max <= min) {
			throw new IllegalArgumentException("max value <" + max + "> must be greater than min value <" + min + ">");
		}
		
	}
	
	public Range(int min, int max) {
		checkMinMax(min, max);
		this.min = min;
		this.max = max;
	}
	
	public boolean checkNumber(int number) {
		return number >= min && number < max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		checkMinMax(min, max);
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		checkMinMax(min, max);
		this.max = max;
	}

}
