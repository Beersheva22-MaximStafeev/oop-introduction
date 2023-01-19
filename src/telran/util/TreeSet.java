package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	private Node<T> root;
	private Comparator<T> comparator;
	
	static private class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		
		Node(T obj) {
			this.obj = obj;
		}
	}
	
	private class TreeSetIterator implements Iterator<T> {
		private Node<T> current = null;
		private Node<T> prev = null;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (current == null) {
				throw new NoSuchElementException();
			}
			T result = current.obj;
			prev = current;
			flNext = true;
			current = getNextCurrent(current);
			return result;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			removeNode(prev);
		}

		public TreeSetIterator() {
			super();
			if (root != null) {
				current = findLeftmostDown(root);
			}
		}
		
	}
	

	public TreeSet(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	
	private Node<T> findReturnFromLeft(Node<T> startNode) {
		Node<T> resultNode = startNode;
		while (resultNode.parent != null && resultNode.parent.left != resultNode) {
			resultNode = resultNode.parent;
		}
		return resultNode.parent;
	}

	private Node<T> findLeftmostDown(Node<T> startNode) {
		Node<T> resultNode = startNode;
		while (resultNode.left != null) {
			resultNode = resultNode.left;
		}
		return resultNode;
	}

	public Node<T> getNextCurrent(Node<T> current) {
		if (current.right != null) {
			current = findLeftmostDown(current.right);
		} else {
			current = findReturnFromLeft(current);
		}
		return current;
	}

	@SuppressWarnings("unchecked")
	// for those types, witch are Comparable already
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	
	@Override
	public boolean add(T element) {
		boolean res = false;
		if (root == null) {
			root = new Node<T>(element);
			size++;
			res = true;
		} else {
			Node<T> node = findPosistionOfElement(root, element);
			if (!isEqual(node.obj, element)) {
				connect(node, new Node<T>(element));
				size++;
				res = true;
			}
		}
		return res;
	}


	private void connect(Node<T> nodeToConnect, Node<T> newNode) {
		if (comparator.compare(newNode.obj, nodeToConnect.obj) < 0) {
			nodeToConnect.left = newNode;
			newNode.parent = nodeToConnect;
		} else {
			nodeToConnect.right = newNode;
			newNode.parent = nodeToConnect;
		}
	}

	private Node<T> findPosistionOfElement(Node<T> startNode, T element) {
		Node<T> nodeAnalyzed = startNode;
		boolean running = comparator.compare(element, startNode.obj) != 0;
		while (running) {
			if (comparator.compare(element, nodeAnalyzed.obj) < 0) {
				if (nodeAnalyzed.left != null) {
					nodeAnalyzed = nodeAnalyzed.left;
				} else {
					running = false;
				}
			} else if (comparator.compare(element, nodeAnalyzed.obj) > 0) {
				if (nodeAnalyzed.right != null) {
					nodeAnalyzed = nodeAnalyzed.right;
				} else {
					running = false;
				}
			} else {
				running = false;
			}
		}
		return nodeAnalyzed;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> nodeToRemove = findPosistionOfElement(root, pattern);
		if (comparator.compare(nodeToRemove.obj, pattern) == 0) {
			removeNode(nodeToRemove);
			res = true;
		}
		return res;
	}
	
	private void removeNode(Node<T> node) {
		if (node.left == null && node.right == null) {
			removeNodeList(node);
		} else if (node.left == null) {
			removeNodeWithLeftNull(node);
		} else if (node.right == null) {
			removeNodeWithRightNull(node);
		} else {
			removeNodeWithBothNotNulls(node);
		}
		size--;
	}

	private void removeNodeWithBothNotNulls(Node<T> node) {
		if (node == root) {
			root = node.left;
			root.parent = null;
		} else {
			connect(node.parent, node.left);
		}
		connect(findPosistionOfElement(node.left, node.right.obj), node.right);
	}

	private void removeNodeWithRightNull(Node<T> node) {
		if (node == root) {
			root = node.left;
			root.parent = null;
		} else {
			connect(node.parent, node.left);
		}
	}

	private void removeNodeWithLeftNull(Node<T> node) {
		if (node == root) {
			root = node.right;
			root.parent = null;
		} else {
			connect(node.parent, node.right);
		}
	}

	private void removeNodeList(Node<T> node) {
		if (node == root) {
			root = null;
		} else {
			if (node.parent.left == node) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}

	@Override
	public boolean contains(T pattern) {
		boolean res = false;
		if (root != null) {
			res = isEqual(findPosistionOfElement(root, pattern).obj, pattern);
		}
		return res;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}

	@Override
	public T floor(T element) {
		// returns greatest among elements less than given element or equal
		T res = null;
		if (root != null) {
			Node<T> current = root;
			int comp = 0;
			while (current != null && (comp = comparator.compare(current.obj, element)) != 0) {
				if (comp < 0) {
					res = res == null ? current.obj : comparator.compare(current.obj, res) > 0 ? current.obj : res;
					current = current.right;
				} else {
					current = current.left;
				}
			}
			if (current != null)
				res = current.obj;
		}
		return res;
	}

	@Override
	public T ceiling(T element) {
		// returns least among elements greater than given element or equal
		T res = null;
		if (root != null) {
			Node<T> current = root;
			int comp = 0;
			while (current != null && (comp = comparator.compare(current.obj, element)) != 0) {
				if (comp > 0) {
					res = res == null ? current.obj : comparator.compare(current.obj, res) < 0 ? current.obj : res;
					current = current.left;
				} else {
					current = current.right;
				}
			}
			if (current != null)
				res = current.obj;
		}
		return res;
	}

	@Override
	public T first() {
		return findLeftmostDown(root).obj;
	}

	@Override
	public T last() {
		Node<T> resultNode = root;
		while (resultNode.right != null) {
			resultNode = resultNode.right;
		}
		return resultNode.obj;
	}

}
