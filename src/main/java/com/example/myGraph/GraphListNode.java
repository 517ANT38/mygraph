package com.example.myGraph;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphListNode implements Graph{

    private Map<Node,Set<Node>> map ;  

    public GraphListNode(InputStream st){

        this.map = new HashMap<>(); 

        Scanner sc = new Scanner(st);
        
        while(sc.hasNextLine()){

            String[] arrStr = sc.nextLine().split(":");
            
            Node node = new Node(Integer.parseInt(arrStr[0]));
            
            var set = Arrays.asList(arrStr[1].split(",")).stream()
                .map(t -> Integer.parseInt(t))
                .map(Node::new)
                .collect(Collectors.toSet());
            
            map.put(node, set);
        }
    }

    


    public GraphListNode(Map<Node, Set<Node>> map) {
        this.map = map;
    }




    @Override
    public String toString() {

        return "GraphListNode [ " + map + " ]";
    
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
