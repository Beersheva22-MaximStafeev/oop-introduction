package telran.reqursion;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import telran.util.*;

public class MdArray<T> {
	private static final boolean USE_LINEAR = true;
	
	private MdArray<T>[] array;
	private T value;
	
	public MdArray(int[] dimensions, T value) {
		this(dimensions, 0, value);
	}

	@SuppressWarnings("unchecked")
	public MdArray(int[] dimensions, int firstDim, T value) {
		if (firstDim == dimensions.length) {
			this.value = value;
			this.array = null;
		} else {
			if (dimensions[firstDim] <= 0) {
				throw new ArrayIndexOutOfBoundsException("Cannot create array with " + dimensions[firstDim] + " length");
			}
			this.value = null;
			array = new MdArray[dimensions[firstDim]];
			for (int i = 0; i < array.length; i++) {
				array[i] = new MdArray<>(dimensions, firstDim + 1, value);
			}
		}
	}
	
	private MdArray<T> findElement(int[] dimensions) {
		if (USE_LINEAR) {
			return findElementLinear(dimensions);
		} else {
			return findElementReqursive(dimensions, 0);
		}
	}
	
	private MdArray<T> findElementLinear(int[] dimensions) {
		MdArray<T> res = this;
		int level = 0;
		while (level < dimensions.length) {
			if (res.array == null) {
				throw new NoSuchElementException("Dimension #" + level + " is absent");
			}
			if (dimensions[level] >= res.array.length) {
				throw new ArrayIndexOutOfBoundsException("Dimension #" + level + " less then " + dimensions[level] + " elements");
			}
			res = res.array[dimensions[level]];
			level++;
		}
		if (res.array != null) {
			throw new IllegalStateException("This array has more than " + level + " dimensions");
		}
		return res;
	}

	private MdArray<T> findElementReqursive(int[] dimensions, int level) {
		MdArray<T> res = this;
		if (dimensions.length == level) {
			if (array != null) {
				throw new IllegalStateException("This array has more than " + level + " dimensions");
			}
		} else {
			if (array == null) {
				throw new NoSuchElementException("Dimension #" + level + " is absent");
			}
			if (dimensions[level] >= array.length) {
				throw new ArrayIndexOutOfBoundsException("Dimension #" + level + " less then " + dimensions[level] + " elements");
			}
			res = array[dimensions[level]].findElementReqursive(dimensions, level + 1);
		}
		return res;
	}
	
	public T getValue(int[] dimensions) {
		return findElement(dimensions).value;
	}

	public void setValue(int[] dimensions, T value) {
		findElement(dimensions).value = value;
	}

	public T[] toArray(T[] ar) {
		List<T> list = new ArrayList<>();
		forEach(element -> list.add(element));
		return list.toArray(ar);
	}

	public void forEach(Consumer<T> consumer) {
		if (array != null) {
			Arrays.stream(array).forEach(element -> element.forEach(consumer));
		} else {
			consumer.accept(value);
		}
	}
}
