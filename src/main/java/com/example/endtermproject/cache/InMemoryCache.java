package com.example.endtermproject.cache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache implements Cache<String, Object> {

    private static volatile InMemoryCache instance;

    private final ConcurrentHashMap<String, Object> store = new ConcurrentHashMap<>();

    private InMemoryCache() { }

    public static InMemoryCache getInstance() {
        if (instance == null) {
            synchronized (InMemoryCache.class) {
                if (instance == null) {
                    instance = new InMemoryCache();
                }
            }
        }
        return instance;
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(store.get(key));
    }

    @Override
    public void put(String key, Object value) {
        store.put(key, value);
    }

    @Override
    public void evict(String key) {
        store.remove(key);
    }

    @Override
    public void clear() {
        store.clear();
    }
}
