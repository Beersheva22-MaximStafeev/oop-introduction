package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {

	public static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		add(size, element);
		return true;
	}
	
	private void checkReallocate() {
		if (size == array.length) {
			reallocate();
		}
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);		
	}

	@Override
	public boolean remove(T pattern) {
		int firstElementIndex = indexOf(pattern);
		boolean res = false;
		if (firstElementIndex > -1) {
			remove(firstElementIndex);
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int index = 0;
		boolean res = false;
		while (index < size) {
			if (predicate.test(array[index])) {
				remove(index);
				res = true;
			} else {
				index++;
			}
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}

	private boolean isEqual(T element1, T element2) {
		return element1 == null ? element1 == element2 : element1.equals(element2);
	}

	@Override
	public T[] toArray(T[] ar) {
		T[] res = ar;
		if (res.length < size) {
			res = Arrays.copyOf(res, size);
		}
		System.arraycopy(array, 0, res, 0, size);
		if (size <= res.length) {
			Arrays.fill(res, size, res.length, null);
		}
		return res;
	}

	@Override
	public void add(int index, T element) {
		checkBounds(index, size + 1);
		checkReallocate();
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	@Override
	public T remove(int index) {
		checkBounds(index);
		T res = array[index];
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return res;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !isEqual(pattern, array[index])) {
			index++;
		}
		return index == size ? -1 : index;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !isEqual(pattern, array[index])) {
			index--;
		}
		return index;
	}

	@Override
	public T get(int index) {
		checkBounds(index);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkBounds(index);
		array[index] = element;
	}
	
	private void checkBounds(int index) {
		checkBounds(index, size);
	}

	private void checkBounds(int index, int max) {
		if (index < 0 || index >= max) {
			throw new IndexOutOfBoundsException(index);
		}
		
	}

}
