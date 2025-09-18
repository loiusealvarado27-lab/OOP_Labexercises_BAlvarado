/*
Class Hierarchy Diagram (Exer3-B: HealthBuddy)

                   HealthFeature
        /               |               \
  WaterIntake       SleepTracker     ExerciseTracker
                                      /       \
                                 Walking    MedicineReminder
*/

public class ProjectTester {
    public static void main(String[] args) {
        WaterIntake water = new WaterIntake();
        SleepTracker sleep = new SleepTracker();
        Walking walk = new Walking();
        MedicineReminder med = new MedicineReminder();

        water.track();
        sleep.track();
        walk.track();
        med.track();
    }
}
