package trie;

import java.lang.Character;

public class Trie {
	private TrieNode root;

	public Trie() {
		this.root = new TrieNode(" ");
	}

	public void setRoot(TrieNode root) {
		this.root = root;
	}

	public TrieNode getRoot() {
		return this.root;
	}

	/*
	 * Adds a node to the trie
	 */
	public void addNode(TrieNode node, String data) {
		addNodeAux(node, data, data);
	}

	public void addNodeAux(TrieNode node, String data, String subString) {
		/*
		 * if single character is passed as subString, then create a
		 * node with the whole word as its child and return since 
		 * this is the last call to addNodeAux
		 */
		if(subString.length() == 1) {
			TrieNode newNode = new TrieNode(data);
			newNode.isLeaf = true;
			node.setChild(newNode);
			return;
		}
		/*
		 * If the function is called with the root node,
		 * then append the subString passed to it to the
		 * character that is stored in the root (" ")
		 */
		if(node == this.root) {
			subString = " ".concat(data);
		}
		/*
		 * Find out the index of the child node that
		 * contains the second character of subString
		 */
		int index = node.indexInChildren(subString.charAt(1));
		/*
		 * If a child node with that character exists,
		 * then make a recursive call to addNodeAux on that node
		 * with the substring that doesn't contain the first
		 * character
		 */
		if(index >= 0) {
			addNodeAux(node.getChild(index), data, subString.substring(1));
		}
		/*
		 * If a child node does not exist, then create a child
		 * node with that character and call addNodeAux
		 * recursively on that node with the substring that doesn't
		 * contain the first character
		 */
		else {
			TrieNode newNode = new TrieNode(Character.toString(subString.charAt(1)));
			node.setChild(newNode);
			addNodeAux(newNode, data, subString.substring(1));
		}
	}

	public void printTrie(TrieNode root) {
		if(root == null) {
			return;
		}
		if(this.root == root) {
			System.out.println("/");
		} else {
			System.out.print(root.getData());
		}
		if(root.hasChildren()) {
			for(int iii = 0; iii < root.getChildren().size(); iii++) {
				printTrie(root.getChildren().get(iii));
			}
		}
	}

	public TrieNode getLastMatchingNode(String data) {
		data = " ".concat(data);
		TrieNode iterator = this.root;
		for(int iii = 0; (iii < (data.length() - 1) && iterator.hasChildren()); iii++) {
			if(iterator.getData().equals(Character.toString(data.charAt(iii)))) {
				int index = iterator.indexInChildren(data.charAt(iii + 1));
				if(index >= 0) {
					iterator = iterator.getChild(index);
				} else {
					return iterator;
				}
			}
		}
		return null;
	}
}