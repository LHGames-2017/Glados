package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public interface Action {
	public int getWeight(GameInfo info);
	public String doIt(GameInfo info);
}
