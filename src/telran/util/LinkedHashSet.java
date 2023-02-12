package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	HashMap<T, Node<T>> hashMap = new HashMap<>();
	Node<T> head = null;
	Node<T> tail = null;
	
	private static class Node<T> {
		T value;
		Node<T> prev = null;
		Node<T> next = null;

		public Node(T value) {
			this.value = value;
		}
	}
	
	private class LinkedHashSetIterator implements Iterator<T> {

		Node<T> current = head;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node<T> res = current;
			current = current.next;
			flNext = true;
			return res.value;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;  
			LinkedHashSet.this.remove(removedNode.value);
			flNext = false;
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
			Node<T> node = new Node<>(element);
			hashMap.put(element, node);
			if (head == null) {
				head = tail = node;
			} else {
				tail.next = node;
				node.prev = tail;
				tail = node;
			}
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
			removeNodeLinks(node);
			hashMap.remove(pattern);
			size--;
			res = true;
		}
		return res;
	}

	private void removeNodeLinks(Node<T> node) {
		if (node == head) {
			removeHeadLink();
		} else if (node == tail) {
			removeTailNotFirstLink();
		} else {
			removeMiddleLink(node);
		}
		
	}

	private void removeHeadLink() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}
	}

	private void removeTailNotFirstLink() {
		Node<T> prev = tail.prev;
		prev.next = null;
		tail = prev;
	}

	private void removeMiddleLink(Node<T> node) {
		Node<T> prev = node.prev;
		Node<T> next = node.next;
		prev.next = next;
		next.prev = prev;
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
		return res == null ? null : res.value;
	}

}
