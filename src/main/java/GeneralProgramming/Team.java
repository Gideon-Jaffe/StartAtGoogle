package GeneralProgramming;

import Utils.FileHandling;
import Utils.RandomNames;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Team {
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

    public static Team createRandomTeamWithTeamSizeAndPositionRequirements(int teamSize, List<Pair<FootBallPlayer.Position, Integer>> requirements, List<FootBallPlayer.Position> restOfTeamInPositions) {
        checkAndFixTeamRequirements(teamSize, requirements);
        RandomNames firstNames = new RandomNames("src/main/resources/first_names.json");
        Team currentTeam = new Team(firstNames.getRandomNameFromJsonFile());
        currentTeam.players = FootBallPlayer.createRandomTeam(teamSize, requirements, restOfTeamInPositions);
        return currentTeam;
    }

    public static Team createRandomTeamWithPositionRequirements(List<Pair<FootBallPlayer.Position, Integer>> requirements) {
        int teamSize = 0;
        for (Pair<FootBallPlayer.Position, Integer> req : requirements) {
            teamSize += req.getValue();
        }
        return createRandomTeamWithTeamSizeAndPositionRequirements(teamSize, requirements, null);
    }

    public static Team createRandomTeamWithPositionRequirements(int Defence, int MiddleField, int Attack) {
        List<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
        requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
        requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, Attack));
        requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, MiddleField));
        requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, Defence));
        return createRandomTeamWithPositionRequirements(requirements);
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
        try(Writer writer = new FileWriter(path + "team.txt")) {
            writer.write(this.toString());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file\n" + e);
        }
    }

    private static void checkAndFixTeamRequirements(int teamSize, List<Pair<FootBallPlayer.Position, Integer>> requirements) {
        List<FootBallPlayer.Position> values = new ArrayList<>(List.of(FootBallPlayer.Position.values()));
        for (Pair<FootBallPlayer.Position, Integer> req : requirements) {
            checkPositionRequirements(req);
            if (values.contains(req.getKey()) && teamSize > 0) {
                values.remove(req.getKey());
                teamSize -= req.getValue();
            } else {
                throw new RuntimeException("Team Requirements not legal");
            }
        }

        if (values.size() > 0) throw new RuntimeException("All positions not specified");
        if (teamSize < 0) throw new RuntimeException("Not enough players for requirements");
    }

    private static void checkPositionRequirements(Pair<FootBallPlayer.Position, Integer> positionRequirements) {
        int min = (positionRequirements.getKey() == FootBallPlayer.Position.GOAL_KEEPER) ? 1 : 2;
        if (min > positionRequirements.getValue()) {
            throw new RuntimeException("Team Requirements not legal");
        }
    }
}
