package GeneralProgramming;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //FootBallPlayer.test();
        ArrayList<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
        requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
        requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, 5));
        requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, 2));
        requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, 2));
        System.out.println(Team.createRandomTeamWithTeamSize(11, requirements, List.of(FootBallPlayer.Position.values())));

        System.out.println(Team.createRandomTeam(requirements));
        Team.createRandomTeam(2, 2, 5).toFile("");
    }
}
