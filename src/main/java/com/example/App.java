package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

import com.example.myGraph.GraphListNode;
import com.example.myGraph.Node;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        var  g = GraphListNode.createGraphFromListNode(new FileInputStream(new File("test")));
  
        System.out.println(g.getDistanse(new Node(8), new Node(1)));
        // g.graphListNodeToOut(System.out);
        // g.graphMatrixAdvecedToOut(System.out);
    }
}
