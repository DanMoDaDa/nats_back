package org.danmo.cache;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
    static Map<String,Cache> map = new HashMap<>();

    public static void put(String key, Object obj){
        map.put(key,new Cache(obj));
    }

    public static void put(String key, Object obj , int outTime){
        map.put(key,new Cache(obj,outTime));
    }

    public static Object get(String key){
        Cache cache = map.get(key);
        if(cache != null && cache.outTime <= System.currentTimeMillis()) {
            return map.get(key).getData();
        }
        return null;
    }

    public static Object getOverlookOutTime(String key){
        Cache cache = map.get(key);
        if(cache != null) {
            return map.get(key).getData();
        }
        return null;
    }

    public static Cache remove(String key){
        return map.remove(key);
    }

    static class Cache {
        private Object data = null;
        private long outTime = 0;

        Cache(Object data){
            this.data = data;
        }
        Cache(Object data,long outTime){
            this.data = data;
            this.outTime = outTime;
        }

        public Object getData() {
            return data;
        }

        public long getOutTime() {
            return outTime;
        }
    }
}
