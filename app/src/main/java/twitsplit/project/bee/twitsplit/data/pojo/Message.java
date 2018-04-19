package twitsplit.project.bee.twitsplit.data.pojo;

public class Message {
    private final String content;

    public String getContent() {
        return content;
    }

    public Message(String content) {
        this.content = content;
    }

    public static Message valueOf(String content) {
        Message message = new Message(content);
        return message;
    }
}
