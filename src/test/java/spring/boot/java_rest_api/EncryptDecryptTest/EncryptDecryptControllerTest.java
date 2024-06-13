package spring.boot.java_rest_api.EncryptDecryptTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.boot.java_rest_api.service.IEncryptDecryptService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EncryptDecryptControllerTest {
    @Autowired
    private IEncryptDecryptService encryptDecryptService;

    @Test
    void shouldReturnNullWhenEncryptValueIsEmpty() {
        String value = "";
        String response = encryptDecryptService.encrypt(value);

        assertThat(response).isNull();
    }

    @Test
    void shouldReturnNullWhenDecryptValueIsEmpty() {
        String value = "";
        Object response = encryptDecryptService.decrypt(value);

        assertThat(response).isNull();
    }

    @Test
    void shouldReturnEncryptValue() {
        String value = "data";
        String response = encryptDecryptService.encrypt(value);

        assertThat(response).isEqualTo("nAANNuAEGDhSCHMxpRCgjw==");
    }

    @Test
    void shouldReturnDecryptValue() {
        String value = "nAANNuAEGDhSCHMxpRCgjw==";
        Object response = encryptDecryptService.decrypt(value);

        assertThat(response).isEqualTo("data");
    }
}
