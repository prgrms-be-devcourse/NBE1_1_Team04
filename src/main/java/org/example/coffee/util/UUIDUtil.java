package org.example.coffee.util;

import com.fasterxml.uuid.Generators;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

public class UUIDUtil {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte[] createUUID() {

        UUID uuidV1 = Generators.timeBasedGenerator().generate();
        String[] uuidV1Parts = uuidV1.toString().split("-");
        String sequentialUUID = uuidV1Parts[2]+uuidV1Parts[1]+uuidV1Parts[0]+uuidV1Parts[3]+uuidV1Parts[4];
        System.out.println(sequentialUUID);
        return hexToBytes(sequentialUUID);
    }

    public static byte[] hexToBytes(String uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(Long.parseUnsignedLong(uuid.substring(0, 16), 16));
        bb.putLong(Long.parseUnsignedLong(uuid.substring(16), 16));
        return bb.array();
    }
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars).toLowerCase();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            UUID uuidV1 = Generators.timeBasedGenerator().generate();
            String[] uuidArr = uuidV1.toString().split("-");
            String sequentialUUID = uuidArr[2]+"-"+uuidArr[1]+"-"+uuidArr[0]+"-"+uuidArr[3]+"-"+uuidArr[4];
            System.out.println(sequentialUUID);
        }
    }
}
