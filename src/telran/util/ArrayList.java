package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {

	public static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	
	private class ArrayListIterator implements Iterator<T> {
		int current = 0;
		boolean flNext = false;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[current++];
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			ArrayList.this.remove(--current);
			flNext = false;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();		
	}
	
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
		int indexNew = 0;
		int indexReal = 0;
		while (indexReal < size) {
			if (predicate.test(array[indexReal])) {
				indexReal++;
			} else {
				array[indexNew++] = array[indexReal++];
			}
		}
		Arrays.fill(array, indexNew, size, null);
		size = indexNew;
		return indexReal > indexNew;
	}

	public boolean removeIfBadN2(Predicate<T> predicate) {
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
		// element not needed now, we erase link to object so sent this object to garbage collector 
		array[size] = null;
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
