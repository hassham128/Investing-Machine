
/**
* BST.java
* Hassham Malik
* The following code focuses on Binary Search Trees, with tons of methods implemented to help navigate through a BST
* There are a wide range of constructors, accessors, and mutator methods.
*/

import java.util.NoSuchElementException;
import java.util.Comparator;

public class BST<T> {
	public class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/

	// Default constructor for BST sets root to null
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * @param bst the BST of which to make a copy.
	 * @param c   the way the tree is organized
	 */
	public BST(BST<T> bst, Comparator c) {
		if (bst == null) {
			return;
		} else if (bst.isEmpty()) {
			root = null;
		} else {
			Node temp = bst.root;
			copyHelper(temp, c);
		}
	}

	/**
	 * Helper method for copy constructor
	 * @param node the node containing data to copy
	 * @param c    the way the tree is organized
	 */
	private void copyHelper(Node node, Comparator c) {
		if (node == null) {
			return;
		} else {
			insert(node.data, c);
			copyHelper(node.left, c);
			copyHelper(node.right, c);
		}
	}

	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot(): isEmpty()");
		} else {
			return root.data;
		}
	}

	/**
	 * Determines whether the tree is empty
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (isEmpty()) {
			return -1;
		}
		if (node == null) {
			return -1;
		}
		if (getHeight(node.left) >= getHeight(node.right)) {
			return getHeight(node.left) + 1;
		} else {
			return getHeight(node.right) + 1;
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): isEmpty()");
		} else {
			return findMin(root);
		}
	}

	/**
	 * Recursive helper method to findMin method
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left == null) {
			return node.data;
		} else {
			return findMin(node.left);
		}
	}

	/**
	 * Returns the largest value in the tree
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMax(): isEmpty()");
		} else {
			return findMax(root);
		}
	}

	/**
	 * Recursive helper method to findMax method
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right == null) {
			return node.data;
		} else {
			return findMax(node.right);
		}
	}

	/**
	 * Searches for a specified value in the tree
	 * @param data   the value to search for
	 * @param update whether to update the node's data with the given data
	 * @param c      the Comparator that indicates the way the data in the tree was
	 *               ordered
	 * @return whether the value is stored in the tree
	 */
	public T search(T data, boolean update, Comparator<T> c) {
		if (root == null) {
			return null;
		} else {
			return search(data, root, update, c);
		}
	}

	/**
	 * Helper method for the search method
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private T search(T data, Node node, boolean update, Comparator<T> c) {
		if (c.compare(data, node.data) == 0) {
			if (update == true) {
				node.data = data;
			}
			return node.data;
		} else if (c.compare(data, node.data) < 0) {
			if (node.left == null) {
				return null;
			} else {
				return search(data, node.left, update, c);
			}
		} else {
			if (node.right == null) {
				return null;
			} else {
				return search(data, node.right, update, c);
			}
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * @param data the data to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	public void insert(T data, Comparator<T> c) {
		if (root == null) {
			root = new Node(data);
		} else {
			insert(data, root, c);
		}

	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location to insert
	 * @param c    the Comparator indicating how data in the tree is ordered
	 */
	private void insert(T data, Node node, Comparator<T> c) {
		if (c.compare(data, node.data) <= 0) {
			if (node.left == null) {
				node.left = new Node(data);
			} else {
				insert(data, node.left, c);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(data);
			} else {
				insert(data, node.right, c);
			}
		}
	}

	/**
	 * Removes a value from the BST
	 * @param data the value to remove
	 * @param c    the Comparator indicating how data in the tree is organized Note:
	 *             updates nothing when the element is not in the tree
	 */
	public void remove(T data, Comparator<T> c) throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("remove(): isEmpty()");
		} else {
			remove(data, root, c);
		}
	}

	/**
	 * Helper method to the remove method
	 * @param data the data to remove
	 * @param node the current node
	 * @param c    the Comparator indicating how data in the tree is organized
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node, Comparator<T> c) {
		if (node == null) {
			return node;
		} else if (c.compare(data, node.data) < 0) {
			node.left = remove(data, node.left, c);
		} else if (c.compare(data, node.data) > 0) {
			node.right = remove(data, node.right, c);
		} else {
			if (node.left == null && node.right == null) {
				if (node == root) {
					root = null;
				}
				node = null;
			} else if (node.left != null && node.right == null) {
				if (node == root) {
					root = node.left;
				}
				node = node.left;
			} else if (node.right != null && node.left == null) {
				if (node == root) {
					root = node.right;
				}
				node = node.right;
			} else {
				node.data = findMin(node.right);
				node.right = remove(findMin(node.right), node.right, c);
			}
		}
		return node;
	}

	/*** ADDITONAL OPERATIONS ***/

	// Prints the data in pre order to the console followed by a new line
	public void preOrderPrint() {
		preOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to preOrderPrint method Prints the data in pre order to the
	 * console followed by a new line
	 */
	private void preOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			System.out.println(node.data);
			preOrderPrint(node.left);
			preOrderPrint(node.right);
		}
	}

	
	// Prints the data in sorted order to the console followed by a new line
	 
	public void inOrderPrint() {
		inOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to inOrderPrint method Prints the data in sorted order to the
	 * console followed by a new line
	 */
	private void inOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			inOrderPrint(node.left);
			System.out.println(node.data);
			inOrderPrint(node.right);
		}
	}

	// Prints the data in post order to the console followed by a new line
	public void postOrderPrint() {
		postOrderPrint(root);
		System.out.println();
	}

	/**
	 * Helper method to postOrderPrint method Prints the data in post order to the
	 * console
	 */
	private void postOrderPrint(Node node) {
		if (node == null) {
			return;
		} else {
			postOrderPrint(node.left);
			postOrderPrint(node.right);
			System.out.println(node.data);
		}
	}
}