package com.tecdata.spaceship.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tecdata.spaceship.model.Ship;

@Repository
@EnableJpaRepositories
public interface ShipRepository extends PagingAndSortingRepository<Ship,Integer>{

	@Modifying
	@Query("INSERT INTO Ship (name) VALUES (:#{#ship.name})")
    void save(@Param("ship") Ship ship);
	
	@Query("SELECT sp FROM Ship sp WHERE sp.id = :id")
	Ship findById(@Param("id") int id);
	
	@Modifying
	@Query("DELETE FROM Ship sp WHERE sp.id = :id")
	void delete(@Param("id") int id);
	
	@Modifying
    @Query("UPDATE Ship sp SET sp.name = :#{#ship.name} WHERE sp.id = :#{#ship.id}")
    void update(@Param("ship") Ship ship);
	
    @Query("SELECT sp FROM Ship sp WHERE sp.name LIKE %:name%")
    Page<Ship> findByName(@Param("name") String name, Pageable pageable);
}
