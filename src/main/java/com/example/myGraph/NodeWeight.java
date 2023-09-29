
package com.example.myGraph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * NodeWeight
 */

@AllArgsConstructor
@Getter
@Setter
public class NodeWeight {

    private Node node;
    private double w;
    @Override
    public int hashCode() {
        
        return node.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodeWeight other = (NodeWeight) obj;
        if (node == null) {
            if (other.node != null)
                return false;
        } else if (!node.equals(other.node))
            return false;
        return true;
    }
    
}