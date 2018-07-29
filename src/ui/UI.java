package ui;

import encrypt.encryptManager.EncryptManager;
import enums.EncryptionType;
import enums.MessageType;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class UI {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static EncryptManager encryptManager = new EncryptManager();


    public static void main(String[] args) throws Exception {
        int option = 0;
        do {
            System.out.println("1.Create key (AES)");
            System.out.println("2.Encrypt Message (AES)");
            System.out.println("3.Decrypt Message (AES)");
            System.out.println("4.Create key (DES)");
            System.out.println("5.Encrypt Message (DES)");
            System.out.println("6.Decrypt Message (DES)");
            System.out.println("7.Create key (RSA)");
            System.out.println("8.Encrypt Message (RSA)");
            System.out.println("9.Decrypt Message (RSA)");
            System.out.println("10.Exit ");
            option = Integer.parseInt(br.readLine());
            if (option >= 1 && option <= 9){
                executeAction(option);
            }
        } while (option != 10);

    }

    private static void executeAction(int option) throws Exception {
        if (option == 1){
            System.out.println("Key name: ");
            String name = br.readLine();
            encryptManager.generateKey(name, EncryptionType.AES);
        } else if (option == 2){
            System.out.println("Key name: ");
            String name = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("Message: ");
            String message = br.readLine();
            encryptManager.encryptMessage(messageName,message,name, EncryptionType.AES, MessageType.NONE);
        } else if (option == 3){
            System.out.println("Key name: ");
            String keyName = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("The message was: " + encryptManager.decryptMessage(messageName, keyName, EncryptionType.AES));
        } else if (option == 4){
            System.out.println("Key name: ");
            String name = br.readLine();
            encryptManager.generateKey(name, EncryptionType.DES);
        } else if (option == 5){
            System.out.println("Key name: ");
            String name = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("Message: ");
            String message = br.readLine();
            encryptManager.encryptMessage(messageName,message,name, EncryptionType.DES, MessageType.NONE);
        } else if (option == 6){
            System.out.println("Key name: ");
            String keyName = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("The message was: " + encryptManager.decryptMessage(messageName, keyName, EncryptionType.DES));
        } else if (option == 7){
            System.out.println("Key name: ");
            String name = br.readLine();
            encryptManager.generateKey(name, EncryptionType.RSA);
        } else if (option == 8){
            System.out.println("Key name: ");
            String name = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("Message: ");
            String message = br.readLine();
            encryptManager.encryptMessage(messageName,message,name, EncryptionType.RSA, MessageType.PUBLIC);
        } else if (option == 9){
            System.out.println("Key name: ");
            String keyName = br.readLine();
            System.out.println("Message name: ");
            String messageName = br.readLine();
            System.out.println("The message was: " + encryptManager.decryptMessage(messageName, keyName, EncryptionType.RSA));
        }
    }
}

