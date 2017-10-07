package com.team_glados.math.graph;

/**
 * Modélisation d'une arête de graphe.
 *
 * @author Philippe Rivest
 */
public class Edge {

	private Node source;
	private Node dest;

	private double weight;

	public Edge(Node source, Node dest, double weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	public Edge(Node source, Node dest) {
		this(source,dest, 0);
	}

	public Node getSource() {
		return source;
	}

	public Node getDest() {
		return dest;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Edge))
			return false;

		Edge edge = (Edge) o;

		return getDest() == edge.getDest() && getSource() == edge.getSource();
	}

	@Override
	public int hashCode() {
		int result = getSource().hashCode();
		result = 31 * result + getDest().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return String.format("Edge : %s --(%s)--> %s", source, weight, dest);
	}
}
