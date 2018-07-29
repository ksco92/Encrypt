package encrypt.encryptManager.tests;

import encrypt.encryptManager.EncryptManager;
import enums.EncryptionType;
import enums.MessageType;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptManagerTest {

    private static EncryptManager em = new EncryptManager();

    @BeforeClass
    public static void generateAESKey() {
        em.generateKey("AESKeyTest", EncryptionType.AES);
    }

    @BeforeClass
    public static void generateDESKey() {
        em.generateKey("DESKeyTest", EncryptionType.DES);
    }

    @BeforeClass
    public static void generateAsymetricKey() {
        em.generateKey("RSAKeyTest", EncryptionType.RSA);
    }

    @Test
    public void AESEncryptDecrypt() throws Exception {

        String originalMessage = "This is a AES test";
        String messageName = "AESMessageTest";
        String keyName = "AESKeyTest";

        em.encryptMessage(messageName, originalMessage, keyName, EncryptionType.AES, MessageType.NONE);
        String message = em.decryptMessage(messageName, keyName, EncryptionType.AES);
        assertEquals(originalMessage, message);

    }

    @Test
    public void DESEncryptDecrypt() throws Exception {

        String originalMessage = "This is a DES test";
        String messageName = "DESMessageTest";
        String keyName = "DESKeyTest";

        em.encryptMessage(messageName, originalMessage, keyName, EncryptionType.DES, MessageType.NONE);
        String message = em.decryptMessage(messageName, keyName, EncryptionType.DES);
        assertEquals(originalMessage, message);

    }

    @Test
    public void RSAEncryptDecrypt() throws Exception {

        String originalMessage = "This is a RSA test";
        String messageName = "RSAMessageTest";
        String keyName = "RSAKeyTest";

        em.encryptMessage(messageName, originalMessage, keyName, EncryptionType.RSA, MessageType.PUBLIC);
        String message = em.decryptMessage(messageName, keyName, EncryptionType.RSA);
        assertEquals(originalMessage, message);

    }

}