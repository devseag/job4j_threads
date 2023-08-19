package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            if (model.getVersion() != value.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            value = new Base(model.getId(), model.getVersion() + 1);
            value.setName(model.getName());
            return value;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Map<Integer, Base> getMemoryForTest() {
        return Collections.synchronizedMap(memory);
    }
}