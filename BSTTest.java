
/**
* BSTTest.java
* Hassham Malik
* The following code tests each of the methods that were implemented in BST.java to make sure they function.
*/

import java.util.NoSuchElementException;
import java.util.Comparator;

public class BSTTest {
	public static void main(String args[]) {
		BSTReference<Integer> tree1 = new BSTReference<>();
		System.out.println("tree1.isEmpty(): " + tree1.isEmpty()); // prints true
		tree1.insert(11);
		System.out.println("tree1.isEmpty(): " + tree1.isEmpty()); // prints false
		tree1.insert(6);
		System.out.println();
		System.out.println("tree1.getSize(): " + tree1.getSize()); // size == 2
		tree1.insert(19);
		tree1.insert(4);
		tree1.insert(8);
		tree1.insert(17);
		tree1.insert(43);
		tree1.insert(5);
		tree1.insert(10);
		tree1.insert(31);
		tree1.insert(49);
		System.out.println("tree1.getSize(): " + tree1.getSize()); // size == 11
		System.out.println("tree1.getHeight(): " + tree1.getHeight()); // height == 3
		System.out.println("tree1.findMin(): " + tree1.findMin()); // min == 4
		System.out.println("tree1.findMax(): " + tree1.findMax()); // max == 49
		System.out.println("tree1.getRoot(): " + tree1.getRoot()); // root == 11
		System.out.println("test1.search(6, false): " + tree1.search(6, false)); // search(6) returns 6
		System.out.println();
		tree1.inOrderPrint();
		System.out.println();
		tree1.preOrderPrint();
		System.out.println();
		tree1.postOrderPrint();
		System.out.println();

		BSTReference<Integer> tree2 = new BSTReference<>(tree1);

		System.out.println("tree2.isEmpty(): " + tree2.isEmpty());
		System.out.println("tree2.getSize(): " + tree2.getSize()); // size == 11
		System.out.println("tree2.getRoot(): " + tree2.getRoot()); // root == 11
		System.out.println();
		tree2.inOrderPrint();
		tree2.remove(4);
		tree2.remove(5);
		tree2.remove(10);
		tree2.remove(31);
		tree2.remove(49);
		tree2.inOrderPrint();
		System.out.println("tree2.getHeight(): " + tree2.getHeight()); // height == 2
		System.out.println("tree2.findMin(): " + tree2.findMin()); // min == 6
		System.out.println("test2.search(43, false): " + tree2.search(43, false)); // search(43) returns 43
		System.out.println("tree2.findMax(): " + tree2.findMax()); // max == 43
		System.out.println();
		tree2.preOrderPrint();
		System.out.println();

		BSTReference<Integer> tree3 = new BSTReference<>(tree2);
		System.out.println("tree3.getRoot(): " + tree3.getRoot()); // root == 11
		tree3.remove(11);
		System.out.println("tree3.getRoot(): " + tree3.getRoot()); // root == 17

		BSTReference<String> tree4 = new BSTReference<>();
		tree4.insert("apple");
		tree4.insert("banana");
		System.out.println("test4.search(banana, false): " + tree4.search("banana", false)); // search(banana) returns banana
		System.out.println("test4.search(bleh, false): " + tree4.search("bleh", false)); // search(bleh) returns null
		tree4.remove("apple");
		System.out.println();
		tree4.inOrderPrint();
	}

}
