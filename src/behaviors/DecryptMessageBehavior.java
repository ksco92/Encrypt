package behaviors;

import enums.EncryptionType;
import factories.DecryptFactory;

public interface DecryptMessageBehavior {

    default String decryptMessage(String messageName, String keyName, EncryptionType encType) throws Exception {
        return new DecryptFactory().decryptMessage(messageName, keyName, encType);
    }

}
