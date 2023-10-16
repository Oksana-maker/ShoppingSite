package com.mashurova.shoppingsite.persistence;

import com.mashurova.shoppingsite.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {

}

