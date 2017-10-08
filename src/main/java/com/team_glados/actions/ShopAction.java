package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.PurchasableItem;

import java.util.Stack;

public class ShopAction extends AbstractAction {

	public static boolean hasHealthPotion = false;
	public static Point storePosition = null;

	private Stack<PurchasableItem> toBuy;

	public ShopAction() {
		toBuy = new Stack<>();
		toBuy.push(PurchasableItem.MicrosoftSword);
		toBuy.push(PurchasableItem.UbisoftShield);
		toBuy.push(PurchasableItem.DevolutionsBackpack);
		toBuy.push(PurchasableItem.DevolutionsPickaxe);
	}

	@Override
	public int getWeight(GameInfo info) {
		if (info.player.TotalResource > 40000 && !toBuy.empty()) {
			return 70;
		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		PurchasableItem item = toBuy.pop();
		UpgradeAction.toUpgrade.pop();
		return AiHelper.CreatePurchaseAction(item);
	}
}
