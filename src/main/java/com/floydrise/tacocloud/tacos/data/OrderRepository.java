package com.floydrise.tacocloud.tacos.data;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
