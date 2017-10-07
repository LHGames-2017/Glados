package com.team_glados.math.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Modélisation d'une multigraphe pondéré et orienté
 *
 * @author Philippe Rivest
 */
public class Graph {

	private HashMap<Object, Node> nodes = new HashMap<>();
	private Set<Edge> edges = new HashSet<>();


	public Graph() {}

	public Graph(Node[] nodes, Edge[] edges) {
		Arrays.stream(nodes).forEach(node -> this.nodes.put(node.getId(), node));
		this.edges.addAll(Arrays.asList(edges));
	}

	public Graph(Graph g){
		this(Arrays.copyOf(g.getNodes(),g.getNodes().length), Arrays.copyOf(g.getEdges(),g.getEdges().length));
	}

	public Node[] getNodes() {
		return nodes.values().toArray(new Node[0]);
	}

	public Edge[] getEdges() {
		return edges.toArray(new Edge[0]);
	}

	public Edge getEdgeBetweenNodes(Node src, Node desc){

		for (Edge edge : edges)
			if (edge.getSource().equals(src) && edge.getDest().equals(desc))
				return edge;

		return null;
	}

	public boolean containsNode(Node n){
		return nodes.get(n.getId()) != null;
	}

	public boolean containsEdge(Edge e){
		return edges.contains(e);
	}

	public boolean addNode(Node n) {
		return nodes.put(n.getId(), n) != n;
	}

	public Node getNode(int id) {
		return nodes.get(id);
	}

	public boolean addEdge(Edge e){
		return edges.add(e);
	}

	public boolean removeEdge(Edge e){
		return edges.remove(e);
	}

	@Override
	public String toString() {

		StringBuilder sBuilder = new StringBuilder("sommets : ");

		for (Node node : this.getNodes()) {
			sBuilder.append(node).append(" ");
		}

		sBuilder.append("\nArêtes :\n");

		for (Edge edge : this.getEdges()) {
			sBuilder.append(edge).append("\n");
		}

		return sBuilder.toString();
	}
}
