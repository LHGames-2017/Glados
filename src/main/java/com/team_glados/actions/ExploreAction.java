package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.*;
import com.team_glados.map.Map;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;
import com.team_glados.math.graph.shortest_path.AStar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExploreAction extends AbstractAction {

	private Graph graph;
	private AStar<Point> aStar;
	private List<Node<Point>> shortestPath;

	@Override
	public int getWeight(GameInfo info) {
		if (ShopAction.storePosition == null && info.player.TotalResource >= 40000) {
			final boolean isShop = Arrays.stream(info.getComputedMap().getTiles())
					.anyMatch(tile -> tile.Content == TileContent.Shop);
			if (isShop)
				return 0;
			return 51;
		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return AiHelper.CreateMoveAction(new Point(info.player.Position.x, info.player.Position.y - 1));
	}
}
