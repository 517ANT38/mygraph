package com.example.myGraph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GraphIncidentMatrix
 */
public class GraphIncidentMatrix implements Graph {

    private Set<Edge> edges;
    private List<List<Boolean>> iList ;

    public GraphIncidentMatrix(Set<Edge> edges, List<List<Boolean>> iList) {
        this.edges = edges;
        this.iList = iList;
    }




    public GraphIncidentMatrix(InputStream st){
        
        this.edges = new HashSet<>();
        this.iList = new ArrayList<>();
        
        Scanner sc = new Scanner(st);

        while(sc.hasNextLine()){

            String[] arrStr = sc.nextLine().split(":");            
            Node node = new Node(Integer.parseInt(arrStr[0]));
            
            var set = Arrays.asList(arrStr[1].split(",")).stream()
                .map(t -> Integer.parseInt(t))
                .map(Node::new)
                .map(x -> new Edge(node, x))
                .collect(Collectors.toSet());            
            edges.addAll(set);

            List<Boolean> bList = new ArrayList<>();            
            iList.set(node.x, bList);

            for (var i : set) 
                bList.set(i.node2.x, true);            
            
            
        }

    }

    


    @Override
    public String toString() {
        return "GraphIncidentMatrix [edges=" + edges + ", iList=" + iList + "]";
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