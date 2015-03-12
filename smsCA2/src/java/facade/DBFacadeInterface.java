/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import entity.exceptions.CompanyNotFoundException;
import java.util.List;

/**
 *
 * @author Søren
 */
public interface DBFacadeInterface {
  
  public Phone addPhoneNumber(Phone phone);//Skal nok ikke bruges for real
  public InfoEntity addPhoneNumberToEntity(InfoEntity infoEntity, Phone phone);
  public List<Person> getPersonsList();
  public Person getPersonByID(int id);
  public List<Company> getCompaniesList();
  public Company getCompanyByID(int id);
  public Person addPerson(Person person);
  public Company addCompany(Company company);
  public Person editPerson(Person person);
  public Company editCompany(Company company);
  public Person getPersonByPhoneNumber(String phoneNumber);
  public Company getCompanyByCVR(int cvr) throws CompanyNotFoundException;
  public List<Person> getPersonsByHobby(Hobby hobby);
  public Hobby addHobby(Hobby hobby);
  public Hobby editHobby(Hobby hobby);
  public Address addAdress(Address address, int zipCode);
  public CityInfo addCity(CityInfo cityInfo);
  public Person addHobbyToPerson(Hobby hobby, Person person);
  public InfoEntity addAddressToInfoEntity(Address address, InfoEntity infoEntity);
  public Hobby getHobbyById(int id);
  public List<Person> getPersonsByZipCode(int zipCode);
  public long getPersonCountByHobby(Hobby hobby);
  public List<CityInfo> getAllCityInfos();
  public List<Company> getCompaniesByEmployeeCount(int empCount);
  
  
}
