javafx-shortest-path-challenge
==============================

Shortest Path Challenge

The application will generate a grid of pixels.  Within the grid, there will be a set of Nodes.
A Node represents a pixel on the grid.  A Node is uniquely identified by its nodeNumber, and its place in the grid is represented by an (x,y) coordinate.

Your challenge is to find the shortest path between all the nodes.

All nodes must be visited AT LEAST once.  Some nodes may lie on the same coordinate.

To create a solution, implement the abstract class com.gmjm.challenge1.ChallengeSolution.  Put your solution in the com.gmjm.challenge1.solutions directory.  The app will find your solution when it loads using reflection, and should appear in the solution list.

Your solution will be in the form of a list of Node objects.  The order of the list represents the order the nodes will be traversed.

NOTE:
===
The solution analizer traverses the nodes from first to last.  If two nodes lie on the same coordinate, you still need to include both in the list to get credit for visiting both of them.

HOW TO RUN:
===

Requires Java 8 due to JavaFX GUI

Build using maven (JAVA_HOME must be JAVA 8 JDK):
mvn clean package

Run the built(./target/challenge1.jar).
