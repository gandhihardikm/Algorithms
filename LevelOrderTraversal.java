/**

Tree related Problems

 * 
 */
package com.example.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author pankaj
 * 
 */

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {

		this.data = data;
		left = null;
		right = null;

	}

}

public class LevelOrderTraversal {

	Node root;

	/**
	 * @param args
	 */

	public void printLevelOrder() {

		int h = height(root);

		for (int i = 0; i <= h; i++)
			printGivenLevel(root, i);

	}

	public void printGivenLevel(Node root, int level) {

		if (root == null)
			return;
		if (level == 1)
			System.out.println(root.data + " ");
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);

		}

	}

	public void printLevelOrderQueue() {

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node tempNode = queue.poll();
			System.out.println(tempNode.data + "");

			if (tempNode.left != null) {
				queue.add(tempNode.left);
			}

			if (tempNode.right != null) {
				queue.add(tempNode.right);
			}

		}

	}

	int height(Node root) {

		if (root == null)
			return 0;

		int leftH = height(root.left);
		int rightH = height(root.right);

		if (leftH > rightH)
			return leftH + 1;
		else
			return rightH + 1;
	}

	public void inorderWithStack() {

		if (root == null) {
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		Node node = root;

		while (node != null) {
			stack.push(node);
			node = node.left;
		}

		while (stack.size() > 0) {

			node = stack.pop();
			System.out.println(node.data + " ");

			if (node.right != null) {
				node = node.right;

				while (node != null) {
					stack.push(node);
					node = node.left;
				}

			}

		}

	}

	public int siz_tree(Node root) {

		if (root == null) {
			return 0;
		}

		return siz_tree(root.left) + siz_tree(root.right) + 1;

		// return si;+1
	}

	public int sumPresent(Node root, int sum, int ip) {

		if (root == null) {

			if (sum == ip) {
				System.out.println("Sum found" + ip);
				return 1;
			}
		} else {
			ip = ip + root.data;

			sumPresent(root.left, sum, ip);
			sumPresent(root.right, sum, ip);
		}

		return 0;
	}

	public void mirror_tree(Node root) {

		if (root == null)
			return;

		Node temp = root.left;
		root.left = root.right;
		root.right = temp;

		mirror_tree(root.left);
		mirror_tree(root.right);

	}

	public void SumLevelOrderQueue() {

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		int sum = 0;
		int level = 1;
		int no_of_nodes = -1;

		/*
		 * while (!queue.isEmpty()) { Node tempNode = queue.poll();
		 * System.out.println(tempNode.data + "");
		 * 
		 * no_of_nodes++; // if(tempNode.) level=level*2;
		 * 
		 * if (tempNode.left != null) { queue.add(tempNode.left); }
		 * 
		 * if (tempNode.right != null) { queue.add(tempNode.right); }
		 * 
		 * 
		 * 
		 * if (tempNode.left == null && tempNode.right == null) {
		 * 
		 * 
		 * 
		 * sum = sum + tempNode.data; System.out.println("Partial Sum" + sum); }
		 * 
		 * }
		 */

		int mul = 1;
		int level_sum = 0;
		boolean flag = false;

		while (true) {

			int level_node_cnt = queue.size();

			flag = false;

			if (queue.isEmpty())
				break;

			while (level_node_cnt > 0) {

				Node temp = queue.poll();

				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}

				if (temp.left == null && temp.right == null) {

					flag = true;
					level_sum = level_sum + temp.data;
				}

				level_node_cnt--;
			}

			if (flag) {
				mul = mul * level_sum;
				level_sum = 0;
			}
		}

		System.out.println("Result is" + mul);
	}

	public int minimum_leaf_distance(Node root) {

		if (root.left == null && root.right == null) {
			return 0;
		} else {
			minimum_leaf_distance(root.left);
			minimum_leaf_distance(root.right);

		}

		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LevelOrderTraversal tree = new LevelOrderTraversal();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		//tree.root.right.right = new Node(8);

		int height = tree.height(tree.root);

		System.out.println("Height of this Tree is " + height);

		System.out.println("Level order traversal of binary tree is:: ");
		tree.printLevelOrder();

		System.out
				.println("Level order traversal of binary tree is:: by using Queue ");
		tree.printLevelOrderQueue();

		System.out.println("In order Traversal using Stack is:");
		tree.inorderWithStack();

		System.out.println("Sum Present");
		int res = tree.sumPresent(tree.root, 7, 0);

		System.out.println(res);

		// System.out.println("Result of Float to Int"+(int)2.6);
		System.out.println("Size of the tree is" + (tree.siz_tree(tree.root)));

		//tree.mirror_tree(tree.root);
		System.out.println("Mirror********************************");
		tree.printLevelOrder();

		System.out.println("Sum of Leaf Nodes********************************");
		tree.SumLevelOrderQueue();

		System.out.println("Max Width********************************");
		tree.printMAxWidth(tree.root);
		
		System.out.println("Is Complete Binary Tree ********************************"+tree.isCompleteBineryTree(tree.root));
		

		System.out.println("Exited!!!");

	}

	public boolean isCompleteBineryTree(Node root) {

		boolean flag=false;
		
		if (root == null)
			return true;

		Queue<Node> q = new LinkedList<Node>();
		q.add(root);

		while (!q.isEmpty()) {

			Node temp=q.poll();
			
			//System.out.println(temp.data);
			
			if (temp.left != null) {
				
				if(flag)
				return false;
				
				q.add(temp.left);
				
			}else{
				flag=true;
			}
			
			if(temp.right!=null){
				
				if(flag)
				return false;
				
				q.add(temp.right);
				
			}else{
				flag=true;
			}

		}

		return true;
	}

	public void printMAxWidth(Node root) {

		int max_width = Integer.MIN_VALUE;
		Queue<Node> q = new LinkedList<Node>();

		q.add(root);

		while (true) {

			int no_of_node = q.size();

			System.out.println(no_of_node);

			// i/f(q.isEmpty())
			// break;

			if (no_of_node == 0)
				break;

			if (max_width < no_of_node) {
				max_width = no_of_node;
			}

			while (no_of_node > 0) {

				Node temp = q.poll();

				if (temp.left != null) {
					q.add(temp.left);

				}
				if (temp.right != null) {

					q.add(temp.right);
				}

				no_of_node--;

			}

		}

		System.out.println("Max Width of the Binary Tree is: " + max_width);
	}

}
