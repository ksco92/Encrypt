package tools;

import enums.EncryptionType;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public interface KeyCreator extends WriteBytesFile {

    default void generateKey(String keyName, EncryptionType encType) {

        switch (encType) {
            case AES:
            case DES:
                byte[] key = generatedSequenceOfBytes(encType);
                writeBytesFile(keyName, key, encType, encType.getKeyExtension());
                break;

            case RSA:
                try {
                    KeyPairGenerator kpg = KeyPairGenerator.getInstance(encType.getEncSpec());
                    KeyFactory fact = KeyFactory.getInstance(encType.getEncSpec());
                    kpg.initialize(encType.getKeySize());
                    KeyPair kp = kpg.genKeyPair();
                    RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(), RSAPublicKeySpec.class);
                    RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(), RSAPrivateKeySpec.class);

                    saveAsymetricKeyToFile(encType.getPath() + keyName + "public.key", pub.getModulus(), pub.getPublicExponent());
                    saveAsymetricKeyToFile(encType.getPath() + keyName + "private.key", priv.getModulus(), priv.getPrivateExponent());

                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("No such algorithm for encryption", e);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException("Invalid key spec", e);
                }
                break;

        }

    }

    default byte[] generatedSequenceOfBytes(EncryptionType encType) {

        StringBuilder randomkey = new StringBuilder();
        for (int i = 0; i < encType.getKeySize(); i++)
            randomkey.append(Integer.parseInt(Double.toString((Math.random() + 0.1) * 1000).substring(0, 2)));

        return randomkey.toString().getBytes(StandardCharsets.UTF_8);

    }

    default void saveAsymetricKeyToFile(String fileName, BigInteger mod, BigInteger exp) {

        try {
            ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
            oout.writeObject(mod);
            oout.writeObject(exp);
            oout.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Path to key director not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error writing key to file", e);
        }
    }

}
