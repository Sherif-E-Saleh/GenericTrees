package CrackingTheCode;

import java.util.LinkedList;

import BasicImplementations.BinaryTree;

public class Prob_4_4_LSDepths{

	/*
	Given a binary tree, design an algorithm which creates a linked list of all the
	nodes at each depth (e.g., if you have a tree with depth D, you'll have D linked
	lists).
	*/

	@SuppressWarnings("rawtypes")
	public static LinkedList <LinkedList> getLSAtEachDepth (BinaryTree tree){
		Object [] BreadthFirstTraversalResult = tree.BreadthFirstTraversal(); 
		LinkedList <LinkedList> lists = new LinkedList <LinkedList>();
		LinkedList <Object> temp;
		for (int i = 0; i < tree.depth(); i++) {
			temp = new LinkedList <Object>();
			for (int j = (int) (Math.pow(2, i)-1); j < (int) (2*(Math.pow(2, i))-1); j++) {
				temp.add(BreadthFirstTraversalResult[j]);
			}
			lists.add(temp);
		}
		return lists;
	}
	
	public static void main(String[] args) {
		BinaryTree <Integer> trial = new BinaryTree<Integer>();
				
				trial.insert(1, trial.getRoot());
				trial.insert(2, trial.getRoot());
				trial.insert(3, trial.getRoot());
				trial.insert(4, trial.getRoot());
				trial.insert(5, trial.getRoot());
				trial.insert(6, trial.getRoot());
				trial.insert(7, trial.getRoot());
				trial.insert(8, trial.getRoot());
				trial.insert(9, trial.getRoot());
				trial.insert(10, trial.getRoot());
				trial.insert(11, trial.getRoot());
				trial.insert(12, trial.getRoot());
				trial.insert(13, trial.getRoot());
				trial.insert(14, trial.getRoot());
				trial.insert(15, trial.getRoot());
				
				
				@SuppressWarnings("rawtypes")
				LinkedList<LinkedList> list = getLSAtEachDepth(trial);
				
				Object [] array = list.toArray();
				
				for (int i = 0; i < array.length; i++) {
					System.out.println(array[i].toString());
				}
					
				}
	}

