package com.jeremycurny.sparkjavarestapi.util;

public class Tile {

        public Tile( Integer content_, Integer x_, Integer y_){

            this.X = x_;
            this.Y = y_;
            this.Content = TileContent.values()[content_];

        }

        public TileContent Content;
        public Integer X;
        public Integer Y;

    @Override
    public String toString() {
        switch (Content) {
            case Empty:
                return " ";
            case Resource:
                return "R";
            case House:
                return "H";
            case Player:
                return "P";
            case Wall:
                return "W";
            case Lava:
                return "L";
            case Shop:
                return "S";
            default:
                break;
        }
         return " ";
    }
}
