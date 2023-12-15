package ly.post.dinar.service.utils;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.KeyAgreement;

public class WalletKeyTools {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 250;

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private static KeyPair generateWalletKeyPairs() {
        try {
            // Choose the Elliptic Curve Algorithm (EC)
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");

            // Initialize the key pair generator with a specific elliptic curve size
            keyPairGenerator.initialize(new ECGenParameterSpec("secp256k1"));

            // Generate the key pair
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Get the public and private keys
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Convert the keys to hexadecimal strings
            String publicKeyString = bytesToHex(publicKey.getEncoded());
            String privateKeyString = bytesToHex(privateKey.getEncoded());

            // Print the generated key pair strings
            System.out.println("Generated Public Key: " + publicKeyString);
            System.out.println("Generated Private Key: " + privateKeyString);

            return keyPair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean doKeysMatch(PublicKey publicKey, PrivateKey privateKey) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DiffieHellman");
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);
            return true;
        } catch (Exception e) {
            // If an exception is thrown, the keys do not match
            return false;
        }
    }

    public byte[] signData(byte[] data, String privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(getPrivateKeyFromString(privateKey));
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifySignature(byte[] data, byte[] signature, String publicKey) {
        try {
            Signature sig = Signature.getInstance("SHA256withECDSA");
            PublicKey verifyKey = getPublicKeyFromString(publicKey);
            sig.initVerify(verifyKey);
            sig.update(data);
            return sig.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static PrivateKey getPrivateKeyFromString(String privateKeyString) {
        try {
            byte[] keyBytes = hexToBytes(privateKeyString);

            // Assuming you are using an Elliptic Curve algorithm (e.g., ECDSA)
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private PublicKey getPublicKeyFromString(String publicKey) {
        try {
            byte[] keyBytes = hexToBytes(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
