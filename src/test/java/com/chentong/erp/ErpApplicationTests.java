package com.chentong.erp;

import com.chentong.erp.common.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.util.Password;

@SpringBootTest
class ErpApplicationTests {

    @Test
    void contextLoads() {
        String encode = PasswordUtils.encode("aaa", "aaa");
        System.out.println(encode);
        String encode1 = PasswordUtils.encode("bbb", "bbb");
        System.out.println(encode1);
    }

}
