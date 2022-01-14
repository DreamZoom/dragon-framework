package cn.dragon.cloud.passport.security;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.security.Key;
import java.security.KeyPair;

@Service
public class JwtKeyService {

    static KeyPair keyPair;

    static {
        try {
            File file =  ResourceUtils.getFile("classpath:server.key");
            try{
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                keyPair = (KeyPair) inputStream.readObject();
                inputStream.close();
            } catch (Exception e) {
                keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
                outputStream.writeObject(keyPair);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Key getPrivateKey(){
        return keyPair.getPrivate();
    }

    public Key getPublicKey(){
        return keyPair.getPublic();
    }

}
