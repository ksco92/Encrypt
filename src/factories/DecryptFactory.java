package factories;

import enums.EncryptionType;
import enums.MessageType;
import tools.KeyReader;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Base64;

public class DecryptFactory implements KeyReader {

    public String decryptMessage(String messageName, String keyName, EncryptionType encType) throws Exception {

        Cipher cipher = Cipher.getInstance(encType.getEncSpec());
        byte[] encryptedMessage = readMessageFile(messageName, encType);
        byte[] decryptedData;
        String message = "";

        switch (encType) {
            case AES:
            case DES:
                byte[] key = (byte[]) readKeyFile(keyName, encType, MessageType.NONE);
                SecretKeySpec k = new SecretKeySpec(key, encType.getEncSpec());
                cipher.init(Cipher.DECRYPT_MODE, k);
                decryptedData = cipher.doFinal(encryptedMessage);
                message = new String(decryptedData, StandardCharsets.UTF_8);
                break;

            case RSA:
                PrivateKey privKey = (PrivateKey) readKeyFile(keyName, encType, MessageType.PRIVATE);
                cipher.init(Cipher.DECRYPT_MODE, privKey);
                decryptedData = cipher.doFinal(encryptedMessage);
                message = new String(decryptedData, StandardCharsets.UTF_8);
                break;
        }

        return message;

    }

    private byte[] readMessageFile(String messageName, EncryptionType encType) {

        try {
            File file = new File(encType.getPath() + messageName + encType.getMessageEncryptExtension());
            int length = (int) file.length();
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[length];
            reader.read(bytes, 0, length);
            Base64.Decoder oneDecoder = Base64.getDecoder();
            reader.close();
            return oneDecoder.decode(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Message not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or closing file", e);
        }

    }

}
