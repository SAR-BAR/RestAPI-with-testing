package com.example.project01.controllers;
import java.util.*;

import com.example.project01.beans.Country;
import com.example.project01.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //to let springB know that corres. class is controller
public class CountryController {
    @Autowired        //dependency injection
    CountryService countryService ;

    @GetMapping("/getcountries")
   public ResponseEntity<List<Country>> getCountries(){
//        return countryService.getAllCountries();
        try{
            List<Country> countries = countryService.getAllCountries();
            return new ResponseEntity<>(countries , HttpStatus.FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getcountriesById(@PathVariable(value="id") int id){
//        return countryService.getCountryById(id);
        try{
            Country country = countryService.getCountryById(id);
            return new ResponseEntity<Country>(country , HttpStatus.OK);
        }catch(Exception e ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName){
//        return countryService.getCountryByName(countryName);
        try{
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country , HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
//       return countryService.addCountry(country);
        try{
            country = countryService.addCountry(country);
            return new ResponseEntity<>(country , HttpStatus.CREATED);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id , @RequestBody Country country){
//        return countryService.updateCountry(country);
        try{
            Country existCountry = countryService.getCountryById(id);
            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());
            Country updated_country = countryService.updateCountry(existCountry);
             return new ResponseEntity<Country>(country , HttpStatus.OK) ;
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deletecountry/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable(value="id") int id){
//        return  countryService.deleteCountry(id);
        Country country = null ;
        try{
            country = countryService.getCountryById(id);
            countryService.deleteCountry(country.getId());
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(country , HttpStatus.OK);
    }

}


