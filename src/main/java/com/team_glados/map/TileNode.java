package com.team_glados.map;

import com.jeremycurny.sparkjavarestapi.util.Point;
import com.team_glados.math.graph.Node;

/**
 * Created on: 17-10-07 at 13:40
 *
 * @author technophil98
 */
public class TileNode extends Node<Point> {

    private Point point;

    public TileNode(Point p) {
        super(p);
        point = p;
    }

    @Override
    public double heuristicToNode(Node n) {
        if (n.getClass() != TileNode.class) return 0;

        TileNode destNode = (TileNode)n;

        return Math.abs(point.x - destNode.getPoint().x) + Math.abs(point.y - destNode.getPoint().y);
    }

    public Point getPoint() {
        return point;
    }
}
