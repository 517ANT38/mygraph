package com.example.myGraph;

public class Node {
    public final  int x;

    public Node(int x){
        this.x = x;
    }

    @Override
    public String toString(){
        return "Node(" + x+ ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
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
        Node other = (Node) obj;
        if (x != other.x)
            return false;
        return true;
    }

    
}
