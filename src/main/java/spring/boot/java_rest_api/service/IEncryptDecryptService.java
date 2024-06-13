package spring.boot.java_rest_api.service;

public interface IEncryptDecryptService {
    String encrypt(String value);
    Object decrypt(String value);
}
