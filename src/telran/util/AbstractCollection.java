package telran.util;

public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;
	
	protected boolean isEqual(T element1, T element2) {
		return element1 == null ? element1 == element2 : element1.equals(element2);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
