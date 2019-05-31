
public class Vertex<V> {

    private V label;
    private int row;
    private boolean visited;

    Vertex(V label, int row) {
        this.label = label;
        this.row = row;
    }

    V label() { return label; }

    int row() { return row; }

    boolean visit() { return visited; }

    boolean isVisited() { return visited; }

    void reset() { visited = false; }

    @Override
    public boolean equals(Object obj) throws ClassCastException {
        Vertex other = (Vertex)obj;
        return label.equals(other.label);
    }
}
