

import java.util.*;

public class GraphMatrixDirected<V,E> implements Graph<V,E> {
    protected int size;
    // arreglo2D en el que se guardan objetos Edge, representando aristas
    protected Object[][] data;
    protected Map<V, Vertex<V>> map;
    protected List<Integer> freeList;

    public GraphMatrixDirected(int size) {
        this.size = size;
        data = new Object[size][size];
        map = new HashMap<>(size);
        freeList = new ArrayList<>();
        // agregar indices vacios
        for(int i = 0; i < size; i++)
            freeList.add(i);
    }

    @Override
    public boolean addVertex(V vertex) {
        // no se permiten vertices duplicados
        if(map.containsKey(vertex)) return false;
        // indice de primer fila vacia
        int row = freeList.remove(0);
        // insertar vertice en mapa
        map.put(vertex, new Vertex<>(vertex, row));
        return true;
    }

    @Override
    public boolean addEdge(V vertex0, V vertex1, E weight) {
        Vertex<V> v0 = map.get(vertex0);
        Vertex<V> v1 = map.get(vertex1);
        // algun vertice no existe, no se crea la arista
        if(v0 == null || v1 == null) return false;
        data[v0.row()][v1.row()] = new Edge<>(v0, v1, weight);
        return true;
    }

    @Override
    public V removeVertex(V vertex) {
        Vertex<V> foundVertex = map.remove(vertex);
        // vertice no existe
        if(foundVertex == null) return null;
        int index = foundVertex.row();
        // invalidar fila y columna de vertice eliminado
        for(int i = 0; i < index; i++) {
            data[index][i] = null;
            data[i][index] = null;
        }
        // indice esta libre de nuevo
        freeList.add(index);
        return foundVertex.label();
    }

    @Override
    public E removeEdge(V vertex0, V vertex1) {
        Vertex<V> v0 = map.get(vertex0);
        Vertex<V> v1 = map.get(vertex1);
        // vertices no existen
        if(v0 == null || v1 == null) return null;
        // borrar arista en arreglo2D
        Edge<V, E> edge = (Edge<V, E>) data[v0.row()][v1.row()];
        if(edge == null) return null;
        // eliminar arista de matriz
        data[v0.row()][v1.row()] = null;
        return edge.weight();
    }

    @Override
    public V getVertex(V vertex) {
        Vertex<V> foundVertex = map.get(vertex);
        if(foundVertex == null) return null;
        return foundVertex.label();
    }

    @Override
    public Edge<V, E> getEdge(V vertex0, V vertex1) {
        Vertex<V> v0 = map.get(vertex0);
        Vertex<V> v1 = map.get(vertex1);
        if(v0 == null || v1 == null) return null;
        Edge<V,E> foundEdge = (Edge<V, E>) data[v0.row()][v1.row()];
        return foundEdge;
    }

    @Override
    public boolean visitVertex(V vertex) {
        Vertex<V> foundVertex = map.get(vertex);
        if(foundVertex == null) return false;
        return foundVertex.visit();
    }

    @Override
    public boolean visitEdge(V vertex0, V vertex1) {
        Vertex<V> v0 = map.get(vertex0);
        Vertex<V> v1 = map.get(vertex1);
        if(v0 == null || v1 == null) return false;
        Edge<V,E> foundEdge = (Edge<V, E>) data[v0.row()][v1.row()];
        if(foundEdge == null) return false;
        return foundEdge.visit();
    }

    @Override
    public boolean isVertexVisited(V vertex) {
        Vertex<V> foundVertex = map.get(vertex);
        if(foundVertex == null) return false;
        return foundVertex.isVisited();
    }

    @Override
    public boolean isEdgeVisited(Edge<V, E> edge) {
        return edge.isVisited();
    }

    void printAdjacencyMatrix() {
        for(int i = 0; i < size; i++) {
            System.out.print("[");
            for(int j = 0; j < size; j++) {
                if(data[i][j] == null) System.out.print("0,");
                else System.out.print("1,");
                if(j == (size-1)) System.out.println("]");
            }
        }
    }
}
