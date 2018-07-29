package tools;

import enums.EncryptionType;
import enums.MessageType;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public interface KeyReader {

    default Object readKeyFile(String keyName, EncryptionType encType, MessageType messageType) {
        switch (encType) {
            case AES:
            case DES:
                try {
                    BufferedReader br = new BufferedReader(new FileReader(encType.getPath() + keyName + encType.getKeyExtension()));
                    String everything = "";
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        line = br.readLine();
                    }

                    everything = sb.toString();

                    br.close();

                    return everything.getBytes(StandardCharsets.UTF_8);

                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Key file not found.", e);
                } catch (IOException e) {
                    throw new RuntimeException("Error reading or closing key file", e);
                }


            case RSA:

                try {
                    InputStream in = new FileInputStream(encType.getPath() + keyName + messageType.toString() + encType.getKeyExtension());
                    ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));

                    BigInteger m = (BigInteger) oin.readObject();
                    BigInteger e = (BigInteger) oin.readObject();
                    if (messageType.equals(MessageType.PUBLIC)) {
                        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
                        KeyFactory fact = KeyFactory.getInstance(encType.getEncSpec());
                        PublicKey pubKey = fact.generatePublic(keySpec);
                        oin.close();
                        return pubKey;
                    } else {
                        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
                        KeyFactory fact = KeyFactory.getInstance(encType.getEncSpec());
                        PrivateKey pubKey = fact.generatePrivate(keySpec);
                        oin.close();
                        return pubKey;
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Key file not found.", e);
                } catch (IOException e) {
                    throw new RuntimeException("Error reading or closing key file", e);
                } catch (Exception e) {
                    throw new RuntimeException("Error reading key.", e);
                }

            default:
                return new Object();

        }
    }
}
