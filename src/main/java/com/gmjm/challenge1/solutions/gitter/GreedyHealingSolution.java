package com.gmjm.challenge1.solutions.gitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmjm.challenge1.Challenge.Node;
import com.gmjm.challenge1.ChallengeSolution;

public class GreedyHealingSolution extends ChallengeSolution {

	private List<Node> nodeList;
	private Map<Node, Map<Node, Double>> distanceMap = new HashMap<Node, Map<Node, Double>>();

	@Override
	public String getSolutionName() {
		return "Gitter's Greedy Self-Healing Solution";
	}

	@Override
	protected List<Node> calculateSolution(int width, int height, List<Node> nodes) {
		initializeDistanceMap(nodes);

		double startingLength = getInitialGreedySolution(new ArrayList<Node>(nodes));
		double length = startingLength;

		System.out.println("Length prior to healing: " + startingLength);

		for (int loop = 1; loop <= 1000; loop++) {
			boolean changesMade = false;

			for (Node node : nodes) {
				if (attemptHealing(node)) {
					changesMade = true;
				}
				length = calculateLength(nodeList);
			}

			// no beneficial moves found
			if (!changesMade) {
				System.out.println("Breaking out on iteration: " + loop);
				break;
			}
		}

		System.out.println("Length after healing: " + length);
		System.out.println("Saved: " + (startingLength - length));

		return nodeList;
	}

	private void initializeDistanceMap(List<Node> nodeList) {
		for (Node outer : nodeList) {
			if (distanceMap.get(outer) == null) {
				distanceMap.put(outer, new HashMap<Node, Double>());
			}
			for (Node inner : nodeList) {
				if (outer.equals(inner)) {
					continue;
				}
				if (distanceMap.get(inner) == null) {
					distanceMap.put(inner, new HashMap<Node, Double>());
				}
				double weight = calculateDistance(outer, inner);
				distanceMap.get(outer).put(inner, weight);
				distanceMap.get(inner).put(outer, weight);
			}
		}
	}

	private boolean attemptHealing(Node node) {
		// if (isBeneficial(node)) {
		int oldIndex = nodeList.indexOf(node);
		double oldLength = calculateLength(nodeList);

		nodeList.remove(node);
		int index = findLeastExpensiveLocation(node);
		nodeList.add(index, node);

		double newLength = calculateLength(nodeList);
		if (oldLength < newLength) {
			nodeList.remove(node);
			nodeList.add(oldIndex, node);
			return false;
		}

		return true;
	}

	private int findLeastExpensiveLocation(Node node) {
		int index = 0;
		double min = Integer.MAX_VALUE;

		for (int ii = 1; ii < nodeList.size() - 1; ii++) {
			double current = distanceMap.get(nodeList.get(ii - 1)).get(node)
					+ distanceMap.get(node).get(nodeList.get(ii));

			if (current < min) {
				index = ii;
				min = current;
			}
		}

		return index;
	}

	private double getInitialGreedySolution(List<Node> nodeList) {
		List<Node> greedySolution = new GreedySolution().calculateSolution(0, 0, nodeList);
		List<Node> greedyManhattanSolution = new GreedyManhattanSolution().calculateSolution(0, 0,
				nodeList);

		double greedySolutionLength = calculateLength(greedySolution);
		double greedyManhattanSolutionLength = calculateLength(greedyManhattanSolution);

		if (greedySolutionLength < greedyManhattanSolutionLength) {
			this.nodeList = greedySolution;
			return greedySolutionLength;
		} else {
			this.nodeList = greedyManhattanSolution;
			return greedyManhattanSolutionLength;
		}
	}

	private double calculateLength(List<Node> nodeList) {
		double total = 0;
		Node current, next;

		current = nodeList.get(0);
		for (int i = 1; i <= nodeList.size() - 1; i++) {
			next = nodeList.get(i);
			total += distanceMap.get(current).get(next);
			current = next;
		}

		return total;
	}

	private double calculateDistance(Node a, Node b) {
		double dx = a.x - b.x;
		double dy = a.y - b.y;

		return Math.sqrt(dx * dx + dy * dy);
	}
}
