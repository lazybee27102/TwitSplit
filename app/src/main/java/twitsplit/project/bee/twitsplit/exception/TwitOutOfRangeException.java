package twitsplit.project.bee.twitsplit.exception;

public class TwitOutOfRangeException extends Exception {
    public TwitOutOfRangeException() {
        super("Each twit can only contain maximum 50 characters");
    }
}
