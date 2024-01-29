package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.example.myGraph.GraphListNode;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        var  g = GraphListNode.createGraphFromListNode(new FileInputStream(new File("test")));
        // System.out.println(g);
        // // System.out.println(g.getDistanse(new Node(8), new Node(1)));
        // System.out.println(g.getD());
        // System.out.println(g.getR());
        // System.out.println(g.isConnected());
        // System.out.println(g.isEulerian());
        // System.out.println(g.isPath(1,6,2,8));
        // System.out.println(g.getWeight(1, 6));
        // // g.removeEdge(1, 6);
        // g.graphListNodeToOut(System.out);
        // g.graphMatrixAdvecedToOut(System.out);
        System.out.println(g.isTree());

    }
}
