package com.gmjm.challenge1;

import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.reflections.Reflections;

public class Main extends Application {
	
	//Canvas to draw challenges and solutions
	Canvas mainCanvas;
	
	//Control object
	Controls controls;
	
	//The current challenge
	Challenge currentChallenge;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Prototype Example");
		
		GridPane rootGrid = new GridPane();
		rootGrid.setPadding(new Insets(10,10,10,10));
		rootGrid.setVgap(20);
		rootGrid.setHgap(20);
		
		int maxX = 800;
		int maxY = 600;
		
		mainCanvas = new Canvas(maxX,maxY);
		
		controls = new Controls(maxX,maxY);
				
		rootGrid.add(controls, 0, 0);
		
		GridPane mainGrid = new GridPane();
		
		mainGrid.add(new Label("Main"),0,0);
		mainGrid.add(mainCanvas,0,1);
		
		rootGrid.add(mainGrid,1,0);
			
		
		Group root = new Group();
		
		root.getChildren().add(rootGrid);
		
		setupButtonActions();
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		clearCanvas();
		
		createChallenge();
		
		addAllSolutions();
		
	}

	

	/**
	 * Use reflection to find all solutions in the solutions package.  Add them to the solutions list.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void addAllSolutions() throws InstantiationException, IllegalAccessException {
		Reflections reflections = new Reflections("com.gmjm.challenge1.solutions");
		Set<Class<? extends ChallengeSolution>> allSolutions = reflections.getSubTypesOf(ChallengeSolution.class);
		
		for(Class<? extends ChallengeSolution> solutionClass : allSolutions)
		{
			controls.solutions.add(solutionClass.newInstance());
		}
	}

	/**
	 * Add actions to the control buttons
	 */
	private void setupButtonActions() {
		//Run the selected solution.
		controls.runSelected.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ChallengeSolution solution = controls.getSelectedSolution();
				SolutionAnalyzer.analyzeSolution(currentChallenge, solution);
			}
		});
		
		//Run all the solutions
		controls.runAll.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(controls.getAllSolutions() == null)
					return;
				
				for(ChallengeSolution solution : controls.getAllSolutions())
				{
					SolutionAnalyzer.analyzeSolution(currentChallenge, solution);
				}
			}
		});
		
		//Create a new challenge
		controls.createChallenge.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				createChallenge();
			}
		});
		
		//Draw the selected solution on the canvas.
		controls.showSolution.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ChallengeSolution solution = controls.getSelectedSolution();
				if(solution != null)
				{
					if(solution.solutionAnlaysis == null)
					{
						System.out.println("No solution exists for: " + solution.getSolutionName());
					}
					else
					{
						ChallengeSolution.drawSolution(solution,Color.BLUE, drawNewChallenge());
						System.out.println(solution.solutionAnlaysis.toString());						
					}
				}
			}
		});
	}

	
	/**
	 * Clear the canvas, and return the graphics context.
	 * @return
	 */
	public GraphicsContext clearCanvas()
	{
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
		return gc;
	}
	
	/**
	 * Draw the new challenge, and return the graphics context.
	 * @return
	 */
	public GraphicsContext drawNewChallenge()
	{
		GraphicsContext gc = clearCanvas();
		currentChallenge.draw(gc);
		return gc;
	}
	
	/**
	 * Create a new challenge.
	 */
	public void createChallenge()
	{
		int width = controls.getChallengeWidth();
		int height = controls.getChallengeHeight();
		int nodes = controls.getChallengeNodes();
		currentChallenge = new Challenge(width,height,nodes);
		drawNewChallenge();
	}
	
}
