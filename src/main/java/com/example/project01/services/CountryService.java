package com.example.project01.services;
import java.util.*;
import com.example.project01.beans.Country;
import com.example.project01.controllers.AddResponse;
import com.example.project01.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component //for autowired
//component("<any_name>") - can give bean name explicitly
//by default bean name = class names in all smalls
@Service
public class CountryService {
    static HashMap<Integer , Country> countryIdMap ;
//    public CountryService() {
//        countryIdMap = new HashMap<>();
//        Country indiaCountry = new Country(1,"India","Delhi");
//        Country usaCountry = new Country(2,"USA","Washington");
//        Country ukCountry = new Country(3,"UK","London");
//        countryIdMap.put(1,indiaCountry);
//        countryIdMap.put(2,usaCountry);
//        countryIdMap.put(3,ukCountry);
//    }
    //property based DI
    @Autowired
    private CountryRepository countryrep ;
    public List getAllCountries(){
//        List countries = new ArrayList(countryIdMap.values());
//        return countries;
        return countryrep.findAll();  //returns in form of JSON
    }

    public Country getCountryById(int id){
     //   Country country = countryIdMap.get(id);
     //   return country ;
        return countryrep.findById(id).get();
    }

    public Country getCountryByName(String countryName){
//        Country country = null ;
//        for(int i : countryIdMap.keySet()){
//            if(countryIdMap.get(i).getCountryName().equals(countryName))
//                country = countryIdMap.get(i);
//        }
//        return country ;
       List<Country> countries = countryrep.findAll(); //findAll return list
        Country country = null ;
        for(Country con : countries){
            if(con.getCountryName().equalsIgnoreCase(countryName))
                country = con ;
        }
        return country ;
    }

    public Country addCountry(Country country){
//        country.setId(getMaxId());
//        countryIdMap.put(country.getId() , country);
//        return country ;
        country.setId(getMaxId());
        countryrep.save(country);   //to save data in table
        return country ;
    }

    public int getMaxId(){
//        int max = 0 ;
//        for(int id:countryIdMap.keySet())
//            if(max <=id)
//                max = id ;
//    return max+1;
        return countryrep.findAll().size()+1;
    }

    public Country updateCountry(Country country){
//        if(country.getId()>0)
//            countryIdMap.put(country.getId() , country);
//        return country ;
        countryrep.save(country);
        return country ;
    }

    public AddResponse deleteCountry(int id){
//        countryIdMap.remove(id);
//        AddResponse res = new AddResponse();
//        res.setMsg("Country deleted");
//        res.setId(id);
//        return res ;
        countryrep.deleteById(id);
        AddResponse res = new AddResponse();
        res.setMsg("Country deleted");
        res.setId(id);
        return res;

    }
}
