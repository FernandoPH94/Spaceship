package com.tecdata.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecdata.spaceship.model.Ship;
import com.tecdata.spaceship.repositories.ShipRepository;
import com.tecdata.spaceship.servicesimpl.ShipServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ShipServiceTest {
	
	@Mock
    private ShipRepository shipRepository;
    @InjectMocks
    private ShipServiceImpl shipService;
        
	@Test
	public void test() {

		shipRepository.save(new Ship(1,"Starship"));
        
		Ship mockShip = new Ship();
		
        when(shipRepository.findById(mockShip.getId())).thenReturn(mockShip);

        Ship result = shipService.getShip(mockShip.getId());

        assertEquals(mockShip, result);
	}
}
