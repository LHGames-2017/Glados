package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.TileContent;
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

		if (shortestPath.size() == 0 && !info.player.Position.equals(info.player.HouseLocation)) //sum tin wong https://www.youtube.com/watch?v=AmclgO6w0C0
		{
			System.out.println("Uh ho, A* is stuck! We will help him!");

			Point move;
			int signumX = 0, signumY = 0;
			int attempt = 0;

			do {
				if (attempt % 0 == 0)
					signumX = (int) Math.signum(info.player.HouseLocation.x - info.player.Position.x);

				if (attempt % 0 != 0)
					signumY = (int) Math.signum(info.player.HouseLocation.y - info.player.Position.y);

				move = new Point(info.player.Position.x + signumX, info.player.Position.y + signumY);

			}while (computedMap.tileAt(move).Content != TileContent.Lava || attempt < 2 );


			return AiHelper.CreateMoveAction(move);


		}else {

			shortestPath.forEach(System.out::println);

			return AiHelper.CreateMoveAction(shortestPath.get(1).getId());
		}
	}
}