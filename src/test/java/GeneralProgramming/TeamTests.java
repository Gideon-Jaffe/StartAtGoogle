package GeneralProgramming;

import Utils.DoubleRandomNames;
import Utils.RandomNames;
import javafx.util.Pair;
import org.junit.jupiter.api.*;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
    private Team team;

    static final RandomNames playerNames = new DoubleRandomNames("src/main/resources/first_names.json", "src/main/resources/last_names.json");
    static final RandomNames teamNames = new RandomNames("src/main/resources/locations.json");

    @Nested
    @DisplayName("When Static Factory Method by ints")
    class InitFromStaticFactoryMethodWithInts {
        @BeforeEach
        void setup() {
            team = Team.createRandomTeamFromPositionRequirements(2, 2, 2, teamNames, playerNames);
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_DefaultTeam_InitializedCorrectly() {
            //Team size == 11
            assertEquals(team.getPlayers().size(), 11, "Default team size not 11");
            //Only one goal keeper
            assertEquals(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.GOAL_KEEPER)).count(), 1, "Default goalie count not 1");
            //amount of attackers >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.ATTACKER)).count() >= 2, "Default attacker count less than 2");
            //amount of midfielders >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.MIDFIELDER)).count() >= 2, "Default mid fielder count less than 2");
            //amount of defenders >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.DEFENDER)).count() >= 2, "Default defender count less than 2");
            //team name != null or empty
            assertNotNull(team.getName(), "Team name is null");
            assertNotEquals(team.getName(), "", "Team name empty");
        }
    }

    @Nested
    @DisplayName("When Static Factory Method by array")
    class InitFromStaticFactoryMethodWithArray {
        @BeforeEach
        void setup() {

            List<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
            requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
            requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, 2));
            requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, 2));
            requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, 2));
            team = Team.createRandomTeamFromPositionRequirements(requirements, teamNames, playerNames);
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_DefaultTeam_InitializedCorrectly() {
            //Team size == 11
            assertEquals(team.getPlayers().size(), 11, "Default team size not 11");
            //Only one goal keeper
            assertEquals(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.GOAL_KEEPER)).count(), 1, "Default goalie count not 1");
            //amount of attackers >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.ATTACKER)).count() >= 2, "Default attacker count less than 2");
            //amount of midfielders >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.MIDFIELDER)).count() >= 2, "Default mid fielder count less than 2");
            //amount of defenders >= 2
            assertTrue(team.getPlayers().stream().filter(footBallPlayer -> footBallPlayer.getPosition().equals(FootBallPlayer.Position.DEFENDER)).count() >= 2, "Default defender count less than 2");
            //team name != null or empty
            assertNotNull(team.getName(), "Team name is null");
            assertNotEquals(team.getName(), "", "Team name empty");
        }
    }

    @Nested
    @DisplayName("Team creation with invalid parameters")
    class CreationWithInvalidParameters {

        @Test
        void createRandomTeamFromIntPositionRequirements_12Requirements_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(4, 4, 3, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_NoAttackers_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(4, 4, 0, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_OneAttackers_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(4, 4, 1, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_NoDefenders_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(0, 4, 3, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_OneDefenders_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(1, 4, 3, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_NoMidFielders_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(4, 0, 3, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromIntPositionRequirements_OneMidFielders_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(4, 1, 3, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromArrayPositionRequirements_12Requirements_ThrowsError() {
            List<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
            requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 1));
            requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, 4));
            requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, 4));
            requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, 3));

            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(requirements, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromArrayPositionRequirements_NoGoalie_ThrowsError() {
            List<Pair<FootBallPlayer.Position, Integer>> requirements = new ArrayList<>();
            requirements.add(new Pair<>(FootBallPlayer.Position.GOAL_KEEPER, 0));
            requirements.add(new Pair<>(FootBallPlayer.Position.ATTACKER, 4));
            requirements.add(new Pair<>(FootBallPlayer.Position.MIDFIELDER, 4));
            requirements.add(new Pair<>(FootBallPlayer.Position.DEFENDER, 3));

            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(requirements, teamNames, playerNames), "Expected runtime error");
        }

        @Test
        void createRandomTeamFromArrayPositionRequirements_NullArray_ThrowsError() {
            assertThrows(InvalidParameterException.class, () -> Team.createRandomTeamFromPositionRequirements(null, teamNames, playerNames), "Expected runtime error");
        }
    }

    @Test
    void toFile_NoPath_fileCreated() {
        team = Team.createRandomTeamFromPositionRequirements(2, 2, 2, teamNames, playerNames);

        team.toFile("");

        assertTrue(new File("team.txt").exists());
    }
}
