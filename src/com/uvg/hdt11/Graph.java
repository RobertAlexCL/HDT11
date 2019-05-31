

public interface Graph<V,E> {
    boolean addVertex(V vertex);
    boolean addEdge(V vertex0, V vertex1, E weight);
    V removeVertex(V vertex);
    E removeEdge(V vertex0, V vertex1);
    V getVertex(V vertex);
    Edge<V,E> getEdge(V vertex0, V vertex1);
    boolean visitVertex(V vertex);
    boolean visitEdge(V vertex0, V vertex1);
    boolean isVertexVisited(V vertex);
    boolean isEdgeVisited(Edge<V,E> edge);
}
