package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class MoveToHouseAction extends AbstractAction {
	@Override
	public int getWeight(GameInfo info) {
		if (info.player.CarriedResources == info.player.CarryingCapacity)
			return 100; // RUN FOR YOUR RESOURCES!!!
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
