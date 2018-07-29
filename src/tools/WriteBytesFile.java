package tools;

import enums.EncryptionType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface WriteBytesFile {

    default void writeBytesFile(String name, byte[] content, EncryptionType encType, String extension) {

        try {
            FileOutputStream fos = new FileOutputStream(encType.getPath() + name + extension);
            fos.write(content);
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Path to file not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error writing or closing file.");
        }

    }
}
