package me.godlycoder.rps;

import me.godlycoder.rps.annotations.Author;
import me.godlycoder.rps.moves.Moves;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Author(name = "Godly Coder", date = "5-26-2021")
public class RockPaperScissors {
    public static void main(String[] args) {
        RockPaperScissors rockPaperScissors = new RockPaperScissors();

        rockPaperScissors.play();
    }

    String playerChoice;
    String aiChoice;

    public Class<? extends Moves> playerMove() {
        Scanner scanner = new Scanner(System.in);
        playerChoice = scanner.nextLine().toLowerCase();
        switch (playerChoice) {
            case "rock":
                return Moves.Rock.class;

            case "paper":
                return Moves.Paper.class;

            case "scissors":
                return Moves.Scissors.class;

        }
        return playerMove();
    }

    public Class<? extends Moves> aiMove() {
        List<String> moves = Arrays.asList("rock", "paper", "scissors");

        switch (moves.get(new Random().nextInt(moves.size()))) {
            case "rock":
                aiChoice = "rock";
                return Moves.Rock.class;
            case "paper":
                aiChoice = "paper";
                return Moves.Paper.class;
            case "scissors":
                aiChoice = "scissors";
                return Moves.Scissors.class;
        }
        return aiMove();
    }

    public void play() {
        System.out.println("Rock Paper Scissors - By: Godly Coder");
        System.out.println("Choose a move: Rock Paper Scissors");
        try {
            if (aiMove() == playerMove()) {
                System.out.println("Winner: Tie");
            }
            else if (playerMove().getDeclaredConstructor().newInstance().beats(aiMove())) {
                System.out.println("Winner: Player");
            }
            else if (aiMove().getDeclaredConstructor().newInstance().beats(playerMove())) {
                System.out.println("Winner: AI");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("Person's Choice: " + playerChoice);
        System.out.println("AI's Choice: " + aiChoice);
    }

}
