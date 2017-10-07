package com.team_glados;

import com.jeremycurny.sparkjavarestapi.controller.RestController;
import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.Tile;
import com.team_glados.actions.*;
import com.team_glados.map.Map;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;
import com.team_glados.math.graph.shortest_path.AStar;
import spark.Request;
import spark.Response;

import java.net.URLDecoder;
import java.util.List;

public class UserController extends RestController {

	private Action[] actions;

	public UserController() {
		actions = new Action[]{
				new AttackAction(),
				new CollectAction(),
				new HealAction(),
				new MoveToCollectAction(),
				new MoveToHouseAction(),
				new MoveToOtherHouseAction(),
				new MoveToPlayerAction(),
				new MoveToShopAction(),
				new RunAwayFromPlayerAction(),
				new ShopAction(),
				new StealAction(),
				new SuicideAction(),
				new UpgradeAction()
		};
	}

	private Map map = new Map();
	private boolean firstRun = true;

	private Graph graph;
	private AStar<Point> aStar;
	private List<Node<Point>> shortestPath;

	@Override
	public Object bot(Request req, Response res) {
		System.out.println("Tock");

		String s = URLDecoder.decode(req.body()).substring(4);
		GameInfo gameInfo = new GameInfo();
		gameInfo.fromJson(s);

		System.out.println("Tock2");

		System.out.println("--------------------------------------------------------------------");
		// Output current map
		for (List<Tile> l1 : gameInfo.map) {
			for (Tile n : l1) {
				System.out.print(n + " ");
			}
			System.out.println();
		}

		//Update map
		for (int i = 0; i < gameInfo.map.size(); i++) {
			for (int j = 0; j < gameInfo.map.get(i).size(); j++) {
				map.addTile(gameInfo.map.get(i).get(j));
			}
		}

		// Get best action
//		int[] weights = new int[actions.length];
//		int highestIndex = 0;
//		int highestValue = 1000;
//		for (int i = 0; i < actions.length; i++) {
//			weights[i] = actions[i].getWeight(gameInfo);
//			if (weights[i] > highestValue) {
//				highestIndex = i;
//				highestValue = weights[i];
//			}
//		}

//		System.out.print(actions[highestIndex].getClass().getName());
//		String action = actions[highestIndex].doIt(gameInfo);

		if (firstRun) {
			graph = map.toGraph();
			aStar = new AStar<>(graph);

			final Node start = graph.getNode(gameInfo.player.Position);
			final Node end = graph.getNode(new Point(15, 25));
			shortestPath = aStar.findShortestPath(start, end);

			shortestPath.remove(0);
			firstRun = false;
		}

		System.out.println(gameInfo.player.Position);
		shortestPath.forEach(System.out::println);



		String action = AiHelper.CreateMoveAction(shortestPath.remove(0).getId());
		return super.resJson(req, res, 200, action);
	}
}