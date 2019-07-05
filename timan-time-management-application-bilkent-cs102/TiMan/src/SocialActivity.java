public class SocialActivity extends Activity {

    private SocialType socialType;

    public enum SocialType {
        Social_Media,
        Meeting_With_Friends,
        Party,
        Cinema,
        Concert,
        Other
    }
    public SocialActivity( String name) {
        super(name);
        socialType =SocialType.Other;
    }


}
