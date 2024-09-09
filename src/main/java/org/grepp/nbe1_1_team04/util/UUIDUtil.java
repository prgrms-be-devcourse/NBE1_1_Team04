package org.grepp.nbe1_1_team04.util;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Component
public class UUIDUtil {

    public byte[] createRandomUUID() {
        //순차 UUID 생성 및 설정
        UUID uuidV1 = Generators.timeBasedGenerator().generate();
        String[] uuidV1Parts = uuidV1.toString().split("-");
        String sequentialUUID = uuidV1Parts[2]+uuidV1Parts[1]+uuidV1Parts[0]+uuidV1Parts[3]+uuidV1Parts[4];

        String sequentialUuidV1 = String.join("", sequentialUUID);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(0, 16), 16));
        bb.putLong(Long.parseUnsignedLong(sequentialUuidV1.substring(16), 16));
        return bb.array();
    }
}
