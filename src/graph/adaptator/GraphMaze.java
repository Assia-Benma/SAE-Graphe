package graph.adaptator;

import graph.Graph;
import maze.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GraphMaze<T> implements Graph.graph<T> {
    private final Maze<T> maze;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    @Override
    public List<Graph.graph.Arc<T>> getSucc(T cellId) {
        List<Graph.graph.Arc<T>> arcs = new ArrayList<>();
        Set<T> neighbors = maze.openedNeighbours(cellId);
        for (T neighbor : neighbors) {
            arcs.add(new Graph.graph.Arc<>(1, neighbor));
        }
        return arcs;
    }
}