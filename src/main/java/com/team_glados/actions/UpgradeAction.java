package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.AiHelper;
import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.UpgradeType;
import javafx.util.Pair;

import java.util.Stack;

public class UpgradeAction extends AbstractAction {

	static int attackLevel = 0;
	static int defenseLevel = 0;
	static int collectingSpeedLevel = 0;
	static int capacityLevel = 0;

	public static Stack<Pair<Integer, UpgradeType>> toUpgrade;

	public UpgradeAction() {
		toUpgrade = new Stack<>();
		toUpgrade.push(new Pair<>(500000, UpgradeType.AttackPower));
		toUpgrade.push(new Pair<>(500000, UpgradeType.Defence));
		toUpgrade.push(new Pair<>(500000, UpgradeType.CarryingCapacity));
		toUpgrade.push(new Pair<>(500000, UpgradeType.CollectingSpeed));
		toUpgrade.push(new Pair<>(100000, UpgradeType.MaximumHealth));
//		toUpgrade.push(new Pair<>(40000, UpgradeType.Item)); // Item : épée

		toUpgrade.push(new Pair<>(250000, UpgradeType.AttackPower));
		toUpgrade.push(new Pair<>(250000, UpgradeType.Defence));
		toUpgrade.push(new Pair<>(250000, UpgradeType.CarryingCapacity));
		toUpgrade.push(new Pair<>(250000, UpgradeType.CollectingSpeed));
		toUpgrade.push(new Pair<>(50000, UpgradeType.MaximumHealth));

		toUpgrade.push(new Pair<>(100000, UpgradeType.AttackPower));
		toUpgrade.push(new Pair<>(100000, UpgradeType.Defence));
		toUpgrade.push(new Pair<>(100000, UpgradeType.CarryingCapacity));
		toUpgrade.push(new Pair<>(100000, UpgradeType.CollectingSpeed));
		toUpgrade.push(new Pair<>(30000, UpgradeType.MaximumHealth));
		toUpgrade.push(new Pair<>(20000, UpgradeType.MaximumHealth));
		toUpgrade.push(new Pair<>(10000, UpgradeType.MaximumHealth));

		toUpgrade.push(new Pair<>(50000, UpgradeType.AttackPower));
		toUpgrade.push(new Pair<>(50000, UpgradeType.Defence));
		toUpgrade.push(new Pair<>(50000, UpgradeType.CarryingCapacity));
		toUpgrade.push(new Pair<>(50000, UpgradeType.CollectingSpeed));

		toUpgrade.push(new Pair<>(15000, UpgradeType.AttackPower));
//		toUpgrade.push(new Pair<>(40000, UpgradeType.Item)); // Item : bouclier

		toUpgrade.push(new Pair<>(15000, UpgradeType.Defence));

//		toUpgrade.push(new Pair<>(40000, UpgradeType.Item)); // Item : sac
//		toUpgrade.push(new Pair<>(40000, UpgradeType.Item)); // Item : pickaxe
		toUpgrade.push(new Pair<>(15000, UpgradeType.CarryingCapacity));
		toUpgrade.push(new Pair<>(15000, UpgradeType.CollectingSpeed));
	}

	@Override
	public int getWeight(GameInfo info) {
		if (info.player.HouseLocation.equals(info.player.Position)) {
			Pair<Integer, UpgradeType> top = toUpgrade.peek();
			if (top.getKey() <= info.player.TotalResource) {
				return 100;
			}
		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return AiHelper.CreateUpgradeAction(toUpgrade.pop().getValue());
	}
}
