package com.gmjm.challenge1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gmjm.challenge1.Challenge.Node;

/**
 * This class generates a SolutionAnalysis for a particular challenge, and solution.
 * 
 * @author 755251
 *
 */
public class SolutionAnalyzer {
	public static void analyzeSolution(Challenge challenge, ChallengeSolution challengeSolution)
	{
		double totalLength = 0;
		long executionInMs = 0;
		int edges = 0;
		List<Node> missedNodes = new ArrayList<Node>();
		Map<Node,Integer> visitedNodes = new HashMap<Node,Integer>();
		
		//calculate solution, record execution time
		long start = System.currentTimeMillis();
		List<Node> solutionNodes = challengeSolution.calculateSolution(challenge.height,challenge.width,challenge.getNodeList());
		executionInMs = System.currentTimeMillis() - start;
		
		//analyze number of edges in solution
		edges = solutionNodes.size();
		
		//initialize visisted nodes map
		for(Node challengeNode : challenge.getNodeList())
		{
			visitedNodes.put(challengeNode, 0);
		}
		
		//calculate length
		for(int i = 0; i < solutionNodes.size() - 2; i++)
		{
			Node a = solutionNodes.get(i);
			Node b = solutionNodes.get(i+1);
			
			double dx = Math.abs(a.x - b.x);
			double dy = Math.abs(a.y - b.y);
			
			totalLength += Math.sqrt(dx * dx + dy * dy);
		}
		
		
		//analyze how many times each node was visited
		for(Node currentNode : solutionNodes)
		{
			Integer newVal = visitedNodes.get(currentNode) + 1;
			visitedNodes.put(currentNode, newVal);
		}
		
		ArrayList<Entry<Node,Integer>> visitedNodesList = new ArrayList<Map.Entry<Node,Integer>>(visitedNodes.entrySet());
		
		//compile analysis
		for(Entry<Node,Integer> entry : visitedNodesList)
		{
			if(entry.getValue() == 0)
				missedNodes.add(entry.getKey());
		}
		
		Collections.sort(visitedNodesList, new Comparator<Entry<Node,Integer>>() {

			@Override
			public int compare(Entry<Node, Integer> o1, Entry<Node, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		List<Entry<Node,Integer>> fiveMostVisited = visitedNodesList.subList(0, 5);
		
		SolutionAnalysis analysis =  new SolutionAnalysis(
				solutionNodes,
				totalLength, 
				edges, 
				executionInMs,
				missedNodes, 
				fiveMostVisited);
		
		challengeSolution.solutionAnlaysis = analysis;
	}
}
