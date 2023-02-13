package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	private HashMap<T, LinkedList.Node<T>> hashMap = new HashMap<>();
	private LinkedList<T> list = new LinkedList<>();
		
	private class LinkedHashSetIterator implements Iterator<T> {

		Iterator<T> it = list.iterator();
		T current = null;

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			return current = it.next();
		}
		
		@Override
		public void remove() {
			it.remove();
			hashMap.remove(current);
			size--;
		}
	}
	
	/**
	 * @return true  if added
	 * @return false if already existed
	 */
	@Override
	public boolean add(T element) {
		boolean res = false;
		if (hashMap.get(element) == null) {
			list.add(element);
			hashMap.put(element, list.getTailNode());
			size++;
			res = true;
		}
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = hashMap.get(pattern);
		if (node != null) {
			list.removeNode(node);
			hashMap.remove(pattern);
			size--;
			res = true;
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		return hashMap.contanisKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		Node<T> res = hashMap.get(pattern);
		return res == null ? null : res.obj;
	}

}
