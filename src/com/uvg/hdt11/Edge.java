

public class Edge<V,E> {

    protected E weight;
    protected V here;
    protected V there;
    protected boolean visited;

    Edge(V vertex0, V vertex1, E weight) {
        this.weight = weight;
        here = vertex0;
        there = vertex1;
    }

    E weight() { return weight; }

    public V here() { return here; }

    public V there() { return there; }

    boolean visit() {
        visited = true;
        return visited;
    }

    boolean isVisited() { return visited; }

    public void reset() { visited = false;}
}
