package com.hsma.ss15.tpe.grp1_2.btree2;

public interface BTreeInterface {

	boolean insert(Integer o); // fugt o in den B-Baum ein

	boolean insert(String filename); // - fugt die Elemente, die in der Datei //
										// stehen in den Baum ein. ¨

	boolean contains(Integer o); // - testet, ob o im Baum vorhanden ist.

	int size(); // - ermittelt die Anzahl der Elemente im Baum.

	int height(); // - ermittelt die Höhe des Baums.

	Integer getMax(); // - liefert das größte Element im Baum.

	Integer getMin(); // - liefert das kleinste Element im Baum.

	boolean isEmpty(); // - ist true genau dann, wenn der Baum leer ist.

	void addAll(BTree otherTree); // fugt alle Elemente des ¨ ubergebenen
									// B-Baums ( ¨ otherTree) in den
									// aktuellen Baum ein.

	void printInorder();// - Ausgabe des Baums in Inorder.

	void printPostorder();// - Ausgabe des Baums in Postorder.

	void printPreorder();// - Ausgabe des Baums in Preorder.

	void printLevelorder();// - Ausgabe des Baums in Levelorder.
}
