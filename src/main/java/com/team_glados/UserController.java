package com.team_glados;

import com.jeremycurny.sparkjavarestapi.controller.RestController;
import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import spark.Request;
import spark.Response;

import java.net.URLDecoder;

public class UserController extends RestController {

	@Override
	public Object bot(Request req, Response res) {
		String s = URLDecoder.decode(req.body()).substring(4);
		GameInfo gameInfo = new GameInfo();
		gameInfo.fromJson(s);

		// AI IMPLEMENTATION HERE.

		String action = AiHelper.CreateMoveAction(gameInfo.player.Position);
	    return super.resJson(req, res, 200, action);
	}
}
