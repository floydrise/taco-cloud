package com.floydrise.tacocloud.tacos.web.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.floydrise.tacocloud.tacos.attributes.TacoOrder;
import com.floydrise.tacocloud.tacos.data.TacoOrderRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/taco_orders", produces = "application/json")
@CrossOrigin("http://localhost:8080")
public class TacoOrderController {
    private TacoOrderRepository tacoOrderRepository;

    public TacoOrderController(TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
    }

    @GetMapping(params = "recent")
    @SuppressWarnings("null")
    public List<TacoOrder> getTacoOrders() {
        PageRequest page = PageRequest.of(0, 12, Sort.by(TacoOrder::getPlacedAt).descending());
        return tacoOrderRepository.findAll(page).getContent();
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TacoOrder patchTaco(@PathVariable Long orderId, @RequestBody TacoOrder patch) {
        return tacoOrderRepository.findById(orderId).map(order -> {
            if (patch.getDeliveryName() != null) {
                order.setDeliveryName(patch.getDeliveryName());
            }
            if (patch.getDeliveryStreet() != null) {
                order.setDeliveryStreet(patch.getDeliveryStreet());
            }
            if (patch.getDeliveryCity() != null) {
                order.setDeliveryCity(patch.getDeliveryCity());
            }
            if (patch.getDeliveryState() != null) {
                order.setDeliveryState(patch.getDeliveryState());
            }
            if (patch.getDeliveryZip() != null) {
                order.setDeliveryZip(patch.getDeliveryZip());
            }
            if (patch.getCcNumber() != null) {
                order.setCcNumber(patch.getCcNumber());
            }
            if (patch.getCcExpiration() != null) {
                order.setCcExpiration(patch.getCcExpiration());
            }
            if (patch.getCcCVV() != null) {
                order.setCcCVV(patch.getCcCVV());
            }

            return tacoOrderRepository.save(order);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long orderId) {
        try {
            tacoOrderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
