package com.example.myGraph;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;


public class GraphListNode {

    private Map<Node,Set<NodeWeight>> map ;  

    private GraphListNode(Map<Node,Set<NodeWeight>> map){
        this.map = map;
    }
    private GraphListNode(){
        this.map = new HashMap<>();
    }
  
    public static GraphListNode createGraphFromMatrixAdveced(InputStream stream){
        
        Map<Node,Set<NodeWeight>> map = new HashMap<>();        
        Scanner sc = new Scanner(stream);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {   
            String[] arrStr = sc.nextLine().split(" ");            
            Node node1 = new Node(i);
            for (int j = 0; j < arrStr.length; j++) {    

                if(i == j) continue;    

                var node2 = new Node(j);
                var v = Double.parseDouble(arrStr[j]);

                var set1 = map.getOrDefault(node1, new HashSet<>());
                var set2 = map.getOrDefault(node2, new HashSet<>());   

                set1.add(new NodeWeight(node2,v));    
                set2.add(new NodeWeight(node1,v));
                
                map.put(node2, set2);
                map.put(node1, set1);
            }

        }   
        return new GraphListNode(map);
    }

    public int getCountNode(){
        return map.size();
    }
    public int getCountEdge(){
        
        return map.values().stream()
                .mapToInt(x -> x.size())
                .sum()/2;
    }

    public static GraphListNode createGraphFromListNode(InputStream stream){

        Map<Node,Set<NodeWeight>> map = new HashMap<>();

        Scanner sc = new Scanner(stream);

        while(sc.hasNextLine()) {      
        
            String[] arrStr = sc.nextLine().split(" ");
            
            Node node1 = new Node(Integer.parseInt(arrStr[0]));
            Node node2 = new Node(Integer.parseInt(arrStr[1]));
            double v = Double.parseDouble(arrStr[2]);

            NodeWeight nWeight1 = new NodeWeight(node2,v);
            NodeWeight nWeight2 = new NodeWeight(node1,v);

            var set1 = map.getOrDefault(node1, new HashSet<>());
            var set2 = map.getOrDefault(node2, new HashSet<>());

            set1.add(nWeight1);    
            set2.add(nWeight2);
            
            map.put(node2, set2);
            map.put(node1, set1);
        }   
        
        return new GraphListNode(map);

    }

    public static GraphListNode createEmptyGraph(){
        return new GraphListNode();
    }



    @Override
    public String toString() {
        return "GraphListNode " + map + "";
    }

    @SneakyThrows
    public void graphListNodeToOut(OutputStream w){    
        w.write((map.size() + "\n").getBytes());     
        Set<Edge> set = new HashSet<>();
        for (var item : map.entrySet()) {
            var key = item.getKey();
            var edge = new Edge();
            edge.setNode1(key);
            for (var it : item.getValue()) {

                edge.setNode2(it.getNode());
                if(set.contains(edge)) continue;
                set.add(edge);
                var s = key.getX() + " " + it.getNode().getX() + " " + it.getW() + "\n";
                w.write(s.getBytes());
            }

        }
        
    }

    @SneakyThrows
    public void graphMatrixAdvecedToOut(OutputStream w){
        w.write((map.size() + "\n").getBytes());        
        for (var item : map.entrySet()) {
            
            double[] arr = new double[map.size()+1];
            for (var i : item.getValue()) {
                var v = i.getNode().getX();
                arr[v] = i.getW();
            }
            
            var s = Arrays.stream(arr, 1, map.size()+1).boxed()
                .map(x -> x.toString())
                .collect(Collectors.joining(" ","","\n"));
            w.write(s.getBytes());

        }
    }

    public void removeEdge(Node x, Node y){
        var set = map.getOrDefault(x, new HashSet<>());
        if(!set.remove(y))
            throw new IllegalArgumentException("Not edge");
        var set2 = map.get(y);
        set2.remove(x);
    }

    public void removeEdge(int x, int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        var set = map.getOrDefault(n1, new HashSet<>());
        if(!set.remove(n2))
            throw new IllegalArgumentException("Not edge");
        var set2 = map.get(n2);
        set2.remove(n1);
    }

    public void addNode(int x){
        var n = new Node(x);
        if(map.containsKey(n))
            throw new IllegalArgumentException("Contains node");
        map.put(new Node(x), new HashSet<>());
    }


    public void addNode(Node x){
        if(map.containsKey(x))
            throw new IllegalArgumentException("Contains node");
        map.put(x, new HashSet<>());
    }

    public void addEdge(int x, int y, double v){

        var n1 = new Node(x);
        var n2 = new Node(y);

        var set1 = map.getOrDefault(n1, new HashSet<>());
        var set2 = map.getOrDefault(n2, new HashSet<>());

        var nWeight1 = new NodeWeight(n1,v);
        var nWeight2 = new NodeWeight(n2,v);

        set1.add(nWeight2);
        set2.add(nWeight1);

        map.put(n1, set1);
        map.put(n2, set2);
    }


    public void addEdge(Node n1, Node n2, double v){

        var set1 = map.getOrDefault(n1, new HashSet<>());
        var set2 = map.getOrDefault(n2, new HashSet<>());

        var nWeight1 = new NodeWeight(n1,v);
        var nWeight2 = new NodeWeight(n2,v);

        set1.add(nWeight2);
        set2.add(nWeight1);

        map.put(n1, set1);
        map.put(n2, set2);
    }
    
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Edge{
        private  Node node1;
        private Node node2;
        @Override
        public int hashCode() {
            return node1.getX() + node2.getX();
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge other = (Edge) obj;
            if (node1 == null) {
                if (other.node1 != null)
                    return false;
            } 
            return true;
        }

    }

    public boolean isAdjacent(Node n1,Node n2){
        if(!map.containsKey(n1))
            throw new IllegalArgumentException("Not node n1");
        return map.get(n1).contains(n2);
    }

    public boolean isAdjacent(int x,int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        if(!map.containsKey(n1))
            throw new IllegalArgumentException("Not node n1");
        return map.get(n1).contains(n2);
    }
  
    public double getWeight(Node n1,Node n2){
        if(!map.containsKey(n1))
            throw new IllegalArgumentException("Not node n1");
        var set = map.get(n1);
        if(!set.contains(n2))
            throw new IllegalArgumentException("Not node n2");
        return set.
    }

    public double getWeight(int x,int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        if(!map.containsKey(n1))
            throw new IllegalArgumentException("Not node n1");
        return map.get(n1).contains(n2);
    }

    
}
