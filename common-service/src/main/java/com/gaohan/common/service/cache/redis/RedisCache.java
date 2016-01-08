package com.gaohan.common.service.cache.redis;

import java.io.*;
import java.text.MessageFormat;

import com.alibaba.fastjson.JSON;
import com.gaohan.biz.module1.vo.PersonalInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.gaohan.common.service.redis.service.RedisCacheService;
import com.google.common.base.Optional;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by gaohan on 2016/1/6.
 */
public class RedisCache implements Cache {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private String name;

    private RedisCacheService redisCacheService;

    private int expireSeconds = 0;//redis缓存过期时间

    public void setName(String name) {
        this.name = name;
    }

    public void setRedisCacheService(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return redisCacheService;
    }

    @Override
    public ValueWrapper get(Object key) {
        //key exists
        if (Optional.fromNullable(key).isPresent()) {

            String keyStr = key.toString();
            String value = redisCacheService.get(keyStr);

            //value exists
            if (Optional.fromNullable(value).isPresent()) {
                Object desValueObj = null;
                try {
                    desValueObj = readObject(value);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn("deserialization failed");
                    return null;
                }

                logger.info(MessageFormat.format("get cache,key:{0},value:{1}", keyStr, value));
                redisCacheService.expire(keyStr, expireSeconds);//过期自动删除
                return new SimpleValueWrapper(desValueObj);
            }
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        //key exists
        if (Optional.fromNullable(key).isPresent()) {

            String keyStr = key.toString();
            String value = redisCacheService.get(keyStr);

            //value exists
            if (Optional.fromNullable(value).isPresent()) {
                Object desValueObj = null;
                try {
                    desValueObj = readObject(value);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn("deserialization failed");
                    return null;
                }

                logger.info(MessageFormat.format("get cache,key:{0},value:{1}", keyStr, value));
                redisCacheService.expire(keyStr, expireSeconds);//过期自动删除
                return type.cast(desValueObj);
            }
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (Optional.fromNullable(key).isPresent()) {
            try {
                String serValueStr = writeObject(value);
                String result = redisCacheService.setex(key.toString(), serValueStr, expireSeconds);
                logger.info(
                    MessageFormat.format("setex cache,key:{0},value:{1},result:{2}", key.toString(), serValueStr, result));
            } catch (Exception e) {
                e.printStackTrace();
                logger.warn("Serialization failed");
            }
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (Optional.fromNullable(key).isPresent()) {
            try {
                String serValueStr = writeObject(value);
                Long result = redisCacheService.setnx(key.toString(), serValueStr);
                logger.info(MessageFormat.format("setex cache,key:{0},value:{1},result:{2}", key.toString(), serValueStr, result));
                //插入成功则返回插入成功数量
                if(result.longValue()>0){
                    redisCacheService.expire(key.toString(),expireSeconds);
                    return new SimpleValueWrapper(result);
                }else{//失败则返回原有数据
                    serValueStr = redisCacheService.get(key.toString());
                    if(Optional.fromNullable(serValueStr).isPresent()){
                        return new SimpleValueWrapper(readObject(serValueStr));
                    }
                    return new SimpleValueWrapper(serValueStr);

                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.warn("Serialization or deserialization failed");
                return null;
            }
        }
        return null;
    }

    @Override
    public void evict(Object key) {
        if (Optional.fromNullable(key).isPresent()) {
            redisCacheService.del(key.toString());
            logger.info(MessageFormat.format("del cache,key:{0}", key.toString()));
        }
    }

    @Override
    public void clear() {
        redisCacheService.flushAll();
    }

    //序列化
    private String writeObject(Object o) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
        bos.close();
        return new BASE64Encoder().encode(bos.toByteArray());
        //        return new String(bos.toByteArray(), "UTF-8");
    }

    //反序列化
    private Object readObject(String object) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(object));
        //        ByteArrayInputStream bis = new ByteArrayInputStream(object.getBytes("UTF-8"));
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = null;
        try {
            o = ois.readObject();
        } catch (EOFException e) {
            System.err.print("read finished");
        }
        bis.close();
        ois.close();
        return o;
    }
}
