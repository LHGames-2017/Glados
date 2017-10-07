package com.jeremycurny.sparkjavarestapi.util;

import org.json.simple.JSONObject;

public class PlayerInfo {

    public String Name;
    public int Health;
    public int MaxHealth;
    public int Defence;
    public int AttackPower;
    public int Resources;
    public Point Position;

    public PlayerInfo() {}

    public PlayerInfo(int health, int maxHealth, Point position) {
        Health = health;
        MaxHealth = maxHealth;
        Position = position;
    }

    public void fromJson(JSONObject playerInfo) {
        Name = String.valueOf(playerInfo.get("Name").toString());
        Health = Integer.valueOf(playerInfo.get("Health").toString());
        MaxHealth = Integer.valueOf(playerInfo.get("MaxHealth").toString());
        AttackPower = Integer.valueOf(playerInfo.get("AttackPower").toString());
        Defence = Integer.valueOf(playerInfo.get("Defence").toString());
        Resources = Integer.valueOf(playerInfo.get("Resources").toString());

        JSONObject position = (JSONObject)playerInfo.get("Position");
        Position = new Point();
        Position.fromJson(position);
    }

}
