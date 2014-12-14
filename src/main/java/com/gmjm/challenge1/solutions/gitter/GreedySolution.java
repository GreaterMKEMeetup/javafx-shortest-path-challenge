package com.gmjm.challenge1.solutions.gitter;

import java.util.ArrayList;
import java.util.List;

import com.gmjm.challenge1.Challenge.Node;
import com.gmjm.challenge1.ChallengeSolution;

public class GreedySolution extends ChallengeSolution {

	@Override
	public String getSolutionName() {
		return "Gitter's Greedy Solution";
	}

	@Override
	protected List<Node> calculateSolution(int width, int height,
			List<Node> nodes) {
		
		List<Node> resultList = null;
		double resultListLength = Double.MAX_VALUE;

		for (int ii = 0; ii < nodes.size(); ii++) {
			List<Node> candidate = calculate(new ArrayList<Node>(nodes), ii);
			double length = calculateLength(candidate);
			
			if (length < resultListLength) {
				resultListLength = length;
				resultList = candidate;
			}
		}

		return resultList;
	}

	private List<Node> calculate(List<Node> nodeList, int startingPosition) {
		List<Node> resultList = new ArrayList<Node>();
		
		resultList.add(nodeList.remove(startingPosition));
		
		while (!nodeList.isEmpty()) {
			Node node = getNextNearestNode(resultList
					.get(resultList.size() - 1), nodeList);
			nodeList.remove(node);
			resultList.add(node);
		}

		return resultList;
	}

	private double calculateLength(List<Node> nodeList) {
		double total = 0;

		for (int i = 0; i < nodeList.size() - 2; i++) {
			Node a = nodeList.get(i);
			Node b = nodeList.get(i + 1);

			double dx = a.x - b.x;
			double dy = a.y - b.y;

			total += Math.sqrt(dx * dx + dy * dy);
		}

		return total;
	}

	private Node getNextNearestNode(Node current, List<Node> nodeList) {
		double shortest = Double.MAX_VALUE;
		Node currentNode = current;
		Node result = null;

		for (Node node : nodeList) {
			double distance = calculateDistance(currentNode, node);

			if (distance == 0) {
				result = node;
				break;
			} else if (distance < shortest) {
				shortest = distance;
				result = node;
			}
		}

		return result;
	}

	protected double calculateDistance(Node node, Node other) {
		return Math.sqrt(Math.pow(node.x - other.x, 2)
				+ Math.pow(node.y - other.y, 2));
	}
}
