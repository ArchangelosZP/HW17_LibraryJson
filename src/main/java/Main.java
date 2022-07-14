import javax.xml.transform.TransformerConfigurationException;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibrarySerializer librarySerializer = new LibrarySerializer();
        Manager manager = new Manager();
        manager = librarySerializer.deserialize(manager);

        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = new UserInterface(manager, scanner);

        String command;
        do {
            userInterface.start();
            command = scanner.nextLine();
            switch (command) {
                case Commands.ADD:
                    userInterface.add();
                    break;
                case Commands.DELETE:
                    userInterface.del();
                    break;
                case Commands.VIEW:
                    userInterface.view();
                    break;
                case Commands.FIND:
                    userInterface.find();
                    break;
                default:
                    if (!command.equalsIgnoreCase(Commands.EXIT)) System.out.println("Command does not exist.");
            }
        } while (!command.equalsIgnoreCase(Commands.EXIT));

        librarySerializer.serialize(manager);

    }
}

