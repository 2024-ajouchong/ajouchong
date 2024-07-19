package com.ajouchong.repository;

import com.ajouchong.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepositoryInterface {

    private static Map<String, User> store = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByName(String name){
        return Optional.ofNullable(store.get(name));
    }
}
