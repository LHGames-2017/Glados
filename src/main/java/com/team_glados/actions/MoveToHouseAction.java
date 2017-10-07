package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.team_glados.map.Map;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;
import com.team_glados.math.graph.shortest_path.AStar;

import java.util.List;

public class MoveToHouseAction extends AbstractAction {

	private Graph graph;
	private AStar<Point> aStar;
	private List<Node<Point>> shortestPath;

	@Override
	public int getWeight(GameInfo info) {
		if (info.player.CarriedResources == info.player.CarryingCapacity)
			return 100; // RUN FOR YOUR RESOURCES!!!
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		final Map computedMap = info.getComputedMap();
		graph = computedMap.toGraph();
		aStar = new AStar<>(graph);

		final Node start = graph.getNode(info.player.Position);
		final Node end = graph.getNode(info.player.HouseLocation);

		shortestPath = aStar.findShortestPath(start, end);

		shortestPath.forEach(System.out::println);

		return AiHelper.CreateMoveAction(shortestPath.get(1).getId());
	}
}
