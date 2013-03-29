package trie;

import java.util.ArrayList;

public class TrieNode {
	private String data;
	private ArrayList<TrieNode> children;
	public boolean isLeaf;

	public TrieNode() {
		this.data = "";
		this.children = new ArrayList<TrieNode>();
		this.isLeaf = false;
	}

	public TrieNode(String data) {
		this.data = data.toLowerCase();
		this.children = new ArrayList<TrieNode>();
		this.isLeaf = false;
	}

	public void setData(String data) {
		this.data = data.toLowerCase();
	}

	public String getData() {
		return this.data;
	}

	public void setChild(TrieNode childNode) {
		this.children.add(childNode);
	}

	public ArrayList<TrieNode> getChildren() {
		return this.children;
	}

	/*
	 * Returns the index of the child node whose
	 * data matches the character passed to it
	 */
	public int indexInChildren(char character) {
		TrieNode[] children = this.children.toArray(new TrieNode[this.children.size()]);
		for(int iii = 0; iii < children.length; iii++) {
			if(children[iii].data.equals(Character.toString(character))) {
				return iii;
			}
		}
		return -1;
	}

	public boolean hasChildren() {
		return !(this.children.isEmpty());
	}

	public int getNumberOfChildren() {
		return this.children.size();
	}

	public TrieNode getChild(int index) {
		return this.children.get(index);
	}
}
