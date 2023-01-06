package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
//import java.util.function.Predicate;

public class LinkedList2<T> extends AbstractCollection<T> implements List<T> {

	private class Node {
		T obj;
		Node prev;
		Node next;
		Node(T obj) {
			this.obj = obj;
		}
	}
	
	private Node head;
	private Node tail;
	
	private class LinkedListIterator implements Iterator<T> {

		Node current = head;
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
			Node res = current;
			current = current.next;
			flNext = true;
			return res.obj;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node removedNode = current == null ? tail : current.prev;  
			removeNode(removedNode);
			flNext = false;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	@Override
	public boolean add(T element) {		
		Node node = new Node(element);
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
		Node node = head;
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

	private void removeNode(Node node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
	}

	private void removeMiddle(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		prev.next = next;
		next.prev = prev;
		size--;
	}

	private void removeTail() {
		if (tail.prev == null) {
			head = tail = null;
		} else {
			Node prev = tail.prev;
			prev.next = null;
			tail = prev;
		}
		size--;
	}

	private void removeHead() {
		if (head.next == null) {
			head = tail = null;
		} else {
			Node next = head.next;
			next.prev = null;
			head = next;
		}
		size--;
	}

//	@Override
//	public boolean removeIf(Predicate<T> predicate) {
//		Node node = head;
//		int oldSize = size;
//		while (node != null) {
//			if (predicate.test(node.obj)) {
//				removeNode(node);
//			}
//			node = node.next;
//		}
//		return oldSize > size;
//	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node node = head;
		for (int i = 0; i < size; i++) {
			ar[i] = node.obj;
			node = node.next;
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
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
		Node node = new Node(element);
		Node nodeIndex = getNode(index);
		Node nodePrev = nodeIndex.prev;
		nodePrev.next = node;
		node.prev = nodePrev;
		nodeIndex.prev = node;
		node.next = nodeIndex;
		size++;		
	}
	
	private Node getNode(int index) {
		return index < size / 2 ? getNodeFromLeft(index) : getNodeFromRight(index);
	}

	private Node getNodeFromRight(int index) {
		Node node = tail;
		for (int i = size - 1; i > index; i--) {
			node = node.prev;
		}
		return node;
	}

	private Node getNodeFromLeft(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	private void addHead(T element) {
		// running when head exists, size > 0 !!!
		Node node = new Node(element);
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, false);
		Node node = getNode(index);
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
		Node node = head;
		while (i < size && !isEqual(node.obj, pattern)) {
			node = node.next;
			i++;
		}
		return i == size ? -1 : i;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = size - 1;
		Node node = tail;
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
		Node node = getNode(index);
		node.obj = element;
	}

}
