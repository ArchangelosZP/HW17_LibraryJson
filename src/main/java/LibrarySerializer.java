import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;

public class LibrarySerializer {

    private final File path;


    public LibrarySerializer() {
        path = new File("src/main/resources/library.json");
    }

    public LibrarySerializer(File path) {
        this.path = path;
    }


    public void serialize(Manager libraryManager){
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.writeValue(path, libraryManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Manager deserialize(Manager lManager){
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            lManager = jsonMapper.readValue(path, Manager.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lManager;
    }

}