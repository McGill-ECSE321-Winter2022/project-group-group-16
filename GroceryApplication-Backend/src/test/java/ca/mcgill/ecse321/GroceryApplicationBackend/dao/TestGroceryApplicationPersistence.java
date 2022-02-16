package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryApplicationPersistence {

    @Autowired
    private AddressRepository addressRepository;
//    @Autowired
//    private EventRepository eventRepository;
//    @Autowired
//    private RegistrationRepository registrationRepository;

//    @AfterEach
//    public void clearDatabase() {
//        // Fisrt, we clear registrations to avoid exceptions due to inconsistencies
////        registrationRepository.deleteAll();
//        // Then we can clear the other tables
//        addressRepository.deleteAll();
////        eventRepository.deleteAll();
//    }

    @Test
    public void testPersistAndLoadAddress() {
        int id = 69;
        String country = "USA";
        // First example for object save/load
        Address address = new Address();
        // First example for attribute save/load
        address.setId(id);
        address.setCountry(country);
        addressRepository.save(address);

        address = null;

        address = addressRepository.findAddressById(id);
        assertNotNull(address);
        assertEquals(id, address.getId());
        assertEquals(country, address.getCountry());
    }
}
