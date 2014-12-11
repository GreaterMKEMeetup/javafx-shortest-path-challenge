package com.gmjm.challenge1;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Challenge class represents a grid of pixels. 
 * A Node represents a pixel in the grid. 
 * 
 * The nodeList represents a randomly generated list of
 * nodes that fall within the grid.  
 * 
 * @author Andrew Glassman
 *
 */
public class Challenge {

	int width;
	int height;
	int nodes;
	
	private List<Node> nodeList = new ArrayList<Node>();
	
	/**
	 * A Node represents a pixel in the challenge field.
	 * 
	 * nodeNumber uniquely identifies this node as there could be 
	 * multiple nodes on one pixel.
	 * 
	 * @author Andrew Glassman
	 *
	 */
	public class Node {
		public final int nodeNumber;
		public final int x;
		public final int y;
		
		public Node(int nodeNumber, int x, int y)
		{
			this.nodeNumber = nodeNumber;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString()
		{
			return Integer.toString(nodeNumber);
		}
	}
	
	/**
	 * Create a new challenge
	 * @param width in pixels
	 * @param height in pixels
	 * @param nodes number of nodes to be generated
	 */
	public Challenge(int width, int height, int nodes) {
		this.width = width;
		this.height = height;
		this.nodes = nodes;
		
		
		
		for(int i = 0; i < nodes; i ++)
		{
			int x = (int)(Math.random() * width);
			int y = (int)(Math.random() * height);
			nodeList.add(new Node(i,x,y));
		}
	}

	/**
	 * Returns a copy of the nodeList for this challenge.
	 * 
	 * @return list of Node objects
	 */
	public List<Node> getNodeList()
	{
		return new ArrayList<Node>(nodeList);
	}
	
	//Used to draw the list of nodes
	public void draw(GraphicsContext gc) {
		for(Node node : nodeList)
		{
			drawNode(node,gc);
		}
	}
	
	//Used to draw a specific node
	private void drawNode(Node node, GraphicsContext gc)
	{
		gc.fillOval(node.x-2, node.y-2, 4, 4);
	}

}
