package GeneralProgramming;

import Utils.DoubleRandomNames;
import Utils.RandomNames;
import javafx.util.Pair;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
        requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
        requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, 6));
        requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, 2));
        requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, 2));

        RandomNames teamNames = new RandomNames("src/main/resources/locations.json");
        RandomNames playerNames = new DoubleRandomNames("src/main/resources/first_names.json", "src/main/resources/last_names.json");
        System.out.println(Team.createRandomTeamFromPositionRequirements(requirements, teamNames, playerNames));
        Team.createRandomTeamFromPositionRequirements(2, 2, 6, teamNames, playerNames).toFile("");
    }
}
