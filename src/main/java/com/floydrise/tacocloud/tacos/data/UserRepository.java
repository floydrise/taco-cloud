package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.User;

public interface UserRepository extends ListCrudRepository<User, Long> {
    User findByUsername(String username);
}
