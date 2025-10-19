package lab1;

public class Message {
    private final String author;
    private final String text;
    private final int id;

    public Message(String author, String text, int id) {
        this.author = author;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{id=" + id + ", from='" + author + "', text='" + text + "'}";
    }
}
