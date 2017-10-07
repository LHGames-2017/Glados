package com.team_glados;

import com.jeremycurny.sparkjavarestapi.controller.RestController;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.team_glados.actions.*;
import com.team_glados.map.Map;
import spark.Request;
import spark.Response;

import java.net.URLDecoder;

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

	@Override
	public Object bot(Request req, Response res) {
		String s = URLDecoder.decode(req.body()).substring(4);
		GameInfo gameInfo = new GameInfo();
		gameInfo.fromJson(s);

		//Update map
        for (int i = 0; i < gameInfo.map.size(); i++) {
            for (int j = 0; j < gameInfo.map.get(i).size(); j++) {
                map.addTile(gameInfo.map.get(i).get(j));
            }
        }

        //Get best action
		int[] weights = new int[actions.length];
		int highestIndex = 0;
		int highestValue = 1000;
		for (int i = 0; i < actions.length; i++) {
			weights[i] = actions[i].getWeight(gameInfo);
			if (weights[i] > highestValue) {
				highestIndex = i;
				highestValue = weights[i];
			}
		}

		//Send action command
        System.out.print(actions[highestIndex].getClass().getName());
        String action = actions[highestIndex].doIt(gameInfo);
        return super.resJson(req, res, 200, action);
    }
}
