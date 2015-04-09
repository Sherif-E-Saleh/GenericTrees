package CrackingTheCode;

import java.util.LinkedList;
import java.util.Stack;

import BasicImplementations.BinaryNode;
import BasicImplementations.BinaryTree;

public class pathsSumToValue {
	/*
	You are given a binary tree in which each node contains a value. Design an algorithm
	to print all paths which sum to a given value. The path does not need to
	start or end at the root or a leaf.
	*/

	@SuppressWarnings({ "unchecked", "hiding", "rawtypes" })
	public static <String> LinkedList pathsSumingTo(BinaryTree<Integer> home,
			int value) {
		if (home.isEmpty())
			return null;
		else {
			Object[] valuesContainer = home.preOrder();
			Stack<BinaryNode> nodesContainer = new Stack<BinaryNode>();
			LinkedList<String> toBeReturned = new LinkedList<String>();
			int sum = 0;
			boolean adding = true;
			for (int i = 0; i < valuesContainer.length; i++) {
				if (valuesContainer[i].equals((Object) value))
					toBeReturned.add((String) valuesContainer[i]);
				else {
					int x = i + 1;
					nodesContainer.push(home.find(valuesContainer[i],
							home.getRoot()));
					sum = sum + (int) valuesContainer[i];
					while (x < valuesContainer.length) {
						if (nodesContainer.isEmpty())
							break;
						if (sum == (int) value && adding) {
							String build = (String) ("{" + nodesContainer
									.get(0).getValue().toString());
							for (int j = 1; j < nodesContainer.size(); j++) {
								build = (String) (build + ", " + nodesContainer
										.get(j).getValue().toString());
							}
							build = (String) (build + "}");
							toBeReturned.add(build);
						}
						if (home.hasNodes(nodesContainer.peek())) {
							if ((nodesContainer.peek().getLeft() != null)
									&& (nodesContainer.peek().getLeft()
											.getValue()
											.equals(valuesContainer[x]))) {
								sum = sum + (int) valuesContainer[x];
								nodesContainer.push(nodesContainer.peek()
										.getLeft());
								x++;
								adding = true;
							} else if ((nodesContainer.peek().getRight() != null)
									&& (nodesContainer.peek().getRight()
											.getValue()
											.equals(valuesContainer[x]))) {
								sum = sum + (int) valuesContainer[x];
								nodesContainer.push(nodesContainer.peek()
										.getRight());
								x++;
								adding = true;
							} else {
								sum = sum
										- (int) (nodesContainer.peek()
												.getValue());
								nodesContainer.pop();
								adding = false;
							}
						} else {
							sum = sum
									- (int) (nodesContainer.peek().getValue());
							BinaryNode justDeleted = nodesContainer.pop();
							if (!nodesContainer.isEmpty()
									&& ((nodesContainer.peek().getRight() == justDeleted) || (nodesContainer
											.peek().getLeft() == justDeleted && nodesContainer
											.peek().getRight() == null))) {
								sum = sum
										- (int) (nodesContainer.peek()
												.getValue());
								nodesContainer.pop();
							}
							adding = false;
						}
					}
					nodesContainer.clear();
					sum = 0;
				}
			}
			return toBeReturned;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree<Integer> trial = new BinaryTree<Integer>();

		trial.insert(1, trial.getRoot());
		trial.insert(2, trial.getRoot());
		trial.insert(5, trial.getRoot());
		trial.insert(4, trial.getRoot());
		trial.insert(5, trial.getRoot());
		trial.insert(1, trial.getRoot());
		trial.insert(7, trial.getRoot());
		trial.insert(8, trial.getRoot());
		trial.insert(9, trial.getRoot());

		@SuppressWarnings("unchecked")
		LinkedList<String> list = pathsSumingTo(trial, 7);
		Object[] temp = list.toArray();

		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}

	}

}
