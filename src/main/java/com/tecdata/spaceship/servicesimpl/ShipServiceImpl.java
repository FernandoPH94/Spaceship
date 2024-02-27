package com.tecdata.spaceship.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecdata.spaceship.exception.NotValidValueException;
import com.tecdata.spaceship.model.Ship;
import com.tecdata.spaceship.repositories.ShipRepository;
import com.tecdata.spaceship.services.ShipService;

import jakarta.transaction.Transactional;

@Service
public class ShipServiceImpl implements ShipService{
	
	@Autowired
	private ShipRepository shipRepository;
	
	@Cacheable("allShip")
	public Page<Ship> getAllShip(PageRequest pageRequest){
		return shipRepository.findAll(
				PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize()));
	}
	
	@Cacheable("ship")
	public Ship getShip(int id){
		if(id < 0) {
			throw new NotValidValueException("ID de la nave no válido: " + id);
		}
		return shipRepository.findById(id);
	}
	@Transactional
	public void updateShip(Ship ship) {
		if(shipRepository.findById(ship.getId())!= null) {
			shipRepository.update(ship);
		}
		else{
			throw new NotValidValueException("ID de la nave no válido: " + ship.getId());				
		}
	}
	@Transactional
	public void saveShip(Ship ship) {
		shipRepository.save(ship);
	}
	@Transactional
	public void deleteShip(int id) {
		shipRepository.delete(id);
	}
	public Page<Ship> findByName(String name, Pageable pageable){
		return shipRepository.findByName(name,pageable);
	}
}
