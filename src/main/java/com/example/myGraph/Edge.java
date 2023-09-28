package com.example.myGraph;

public class Edge {
    
    public final Node node1;
    public final Node node2;

    public Edge(Node node1,Node node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String toString() {
        return "Edge [node1=" + node1 + ", node2=" + node2 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node1 == null) ? 0 : node1.hashCode());
        result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
        return result;
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
        } else if (!node1.equals(other.node1))
            return false;
        if (node2 == null) {
            if (other.node2 != null)
                return false;
        } else if (!node2.equals(other.node2))
            return false;
        return true;
    }
    
}
