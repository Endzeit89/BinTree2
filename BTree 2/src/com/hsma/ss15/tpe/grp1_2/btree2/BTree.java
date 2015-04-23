package com.hsma.ss15.tpe.grp1_2.btree2;

public class BTree implements BTreeInterface {

	private int height;

	BNode root;
	int ordnung;

	/**
	 * Konstruktor
	 * 
	 * @param ordnung
	 */
	public BTree(int ordnung) {
		this.ordnung = ordnung;
		root = new BNode(ordnung);

	}

	/**
	 * class for Node
	 */

	@Override
	public boolean insert(Integer o) {

		// System.out.println(o+ ": Wird eingefügt");
		root.insert(o);
		if (root.needSplit()) {
			root = root.rootSplit();
			height++;
		}

		if (height > 0)
			for (int i = 0; i < (root.children.length); i++) {
				if (root.children[i] != null && root.children[i].needSplit())
					root.children[i].childSplit();
				if (root.children[i] != null)
					for (int j = 0; j < (root.children[i].children.length); j++) {
						if (root.children[i].children[j] != null
								&& root.children[i].children[j].needSplit()) {
							//System.out.println("x");
							root.children[i].children[j].childSplit();
						}
					}

			}
		if (root.needSplit()) {
			root = root.rootSplit();
			height++;
		}
		
		// printInorder();
		return true;

	}

	@Override
	public boolean insert(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Integer o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addAll(BTree otherTree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printInorder() {

	}

	public void print() {
		String s = "";
		s += root.toString() + "\n";
		if (root.children != null)
			for (int i = 0; i < root.children.length; i++) {
				if (root.children[i] != null)
					s += i + root.children[i].toString();

			}
		s += "\n";
		if (root.children != null)
			for (int i = 0; i < root.children.length; i++) {
				if (root.children[i] != null) {
					for (int j = 0; j < root.children[i].children.length; j++) {
						if (root.children[i].children[j] != null)
							s += j + root.children[i].children[j].toString();
					}
				}
			}
		System.out.println(s);
	}

	@Override
	public void printPostorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPreorder() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printLevelorder() {
		// TODO Auto-generated method stub

	}

}
