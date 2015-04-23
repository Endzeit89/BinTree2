package com.hsma.ss15.tpe.grp1_2.btree2;

public class BNode {
	int ordnung;
	BNode parent;
	Integer[] keys;
	BNode[] children;
	int height;

	BNode(int ordnung) {
		this.ordnung = ordnung;
		keys = new Integer[(ordnung * 2) + 1];
		children = new BNode[(ordnung * 2) + 2];
	}

	public boolean insert(Integer o) {
		if (height == 0) {
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] == null) {
					keys[i] = o;
					o = null;
				}
				keys = sort(keys);
			}
			keys = sort(keys);
		} else if (height == 1) {
			int max = 0;
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != null)
					max = keys[i];
				if (o < max) {
					children[i].insert(o);
					break;
				}

			}
			if (o > max)
				children[getPositionOfKey(max) + 1].insert(o);
		} else {
			int max = 0;
			int deepmax = 0;
			for (int i = 0; i < keys.length; i++) {
				if (keys[i] != null)
					max = keys[i];
				if (o < max) {
					for (int j = 0; j < (children[getPositionOfKey(max)].keys.length); j++) {
						if (children[getPositionOfKey(max)].keys[j] != null)
							deepmax = children[getPositionOfKey(max)].keys[j];
						if (o < deepmax) {
							children[getPositionOfKey(max)].children[j]
									.insert(o);
							break;
						}
					}
					break;
				}

			}
			if (o > max)
				for (int j = 0; j < (children[getPositionOfKey(max) + 1].keys.length); j++) {
					if (children[getPositionOfKey(max) + 1].keys[j] != null)
						deepmax = children[getPositionOfKey(max) + 1].keys[j];
					if (o < deepmax) {
						children[getPositionOfKey(max) + 1].children[j]
								.insert(o);
						break;
					}
				}
		}

		return false;
	}

	private Integer[] sort(Integer[] keys) {
		int x;
		BNode y, z;
		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < keys.length; j++) {
				if (keys[j] != null && keys[i] != null
						&& (keys[i].compareTo(keys[j])) == -1) {
					x = keys[i];
					y = children[i + 1];
					// if (y != null)
					// System.out.println(j+""+y.toString());
					keys[i] = keys[j];
					children[i + 1] = children[j + 1];
					keys[j] = x;
					children[j + 1] = y;

				}
			}
		}
		return keys;
	}

	public boolean needSplit() {
		boolean full = true;
		for (int i = 0; i < keys.length; i++)
			if (keys[i] == null)
				full = false;
		return full;
	}

	public BNode rootSplit() {
		BNode newRoot = new BNode(ordnung);
		BNode newChildLeft = new BNode(ordnung);
		BNode newChildRight = new BNode(ordnung);
		newChildLeft.setParent(newRoot);
		newChildLeft.children[0] = children[0];
		newChildLeft.children[1] = children[2];
		newChildLeft.children[2] = children[1];
		newChildRight.children[0] = children[3];
		newChildRight.children[1] = children[4];
		newChildRight.children[2] = children[5];
		newChildRight.setParent(newRoot);

		BNode[] rootChildren = new BNode[(ordnung * 2) + 2];
		newRoot.insert(keys[ordnung]);
		for (int i = ordnung - 1; i >= 0; i--)
			newChildLeft.insert(keys[i]);
		for (int j = ordnung + 1; j < keys.length; j++)
			newChildRight.insert(keys[j]);
		rootChildren[(newRoot.getPositionOfKey(keys[ordnung]))] = newChildLeft;
		rootChildren[(newRoot.getPositionOfKey(keys[ordnung])) + 1] = newChildRight;
		newRoot.setChildren(rootChildren);
		newRoot.setHeight(this.height + 1);

		return newRoot;

	}

	public void childSplit() {
		BNode newChildLeft = new BNode(ordnung);
		BNode newChildRight = new BNode(ordnung);
		newChildLeft.setParent(this.parent);
		newChildRight.setParent(this.parent);
		Integer keyMid = keys[ordnung]; // key[ordnung]= der key, der nach oben
//					System.out.println(keyMid);	
//					System.out.println(parent.toString());// geschoben wird
		if (keyMid != null) {
			parent.insertInParent(keyMid);
		}
		for (int i = ordnung - 1; i >= 0; i--)
			newChildLeft.insert(keys[i]);
		for (int j = ordnung + 1; j < keys.length; j++)
			newChildRight.insert(keys[j]);
		parent.children[(parent.getPositionOfKey(keyMid))] = newChildLeft;
		parent.children[(parent.getPositionOfKey(keyMid) + 1)] = newChildRight;

	}

	private void insertInParent(Integer o) {

		for (int i = 0; i < keys.length; i++) {
			if (keys[i] == null) {
				keys[i] = o;
				o = null;
			}

		}
		keys = sort(keys);
	}

	private int getPositionOfKey(Integer key) {
		int pos = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] == key) {
				pos = i;
			}
		}
		return pos;
	}

	public Integer[] getKeys() {
		return keys;
	}

	public void setKeys(Integer[] keys) {
		this.keys = keys;
	}

	public BNode[] getChildren() {
		return children;
	}

	public void setChildren(BNode[] children) {
		this.children = children;
	}

	@Override
	public String toString() {
		String s = "{[";

		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				s += keys[i] + ",";
		}
		s = s.substring(0, (s.length() - 1));
		s += "]} \t";
		return s;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BNode getParent() {
		return parent;
	}

	public void setParent(BNode parent) {
		this.parent = parent;
	}
}
