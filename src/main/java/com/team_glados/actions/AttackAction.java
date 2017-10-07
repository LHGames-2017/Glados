package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class AttackAction extends AbstractAction {
	@Override
	public int getWeight(GameInfo info) {
		return weight;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
