package dev.research.himanshu.algorithm.assignments.assignment6;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
	private static long startTime, endTime, elapsedTime;
	private static long phase = 0;
	public static long maxDepth = 0;

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			System.out.println("Time: " + elapsedTime + " msec.");
			memory();
			phase = 0;
		}
	}

	public static void memory() {
		long memAvailable = Runtime.getRuntime().totalMemory();
		long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		System.out.println("Memory: " + memUsed / 1000000 + " MB / " + memAvailable / 1000000 + " MB.");
	}

	TreeNode root;
	static final long p = 999959;

	Tree() {
		root = new TreeNode(0);
	}

	// added addition information bearing fields in TreeNode class
	
	/** binary tree node */
	public class TreeNode {
		long data;
		TreeNode left, right, parent;
		long depth, height;

		TreeNode(long x) {
			data = x;
			left = null;
			right = null;
			parent = null;
			depth = 0;
			height = 0;
		}

		/**
		 * create a new node that is attached to par node as left child if
		 * goLeft is true; otherwise, the new node is attached to par as right
		 * child
		 */
		// return t == null ? -1 : 1 + Math.max(height(t.left),
		// height(t.right));
		TreeNode(long x, TreeNode par, boolean goLeft) {
			data = x;
			left = null;
			right = null;
			parent = par;
			depth = (par == null ? 1 : 1 + parent.depth);
			maxDepth = Math.max(maxDepth, depth);
			if (goLeft) {
				par.left = this;
			} else {
				par.right = this;
			}

		}
	} // end of TreeNode class

	// If there is a command line argument, it is used as the depth of the tree
	// generated
	public static void main(String[] args) {
		
		long depth = 100000;
		
		if (args.length > 0)
			depth = Long.parseLong(args[0]);
		Tree x = new Tree();

		// Create a tree composed of 2 long paths
		TreeNode last = x.root;
		for (long i = 1; i <= depth; i++) {
			last = x.new TreeNode(i, last, true);
		}

		last = x.root;
		for (long i = 1; i <= depth; i++) {
			last = x.new TreeNode(depth + i, last, false);
		}

		timer();
		// The tree is visited in level order, using a Queue. Depth and height
		// of each node is computed recursively
		long minSum = Long.MAX_VALUE;
		Queue<TreeNode> Q = new LinkedList<>();
		Q.add(x.root);
		while (!Q.isEmpty()) {
			TreeNode cur = Q.remove();

			if (cur != null) {
				// added logic to calculate the depth in 1 pass.
				cur.height = maxDepth - cur.depth;
				minSum = Math.min(minSum, someFunction(cur));
				Q.add(cur.left);
				Q.add(cur.right);
			}
		}
		System.out.println("Answer: " + minSum + " ");
		timer();
	}

	/**
	 * modified someFunction to Improve the efficiency. Removed 2-pass implementation. (see line : 131)
	 * 
	 * @param cur
	 * @return
	 */
	static long someFunction(TreeNode cur) {
		return rotater(rotater((cur.depth) * (cur.depth)) % p + rotater(cur.height * cur.height) % p);
		
		// return rotater(rotater(depth(cur)*depth(cur))%p +
		// rotater(height(cur)*height(cur))%p);
	}

	static long rotater(long h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		h = (h ^ (h >>> 7) ^ (h >>> 4));
		return h;
	}

	// find the depth of a node
	static long depth(TreeNode t) {
		return t.parent == null ? 0 : 1 + depth(t.parent);
	}

	// height of a node
	static long height(TreeNode t) {
		return t == null ? -1 : 1 + Math.max(height(t.left), height(t.right));
	}

}