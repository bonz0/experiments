package main;

import trie.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie myTrie = new Trie();
		myTrie.addNode(myTrie.root, "farhan");
		myTrie.addNode(myTrie.root, "farther");
		myTrie.addNode(myTrie.root, "fart");
		myTrie.addNode(myTrie.root, "father");
		myTrie.addNode(myTrie.root, "farhall");
		myTrie.addNode(myTrie.root, "miss");
		myTrie.addNode(myTrie.root, "misses");
		myTrie.addNode(myTrie.root, "mishap");
		myTrie.addNode(myTrie.root, "misfit");
		myTrie.addNode(myTrie.root, "missed");
		myTrie.addNode(myTrie.root, "misuse");
		for(String temp : myTrie.getLastMatchingNode("mishal")) {
			System.out.println(temp);
		}
	}

}
