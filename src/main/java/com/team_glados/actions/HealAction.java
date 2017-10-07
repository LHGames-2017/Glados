package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class HealAction extends AbstractAction {
	@Override
	public int getWeight(GameInfo info) {
//		if (info.player.MaxHealth - info.player.Health >= 5 && ShopAction.hasHealthPotion)
//			return 90;
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		ShopAction.hasHealthPotion = false;
		return AiHelper.CreateHealAction();
	}
}
