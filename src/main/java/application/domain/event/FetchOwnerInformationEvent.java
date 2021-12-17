package application.domain.event;

public class FetchUserDataEvent extends AbstractEvent<String> {

    public FetchUserDataEvent(String userId, String payload) {
        super(payload);
    }
}
