package BasicImplementations;

public class AVLMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AVLTree <Integer> trial = new AVLTree<Integer>();
		
		trial.insert(3);
		trial.insert(2);
		trial.insert(1);

		trial.insert(4);

		trial.insert(5);
		trial.insert(6);
		trial.insert(7);
		trial.printPreOrderTree();
		trial.insert(16);
		trial.printPreOrderTree();

		trial.insert(15);
		trial.printPreOrderTree();

		trial.insert(14);
		trial.printPreOrderTree();

		trial.insert(13);
		trial.printPreOrderTree();

		trial.insert(12);
		trial.printPreOrderTree();

		trial.insert(11);
		trial.insert(10);
		trial.insert(8);
		trial.insert(9);
		
		trial.printPreOrderTree();
		
		trial.delete(12, trial.getRoot());
		
		trial.printPreOrderTree();
		
		//trial.delete(12, trial.getRoot());
		
		//trial.printPreOrderTree();
		
		


	//	trial.insert(6, trial.getRoot());
		//trial.insert(9, trial.getRoot());
		
		
	}

}
