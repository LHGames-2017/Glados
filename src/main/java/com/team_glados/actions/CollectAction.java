package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.TileContent;

import static com.jeremycurny.sparkjavarestapi.util.AiHelper.CreateCollectAction;

public class CollectAction extends AbstractAction {

	@Override
	public int getWeight(GameInfo info) {
		if (info.hasTileNextToPlayer(TileContent.Resource)) {
			return 60;
		}
		return 0;
	}


	@Override
	public String doIt(GameInfo info) {
		final Point tileNextToPlayer = info.findTileNextToPlayer(TileContent.Resource);
		return CreateCollectAction(info.relativeToAbsolute(tileNextToPlayer));
	}
}
