package BasicImplementations;

import java.util.ArrayList;
import java.util.Collection;

public interface AVLTreeInterface<anyType> {

	AVLNode root = null;
	int size = 0;
	AVLNode imbalancedNode = null;
	ArrayList<Object> TraversalContainer = new ArrayList<Object>();

	AVLNode delete(anyType element);

	void singlyRotate(AVLNode imbalNode);

	void doubleRotate(AVLNode imbalNode);

	boolean isEmpty();

	int size();

	AVLNode getRoot();

	void insert(anyType element);

	void printInOrderTree(); // done

	void printPreOrderTree(); // done

	void printPostOrderTree(); // done

	AVLNode deleteMin(AVLNode node);

	AVLNode deleteMax(AVLNode node);

	void deleteAll(Collection<anyType> list);

}
