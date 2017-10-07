package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class MoveToCollectAction extends AbstractAction {
	@Override
	public int getWeight(GameInfo info) {
		if (info.player.CarriedResources < info.player.CarryingCapacity)
			return 50;
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
