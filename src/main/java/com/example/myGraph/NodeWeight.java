
package com.example.myGraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * NodeWeight
 */

@AllArgsConstructor
@Data
@ToString
public class NodeWeight {

    private Node node;
    private double w;
   

    public NodeWeight(NodeWeight n){
        this(n.node,n.w);
    }

   
    
}