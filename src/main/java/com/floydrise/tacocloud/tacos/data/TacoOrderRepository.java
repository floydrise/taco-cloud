package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {
}
