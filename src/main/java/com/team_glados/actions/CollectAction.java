package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.TileType;

import static com.jeremycurny.sparkjavarestapi.util.AiHelper.CreateCollectAction;

public class CollectAction extends AbstractAction {

	@Override
	public int getWeight(GameInfo info) {
		if (info.hasTileNextTo(1, info.player.Position))
		{
			return 50;
		}
		return 0;
	}

	@Override
	public String doIt(GameInfo info) {
		return CreateCollectAction(info.findTileNextTo(TileType.Tile, info.player.Position));
	}
}
