package com.team_glados.map;

import com.jeremycurny.sparkjavarestapi.util.Point;
import com.jeremycurny.sparkjavarestapi.util.Tile;
import com.jeremycurny.sparkjavarestapi.util.TileContent;
import com.team_glados.math.graph.Edge;
import com.team_glados.math.graph.Graph;
import com.team_glados.math.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on: 17-10-07 at 14:37
 *
 * @author technophil98
 */
public class Map {

    public static final int BASE_WEIGHT = 1;
    private HashMap<Point, Tile> tiles = new HashMap<>(400);

    public boolean addTile(Tile t){
        return tiles.put(new Point(t.X, t.Y), t) == t;
    }

    public Tile tileAt(Point p){
        return tiles.get(p);
    }

    public Tile[] getTiles(){
        return tiles.values().toArray(new Tile[0]);
    }

    public Graph toGraph(){

        HashMap<Point, Node<Point>> nodes = new HashMap<>(tiles.values().size());
        Set<Edge> edges = new HashSet<>(8 * tiles.values().size());


        tiles.forEach((point, tile) -> {
            if (tile.Content != TileContent.Lava &&
                tile.Content != TileContent.Wall
                )
            {
                TileNode t = new TileNode(point);
                nodes.put(point, t);
            }
        });

        nodes.forEach((point, node) -> {

            final Node<Point> nodeToLeft = nodes.get(new Point(point.x - 1, point.y));
            if (nodeToLeft != null){

                edges.add(new Edge(node, nodeToLeft, BASE_WEIGHT));
                edges.add(new Edge(nodeToLeft, node, BASE_WEIGHT));

            }

            final Node<Point> nodeToRight = nodes.get(new Point(point.x + 1, point.y));
            if (nodeToRight != null){

                edges.add(new Edge(node, nodeToRight, BASE_WEIGHT));
                edges.add(new Edge(nodeToRight, node, BASE_WEIGHT));

            }

            final Node<Point> nodeToTop = nodes.get(new Point(point.x, point.y + 1));
            if (nodeToTop != null){

                edges.add(new Edge(node, nodeToTop, BASE_WEIGHT));
                edges.add(new Edge(nodeToTop, node, BASE_WEIGHT));

            }

            final Node<Point> nodeToBottom = nodes.get(new Point(point.x, point.y - 1));
            if (nodeToBottom != null){

                edges.add(new Edge(node, nodeToBottom, BASE_WEIGHT));
                edges.add(new Edge(nodeToBottom, node, BASE_WEIGHT));

            }

        });

        return new Graph(nodes.values().toArray(new Node[0]), edges.toArray(new Edge[0]));
    }

}
