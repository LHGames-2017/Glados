package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class MoveToShopAction extends AbstractAction {


    @Override
    public int getWeight(GameInfo info)
    {
    	if (info.player.CarriedResources == 0 && info.player.TotalResource >= 40000)
    		return 61;
        return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
