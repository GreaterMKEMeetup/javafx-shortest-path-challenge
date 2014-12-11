package com.gmjm.challenge1.solutions;

import java.util.List;

import com.gmjm.challenge1.Challenge.Node;
import com.gmjm.challenge1.ChallengeSolution;

/**
 * An example solution that just returns the nodes in their generated order,
 * but removes node 6 just for the heck of it.
 * 
 * A real implementation will do some sort of algorithm to find the shortest path between
 * all the nodes.
 * 
 * @author Andrew Glassman
 *
 */
public class DefaultSolution extends ChallengeSolution {

	@Override
	public List<Node> calculateSolution(int width, int height, List<Node> nodes) {
		nodes.remove(6);
		return nodes;
	}

	@Override
	public String getSolutionName() {
		return "Default Solution";
	}

}
