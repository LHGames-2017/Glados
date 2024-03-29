package com.jeremycurny.sparkjavarestapi.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GameInfo {

	public Player player = new Player();
	public List<List<Tile>> map;
	public Map<String, PlayerInfo> otherPlayers = new HashMap<>();

    private com.team_glados.map.Map ComputedMap = new com.team_glados.map.Map();

    public GameInfo() {
    }

	public boolean hasTileNextToPlayer(TileContent tileContent) {
		return (map.get(9).get(10).Content == tileContent)
				|| (map.get(11).get(10).Content == tileContent)
				|| (map.get(10).get(9).Content == tileContent)
				|| (map.get(10).get(11).Content == tileContent);
	}

	public Point findTileNextToPlayer(TileContent tileContent) {
		if (map.get(9).get(10).Content == tileContent) {
			return new Point(10, 9);
		}
		if (map.get(11).get(10).Content == tileContent) {
			return new Point(10, 11);
		}
		if (map.get(10).get(9).Content == tileContent) {
			return new Point(9, 10);
		}
		if (map.get(10).get(11).Content == tileContent) {
			return new Point(11, 10);
		}
		return null;
	}

	public Point relativeToAbsolute(Point relPos) {
		return new Point(player.Position.x + (relPos.x - 10),
				player.Position.y + (relPos.y - 10));
	}

	public void fromJson(String data) {
		JSONParser parser = new JSONParser();

        try {
            JSONObject gameInfo = (JSONObject)parser.parse(data);
            JSONObject player = (JSONObject)gameInfo.get("Player");
            this.player.fromJson(player);
            String customSerializedMap = String.valueOf(gameInfo.get("CustomSerializedMap"));
            this.map = AiHelper.deserializeMap(customSerializedMap);
            JSONArray jaobj = (JSONArray)gameInfo.get("OtherPlayers");
            Object[] otherPlayers = jaobj.toArray();
            for (Object obj : otherPlayers) {
                PlayerInfo playerInfo = new PlayerInfo();
                JSONObject otherPlayer = (JSONObject)obj;
                playerInfo.fromJson(otherPlayer);
                this.otherPlayers.put(playerInfo.Name, playerInfo);
            }
        } catch(ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
    }


    public com.team_glados.map.Map getComputedMap() {
        return ComputedMap;
    }

    public void setComputedMap(com.team_glados.map.Map computedMap) {
        ComputedMap = computedMap;
    }
}
