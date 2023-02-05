package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

//import telran.util.LinkedList.Node;

public class HashSet<T> extends AbstractCollection<T> implements Set<T> {
	private static final int DEFAULT_TABLE_SIZE = 16;
	private static final float DEFAULT_FACTOR = 0.75f;
//	LinkedList<T>[] hashTable;
	private List<T>[] hashTable;
	private float factor;
	
	private class HashSetIterator implements Iterator<T> {		
		int currentIndex = 0;
		Iterator<T> it = null;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return getNextIndex() < hashTable.length;
		}
		
		private int getNextIndex() {
			int nextIndex = currentIndex;
			if (currentIndex < hashTable.length) {
				if (!it.hasNext()) {
					nextIndex = getNextNotNull(currentIndex + 1);
				}
			}
			return nextIndex;
		}

		@Override
		public T next() {
			T res;
			int nextIndex = getNextIndex();
			if (nextIndex >= hashTable.length) {
				throw new NoSuchElementException();
			}
			if (currentIndex < nextIndex) {
				currentIndex = nextIndex; 
				it = hashTable[currentIndex].iterator();
			}
			res = it.next(); 
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			it.remove();
			size--;
			if (!it.hasNext()) {
				if (hashTable[currentIndex].isEmpty()) {
					hashTable[currentIndex] = null;
				}
				currentIndex = getNextIndex();
				if (currentIndex < hashTable.length) {
					it = hashTable[currentIndex].iterator();
				}
			}
			flNext = false;
		}

		public HashSetIterator() {
			super();
			currentIndex = getNextNotNull(0);
			if (currentIndex < hashTable.length) {
				it = hashTable[currentIndex].iterator();
			}
		}
		
		private int getNextNotNull(int index) {	
			int res = index;
			while (res < hashTable.length && hashTable[res] == null) {
				res++;
			}
			return res;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableSize, float factor) {
		if (tableSize < 1) {
			throw new IllegalArgumentException("Wrong initial size of HashTable");
		}
		if (factor < 0.3 || factor > 1) {
			throw new IllegalArgumentException("Wrong factor value");
		}
//		hashTable = new LinkedList[tableSize];
		hashTable = new List[tableSize];
		this.factor = factor;
	}
	
	public HashSet() {
		this(DEFAULT_TABLE_SIZE, DEFAULT_FACTOR);
	}
	
	@Override
	public boolean add(T element) {
		if (size >= hashTable.length * factor) {
			tableRecreation();
		}
		int index = getHashIndex(element);
		List<T> list = hashTable[index];
		boolean res = false;
		if (list == null) {
			list = new LinkedList<T>();
			hashTable[index] = list;
		}
		if (!list.contains(element)) {
			res = true;
			list.add(element);
			size++;
		}
		return res;
	}

	private void tableRecreation() {
		HashSet<T> hashSet = new HashSet<>(hashTable.length * 2, factor);
		for (List<T> list: hashTable) {
			if (list != null) {
				for (T obj: list) {
					hashSet.add(obj);
				}
			}
		}
		hashTable = hashSet.hashTable;
	}

	private int getHashIndex(T element) {
		return Math.abs(element.hashCode()) % hashTable.length;
	}

	@Override
	public boolean remove(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
				if (hashTable[index].isEmpty()) {
					hashTable[index] = null;
				}
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashIndex(pattern);
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}
	
	@Override
	public T get(T pattern) {
		// FIXME
		T res = null;
		List<T> list = hashTable[getHashIndex(pattern)];
		if (list != null) {
			T obj = null;
			Iterator<T> it = list.iterator();
			while (it.hasNext() && !isEqual(obj = it.next(), pattern)) {}
			if (isEqual(obj, pattern)) {
				res = obj;
			}
		}
		return res;
	}

}
