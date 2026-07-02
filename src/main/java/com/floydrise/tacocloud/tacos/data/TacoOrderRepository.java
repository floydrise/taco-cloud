package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {
}
