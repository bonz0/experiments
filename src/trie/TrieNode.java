package trie;

import java.util.ArrayList;

public class TrieNode {
	public String data;
	public boolean isLeaf;
	private ArrayList<TrieNode> children;

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

	protected void setChild(TrieNode childNode) {
		this.children.add(childNode);
	}

	protected ArrayList<TrieNode> getChildren() {
		return this.children;
	}

	/*
	 * Returns the index of the child node whose
	 * data matches the character passed to it
	 */
	protected int indexInChildren(char character) {
		TrieNode[] children = this.children.toArray(new TrieNode[this.children.size()]);
		for(int iii = 0; iii < children.length; iii++) {
			if(children[iii].data.equals(Character.toString(character))) {
				return iii;
			}
		}
		return -1;
	}

	protected boolean hasChildren() {
		return !(this.children.isEmpty());
	}

	protected int getNumberOfChildren() {
		return this.children.size();
	}

	protected TrieNode getChild(int index) {
		return this.children.get(index);
	}
}
