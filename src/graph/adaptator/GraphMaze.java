package graph.adaptator;

import graph.Graph;
import maze.Maze;

import java.util.List;

public class GraphMaze<T> implements Graph.graph<T> {
    private final Maze<T> maze;

    public GraphMaze(Maze<T> maze) {
        this.maze = maze;
    }

    @Override
    public List<Arc<T>> getSucc(T s) {
        return maze.neighbours(s).stream()
                .map(neighbour -> new Arc<>(1, neighbour)) // Assuming a default weight of 1
                .toList();
    }
}