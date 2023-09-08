package com.example.project01.beans;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//bean class
@Entity
@Table(name="Country")  //table name and class name must be same
public class Country {

    public Country(){}
    public Country(int id , String countryName , String countryCapital){
        this.id = id ;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    @Id    //only for primary key
    @Column(name="id")
    int id ;
    @Column(name="country_name")
    String countryName ;
    @Column(name="capital")
    String countryCapital ;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCountryCapital() {
        return countryCapital;
    }
    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }


}
