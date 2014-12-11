package com.gmjm.challenge1;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.gmjm.challenge1.Challenge.Node;

public abstract class ChallengeSolution {
	
	/**
	 * Implement this method to uniquely identify your solution implementation.
	 * 
	 * @return String
	 */
	public abstract String getSolutionName();
	
	/**
	 * Return the order your solution will traverse the nodes.
	 * 
	 * @param width in pixels of the challenge field
	 * @param height in pixels of the challenge field
	 * @param nodes in the challenge field in no particular order
	 * 
	 * @return Your solution, which represents the order to traverse the nodes. 
	 */
	protected abstract List<Node> calculateSolution(int width, int height, List<Node> nodes);

	public SolutionAnalysis solutionAnlaysis = null;
	
	@Override
	public String toString()
	{
		return getSolutionName();
	}
	
	/**
	 * Draw a particular solution.
	 * 
	 * @param solution The solution to draw
	 * @param color the color to draw the edges in
	 * @param gc
	 */
	public static void drawSolution(ChallengeSolution solution, Color color, GraphicsContext gc)
	{
		List<Node> nodes = solution.solutionAnlaysis.solutionNodes;
		
		gc.save();
		gc.setFill(color);
		gc.setStroke(color);
		gc.setLineWidth(1);
		
		Node a = null;
		Node b = null;
		
		for(int i = 0; i < nodes.size(); i ++)
		{
			if(i - 1 >= 0)
			{
				a = nodes.get(i - 1);
			}
			
			b = nodes.get(i);
			
			if(a != null && b != null)
			{
				drawLine(a,b,gc);
			}
		}
		
		gc.setFill(Color.GREENYELLOW);
		Node start = nodes.get(0);
		Node end = nodes.get(nodes.size()-1);
		gc.fillOval(start.x - 4, start.y - 4, 8, 8);
		gc.setFill(Color.RED);
		gc.fillOval(end.x - 4, end.y - 4, 8, 8);
		
		gc.restore();
	}
	
	/**
	 * Draw a line between two nodes.
	 * 
	 * @param a
	 * @param b
	 * @param gc
	 */
	public static void drawLine(Node a, Node b, GraphicsContext gc)
	{
		gc.strokeLine(a.x, a.y, b.x, b.y);
	}
}
