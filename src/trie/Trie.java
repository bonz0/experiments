package trie;

import java.lang.Character;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Trie {
	public TrieNode root;

	public Trie() {
		this.root = new TrieNode(" ");
	}

	/*
	 * Adds a node to the trie
	 * @param	TrieNode	: root of trie for recursive calls
	 * @param	data		: string to be stored
	 */
	public void addNode(TrieNode node, String data) {
		addNodeAux(node, data, data);
	}

	/*
	 * Auxiliary function to add data
	 * @param	TrieNode	: root of trie for recursive calls
	 * @param	data		: string to be stored
	 * @param	subString	: subString at particular recursive call
	 */
	private void addNodeAux(TrieNode node, String data, String subString) {
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

	/*
	 * Prints trie. For debugging purposes
	 */
	private void printTrie(TrieNode root) {
		if(root == null) {
			return;
		}
		if(this.root == root) {
			System.out.println("/");
		} else {
			System.out.println(root.data);
		}
		if(root.hasChildren()) {
			for(int iii = 0; iii < root.getChildren().size(); iii++) {
				printTrie(root.getChildren().get(iii));
			}
		}
	}

	/*
	 * @param 	data		: string to be searched
	 * @return	the last node in trie that matches data
	 */
	private TrieNode getLastDescendent(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		data = " ".concat(data);
		TrieNode node = this.root;
		for (int iii = 1; iii < data.length(); iii++) {
			int index = node.indexInChildren(data.charAt(iii));
			if (index != -1) {
				node = node.getChild(index);
			} else {
				return null;
			}
		}
		return node;
	}

	/*
	 * @param	data		: string to be searched
	 * @return array of matches
	 */
	public String[] getLastMatchingNode(String data) {
		// get last node that matches data
		TrieNode lastNode = this.getLastDescendent(data);
		if(lastNode == null)
			return new String[0];
		ArrayList<String> results = new ArrayList<String>();
		/*
		 * Visit all children of last node using BFS
		 * keep track of data in leaf nodes to be returned
		 */
		Queue<TrieNode> myQueue = new LinkedList<TrieNode>();
		myQueue.add(lastNode);
		while(!myQueue.isEmpty()) {
			TrieNode currentNode = myQueue.poll();
			if(currentNode.isLeaf) {
				results.add(currentNode.data);
			}
			ArrayList<TrieNode> children = currentNode.getChildren();
			myQueue.addAll(children);
		}
		return results.toArray(new String[results.size()]);
	}
}