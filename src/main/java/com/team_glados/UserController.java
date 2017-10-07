package com.team_glados;

import com.jeremycurny.sparkjavarestapi.controller.RestController;
import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.Tile;
import com.team_glados.actions.*;
import com.team_glados.map.Map;
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

	@Override
	public Object bot(Request req, Response res) {

		String s = URLDecoder.decode(req.body()).substring(4);
		GameInfo gameInfo = new GameInfo();
		gameInfo.fromJson(s);

		System.out.println("\n--------------------------------------------------------------------\n");
		// Output current map
		for (List<Tile> l1 : gameInfo.map) {
			for (Tile n : l1) {
				System.out.print(n + " ");
			}
			System.out.println();
		}

		Map map = new Map();

		//Update map
		for (int i = 0; i < gameInfo.map.size(); i++) {
			for (int j = 0; j < gameInfo.map.get(i).size(); j++) {
				map.addTile(gameInfo.map.get(i).get(j));
			}
		}

		gameInfo.setComputedMap(map);
		System.out.println(gameInfo.player.CarriedResources);
		// Get best action
		int[] weights = new int[actions.length];
		int highestIndex = 0;
		int highestValue = 0;
		for (int i = 0; i < actions.length; i++) {
			weights[i] = actions[i].getWeight(gameInfo);
			if (weights[i] > highestValue) {
				highestIndex = i;
				highestValue = weights[i];
			}
		}

		System.out.println(actions[highestIndex].getClass().getName());

		String action;
		if (highestValue == 0) {
			action = AiHelper.CreateMoveAction(new Point(gameInfo.player.Position.x, gameInfo.player.Position.y + 1));
		} else {
			action = actions[highestIndex].doIt(gameInfo);
		}

		return super.resJson(req, res, 200, action);
	}
}