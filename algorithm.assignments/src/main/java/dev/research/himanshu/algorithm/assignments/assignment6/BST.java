package dev.research.himanshu.algorithm.assignments.assignment6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author rbk
 * @modified by G31
 * 
 *           Binary search tree (nonrecursive version) Ver 1.1: Bug fixed -
 *           parent of child updated after removeOne
 * 
 **/

public class BST<T extends Comparable<? super T>> {
	
	class Entry<T> {
		T element;
		Entry<T> left, right, parent;

		Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
			element = x;
			left = l;
			right = r;
			parent = p;
		}
	}

	final Entry<T> napointer = new Entry<>(null, null, null, null);

	Entry<T> root;
	int size;
	
	public boolean toRemoveRightSmallest = true;

	BST() {
		root = null;
		size = 0;
	}

	BST(T[] arr) {
		int high = arr.length - 1;

		if (arr.length % 2 == 0)
			high --;

		int mid = (high + 1) / 2;

		this.root = new Entry<T> (arr[mid], recursivelyBuildTree(arr, 0, mid - 1), recursivelyBuildTree(arr, mid + 1, high), null);
		this.size += high + 1; 
				
		if (arr.length % 2 == 0)
			this.add(arr[arr.length - 1]);

		printTree();
	}

	BST(List<T> lst) {
		int high = lst.size() - 1;

		if (lst.size() % 2 == 0)
			high --;

		int mid = (high + 1) / 2;

		this.root = new Entry<T> (lst.get(mid), recursivelyBuildTree(lst, 0, mid - 1), recursivelyBuildTree(lst, mid + 1, high), null);
		this.size += (high + 1); 
				
		if (lst.size() % 2 == 0)
			this.add(lst.get(lst.size() - 1));

		printTree();
	}
	
	public Entry<T> recursivelyBuildTree(List<T> arrList, int low, int high) {
		if (low < high) {
			int mid = low + ((high - low) / 2);

			return new Entry<T>(arrList.get(mid), recursivelyBuildTree(arrList, low, mid - 1),
					recursivelyBuildTree(arrList, mid + 1, high), null);
		}

		return new Entry<T> (arrList.get(low), null, null, null);
	}
	
	public Entry<T> recursivelyBuildTree(T[] arr, int low, int high) {
		if (low < high) {
			int mid = low + ((high - low) / 2);

			return new Entry<T>(arr[mid], recursivelyBuildTree(arr, low, mid - 1),
					recursivelyBuildTree(arr, mid + 1, high), null);
		}

		return new Entry<T> (arr[low], null, null, null);
	}

	// Find x in subtree rooted at node t. Returns node where search ends.
	Entry<T> find(Entry<T> t, T x) {
		Entry<T> pre = t;
		while (t != null) {
			pre = t;
			int cmp = x.compareTo(t.element);
			if (cmp == 0) {
				return t;
			} else if (cmp < 0) {
				t = t.left;
			} else {
				t = t.right;
			}
		}
		return pre;
	}

	// Is x contained in tree?
	public boolean contains(T x) {
		Entry<T> node = find(root, x);
		return node == null ? false : x.equals(node.element);
	}

	// Add x to tree. If tree contains a node with same key, replace element by
	// x.
	// Returns true if x is a new element added to tree.
	public boolean add(T x) {
		if (size == 0) {
			root = new Entry<>(x, null, null, null);
		} else {
			Entry<T> node = find(root, x);
			int cmp = x.compareTo(node.element);
			if (cmp == 0) {
				node.element = x;
				return false;
			}
			Entry<T> newNode = new Entry<>(x, null, null, node);
			if (cmp < 0) {
				node.left = newNode;
			} else {
				node.right = newNode;
			}
		}
		size++;
		return true;
	}

	// Remove x from tree. Return x if found, otherwise return null
	public T remove(T x) {
		T rv = null;
		if (size > 0) {
			Entry<T> node = find(root, x);
			if (x.equals(node.element)) {
				rv = node.element;
				remove(node);
				size--;
			}
		}
		return rv;
	}

	// Called when node has at most one child. Returns that child.
	Entry<T> oneChild(Entry<T> node) {
		return node.left == null ? node.right : node.left;
	}

	// Remove a node from tree
	void remove(Entry<T> node) {
		if (node.left != null && node.right != null) {
			removeTwo(node);
		} else {
			removeOne(node);
		}
	}

	// remove node that has at most one child
	void removeOne(Entry<T> node) {
		if (node == root) {
			Entry<T> nc = oneChild(root);
			root = nc;
			root.parent = null;
		} else {
			Entry<T> p = node.parent;
			Entry<T> nc = oneChild(node);
			if (p.left == node) {
				p.left = nc;
			} else {
				p.right = nc;
			}
			if (nc != null)
				nc.parent = p;
		}
	}

	// remove node that has two children
	void removeTwo(Entry<T> node) {
		if (toRemoveRightSmallest) {
			Entry<T> minRight = node.right;
			while (minRight.left != null) {
				minRight = minRight.left;
			}
			node.element = minRight.element;
			removeOne(minRight);
		} else {
			Entry<T> maxLeft = node.left;
			while (maxLeft.right != null) {
				maxLeft = maxLeft.right;
			}
			node.element = maxLeft.element;
			removeOne(maxLeft);			
		}
		
		toRemoveRightSmallest = !toRemoveRightSmallest;
	}

	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
			} else {
				Comparable[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println("\n");

				arr = t.levelOrderArray();
				System.out.print("(problem a) Level Order Array: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				
				// checking BST(T[] arr) constructor
				System.out.print("(problem b) BST(T[] arr) constructor: ");
				new BST<Integer>(t.toArray(new Integer[t.size]));
				
				// checking BST(List<T> list) constructor
				System.out.print("(problem b) BST(List<T> list) constructor: ");
				new BST<Integer>(Arrays.asList(t.toArray(new Integer[t.size])));
				
				return;
			}
		}
	}

	// Create an array with the elements using in-order traversal of tree
	public T[] toArray(T[] t) {
		inOrder(root, t, 0);
		return t;
	}

	// Create an array with the elements using in-order traversal of tree
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		inOrder(root, arr, 0);
		return arr;
	}

	// Recursive in-order traversal of tree rooted at "node".
	// "index" is next element of array to be written.
	// Returns index of next entry of arr to be written.
	int inOrder(Entry<T> node, Comparable[] arr, int index) {
		if (node != null) {
			index = inOrder(node.left, arr, index);
			arr[index++] = node.element;
			index = inOrder(node.right, arr, index);
		}
		return index;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

	// Preorder traversal of tree
	void preTree(Entry<T> node) {
		if (node != null) {
			System.out.print(" " + node.element);
			preTree(node.left);
			preTree(node.right);
		}
	}

	Comparable[] levelOrderArray() {
		Queue<Entry<T>> level = new LinkedList<>();
		List<Comparable> currentLevel = new ArrayList<Comparable>();
		level.add(root);

		while (!level.isEmpty()) {
			Entry<T> temp = level.poll();
			currentLevel.add(temp.element);

			if (((Entry<T>) temp).left != null)
				level.add(((Entry<T>) temp).left);
			if (((Entry<T>) temp).right != null)
				level.add(((Entry<T>) temp).right);
		}

		Comparable[] arr = currentLevel.toArray((T[]) Array.newInstance(root.element.getClass(), currentLevel.size()));
		return arr;
	}
}
/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output: Add 1 : [1] 1 Add 3 : [2] 1 3 Add 5 : [3] 1 3 5 Add 7 : [4] 1 3 5 7
 * Add 9 : [5] 1 3 5 7 9 Add 2 : [6] 1 2 3 5 7 9 Add 4 : [7] 1 2 3 4 5 7 9 Add 6
 * : [8] 1 2 3 4 5 6 7 9 Add 8 : [9] 1 2 3 4 5 6 7 8 9 Add 10 : [10] 1 2 3 4 5 6
 * 7 8 9 10 Remove -3 : [9] 1 2 4 5 6 7 8 9 10 Remove -6 : [8] 1 2 4 5 7 8 9 10
 * Remove -3 : [8] 1 2 4 5 7 8 9 10 Final: 1 2 4 5 7 8 9 10
 * 
 * Extending to AVL tree:
 * 
 * class AVLEntry<T> extends Entry<T> { int height; AVLEntry(T x, Entry<T> l,
 * Entry<T> r, Entry<T> p) { super(x,l,r,p); height = 0; } }
 * 
 * Extending to Red-Black tree:
 * 
 * private static final boolean RED = false; private static final boolean BLACK
 * = true;
 * 
 * class RBEntry<T> extends Entry<T> { boolean color; RBEntry(T x, Entry<T> l,
 * Entry<T> r, Entry<T> p) { super(x,l,r,p); color = RED; } }
 * 
 */