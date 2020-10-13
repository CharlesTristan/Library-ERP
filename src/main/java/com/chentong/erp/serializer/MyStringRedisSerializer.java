package com.chentong.erp.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * TODO
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/10/13 10:35
 */
public class MyStringRedisSerializer implements RedisSerializer<Object> {
    private final Charset charset;

    public MyStringRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public MyStringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @SneakyThrows
    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        if(object instanceof String){
            return object.toString().getBytes(charset);
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            String string = objectMapper.writeValueAsString(object);
            return string.getBytes(charset);
        }

    }
}
