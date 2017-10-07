package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.*;
import com.team_glados.map.Map;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;
import com.team_glados.math.graph.shortest_path.AStar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoveToShopAction extends AbstractAction {
	
	private Graph graph;
	private AStar<Point> aStar;
	private List<Node<Point>> shortestPath;

    @Override
    public int getWeight(GameInfo info)
    {
    	if (info.player.CarriedResources == 0 && info.player.TotalResource >= 40000 && UpgradeAction.toUpgrade.peek().getValue() == UpgradeType.Item)
    		return 61;
        return 0;
	}

	@Override
	public String doIt(GameInfo info) {

		final Map computedMap = info.getComputedMap();
		graph = computedMap.toGraph();
		aStar = new AStar<>(graph);

		final Tile closestShop = Arrays.stream(info.getComputedMap().getTiles())
				.filter(tile -> tile.Content == TileContent.Shop)
				.min(Comparator.comparingDouble(tile -> Math.sqrt(Math.pow(tile.X - info.player.Position.x, 2) + Math.pow(tile.Y - info.player.Position.x, 2))))
				.orElse(new Tile(0, info.player.Position.x, info.player.Position.y));

		final Node start = graph.getNode(info.player.Position);
		final Node end = graph.getNode(new Point(closestShop.X,closestShop.Y));

		shortestPath = aStar.findShortestPath(start, end);

		return AiHelper.CreateMoveAction(shortestPath.get(1).getId());
	}
}
