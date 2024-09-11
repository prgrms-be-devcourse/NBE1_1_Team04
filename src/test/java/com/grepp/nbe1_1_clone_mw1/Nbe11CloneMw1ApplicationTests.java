package com.grepp.nbe1_1_clone_mw1;


import com.grepp.nbe1_1_clone_mw1.global.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class Nbe11CloneMw1ApplicationTests {

    @Test
    void test1(){
        byte[] uuid = UUIDUtil.createUUID();
        String s = UUIDUtil.bytesToHex(uuid);
        byte[] bytes = UUIDUtil.hexStringToByteArray(s);
        assertThat(bytes).isEqualTo(uuid);
    }

    @Test
    void contextLoads() {
    }

}
