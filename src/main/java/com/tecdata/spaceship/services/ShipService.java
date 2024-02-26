package com.tecdata.spaceship.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecdata.spaceship.model.Ship;

@Service
public interface ShipService {
	Page<Ship> getAllShip(PageRequest pageRequest);
	Ship getShip(int id);
	void saveShip(Ship ship);
	void updateShip(Ship ship);
	void deleteShip(int id);
	Page<Ship> findByName(String name, Pageable pageable);
}
