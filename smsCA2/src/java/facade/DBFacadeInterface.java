/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
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
  
}
