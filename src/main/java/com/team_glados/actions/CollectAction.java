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

	//info.findTileNextToPlayer(TileContent.Resource)

	@Override
	public String doIt(GameInfo info) {
//		System.out.println(info.player.Position);
		final Point tileNextToPlayer = info.findTileNextToPlayer(TileContent.Resource);

//		System.out.println(tileNextToPlayer);
//		System.out.println(info.relativeToAbsolute(tileNextToPlayer));

		return CreateCollectAction(info.relativeToAbsolute(tileNextToPlayer));
	}
}
