import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.Stack;

public class QuizBowlScorerUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Gather team names
        Team teamOne = new Team("", 0, 0, new Stack<>(), new Stack<>());
        Team teamTwo = new Team("", 0, 0, new Stack<>(), new Stack<>());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Team One Name: ");
        teamOne.setName(scanner.nextLine());
        System.out.print("Team Two Name: ");
        teamTwo.setName(scanner.nextLine());

        //Score Labels
        Label teamOneScore = new Label("Score: "+Integer.toString(teamOne.getScore()));
        Label teamTwoScore = new Label("Score: "+Integer.toString(teamTwo.getScore()));

        //Toss-Up Count Labels
        Label teamOneTossUpCount = new Label("Toss-Ups Answered: "+Integer.toString(teamOne.getTossUpCount()));
        Label teamTwoTossUpCount = new Label("Toss-Ups Answered: "+Integer.toString(teamTwo.getTossUpCount()));

        //Toss-Up Button
        Button addTossUpOne = new Button("Toss-Up");
        addTossUpOne.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamOne.setScore(10, true);
                    teamOneScore.setText("Score: "+Integer.toString(teamOne.getScore()));
                    teamOneTossUpCount.setText("Toss-Ups Answered: "+Integer.toString(teamOne.getTossUpCount()));
                });
        Button addTossUpTwo = new Button("Toss-Up");
        addTossUpTwo.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamTwo.setScore(10, true);
                    teamTwoScore.setText("Score: "+Integer.toString(teamTwo.getScore()));
                    teamTwoTossUpCount.setText("Toss-Ups Answered: "+Integer.toString(teamTwo.getTossUpCount()));
                });

        //Power Mark Button
        Button addPowerMarkOne = new Button("Power Mark");
        addPowerMarkOne.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamOne.setScore(15, true);
                    teamOneScore.setText("Score: "+Integer.toString(teamOne.getScore()));
                    teamOneTossUpCount.setText("Toss-Ups Answered: "+Integer.toString(teamOne.getTossUpCount()));
                });
        Button addPowerMarkTwo = new Button("Power Mark");
        addPowerMarkTwo.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamTwo.setScore(15, true);
                    teamTwoScore.setText("Score: "+Integer.toString(teamTwo.getScore()));
                    teamTwoTossUpCount.setText("Toss-Ups Answered: "+Integer.toString(teamTwo.getTossUpCount()));
                });

        //Bonus Button
        Button addBonusOne = new Button("Bonus");
        addBonusOne.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamOne.setScore(10, false);
                    teamOneScore.setText("Score: "+Integer.toString(teamOne.getScore()));
                });
        Button addBonusTwo = new Button("Bonus");
        addBonusTwo.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    teamTwo.setScore(10, false);
                    teamTwoScore.setText("Score: "+Integer.toString(teamTwo.getScore()));
                });

        //Undo Button
        Button undoButtonOne = new Button("Undo");
        undoer(teamOne, teamOneScore, teamOneTossUpCount, undoButtonOne);
        Button undoButtonTwo = new Button("Undo");
        undoer(teamTwo, teamTwoScore, teamTwoTossUpCount, undoButtonTwo);

        //Button HBoxes
        HBox buttonRowOneOne = new HBox(10, addTossUpOne, addPowerMarkOne, addBonusOne);
        HBox buttonRowTwoOne = new HBox(10, undoButtonOne);
        HBox buttonRowOneTwo = new HBox(10, addTossUpTwo, addPowerMarkTwo, addBonusTwo);
        HBox buttonRowTwoTwo = new HBox(10, undoButtonTwo);

        //TeamVBoxes
        VBox teamOneVBox = new VBox(10, new Label(teamOne.getName()), teamOneScore, teamOneTossUpCount, buttonRowOneOne, buttonRowTwoOne);
        VBox teamTwoVBox = new VBox(10, new Label(teamTwo.getName()), teamTwoScore, teamTwoTossUpCount, buttonRowOneTwo, buttonRowTwoTwo);

        //Make scene with a BorderPane
        BorderPane layout = new BorderPane(null, new Label("RIT Quiz Bowl"), teamTwoVBox, null, teamOneVBox);
        Scene scene = new Scene(layout);

        //Set the stage
        primaryStage.setTitle("RIT Quiz Bowl Scorer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void undoer(Team team, Label teamScore, Label teamTossUpCount, Button undoButton) {
        undoButton.addEventHandler(javafx.event.ActionEvent.ANY,
                (javafx.event.ActionEvent event ) -> {
                    if (!team.getScoreTracker().isEmpty()) {
                        team.undoScore(team.getScoreTracker().peek());
                    }
                    teamScore.setText("Score: "+Integer.toString(team.getScore()));
                    teamTossUpCount.setText("Toss-Ups Answered: "+Integer.toString(team.getTossUpCount()));
                });
    }
}
