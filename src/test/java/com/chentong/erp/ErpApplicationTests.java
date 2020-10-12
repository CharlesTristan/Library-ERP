package com.chentong.erp;

import com.chentong.erp.common.util.PasswordUtils;
import com.chentong.erp.vo.resp.DataResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.util.Password;

@SpringBootTest
class ErpApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        DataResult dataResult = DataResult.success();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(dataResult);
        System.out.println(s);
    }

}
