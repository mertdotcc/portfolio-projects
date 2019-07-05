public class SleepActivity extends Activity {

    private SleepType sleepType;

    public enum SleepType {
        Nap, Long_Sleep
    }

    public SleepActivity( String name) {
        super( name);
        sleepType = SleepType.Long_Sleep;
    }
}
