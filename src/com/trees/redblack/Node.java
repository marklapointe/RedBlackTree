package com.trees.redblack;

import java.util.ArrayList;
import java.util.List;



public class Node {

	Node parent;
	Node left;
	Node right;
	Integer data;
	int height = 0;
	int balance = 0;
	RedBlack colour = RedBlack.BLACK;	

	public Node () {
	}

	public Node (Integer data) {
		this.data = data; 
		this.height = 1; 
	}

	Node insert(Node node, int data) { 
		//  Perform the normal insertion
		if (node == null || node.data == null) 
			return (new Node(data)); 

		if (data < node.data) 
			node.left = insert(node.left, data); 
		else if (data > node.data) 
			node.right = insert(node.right, data); 
		else // Duplicate data not allowed 
			return node; 

		node.height = 1 + max(
				(node.left!=null) ? node.left.getHeight():0, 
						(node.right != null)?node.right.getHeight():0
				); 

		this.balance = node.getBalance(); 

		// If this node becomes unbalanced, then there 
		// are 4 cases Left Left Case 
		if (this.balance > 1 && data < node.left.data) 
			return this.rightRotate(node); 

		// Right Right Case 
		if (this.balance < -1 && data > node.right.data) 
			return this.leftRotate(node); 

		// Left Right Case 
		if (this.balance > 1 && data > node.left.data) { 
			node.left = this.leftRotate(node.left); 
			return this.rightRotate(node); 
		} 

		// Right Left Case 
		if (this.balance < -1 && data < node.right.data) { 
			node.right = this.rightRotate(node.right); 
			return this.leftRotate(node); 
		} 

		/* return the (unchanged) node pointer */
		return node; 
	} 

	public List<Node> getInOrder() {
		List<Node> retVal = new ArrayList<Node>();
		if( this.left!=null) retVal.addAll(this.left.getInOrder());
		retVal.add(this);
		if( this.right!=null) retVal.addAll(this.right.getInOrder());
		return retVal;
	}

	public int getBalance() { 
		if (data == null) 
			return 0; 
		int lh = (this.left != null) ? this.left.getHeight() : 0 ;
		int rh = (this.right != null) ? this.right.getHeight() : 0 ;
		return lh - rh; 
	} 

	public int getHeight() {
		if (this.data != null) {
			int lh = (this.left != null) ? this.left.getHeight() : 0 ;
			int rh = (this.right != null) ? this.right.getHeight() : 0 ;
			return 1 + this.max(lh, rh);
		}
		return 0;
	}

	private int max (int a, int b) {
		if ( a > b ) { 
			return a;
		} else {
			return b;
		}
	}

	Node rightRotate(Node T) { 
		Node T1 = T.left; 
		Node T2 = T1.right; 

		// Perform rotation 
		T1.right = T; 
		T.left = T2; 

		// Update heights 
		T.height = T.getHeight();
		T1.height = T1.getHeight();

		// Return new root 
		return T1; 
	} 

	Node leftRotate(Node T) { 
		Node T1  = T.right; 
		Node T2 = T1.left; 

		// Perform rotation 
		T1.left = T; 
		T.right = T2; 

		// Update heights 
		T.height = T.getHeight();
		T1.height = T1.getHeight();

		// Return new root 
		return T1; 
	} 

}
