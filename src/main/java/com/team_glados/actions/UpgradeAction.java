package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class UpgradeAction extends AbstractAction {

	static int attackLevel = 0;
	static int defenseLevel = 0;
	static int collectingSpeedLevel = 0;
	static int capacityLevel = 0;

	@Override
	public int getWeight(GameInfo info)
	{
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
