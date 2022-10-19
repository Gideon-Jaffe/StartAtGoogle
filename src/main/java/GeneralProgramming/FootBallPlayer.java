package GeneralProgramming;

import Utils.RandomNames;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FootBallPlayer {
    private String name;
    private float grade;

    private Position position;

    private int jerseyNumber;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public FootBallPlayer(String name, float grade, Position position, int jerseyNumber) {
        this.name = name;
        this.grade = grade;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
    }

    public FootBallPlayer() {
        this.name = "";
        this.grade = 0.0f;
        this.position = Position.GOAL_KEEPER;
        this.jerseyNumber = 0;
    }

    @Override
    public String toString() {
        return name + " No: " + jerseyNumber + " Grade: " + grade + " Position: " + position;
    }

    public static FootBallPlayer randomFootBallPlayerWithPosition(Position position, List<Integer> takenJerseyNumbers) {
        FootBallPlayer randomPlayer = new FootBallPlayer();
        RandomNames names = new RandomNames("src/main/resources/first_names.json");
        randomPlayer.name = names.getRandomName();
        randomPlayer.grade = ThreadLocalRandom.current().nextFloat() * 100;
        randomPlayer.jerseyNumber = randomAllowedJerseyNumber(takenJerseyNumbers);

        if (position == null) {
            randomPlayer.position = getRandomPosition();
        } else {
            randomPlayer.position = position;
        }
        return randomPlayer;
    }

    public static FootBallPlayer randomFootBallPlayerWithJerseyNumber(int jerseyNumber, List<Integer> takenJerseyNumbers) {
        if (takenJerseyNumbers != null && takenJerseyNumbers.contains(jerseyNumber))
            throw new RuntimeException("Jersey number Taken!");
        FootBallPlayer player = randomFootBallPlayer();
        player.jerseyNumber = jerseyNumber;
        return player;
    }

    public static FootBallPlayer randomFootBallPlayer(List<Position> possiblePositions, List<Integer> takenJerseyNumbers) {
        return randomFootBallPlayerWithPosition(possiblePositions.get(ThreadLocalRandom.current().nextInt(possiblePositions.size())), takenJerseyNumbers);
    }

    public static FootBallPlayer createRandomGoalkeeper() {
        return randomFootBallPlayerWithPosition(Position.GOAL_KEEPER, null);
    }

    public static FootBallPlayer randomFootBallPlayer() {
        return randomFootBallPlayer(List.of(Position.values()), null);
    }

    private static Integer randomAllowedJerseyNumber(List<Integer> takenJerseyNumbers) {
        if (takenJerseyNumbers == null || takenJerseyNumbers.size() == 0)
            return ThreadLocalRandom.current().nextInt(100);

        int randomNumber = ThreadLocalRandom.current().nextInt(100);
        int tries = 10;
        while (takenJerseyNumbers.contains(randomNumber) && tries > 0) {
            randomNumber = ThreadLocalRandom.current().nextInt(100);
            tries--;
        }
        if (takenJerseyNumbers.contains(randomNumber)) {
            for (int i = 0; i < 100; i++) {
                if (!takenJerseyNumbers.contains(i)) {
                    return i;
                }
            }
        }

        return randomNumber;
    }

    public static ArrayList<FootBallPlayer> createRandomTeam(int teamSize, List<Pair<Position, Integer>> requirements, List<Position> restOfTeamInPositions) {
        List<Integer> takenJerseyNumbers = new ArrayList<>();
        ArrayList<FootBallPlayer> team = new ArrayList<>();
        if (requirements != null) {
            for (Pair<Position, Integer> amountPerPosition : requirements) {
                for (int i = 0; i < amountPerPosition.getValue(); i++) {
                    FootBallPlayer randomPlayer = randomFootBallPlayerWithPosition(amountPerPosition.getKey(), takenJerseyNumbers);
                    team.add(randomPlayer);
                    takenJerseyNumbers.add(randomPlayer.jerseyNumber);
                    teamSize--;
                }
            }
        }

        for (int i = 0; i < teamSize; i++) {
            FootBallPlayer randomPlayer = randomFootBallPlayer(restOfTeamInPositions, takenJerseyNumbers);
            team.add(randomPlayer);
            takenJerseyNumbers.add(randomPlayer.getJerseyNumber());
        }
        return team;
    }

    public enum Position {
        GOAL_KEEPER,
        DEFENDER,
        MIDFIELDER,
        ATTACKER,
    }

    public static Position getRandomPosition() {
        Position[] positions = Position.values();
        return positions[ThreadLocalRandom.current().nextInt(1, positions.length)];
    }

    public static void test() {
        ArrayList<Pair<Position, Integer>> requirements = new ArrayList<>();
        requirements.add(new Pair<>(Position.GOAL_KEEPER, 1));
        requirements.add(new Pair<>(Position.ATTACKER, 2));
        requirements.add(new Pair<>(Position.MIDFIELDER, 2));
        requirements.add(new Pair<>(Position.DEFENDER, 2));
        ArrayList<FootBallPlayer> randomTeam = createRandomTeam(11, requirements, List.of(new Position[]{Position.ATTACKER, Position.MIDFIELDER, Position.DEFENDER}));
        for (FootBallPlayer player : randomTeam) {
            System.out.println(player);
        }
    }
}
