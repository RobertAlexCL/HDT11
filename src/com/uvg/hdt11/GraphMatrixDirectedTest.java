package com.uvg.hdt11;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphMatrixDirectedTest {

    @Test
    public void addVertex() {
        Graph<String, Float> g = new GraphMatrixDirected<>(4);
        String label = "Guatemala";
        g.addVertex(label);
        String found = g.getVertex(label);
        Assert.assertEquals(label, found);
    }

    @Test
    public void addEdge() {
        Graph<String, Float> g = new GraphMatrixDirected<>(4);
        g.addEdge("Guatemala", "Sacatepequez", 45.0f);
        Edge<String, Float> e = (Edge<String, Float>) ((GraphMatrixDirected<String, Float>) g).data[0][0];
        Assert.assertEquals("Guatemala", e.here());
        Assert.assertEquals("Sacatepequez", e.there());
    }

    @Test
    public void removeEdge() {
        Graph<String, Float> g = new GraphMatrixDirected<>(4);
        String ciudad1 = "Guatemala";
        String ciudad2 = "Sacatepequez";
        g.addEdge(ciudad1, ciudad2, 45.0f);
        g.removeEdge(ciudad1, ciudad2);
        Edge<String, Float> e = (Edge<String, Float>) ((GraphMatrixDirected<String, Float>) g).data[0][0];
        Assert.assertNull(e);
    }
}