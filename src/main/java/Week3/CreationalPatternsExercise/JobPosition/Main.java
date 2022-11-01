package Week3.CreationalPatternsExercise.JobPosition;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        JobPosition position = new JobPosition.Builder(LocalDate.now(), "google", false).build();
        System.out.println(position);

        JobPosition newPosition = new JobPosition.Builder(LocalDate.now().minusDays(5), "awesome Job", true)
                .description("The Most awesome job ever. and it pays well")
                .location("wherever you want").salaryCap(1000000)
                .yearsOfExperience(5).build();
        System.out.println(newPosition);
    }
}
