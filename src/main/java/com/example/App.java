package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
        System.out.println(g.getCountEdge());
    }
}
