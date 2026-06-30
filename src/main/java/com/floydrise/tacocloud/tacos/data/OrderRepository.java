package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;
import com.floydrise.tacocloud.tacos.attributes.User;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
