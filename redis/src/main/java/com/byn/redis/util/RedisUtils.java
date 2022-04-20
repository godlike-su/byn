package com.byn.redis.util;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author tianke
 * @date 2021/2/19 10:27
 */
@Slf4j
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置失效时间
     * @param key key
     * @param time 时间，单位秒
     */
    public void expire(String key,long time){
        if (time <= 0){
            throw new RuntimeException("失效时间必须大于0");
        }
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    /**
     * 获取失效时间
     * @param key key
     * @return long，返回时间，单位秒，为-1，则永久有效
     */
    public long getExpireTime(String key){
        if (StringUtils.isBlank(key)){
            throw new RuntimeException("key值不能为空！");
        }
        var time = redisTemplate.getExpire(key);
        return time == null ? 0 : time;
    }

    /**
     * 删除缓存
     * @param key key
     */
    @SuppressWarnings("unchecked")
    public void del(Object... key){
        if (key == null){
            throw new RuntimeException("key不能为空！");
        }
        List<String> list = (List<String>) CollectionUtils.arrayToList(key);
        redisTemplate.delete(list);
    }

    /**
     * 判断是否存在key
     * @param key key
     * @return boolean
     */
    public boolean hasKey(String key){
        var hasKey = redisTemplate.hasKey(key);
        if (hasKey == null){
            hasKey = false;
        }
        return hasKey;
    }

    /**
     * 根据key获取值
     * @param key key值
     * @return Object
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 放入缓存
     * @param key key
     * @param value value
     */
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 放入缓存并设置时间（单位秒）
     * @param key key
     * @param value value
     * @param time 时间，单位秒
     */
    public void set(String key,Object value,long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            }else {
                set(key,value);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

    }

    /**
     * 递增
     * @param key key
     * @param step 递增长度，小于0，则为0
     * @return long
     */
    public long increase(String key,long step){
        if (step < 0){
            step = 0;
        }
        var result = redisTemplate.opsForValue().increment(key,step);
        return result == null ? 0 : result;
    }

    /**
     * 递减
     * @param key  key
     * @param step 递减长度
     * @return long
     */
    public long decrease(String key,long step){
        if (step < 0){
            step = 0;
        }
        var result = redisTemplate.opsForValue().decrement(key,step);
        return result == null ? 0 : result;
    }

    /**
     * 设置hash缓存
     * @param key key
     * @param value 值
     */
    public void hSet(String key, Map<String, Object> value){
        if (StringUtils.isBlank(key) || value.isEmpty()){
            throw new RuntimeException("key值或者value值不能为空！");
        }
        redisTemplate.opsForHash().putAll(key,value);
    }

    /**
     * 设置有效期的hash缓存
     * @param key key
     * @param value value
     * @param time 时间，单位秒
     */
    public void hSet(String key,Map<String,Object> value,long time){
        hSet(key,value);
        expire(key, time);
    }

    /**
     * 获取hash的所有值
     * @param key key
     * @return Map
     */
    public Map<Object,Object> hGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hash的值
     * @param key key
     * @param hk 每项的key
     * @return T
     */
    public Object hGet(String key,Object hk){
        if (StringUtils.isBlank(key) || hk == null){
            throw new RuntimeException("key值或者hk值不能为空！");
        }
        return redisTemplate.opsForHash().get(key,hk);
    }

    /**
     * 设置hash项的值，不存在则创建
     * @param key key
     * @param hk 每项的key
     * @param hv 每项的value
     */
    public void hSet(String key,Object hk,Object hv){
        if (StringUtils.isBlank(key) || hk == null || hv == null){
            throw new RuntimeException("key值或者hk值或者hv值不能为空！");
        }
        redisTemplate.opsForHash().put(key,hk,hv);
    }

    /**
     * 设置可以过期的hash值缓存
     * @param key key
     * @param hk 每项的key
     * @param hv 每项的value
     * @param time 过期时间，单位秒
     */
    public void hSet(String key,Object hk,Object hv,long time){
        hSet(key,hk,hv);
        redisTemplate.opsForHash().put(key,hk,hv);
        expire(key, time);
    }

    /**
     * 删除hash的值
     * @param key key
     * @param hk 每项的key
     */
    public long hDel(String key,Object... hk){
        return redisTemplate.opsForHash().delete(key,hk);
    }

    /**
     * 判断hash中是否存在该项的值
     * @param key key
     * @param hk 每项的key
     * @return boolean
     */
    public boolean hHasKey(String key,Object hk){
        return redisTemplate.opsForHash().hasKey(key,hk);
    }

    /**
     * 增加hash的指定项的值
     * @param key key
     * @param hk 每项的key
     * @param step 增加长度（减少则为负数）
     * @return long
     */
    public long hIncrease(String key,Object hk,long step){
        return redisTemplate.opsForHash().increment(key,hk,step);
    }

    /**
     * 增加hash的指定项的值
     * @param key key
     * @param hk 每项的key
     * @param step 增加长度（减少则为负数）
     * @return double
     */
    public double hIncrease(String key,Object hk,double step){
        return redisTemplate.opsForHash().increment(key,hk,step);
    }

    /**
     * 设置set缓存
     * @param key key
     * @param value value
     * @return long
     */
    public long sSet(String key,Object... value){
        if (StringUtils.isBlank(key) || value == null){
            throw new RuntimeException("key或者value不能为空！");
        }
        var result = redisTemplate.opsForSet().add(key,value);
        return result == null ? 0L : result;
    }

    /**
     * 设置有过期时间的set值
     * @param key key
     * @param time 时间，单位秒
     * @param value value
     * @return long
     */
    public long sSetAndTime(String key,long time,Object... value){
        var result = sSet(key,value);
        expire(key,time);
        return result;
    }

    /**
     * 获取set的值
     * @param key key
     * @return set
     */
    public Set<Object> sMembers(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取多个元素
     * @param key key
     * @param count 数量
     * @return List
     */
    public List<Object> sRandomMembers(String key,long count){
        return redisTemplate.opsForSet().randomMembers(key,count);
    }

    /**
     * 随机获取一个元素
     * @param key key
     * @return Object
     */
    public Object sRandomMember(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 判断set中是否存在值
     * @param key key
     * @param value value
     * @return boolean
     */
    public boolean sHasKey(String key,Object value){
        var result = redisTemplate.opsForSet().isMember(key,value);
        if (result == null){
            result = false;
        }
        return result;
    }

    /**
     * 获取set的长度
     * @param key key
     * @return long
     */
    public long sSetSize(String key){
        var size = redisTemplate.opsForSet().size(key);
        return size == null ? 0L : size;
    }

    /**
     * 移除set的值
     * @param key key
     * @param values value
     * @return long
     */
    public long sSetRemove(String key,Object... values){
        var result = redisTemplate.opsForSet().remove(key,values);
        return result == null ? 0L : result;
    }

    /**
     * 将list放入缓存
     * @param key key
     * @param value value
     */
    public void lSet(String key,Object value){
        if (StringUtils.isBlank(key) || value == null){
            throw new RuntimeException("key或者value不能为空！");
        }
        redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 将list放入有过期时间的缓存
     * @param key key
     * @param value value
     * @param time 过期时间，单位秒
     */
    public void lSet(String key,Object value,long time){
        lSet(key,value);
        expire(key,time);
    }

    /**
     * 将list放入缓存
     * @param key key
     * @param value value
     */
    public void lSet(String key, Collection<String> value){
        if (StringUtils.isBlank(key) || value == null || value.isEmpty()){
            throw new RuntimeException("key或者value不能为空！");
        }
        redisTemplate.opsForList().rightPushAll(key,value);
    }

    /**
     * 将list放入缓存，并设置过期时间
     * @param key key
     * @param value value
     * @param time 过期时间，单位秒
     */
    public void lSet(String key,Collection<String> value,long time){
        lSet(key,value);
        expire(key,time);
    }

    /**
     * 通过索引设置list的值
     * @param key key
     * @param index 索引
     * @param value value
     */
    public void lSet(String key, long index, Object value) {
        if (StringUtils.isBlank(key) || value == null ){
            throw new RuntimeException("key或者value不能为空！");
        }
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 为已存在的列表添加值
     * @param key key
     * @param value value
     * @return long
     */
    public long lRightPushIfPresent(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null){
            throw new RuntimeException("key或者value不能为空！");
        }
        var result = redisTemplate.opsForList().rightPushIfPresent(key, value);
        return result == null ? 0L : result;
    }

    /**
     * 获取list的值，0到-1，表示获取所有
     * @param key key
     * @param start 开始索引
     * @param end 结束索引
     */
    public List<Object> lGetRang(String key,long start,long end){
        return redisTemplate.opsForList().range(key,start,end);
    }

    /**
     * 通过索引获取list的值,index>=0时， 0 表头，1 第二个元素；index<0时，-1，表尾，-2倒数第二个元素
     * @param key key
     * @param index 索引值
     * @return Object
     */
    public Object lGetByIndex(String key,long index){
        return redisTemplate.opsForList().index(key,index);
    }

    /**
     * 获取list的长度
     * @param key key
     * @return long
     */
    public long lGetSize(String key){
        var size = redisTemplate.opsForList().size(key);
        return size == null ? 0L : size;
    }

    /**
     * 移出并获取列表的第一个元素
     * @param key key
     * @return Object
     */
    public Object lLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并获取列表最后一个元素
     * @param key key
     * @return Object
     */
    public Object lRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 删除集合中值等于value的元素
     * @param key key
     * @param index
     *            index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *            index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value value
     * @return long
     */
    public long lRemove(String key, long index, Object value) {
        var result = redisTemplate.opsForList().remove(key, index, value);
        return result == null ? 0L : result;
    }

    /**
     * 新增zSet缓存,有序集合是按照元素的score值由小到大排列
     * @param key key
     * @param value value
     * @param score 排序号
     * @return boolean
     */
    public boolean zAdd(String key, Object value, double score) {
        var result = redisTemplate.opsForZSet().add(key, value, score);
        if (result == null){
            result = false;
        }
        return result;
    }

    /**
     * 批量增加zSet的值
     * @param key key
     * @param values value
     * @return long
     */
    public long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> values) {
        var result = redisTemplate.opsForZSet().add(key, values);
        return result == null ? 0L : result;
    }

    /**
     * 增加元素的score的值，并返回增加后的值
     * @param key key
     * @param value value
     * @param delta 需要增加的值
     * @return double
     */
    public double zIncreaseScore(String key,Object value,double delta){
        var result = redisTemplate.opsForZSet().incrementScore(key,value,delta);
        return result == null ? 0D : result;
    }

    /**
     * 返回元素在集合中的排名，按元素的score值由大到小排列
     * @param key key
     * @param value value
     * @return long
     */
    public long zRank(String key,Object value){
        var result = redisTemplate.opsForZSet().rank(key,value);
        return result == null ? 0L : result;
    }

    /**
     * 获取集合元素，按照score从小到大顺序排列
     * @param key key
     * @param start 索引开始值
     * @param end 索引结束值
     * @return Set
     */
    public Set<Object> zRang(String key,long start,long end){
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取集合元素, 并且把score值也获取到
     * @param key key
     * @param start 索引开始值
     * @param end 索引结束值
     * @return Set
     */
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key,long start,long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key,start,end);
    }

    /**
     * 根据score值获取集合
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @return Set
     */
    public Set<Object> zRangeByScore(String key,double min,double max) {
        return redisTemplate.opsForZSet().rangeByScore(key,min,max);
    }

    /**
     * 获取集合的元素, 从大到小排序
     * @param key key
     * @param start 索引开始值
     * @param end 索引结束值
     * @return Set
     */
    public Set<Object> zReverseRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }
    /**
     * 根据Score值查询集合元素, 从大到小排序
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @return Set
     */
    public Set<Object> zReverseRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    /**
     * 根据score获取范围内的集合，从大到小排序
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @param offSet 索引开始值
     * @param count 数量
     * @return Set
     */
    public Set<Object> zReverseRangeByScore(String key, double min, double max, long offSet, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key,min,max,offSet,count);
    }

    /**
     * 根据score统计集合数量
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @return long
     */
    public long zCount(String key,double min,double max){
        var result = redisTemplate.opsForZSet().count(key,min,max);
        return result == null ? 0L : result;
    }

    /**
     * 获取结合长度
     * @param key key
     * @return long
     */
    public long zSize(String key){
        var result = redisTemplate.opsForZSet().size(key);
        return result == null ? 0L : result;
    }

    /**
     * 获取集合的score值
     * @param key key
     * @param value value
     * @return double
     */
    public double zScore(String key,Object value){
        var result = redisTemplate.opsForZSet().score(key,value);
        return result == null ? 0D : result;
    }

    /**
     * 根据指定的score值的范围来移除成员
     * @param key key
     * @param min score最小值
     * @param max score最大值
     * @return long
     */
    public long zRemoveRangeByScore(String key, double min, double max) {
        var result = redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
        return result == null ? 0L : result;
    }

    /**
     * 移除指定索引位置的成员
     * @param key key
     * @param start 索引开始时间
     * @param end 索引结束时间
     * @return long
     */
    public long zRemoveRange(String key, long start, long end) {
        var result = redisTemplate.opsForZSet().removeRange(key,start,end);
        return result == null ? 0L : result;
    }

    /**
     * 删除集合的元素
     * @param key key
     * @param values values
     * @return long
     */
    public long zRemove(String key,Object... values){
        var result = redisTemplate.opsForZSet().remove(key,values);
        return result == null ? 0L : result;
    }

}
