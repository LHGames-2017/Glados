package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;

public class ShopAction extends AbstractAction {

	static boolean hasSword = false;
	static boolean hasShield = false;
	static boolean hasBackpack = false;
	static boolean hasPickaxe = false;
	static boolean hasHealthPotion = false;

	@Override
	public int getWeight(GameInfo info) {
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return null;
	}
}
