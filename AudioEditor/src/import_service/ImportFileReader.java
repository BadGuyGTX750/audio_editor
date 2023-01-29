package import_service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utility.Logger.logger;

public class ImportFileReader {
    public ImportFileReader() {
        logger(4, "ImportFileReader is being initialized");
    }
    public byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] arr = Files.readAllBytes(path);
        return arr;
    }
}
