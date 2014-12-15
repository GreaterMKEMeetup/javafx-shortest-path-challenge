package com.gmjm.challenge1.solutions.gitter;

import com.gmjm.challenge1.Challenge.Node;

public class GreedyManhattanSolution extends GreedySolution {

	@Override
	protected double calculateDistance(Node node, Node other) {
		return Math.abs(node.x - other.x) + Math.abs(node.y - other.y);
	}

	@Override
	public String getSolutionName() {
		return "Gitter's Greedy Manhattan Solution";
	}

}
