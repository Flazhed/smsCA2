/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Søren
 */
@Entity
@NamedQueries({
@NamedQuery(name="CityInfo.findByZip", query="SELECT c FROM CityInfo c WHERE c.zipCode = :zipCode")
})
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer zipCode;
    private String city;
    @OneToMany(mappedBy = "cityInfo")
    private List<Address> addresses;
    public CityInfo() {
    }

    public CityInfo(Integer zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addAdress(Address adress){
        adress.setCityInfo(this);
        addresses.add(adress);
        
    }

    public List<Address> getAdresses() {
        return addresses;
    }

    public void setAdresses(List<Address> adresses) {
        this.addresses = adresses;
    }
    
    
}
