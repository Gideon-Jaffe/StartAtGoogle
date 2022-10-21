package GeneralProgramming;

import Utils.FileHandling;
import Utils.RandomNames;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Team {

    private static final int TEAM_SIZE = 11;

    String name;
    List<FootBallPlayer> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FootBallPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<FootBallPlayer> players) {
        this.players = players;
    }

    public Team(String name) {
        this.name = name;
        this.players = null;
    }

    private static ArrayList<FootBallPlayer> createRandomTeamPlayers(List<Pair<FootBallPlayer.Position, Integer>> requirements, RandomNames nameGenerator) {
        List<Integer> takenJerseyNumbers = new ArrayList<>();
        ArrayList<FootBallPlayer> team = new ArrayList<>();
        int playersLeft = createPlayersFromRequirements(requirements, nameGenerator, takenJerseyNumbers, team);
        CreateRandomPlayersNoGoalie(nameGenerator, takenJerseyNumbers, team, playersLeft);
        return team;
    }

    private static void CreateRandomPlayersNoGoalie(RandomNames nameGenerator, List<Integer> takenJerseyNumbers, ArrayList<FootBallPlayer> team, int teamSize) {
        List<FootBallPlayer.Position> NonGoaliePositions = List.of(FootBallPlayer.Position.DEFENDER, FootBallPlayer.Position.MIDFIELDER, FootBallPlayer.Position.ATTACKER);
        for (int i = 0; i < teamSize; i++) {
            FootBallPlayer randomPlayer = FootBallPlayer.randomFootBallPlayer(NonGoaliePositions, takenJerseyNumbers, nameGenerator);
            team.add(randomPlayer);
            takenJerseyNumbers.add(randomPlayer.getJerseyNumber());
        }
    }

    private static int createPlayersFromRequirements(List<Pair<FootBallPlayer.Position, Integer>> requirements, RandomNames nameGenerator, List<Integer> takenJerseyNumbers, ArrayList<FootBallPlayer> team) {
        int playersLeft = TEAM_SIZE;
        if (requirements != null) {
            for (Pair<FootBallPlayer.Position, Integer> amountPerPosition : requirements) {
                for (int i = 0; i < amountPerPosition.getValue(); i++) {
                    FootBallPlayer randomPlayer = FootBallPlayer.randomFootBallPlayerWithPosition(amountPerPosition.getKey(), takenJerseyNumbers, nameGenerator);
                    team.add(randomPlayer);
                    takenJerseyNumbers.add(randomPlayer.getJerseyNumber());
                    playersLeft--;
                }
            }
        }
        return playersLeft;
    }

    public static Team createRandomTeamFromPositionRequirements(List<Pair<FootBallPlayer.Position, Integer>> requirements, RandomNames teamNamesGenerator, RandomNames playerNamesGenerator) {
        checkAndFixTeamRequirements(requirements);
        Team currentTeam = new Team(teamNamesGenerator.getRandomNameFromJsonFile());
        currentTeam.players = createRandomTeamPlayers(requirements, playerNamesGenerator);
        return currentTeam;
    }

    public static Team createRandomTeamFromPositionRequirements(int Defence, int MiddleField, int Attack, RandomNames teamNamesGenerator, RandomNames playerNamesGenerator) {
        List<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
        requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
        requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, Attack));
        requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, MiddleField));
        requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, Defence));
        return createRandomTeamFromPositionRequirements(requirements, teamNamesGenerator, playerNamesGenerator);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("Team Name: " + this.name + " Total Players: " + this.players.size());
        for (FootBallPlayer player : players) {
            returnString.append("\n").append(player.toString());
        }
        return returnString.toString();
    }

    public void toFile(String path) {
        if (!FileHandling.tryCreateFile(path + "team.txt")) throw new RuntimeException("can't create file");
        try (Writer writer = new FileWriter(path + "team.txt")) {
            writer.write(this.toString());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file\n" + e);
        }
    }

    private static void checkAndFixTeamRequirements(List<Pair<FootBallPlayer.Position, Integer>> requirements) {
        int teamSize = TEAM_SIZE;
        List<FootBallPlayer.Position> values = new ArrayList<>(List.of(FootBallPlayer.Position.values()));
        for (Pair<FootBallPlayer.Position, Integer> req : requirements) {
            checkPositionRequirements(req);
            if (values.contains(req.getKey()) && teamSize - req.getValue() >= 0) {
                values.remove(req.getKey());
                teamSize -= req.getValue();
            } else {
                throw new RuntimeException("Team Requirements not legal");
            }
        }

        if (values.size() > 0) throw new RuntimeException("All positions not specified");
    }

    private static void checkPositionRequirements(Pair<FootBallPlayer.Position, Integer> positionRequirements) {
        int min = (positionRequirements.getKey() == FootBallPlayer.Position.GOAL_KEEPER) ? 1 : 2;
        if (min > positionRequirements.getValue()) {
            throw new RuntimeException("Team Requirements not legal");
        }
    }
}
