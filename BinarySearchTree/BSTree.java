package BasicImplementations;

import java.util.ArrayList;
import java.util.Collection;

public interface BSTree<anyType> {

	BinaryNode root = null;
	int length = 0;
	ArrayList <Object> TraversalContainer = new  ArrayList<Object>();
	
	
	// inserting methods
	void insert(anyType value, BinaryNode root);				//done

	void insertAll(Collection<anyType> list, BinaryNode root);	//done

	// deleting methods
	void clear();												//done

	void delete(anyType element,BinaryNode root);				//done

	void deleteAll(Collection<anyType> list);					//done
	
	BinaryNode deleteMin (BinaryNode node);						//done
	
	BinaryNode deleteMax (BinaryNode node);						//done
			
	void retainAll(Collection<anyType> list);					//done

	// finding and searching methods
	
	BinaryNode getRoot();										//done
	
	BinaryNode find(anyType element);							//done

	BinaryNode findMin(BinaryNode rootNode);					//done

	BinaryNode findMax(BinaryNode rootNode);					//done

	boolean contains(anyType element);							//done

	boolean containsAll(Collection<anyType> list);				//done

	// properties methods 
	int length();											    //done

	boolean isEmpty();									        //done
	
	void balance();											    //Pending for Study
 
	// Traversal methods 
	BinaryNode clone();										    //done

	Object [] toArray();									    //done

	void printInOrderTree();									//done
	
	void printPreOrderTree();									//done

	void printPostOrderTree();									//done

}