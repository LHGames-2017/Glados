package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.PlayerInfo;

import java.util.Map;

public class AttackAction extends AbstractAction {
	@Override
	public int getWeight(GameInfo info) {
//		for (Map.Entry<String, PlayerInfo> entry : info.otherPlayers.entrySet()) {
//			PlayerInfo p = entry.getValue();
//			int distance = (p.Position.x - info.player.Position.x) + (p.Position.y - info.player.Position.y);
//			System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		if (info.hasTileNextTo(3, info.player.Position) &&) {
//
//		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
