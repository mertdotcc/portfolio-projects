public class WorkActivity extends Activity {
    private WorkType workType;

    public enum WorkType {
        Shift, Study, Homework, Other
    }

    public WorkActivity( String name) {
        super(name);
        workType = WorkType.Other;
    }
}
