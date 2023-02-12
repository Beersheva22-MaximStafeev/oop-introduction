package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements Sorted<T> {
	private static final int SYMBOLS_PER_LEVEL = 3;
	private static final String SYMBOL = " ";
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
			if (prev.left != null && prev.right != null) {
				current = prev;
			}
			removeNode(prev);
		}

		@SuppressWarnings("unused")
		public void removeV0() {
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
		if (node.left == null || node.right == null) {
			removeNodeNotBothNulls(node);
		} else {
			removeNodeWithBothNotNulls(node);
		}
		size--;
	}
	
	private void removeNodeNotBothNulls(Node<T> node) {
		if (node.left == null && node.right == null) {
			removeNodeList(node);
		} else if (node.left == null) {
			removeNodeWithLeftNull(node);
		} else if (node.right == null) {
			removeNodeWithRightNull(node);
		}
	}

	private void removeNodeWithBothNotNulls(Node<T> node) {
		Node<T> removedNode = findLeftmostDown(node.right);
		node.obj = removedNode.obj;
		removeNodeNotBothNulls(removedNode);
	}
	
	@SuppressWarnings("unused")
	private void removeNodeWithBothNotNullsV0(Node<T> node) {
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
	
	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int comp = 0;
		Node<T> current = root;
		while (current != null && (comp = comparator.compare(pattern, current.obj)) != 0) {
			if ((comp < 0 && !isFloor) || (comp > 0 && isFloor) ) {
				res = current.obj;
			} 
			current = comp < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;
		
	}	

	@Override
	public T floor(T element) {
		return floorCeiling(element, true);
	}
	
	public T floorV0(T element) {
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
		return floorCeiling(element, false);
	}
		
	public T ceilingV0(T element) {
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
	
	public void displayTreeRotated() {
		displayTreeRotated(root, 0);
	}
	
	private void displayTreeRotated(Node<T> node, int level) {
		if (node != null) {
			displayTreeRotated(node.right, level + 1);
			displayRoot(node, level);
			displayTreeRotated(node.left, level + 1);
		}
	}


	private void displayRoot(Node<T> node, int level) {
		System.out.printf("%s%s%n", SYMBOL.repeat(SYMBOLS_PER_LEVEL * level), node.obj.toString());		
	}


	public int height() {
		return height(root);
	}

	private int height(Node<T> node) {
		return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
	}


	// 2. find count of end elements = width
	public int width() {
		return width(root);
	}

	private int width(Node<T> node) {
		int res = 0;
		if (node != null) {
			if (node.left == null && node.right == null) {
				res = 1;
			} else {
				res = width(node.left) + width(node.right);
			}
		}
		return res;
	}

	// 1. inversion: left <-> right
	public void inversion() {
		inversion(root);
		comparator = comparator.reversed();
	}


	private void inversion(Node<T> node) {
		if (node != null) {
			inversion(node.left);
			inversion(node.right);
			Node<T> tmp = node.left;
			node.left = node.right;
			node.right = tmp;
		}
	}
	
	public void balance() {
		Node<T>[] array = getNodesArray();
		root = balance(array, 0, array.length - 1, null);
	}
	
	private Node<T> balance(Node<T>[] array, int left, int right, Node<T>parent) {
		Node<T> root = null;
		if (left <= right) {
			final int rootIndex = (left + right) / 2;
			root = array[rootIndex];
			root.parent = parent;
			root.left = balance(array, left, rootIndex - 1, root);
			root.right = balance(array, rootIndex + 1, right, root);
		}
		return root;
	}
	
	@SuppressWarnings("unchecked")
	private Node<T>[] getNodesArray() {
		Node<T> res[] = new Node[size];
		int index = 0;
		if (root != null) {
			Node<T> current = findLeftmostDown(root);
			while (current != null) {
				res[index++] = current;
				current = getNextCurrent(current);
			} 
		}
		return res;
	}


	@Override
	public T get(T pattern) {
		// FIXME
//		T res = null;
//		if (root != null) {
//			res = findPosistionOfElement(root, pattern).obj;
//			if (!isEqual(res, pattern)) {
//				res = null;
//			}
//		}
//		return res;
		T res = floor(pattern);
		if (!isEqual(pattern, res)) {
			res = null;
		}
		return res;
	}

}
