package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
