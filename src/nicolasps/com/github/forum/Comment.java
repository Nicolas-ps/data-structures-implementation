package nicolasps.com.github.forum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private static int idCounter = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final int id;
    private String author;
    private final LocalDateTime date;
    private String text;

    public Comment(String author, String text) {
        this.id = idCounter++;
        this.author = author;
        this.date = LocalDateTime.now();
        this.text = text;
    }

    public int getId() { return id; }
    public String getAuthor() { return author; }
    public LocalDateTime getDate() { return date; }
    public String getText() { return text; }

    public void setAuthor(String author) { this.author = author; }
    public void setText(String text) { this.text = text; }

    public String toShortString() {
        String preview = text.length() > 40 ? text.substring(0, 37) + "..." : text;
        return "[#" + id + "] " + author + ": " + preview;
    }

    @Override
    public String toString() {
        return "[#" + id + "] " + author + " (" + date.format(FORMATTER) + "): " + text;
    }
}
