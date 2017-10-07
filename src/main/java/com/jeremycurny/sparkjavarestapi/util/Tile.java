package com.jeremycurny.sparkjavarestapi.util;

public class Tile {

        public Tile( Integer content_, Integer x_, Integer y_){

            this.X = x_;
            this.Y = y_;
            this.Content = content_;

        }
        public Integer Content;
        public Integer X;
        public Integer Y;

    @Override
    public String toString() {
         switch (Content) {
             case 0:
                 return " ";
             case 1:
                 return "W";
             case 2:
                 return "H";
             case 3:
                 return "L";
             case 4:
                 return "R";
             case 5:
                 return "S";
             case 6:
                 return "P";
             default:
                 break;
         }
         return " ";
    }
}
