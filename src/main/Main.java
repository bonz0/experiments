package main;

import trie.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie myTrie = new Trie();
		myTrie.addNode(myTrie.root, "farhang");
		myTrie.addNode(myTrie.root, "farhan");
		myTrie.addNode(myTrie.root, "farther");
		myTrie.addNode(myTrie.root, "fart");
		myTrie.addNode(myTrie.root, "father");
		myTrie.addNode(myTrie.root, "farhall");
//		myTrie.printTrie(myTrie.root);
		for(String temp : myTrie.getLastMatchingNode("farh")) {
			System.out.println(temp);
		}
	}

}
