package factories;

import enums.EncryptionType;
import enums.MessageType;
import tools.KeyReader;
import tools.WriteBytesFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Base64;

public class EncryptFactory implements KeyReader, WriteBytesFile {

    public void generateEncryptedMessage(String messageName, String message, String keyName, EncryptionType encType, MessageType messageType) throws Exception {

        Cipher cipher = Cipher.getInstance(encType.getEncSpec());

        switch (encType) {
            case AES:
            case DES:
                byte[] key = (byte[]) readKeyFile(keyName, encType, messageType);
                SecretKeySpec k = new SecretKeySpec(key, encType.getEncSpec());
                cipher.init(Cipher.ENCRYPT_MODE, k);
                break;

            case RSA:
                PublicKey pubKey = (PublicKey) readKeyFile(keyName, encType, messageType);
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                break;

        }

        byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        Base64.Encoder oneEncoder = Base64.getEncoder();
        encryptedData = oneEncoder.encode(encryptedData);
        writeBytesFile(messageName, encryptedData, encType, encType.getMessageEncryptExtension());

    }
}
