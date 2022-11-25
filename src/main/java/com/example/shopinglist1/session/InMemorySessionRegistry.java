package com.example.shopinglist1.session;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class InMemorySessionRegistry {


    private static final HashMap<String, String> SESSION = new HashMap<>();

    public String registerSession(final String username){
        if(username == null){
            throw new RuntimeException("Username needs to by provided");
        }
        final String sessionId = generateSessionId();
        SESSION.put(sessionId,username);
        return sessionId;
    }
public String getUsernameForSession(final String sessionId){
        return SESSION.get(sessionId);
}
    private String generateSessionId(){
        return new String(Base64.getEncoder().encode(
                UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8)));
    }
}
