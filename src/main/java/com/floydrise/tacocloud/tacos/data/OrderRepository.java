package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends ListCrudRepository<TacoOrder, UUID> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

}
