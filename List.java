
/**
 * Defines a doubly-linked list class
 * Hassham Malik
 * The following code focuses on Linked Lists, with tons of methods implemented to help navigate through a List
 * There are a wide range of constructors, accessors, and mutator methods.
 */

import java.util.NoSuchElementException;

public class List<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTOR ****/

	/**
	 * instantiates a new List with default values
	 * @postcondition New list constructed
	 */
	public List() {
		first = null;
		last = null;
		iterator = null;
		length = 0;
	}

	/**
	 * makes a copy of this list object
	 * @postcondition New list constructed
	 */
	public List(List<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			length = 0;
			first = last = iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored in the first node
	 * @precondition first != null
	 * @return the value stored at node first
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst(): Cannot read first.data from an empty List.");
		}
		return first.data;
	}

	/**
	 * Returns the value stored in the last node
	 * @precondition last != null
	 * @return the value stored in the node last
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast(): Cannot read last.data from an empty List.");
		}
		return last.data;
	}

	/**
	 * Returns the value stored in the iterator node
	 * @precondition iterator != null
	 * @return the value stored in the node iterator
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getIterator() throws NoSuchElementException {
		if (offEnd()) {
			throw new NoSuchElementException("getIterator(): iterator is off end");
		}
		return iterator.data;
	}

	/**
	 * Returns the current length of the list
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the list is currently empty
	 * @return whether the list is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether iterator is off the end of the list, i.e. is NULL
	 * @return whether iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}

	/**
	 * Overrides the equals method for object to compare this list to another list
	 * @return whether two lists are the same
	 */
	public boolean equals(List<T> obj) {
		if (this.getLength() != obj.getLength()) {
			return false;
		}
		Node temp = this.first;
		Node temp2 = obj.first;
		while (temp != null || temp2 != null) {
			if (temp.data != temp2.data) {
				return false;
			}
			temp = temp.next;
			temp2 = temp2.next;
		}
		return true;
	}

	/**** MUTATORS ****/

	/**
	 * Creates a new first element
	 * @param data the data to insert at the front of the list
	 * @postcondition New Node constructed with T data at the front of the data set,
	 *                referenced by first
	 */
	public void addFirst(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			N.next = first;
			first.prev = N;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 * @param data the data to insert at the end of the list
	 * @postcondition New Node constructed with T data at the end of the data set
	 *                (List.next == null), referenced by last
	 */
	public void addLast(T data) {
		if (first == null) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			last.next = N;
			N.prev = last;
			last = N;
		}
		length++;
	}

	/**
	 * Inserts an element after the node currently pointed to by the iterator
	 * @precondition iterator != null;
	 * @postcondition New Node constructed with T data at the node after iterator
	 */
	public void addIterator(T data) {
		if (iterator == null) {
			throw new NoSuchElementException("addIterator(): iterator is off end");
		}
		if (iterator == last) {
			addLast(data);
		} else {
			Node N = new Node(data);
			iterator.next.prev = N;
			N.next = iterator.next;
			iterator.next = N;
			N.prev = iterator;
			length++;
		}
	}

	/**
	 * removes the element at the front of the list
	 * @precondition first != null
	 * @postcondition dereferences first node, first references second node if it
	 *                exists, else references null
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List.");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * removes the element at the end of the list
	 * @precondition last != null
	 * @postcondition dereferences last node, last references second-to-last node if
	 *                it exists, else references null.
	 * @throws NoSuchElementException when precondition is violated
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): Cannot remove from an empty List.");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * removes the element pointed to by iterator
	 * @precondition iterator != null
	 * @postcondition dereferences iterator node, iterator points to null
	 * @throws NoSuchElementException
	 */
	public void removeIterator() throws NoSuchElementException {
		if (offEnd()) {
			throw new NoSuchElementException("removeIterator(): iterator is off end.");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.next.prev = iterator.prev;
			iterator.prev.next = iterator.next;
			iterator = null;
			length--;
		}
	}

	/**
	 * moves iterator to the start of the list
	 * @postcondition iterator = first
	 */
	public void placeIterator() {
		iterator = first;
	}

	/**
	 * Moves the iterator up by one node
	 * @precondition iterator != null
	 * @postcondition iterator points to its next node
	 */
	public void advanceIterator() {
		if (iterator == last) {
			iterator = null;
		}
		if (!offEnd()) {
			iterator = iterator.next;
		}
	}

	/**
	 * Moves the iterator down by one node
	 * @precondition iterator is not off end
	 * @postcondition iterator points to its previous node
	 */
	public void reverseIterator() {
		if (iterator == first) {
			iterator = null;
		} else if (!offEnd()) {
			iterator = iterator.prev;
		}
	}

	/**** ADDITIONAL OPERATIONS ****/

	/**
	 * List with each value on its own line At the end of the List a new line
	 * @return the List as a String for display
	 */
	@Override
	public String toString() {
		String result = "";
		Node temp = first;
		int pos = 1;
		while (temp != null) {
			result += +pos + ". " + temp.data;
			temp = temp.next;
			pos++;
		}
		return result;
	}

	public String printNumberedList() {
		String result = "";
		int num = 1;
		Node temp = first;
		while (temp != null) {
			result += num + ": " + temp.data + '\n';
			num++;
			temp = temp.next;
		}
		return result;
	}

	/**
	 * Points the iterator at first and then advances it to the specified index
	 * @param index the index where the iterator should be placed
	 * @precondition 0 < index <= length
	 * @throws IndexOutOfBoundsException when precondition is violated
	 */
	public void iteratorToIndex(int index) throws IndexOutOfBoundsException {
		if (0 >= index || index > length) {
			throw new IndexOutOfBoundsException("iteratorToIndex(" + index + "): Index Out of Bounds");
		} else {
			iterator = first;
			for (int i = 1; i < index; i++) {
				advanceIterator();
			}
		}
	}

	/**
	 * Searches the List for the specified value using the linear search algorithm
	 * @param value the value to search for
	 * @return the location of value in the List or -1 to indicate not found Note
	 *         that if the List is empty we will consider the element to be not
	 *         found post: position of the iterator remains unchanged
	 */
	public int linearSearch(T value) {
		Node temp = first;
		for (int i = 0; i < length; i++) {
			if (temp.equals(value)) {
				return i;
			}
			temp = temp.next;
		}
		return -1;
	}
}