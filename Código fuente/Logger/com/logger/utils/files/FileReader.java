package com.logger.utils.files;

import java.io.FileNotFoundException;
import java.util.Scanner;
import com.logger.exceptions.ConfigFileNotFoundException;

/**
 *
 * @author hikingcarrot7
 */
public class FileReader extends FileHandler {

    public FileReader(String path) {
        super(path);
    }

    public String readFileContent() {
        String json = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext())
                json += scanner.nextLine();

            return json;
        } catch (FileNotFoundException ex) {
            throw new ConfigFileNotFoundException();
        }
    }

}
