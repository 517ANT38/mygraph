package com.example.myGraph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * GraphAdjacencyMatrix
 */
public class GraphAdjacencyMatrix implements Graph {

    private List<List<Node>> nLists ;

    public GraphAdjacencyMatrix(InputStream st){

        this.nLists = new ArrayList<>();

        Scanner sc = new Scanner(st);
        
        while(sc.hasNextLine()){

            nLists.add(Arrays.stream(sc.nextLine().split(" "))
                .map(t -> Integer.parseInt(t))
                .map(Node::new)
                .collect(Collectors.toList()));

        }
    }

    

    public GraphAdjacencyMatrix(List<List<Node>> nLists) {
        this.nLists = nLists;
    }



    @Override
    public String toString() {

        return "GraphAdjacencyMatrix [ " + nLists + " ]";
        
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