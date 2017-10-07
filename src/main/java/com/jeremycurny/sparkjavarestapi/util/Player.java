package com.jeremycurny.sparkjavarestapi.util;

import org.json.simple.JSONObject;

public class Player {

    public int Health;
    public int MaxHealth;
    public int CarriedResources;
    public int CarryingCapacity;
    public int Defence;
    public int AttackPower;
    public int TotalResource;
    public Point Position;
    public Point HouseLocation;
    public int Score;

    public Player() {}

//    public Player(int health, int maxHealth, Point position, Point houseLocation, int score,
//                  int carriedResources, int carryingCapacity) {
//        Health = health;
//        MaxHealth = maxHealth;
//        Position = position;
//        HouseLocation = houseLocation;
//        Score = score;
//        CarriedResources = carriedResources;
//        CarryingCapacity = carryingCapacity;
//    }

    public void fromJson(JSONObject player) {
        Health = Integer.valueOf(player.get("Health").toString());
        MaxHealth = Integer.valueOf(player.get("MaxHealth").toString());
        CarriedResources = Integer.valueOf(player.get("CarriedResources").toString());
        CarryingCapacity = Integer.valueOf(player.get("CarryingCapacity").toString());
        Defence = Integer.valueOf(player.get("Defence").toString());
        AttackPower = Integer.valueOf(player.get("AttackPower").toString());
        TotalResource = Integer.valueOf(player.get("TotalResources").toString());

        JSONObject position = (JSONObject)player.get("Position");
        Position = new Point();
        Position.fromJson(position);
        JSONObject houseLocation = (JSONObject)player.get("HouseLocation");
        HouseLocation = new Point();
        HouseLocation.fromJson(houseLocation);
    }
}
