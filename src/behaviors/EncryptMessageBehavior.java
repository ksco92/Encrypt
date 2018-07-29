package behaviors;

import enums.EncryptionType;
import enums.MessageType;
import factories.EncryptFactory;

public interface EncryptMessageBehavior {

    default void encryptMessage(String messageName, String message, String keyName, EncryptionType encType, MessageType messageType) throws Exception {
        new EncryptFactory().generateEncryptedMessage(messageName, message, keyName, encType, messageType);
    }

}
