import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class MTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    public Manager manager;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void clearStTest() {
        Manager manager = new Manager();
        //LibrarySerializer librarySerializer = new LibrarySerializer();
        //manager = librarySerializer.deserialize(manager);

        manager.add("Gogol","Taras Bulba");
        if (manager.find("Taras")) {
            Stream<String> stream = manager.booksToScreen.stream();
            stream.forEach(System.out::println);
        } else {
            System.out.println("Sorry, nothing found");
        }
        Assert.assertEquals("id:0, author: Gogol, title: Taras Bulba\r\n", output.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

}
