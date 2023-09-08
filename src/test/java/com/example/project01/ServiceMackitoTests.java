package com.example.project01;

import com.example.project01.beans.Country;
import com.example.project01.repository.CountryRepository;
import com.example.project01.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;  //available in Junit5(default in SpringB)
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.class)   //to execute test methods by order
@SpringBootTest(classes = {ServiceMackitoTests.class})
//external specification
public class ServiceMackitoTests {
    //mocking of service and repository classes
    @Mock
    CountryRepository countryrep ;
    public List<Country> mycountries ;
    @InjectMocks  //to invoke the service class
    CountryService countryService ;

    @Test
    @Order(1)  //to specify the sequence for execution
    public void test_getAllCountries(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1 , "India" , "Delhi"));
        mycountries.add(new Country(2 , "USA" ,"Washington"));
        when(countryrep.findAll()).thenReturn(mycountries);
        countryService.getAllCountries(); //returns all countries from mock data
        assertEquals(2 , countryService.getAllCountries().size());
    }
    @Test
    @Order(2)
    public void test_getCountryById(){
        List<Country> mycountries = new ArrayList<Country>();
        mycountries.add(new Country(1 , "India" , "Delhi"));
        mycountries.add(new Country(2 , "USA" ,"Washington"));
        int countryId=1 ;
        when(countryrep.findAll()).thenReturn(mycountries);
        //the id returned must be equal to countryId that is 1
        countryService.getCountryById(countryId);
        assertEquals(countryId ,countryService.getCountryById(countryId).getId());

    }
    @Test
    @Order(3)
    public void test_getCountryByName(){
        List<Country> mycountries = new ArrayList<>();
        mycountries.add(new Country(1 , "India" , "Delhi"));
        mycountries.add(new Country(2 , "USA" , "Washington"));
        String countryName = "India";
        when(countryrep.findAll()).thenReturn(mycountries);  //mocking
        assertEquals(countryName , countryService.getCountryByName(countryName).getCountryName());
    }
    @Test
    @Order(4)
    public void test_addCountry(){
        Country country = new Country(3 , "Germany" , "Berlin");
        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country , countryService.addCountry(country));
    }
    @Test
    @Order(5)
    public void test_updateCountry(){
        Country country = new Country(3 , "Germany" , "Berlin");
        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country , countryService.updateCountry(country));
    }
    @Test
    @Order(6)
    public void test_delete(){
        Country country = new Country(3 , "Germany" , "Berlin");
        countryService.deleteCountry(country.getId());
        verify(countryrep , times(1)).delete(country);
    }
}
