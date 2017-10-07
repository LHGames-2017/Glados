package com.team_glados.math.graph;

/**
 * Modélisation d'un sommet de graphe.
 *
 * @author Philippe Rivest
 */
public class Node<T> {

	private T id;

	public Node(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public double heuristicToNode(Node n){
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Node))
			return false;

		Node node = (Node) o;

		return getId() == node.getId();
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return String.format("Node{%s}", id.toString());
	}
}
