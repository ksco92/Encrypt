package enums;

public enum EncryptionType {
    AES(8, ".key", ".encrypt", "C:/encrypt/AES/", "AES"),
    RSA(2048, ".key", ".encrypt", "C:/encrypt/RSA/", "RSA"),
    DES(4, ".key", ".encrypt", "C:/encrypt/DES/", "DES");

    private int keySize;
    private String keyExtension;
    private String messageEncryptExtension;
    private String path;
    private String encSpec;

    EncryptionType(int keySize, String keyExtension, String messageEncryptExtension, String path, String encSpec) {
        this.keySize = keySize;
        this.keyExtension = keyExtension;
        this.messageEncryptExtension = messageEncryptExtension;
        this.path = path;
        this.encSpec = encSpec;
    }

    public int getKeySize() {
        return keySize;
    }

    public String getKeyExtension() {
        return keyExtension;
    }

    public String getMessageEncryptExtension() {
        return messageEncryptExtension;
    }

    public String getPath() {
        return path;
    }

    public String getEncSpec() {
        return encSpec;
    }

}
