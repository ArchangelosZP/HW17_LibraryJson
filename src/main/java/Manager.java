import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Manager implements Serializable {
    Integer maxID;

    public static class Card implements Serializable {
        public String author;
        public String title;
    }

    public Map<Integer, Card> books = new HashMap<>();
    public List<String> booksToScreen = new ArrayList<>();

    public boolean add(String author, String title) {
        boolean noRepeat = books.entrySet().stream().
                noneMatch((s) ->
                        s.getValue().title.equalsIgnoreCase(title) && s.getValue().author.equalsIgnoreCase(author)
                );
        if (noRepeat) {
            Card card = new Card();
            card.author = author;
            card.title = title;
            if (maxID == null) {maxID=0;}
            books.put(maxID+1, card);
            maxID++;
        }
        return noRepeat;
    }

    public boolean del(Integer id) {
        boolean result = books.remove(id) != null;
        return result;
    }

    public boolean find(String find) {
        booksToScreen.clear();
        booksToScreen = books.entrySet().stream().
                filter(s -> s.getValue().author.toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT)) ||
                        s.getValue().title.toLowerCase(Locale.ROOT).contains(find.toLowerCase(Locale.ROOT))).
                map(s -> String.format("id:%s, author: %s, title: %s", s.getKey(), s.getValue().author, s.getValue().title)).
                collect(Collectors.toList());
        return !booksToScreen.isEmpty();
    }

    public String findID(Integer id) {
        String result = null;
        if (books.get(id) != null) {
            result = String.format("id:%s, author: %s, title: %s", id, books.get(id).author, books.get(id).title);
        }
        return result;
    }

    public boolean view() {
        boolean result = !books.isEmpty();
        booksToScreen.clear();
        if (result) {
            booksToScreen = books.entrySet().stream().
                    map(s -> String.format("id:%s, author: %s, title: %s", s.getKey(), s.getValue().author, s.getValue().title)).
                    collect(Collectors.toList());
        }
        return result;
    }
}