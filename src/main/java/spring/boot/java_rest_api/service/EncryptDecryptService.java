package spring.boot.java_rest_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class EncryptDecryptService implements IEncryptDecryptService {
    private final String secretKey;
    private final String initVector;

    public EncryptDecryptService(
            @Value("${encryptdecrypt.secret.key}") String secretKey,
            @Value("${encryptdecrypt.init.vector}") String initVector
    ) {
        this.secretKey = secretKey;
        this.initVector = initVector;
    }

    @Override
    public String encrypt(String value) {
        try {
            if(value.isEmpty()) return null;

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        }
        catch (Exception e) {
            throw new RuntimeException("Error to try encrypt value", e);
        }
    }

    @Override
    public Object decrypt(String value) {
        try {
            if(value.isEmpty()) return null;

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);

            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(value));

            return new String(decrypted);
        }
        catch (Exception e) {
            throw new RuntimeException("Error to try decrypt value", e);
        }
    }
}
