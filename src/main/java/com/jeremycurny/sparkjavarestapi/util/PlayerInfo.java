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
        Name = String.valueOf(playerInfo.get("Key").toString());

        final JSONObject playerValues = ((JSONObject) playerInfo.get("Value"));

        Health = Integer.valueOf(playerValues.get("Health").toString());
        MaxHealth = Integer.valueOf(playerValues.get("MaxHealth").toString());
        AttackPower = Integer.valueOf(playerValues.get("AttackPower").toString());
        Defence = Integer.valueOf(playerValues.get("Defence").toString());
        Resources = Integer.valueOf(playerValues.get("Resources").toString());

        JSONObject position = (JSONObject)playerValues.get("Position");
        Position = new Point();
        Position.fromJson(position);
    }

}
