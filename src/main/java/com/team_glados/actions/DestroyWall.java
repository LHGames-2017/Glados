package com.team_glados.actions;

import com.jeremycurny.sparkjavarestapi.util.GameInfo;
import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.TileContent;

import static com.jeremycurny.sparkjavarestapi.util.AiHelper.CreateAttackAction;
import static com.jeremycurny.sparkjavarestapi.util.AiHelper.CreateCollectAction;

public class DestroyWall extends AbstractAction {

    public int getWeight(GameInfo info) {
        if (info.hasTileNextToPlayer(TileContent.Wall)) {
            return 100;
        }
        return 0;
    }
    
    @Override
    public String doIt(GameInfo info) {
        final Point tileNextToPlayer = info.findTileNextToPlayer(TileContent.Wall);
        return CreateAttackAction(info.relativeToAbsolute(tileNextToPlayer));
    }
}
