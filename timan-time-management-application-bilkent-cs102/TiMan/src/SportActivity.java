public class SportActivity extends Activity {

    private SportType sportType;

    public enum SportType {
        Football, Basketball, Tennis, Swimming, Golf, Gym, Running, Athletism, Other
    }

    public SportActivity( String name) {
        super(name);
        sportType = SportType.Other;
    }
}
