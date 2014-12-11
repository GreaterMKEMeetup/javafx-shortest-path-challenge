package com.gmjm.challenge1;


import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Controls extends GridPane{
	
	private Slider width;
	private Slider height;
	private Slider nodes;
	
	private Label widthOut;
	private Label heightOut;
	private Label nodesOut;
	
	public  ObservableList<ChallengeSolution> solutions = FXCollections.observableArrayList();
	private  ListView<ChallengeSolution> solutionView = new ListView<ChallengeSolution>();
	
	public Button runSelected = new Button("Run Selected");
	public Button runAll = new Button("Run All");
	public Button showSolution = new Button("Show Solution");
	
	public Button createChallenge = new Button("New Challenge");
	
	
	/**
	 * Create a control panel for creating new challenges, and running solutions.
	 * 
	 * @param maxX
	 * @param maxY
	 */
	public Controls(int maxX, int maxY)
	{
		super();
				
		setPadding(new Insets(10,10,10,10));
		
		setVgap(5);
		
		int line = 0;
		
		Label title = new Label("Controls");
		title.setFont(Font.font(title.getFont().getFamily(), 30));
		
		add(title,0,line);
		line++;
		
		//Width
		add(new Label("Width (px)"),0,line);
		width = new Slider(200, maxX, maxX / 2);
		add(width,1,line);
		widthOut = new Label(String.format("%3d",(int)width.getValue()));
		widthOut.setMinWidth(40);
		add(widthOut,2,line);
		width.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number oldVal, Number newVal) {
				widthOut.setText(String.format("%3d",newVal.intValue()));
			}
		});
		line++;
		
		//Height
		add(new Label("Height (px)"),0,line);
		height = new Slider(200, maxY, maxY / 2);
		add(height,1,line);
		heightOut = new Label(String.format("%3d",(int)height.getValue()));
		heightOut.setMinWidth(40);
		add(heightOut,2,line);
		height.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number oldVal, Number newVal) {
				heightOut.setText(String.format("%3d",newVal.intValue()));
			}
		});
		line++;
		
		//Nodes
		add(new Label("Nodes (px)"),0,line);
		nodes = new Slider(2, 1000, 100);
		add(nodes,1,line);
		nodesOut = new Label(String.format("%3d",(int)nodes.getValue()));
		nodesOut.setMinWidth(40);
		add(nodesOut,2,line);
		nodes.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number oldVal, Number newVal) {
				nodesOut.setText(String.format("%3d",newVal.intValue()));
			}
		});
		line++;
		
		add(createChallenge, 0, line);
		line++;
		
		solutionView.setItems(solutions);
		Label solutionLabel = new Label("Challenge Solutions");
		solutionLabel.setFont(Font.font(solutionLabel.getFont().getFamily(), 20));
		
		add(solutionLabel,1,line);
		line++;
		
		add(solutionView,1,line);
		line++;
		
		HBox buttons = new HBox();
		buttons.getChildren().add(runSelected);
		buttons.getChildren().add(runAll);
		buttons.getChildren().add(showSolution);
		add(buttons,1,line);
		line++;
	}

	public int getChallengeWidth() {
		return (int)width.getValue();
	}

	public int getChallengeHeight() {
		return (int)height.getValue();
	}

	public int getChallengeNodes() {
		return (int)nodes.getValue();
	}
	
	public ChallengeSolution getSelectedSolution()
	{
		return solutionView.getSelectionModel().getSelectedItem();
	}

	public Collection<ChallengeSolution> getAllSolutions() {
		return solutions;
	}
}
