package me.godlycoder.rps;

import me.godlycoder.rps.annotations.Author;
import me.godlycoder.rps.moves.Moves;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Author(name = "Godly Coder", dateCreated = "5-26-2021", dateUpdated = "5-27-2021")
public class RockPaperScissors {
    public static void main(String[] args) {
        System.out.println("Rock Paper Scissors - By: Godly Coder");
        System.out.println("Choose a move: Rock Paper Scissors");
        RockPaperScissors rockPaperScissors = new RockPaperScissors();
        while (rockPaperScissors.points[0] < 3 && rockPaperScissors.points[1] < 3) {
            rockPaperScissors.play();
        }
        System.out.println();
        System.out.println(rockPaperScissors.points[0] > rockPaperScissors.points[1] ? "Final Winner: Person Wins" : "Final Winner: AI Wins");
    }

    String playerChoice;
    String aiChoice;
    int rounds;
    int[] points = new int[2];

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
        final Class<? extends Moves> AI_MOVE = aiMove();
        final Class<? extends  Moves> PLAYER_MOVE = playerMove();

        try {
            if (AI_MOVE == PLAYER_MOVE) {
                System.out.println("Winner: Tie");
            }
            else if (PLAYER_MOVE.getDeclaredConstructor().newInstance().beats(AI_MOVE)) {
                points[0] ++;
                System.out.println("Winner: Player");
            }
            else if (AI_MOVE.getDeclaredConstructor().newInstance().beats(PLAYER_MOVE)) {
                points[1] ++;
                System.out.println("Winner: AI");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        rounds++;
        System.out.printf("\nPerson's Choice: %s", playerChoice);
        System.out.printf("\nAI's Choice: %s", aiChoice);
        System.out.printf("\nRounds: %d", rounds);
        System.out.printf("\nPoints: Person: %d\tAI: %d", points[0], points[1]);
    }

}
