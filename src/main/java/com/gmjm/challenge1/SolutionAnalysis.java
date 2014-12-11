package com.gmjm.challenge1;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gmjm.challenge1.Challenge.Node;

/**
 * This class represents an analysis of a particular solution.
 * 
 * @author Andrew Glassman
 *
 */
public class SolutionAnalysis {
	public final List<Node> solutionNodes;
	public final double totalLength;
	public final int edges;
	public final long executionInMs;
	public final List<Node> missedNodes;
	public final List<Entry<Node,Integer>> mostVisitedNodes;
	
	
	public SolutionAnalysis(
			List<Node> solutionNodes,
			double totalLength,
			int edges,
			long executionInMs,
			List<Node> missedNodes,
			List<Entry<Node,Integer>> mostVisitedNodes)
	{
		this.solutionNodes = solutionNodes;
		this.totalLength = totalLength;
		this.edges = edges;
		this.executionInMs = executionInMs;
		this.missedNodes = missedNodes;
		this.mostVisitedNodes = mostVisitedNodes;
	}


	@Override
	public String toString() {
		return "SolutionAnalysis [totalLength="
				+ totalLength + ", edges=" + edges + ", executionInMs="
				+ executionInMs + ", missedNodes=" + missedNodes
				+ ", mostVisitedNodes=" + mostVisitedNodes + "]";
	}
	
	
	
}
