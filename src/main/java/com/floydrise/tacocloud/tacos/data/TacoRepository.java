package com.floydrise.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.floydrise.tacocloud.tacos.attributes.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
