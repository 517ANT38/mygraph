package com.example.myGraph;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
        sc.close();
        return new GraphListNode(map);
    }

    public int getCountNode(){
        return map.size();
    }
    public int getCountEdge(){
        
        return map.size()*((map.size()-1)/2);
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
        sc.close();
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

    private NodeWeight getNw(Node x, Set<NodeWeight> set){
        NodeWeight nw = null;
        for (var nodeWeight : set) {
            if(nodeWeight.getNode().equals(x)){
                nw = nodeWeight;
                break;
            }
        }
        return nw;
    }

    public void removeEdge(Node x, Node y){
        var set = map.getOrDefault(x, new HashSet<>());
        
        if(!set.remove(getNw(y, set)))
            throw new IllegalArgumentException("Not edge");
        var set2 = map.get(y);
        
        if(!set2.remove(getNw(x, set2)))
            throw new IllegalArgumentException("Not edge");
    }

    public void removeEdge(int x, int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        removeEdge(n1, n2);
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
            return false;
       for (var i: map.get(n1)){
            if(i.getNode().equals(n2))
                return true;
        }
        return false;
    }

    public boolean isAdjacent(int x,int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        if(!map.containsKey(n1))
            return false;
        for (var i: map.get(n1)){
            if(i.getNode().equals(n2))
                return true;
        }
        return false;
        
    }
  
    public double getWeight(Node n1,Node n2){
        if(!map.containsKey(n1))
            throw new IllegalArgumentException("Not node n1");
        var set = map.get(n1);
        var rv = getNw(n2, set);
        if(!set.contains(rv))
            throw new IllegalArgumentException("Not node n2");
        
        return rv.getW();
    }

    public double getWeight(int x,int y){
        var n1 = new Node(x);
        var n2 = new Node(y);
        return getWeight(n1, n2);
    }

    public List<Integer> getrRowNode(){
        return map.values().stream()
            .map(x -> x.size())
            .sorted()
            .collect(Collectors.toList());
    }
    
    public int getMinRow(){
        return map.values().stream()
            .mapToInt(x -> x.size())
            .min().orElse(0);
    }

    public int getMaxRow(){
        return map.values().stream()
            .mapToInt(x -> x.size())
            .max().orElse(0);
    }


    public double getR(){
        double d = Double.MAX_VALUE;
        for(var node: map.keySet()){
            d = Math.min(getEccentricity(node), d);
        }
        return d;
    }

    public double getD(){
        double d = -1;
        for(var node: map.keySet()){
            var dist = distances(node);
            for (int i = 1; i < dist.length; i++) {
                d = Math.max(dist[i], d);
            }
        }
        return d;
    }

    public double getEccentricity(Node n){
        double d = -1;
        var ds = distances(n);
        for (int i = 1; i < ds.length; i++) {
                d = Math.max(ds[i], d);
        }
        return d;
    }

    
    public double getDistanse(Node s,Node e){
       
        var dist = distances(s);
       
        // System.out.println(Arrays.toString(dist));
        return dist[e.getX()];
        
    }

    private double[] distances(Node s){
         
        if(map.isEmpty())
            throw new RuntimeException("Graph is empty");
        
        double[] dist = new double[map.size()+1];
        boolean[] marked = new boolean[map.size()+1];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }

        Queue<Node> queue = new ArrayDeque<>();
        dist[s.getX()] = 0.0;
        queue.add(s);

        while (!queue.isEmpty()) {
            var vi = queue.poll();
            var v = vi.getX();
            if(marked[v])
                continue;
            marked[v] = true;
            for(var item: map.get(vi)){
                var node = item.getNode();
                var w = node.getX();
                if(dist[w] > dist[v] + item.getW()){
                    dist[w] = dist[v] + item.getW();					
					queue.add(node);
                }
            }
        }

        return dist;
    }

    public boolean isConnected(){
        if(map.isEmpty())
            throw new RuntimeException("Graph is empty");

        boolean[] visited = new boolean[map.size() + 1];
        Node v = map.keySet().stream().findFirst().get();

        for(var node: map.keySet()) {
            var set = map.get(node);
            if (set.size() != 0) {
                v = node;
                break;
            }
        }

        helperDfs(v, visited);

        for(var node: map.keySet()) {
            var set = map.get(node);
            if (!visited[node.getX()] && set.size() > 0) {
                return false;
            }
        }

        return true;

    }

    private void helperDfs(Node node, boolean[] visited){
        visited[node.getX()] = true;
        var setIter = map.get(node).iterator();
        while (setIter.hasNext()) {
            var n = setIter.next().getNode();
                
            if(!visited[n.getX()]){
                helperDfs(n, visited);
            }
        }
    }
    
    public int isEulerian() {
        if (isConnected() == false)
            return 0;

        int odd = 0;
        for(var node: map.keySet()){
            var set = map.get(node);
            if (set.size() % 2 != 0)
                odd++;
        }

        if (odd > 2)
            return 0;

        return (odd == 0) ? 2 : 1;
    }

    public boolean isPath(int...nodes){
        var curr = new Node(nodes[0]);
        boolean fl = true;
        for (int i = 1; i < nodes.length; i++) {
            Node tmpNode = new Node(nodes[i]);
            var set = map.get(curr);
            fl = set.stream()
                .map(NodeWeight::getNode)
                .anyMatch(x -> x.equals(tmpNode));
            if(!fl){
                break;
            }
            curr = tmpNode;
        }
        return fl;
    }
}
