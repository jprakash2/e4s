package com.landisgyr.e4s.pubSub;

import java.util.Base64;

/**
 * This class contains methods to encode/decode Base-64 payloads used in MQTT + Web Things.
 * TODO: Not yet certain if Base64 encoding will be used in E4S - this class/code is here as
 *       a placeholder in case it is needed.
 */
public class Base64Helper {
    /**
     * Encodes a byte[] array to a String in Base-64 format.
     * @param payload - the byte[] array to encode
     * @return the encoded string in base-64 format
     */
    public static String encode(byte[] payload) {
        String encodedString = java.util.Base64.getEncoder().encodeToString(payload);

        return encodedString;
    }

    /**
     * Decodes a String in Base-64 format to a byte[] array.
     * @param payload - the encoded payload in Base64 format
     * @return the decoded byte[] array
     */
    public static byte[] decode(String payload) {
       byte[] decodedBytes = java.util.Base64.getDecoder().decode(payload);

       return decodedBytes;
    }
}
