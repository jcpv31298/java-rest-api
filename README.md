# Java Rest Api with OAuth2 and JWT

***

## Generate RSA Keys

- CREATE FOLDER certs IN src/main/resources

- INSIDE certs CREATE keypair.pem
    openssl genrsa -out keypair.pem 2048

- INSIDE certs CREATE public.pem
    openssl rsa -in keypair.pem -pubout -out public.pem  

- INSIDE certs CREATE private.pem
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

- INSIDE certs DELETE keypair.pem

***

## Generate JWT with OAuth2 - Authorization: Basic Auth

- user: carlos.paez
- password: password

***

## URL's where need token

### UsersController
- Create user: POST - /users
```
{
    "name": "",
    "username": "",
}
```

- Get users: GET - /users

- Get users by name: GET - /users/{name}

***

## URL's where no need token

### PokemonController
- Get pokemon by name: GET - /pokemon/{name}

### EncryptDecryptController
- Encrypt: GET - /encryptdecrypt/encrypt/{value}
- Decrypt: GET - /encryptdecrypt/decrypt/{value}
