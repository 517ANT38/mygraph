package com.example.myGraph;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GraphListEdge implements Graph {

    private Map<Node,Set<Edge>> map ;

    

    public GraphListEdge(Map<Node, Set<Edge>> map) {
        this.map = map;
    }

    public GraphListEdge(InputStream st){
        this.map = new  HashMap<>();
    }


    @Override
    public Graph toGraphAdjacencyMatrix() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toGraphAdjacencyMatrix'");
    }

    @Override
    public Graph toGraphIncidentMatrix() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toGraphIncidentMatrix'");
    }

    @Override
    public Graph toGraphListNode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toGraphListNode'");
    }

    @Override
    public Graph toListEdge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toListEdge'");
    }
    
}
