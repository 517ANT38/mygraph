package com.example.myGraph;

public interface Graph {
    Graph toGraphAdjacencyMatrix();
    Graph toGraphIncidentMatrix();
    Graph toGraphListNode();
    Graph toListEdge();
}
