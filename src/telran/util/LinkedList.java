package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T> {

	// ForLinkedHashSet
	// private
	static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		Node(T obj) {
			this.obj = obj;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	
	private class LinkedListIterator implements Iterator<T> {

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
			return res.obj;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current == null ? tail : current.prev;  
			removeNode(removedNode);
			flNext = false;
		}
	}
	
	// ForLinkedHashSet
	Node<T> getTailNode() {
		return tail;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	@Override
	public boolean add(T element) {		
		Node<T> node = new Node<>(element);
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> node = head;
		boolean res = false;
		while (node != null && !isEqual(node.obj, pattern)) {
			node = node.next;
		}
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}

	// ForLinkedHashSet
	// private 
	void removeNode(Node<T> node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
	}

	private void removeMiddle(Node<T> node) {
		Node<T> prev = node.prev;
		Node<T> next = node.next;
		prev.next = next;
		next.prev = prev;
		size--;
	}

	private void removeTail() {
		if (tail.prev == null) {
			head = tail = null;
		} else {
			Node<T> prev = tail.prev;
			prev.next = null;
			tail = prev;
		}
		size--;
	}

	private void removeHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node<T> next = head.next;
			next.prev = null;
			head = next;
		}
		size--;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, true);
		if (index == size) {
			add(element);
		} else if (index == 0) {
			addHead(element);
		} else {
			addMiddle(index, element);
		}		
	}

	private void addMiddle(int index, T element) {
		// index > 0 && index < size - 1
		Node<T> node = new Node<>(element);
		Node<T> nodeIndex = getNode(index);
		Node<T> nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;		
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> node = tail;
		for (int i = size - 1; i > index; i--) {
			node = node.prev;
		}
		return node;
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	private void addHead(T element) {
		// running when head exists, size > 0 !!!
		Node<T> node = new Node<>(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		if (node == null) {
			throw new IllegalStateException("Removing node is null");
		}
		T element = node.obj;
		removeNode(node);
		return element;
	}

	@Override
	public int indexOf(T pattern) {
		int i = 0;
		Node<T> node = head;
		while (i < size && !isEqual(node.obj, pattern)) {
			node = node.next;
			i++;
		}
		return i == size ? -1 : i;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = size - 1;
		Node<T> node = tail;
		while (i > -1 && !isEqual(node.obj, pattern)) {
			node = node.prev;
			i--;
		}
		return i;
	}

	@Override
	public T get(int index) {
		checkIndex(index, false);
		return getNode(index).obj;
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, false);
		Node<T> node = getNode(index);
		node.obj = element;
	}

	public void setNext(int index1, int index2) {
		//TO DO
		// index2 should be <= index1
		checkIndex(index1, false);
		checkIndex(index2, false);
		if (index1 < index2) {
			throw new IllegalArgumentException();
		}
		getNode(index1).next = getNode(index2);
	}
	
	/*
	 *  Write method isLoop returning true if the LinkedList object has a loop
	 *  3.1. Limitations
	 *  3.1.1. O[N]
	 *  3.1.2. No additional collections, no arrays
	 *  3.1.3. No using property/method “size”
	 *  3.1.4. No using field prev in the class Node
	 */
	public boolean hasLoop() {
		Node<T> index = head;
		Node<T> indexD2 = head;
		boolean res = false;
		while (index != null && index.next != null && !res) {
			index = index.next.next;
			indexD2 = indexD2.next;
			res = index == indexD2;
		}
		return res;
	}
}
