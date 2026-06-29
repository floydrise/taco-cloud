package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

}
