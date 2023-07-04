package com.bharadwaj.demotwo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.DigestUtils;

public class EtagUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String generateEtag(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            return DigestUtils.md5DigestAsHex(json.getBytes());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to generate ETag", ex);
        }
    }
}

