package com.team_glados;

import com.jeremycurny.sparkjavarestapi.controller.RestController;
import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.Tile;
import com.team_glados.actions.*;
import spark.Request;
import spark.Response;

import java.net.URLDecoder;
import java.util.Arrays;
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

		// Output current map
		for (List<Tile> l1 : gameInfo.map) {
			for (Tile n : l1) {
				System.out.print(n + " ");
			}
			System.out.println();
		}

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

		String action = AiHelper.CreateMoveAction(new Point(gameInfo.player.Position.x, gameInfo.player.Position.y + 1));
		return super.resJson(req, res, 200, action);
	}
}
