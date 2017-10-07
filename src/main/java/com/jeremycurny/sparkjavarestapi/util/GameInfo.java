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

    public GameInfo() {
    }

    public boolean hasTileNextTo(int tileContent, Point point)
    {
        return (map.get(point.x + 1).get(point.y).Content == tileContent)
                || (map.get(point.x - 1).get(point.y).Content == tileContent)
                || (map.get(point.x).get(point.y + 1).Content == tileContent)
                || (map.get(point.x).get(point.y - 1).Content == tileContent);
    }

    public Point findTileNextTo(int tileContent, Point point)
    {
        if (map.get(point.x + 1).get(point.y).Content == tileContent)
        {
            return new Point(point.x + 1, point.y);
        }
        if (map.get(point.x - 1).get(point.y).Content == tileContent)
        {
            return new Point(point.x - 1, point.y);
        }
        if (map.get(point.x).get(point.y + 1).Content == tileContent)
        {
            return new Point(point.x, point.y + 1);
        }
        if (map.get(point.x).get(point.y - 1).Content == tileContent)
        {
            return new Point(point.x, point.y - 1);
        }
        return null;
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
        System.out.println();
    }
}
