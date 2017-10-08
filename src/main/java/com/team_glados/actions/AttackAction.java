package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.*;

import java.util.Map;

public class AttackAction extends AbstractAction {
	private Point target;

	@Override
	public int getWeight(GameInfo info) {
		Point p = info.findTileNextToPlayer(TileContent.Player);
		if (p == null)
			return 0;

		p = info.relativeToAbsolute(p);
		for (Map.Entry<String, PlayerInfo> other : info.otherPlayers.entrySet()) {
			if (other.getValue().Position.equals(p)) {
				int potentialDamage = (int) (3 + info.player.AttackPower - 2 * Math.pow(other.getValue().Defence + 2, 0.6));
				if (potentialDamage > other.getValue().Health) {
					target = p;
					return 100;
				}
				break;
			}
		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return AiHelper.CreateAttackAction(target);
	}
}
