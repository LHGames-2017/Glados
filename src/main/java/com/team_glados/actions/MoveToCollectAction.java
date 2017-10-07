package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.*;
import com.team_glados.map.Map;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;
import com.team_glados.math.graph.shortest_path.AStar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoveToCollectAction extends AbstractAction {


	private Graph graph;
	private AStar<Point> aStar;
	private List<Node<Point>> shortestPath;

	@Override
	public int getWeight(GameInfo info) {
		if (info.player.CarriedResources < info.player.CarryingCapacity)
			return 50;
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {

		final Map computedMap = info.getComputedMap();
		graph = computedMap.toGraph();
		aStar = new AStar<>(graph);

		final Tile closestTile = Arrays.stream(computedMap.getTiles())
				.filter(tile -> tile.Content == TileContent.Resource)
				.min(Comparator.comparingDouble(tile -> Math.sqrt(Math.pow(tile.X - info.player.Position.x, 2) + Math.pow(tile.Y - info.player.Position.x, 2))))
				.orElse(new Tile(0, info.player.Position.x, info.player.Position.y));

		System.out.println("To mine:" + new Point(closestTile.X, closestTile.Y));

		final Node start = graph.getNode(info.player.Position);
		final Node end = graph.getNode(new Point(closestTile.X,closestTile.Y));

		shortestPath = aStar.findShortestPath(start, end);

		System.out.println(info.player.Position);
		shortestPath.forEach(System.out::println);

		return AiHelper.CreateMoveAction(shortestPath.get(1).getId());
	}
}
