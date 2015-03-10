/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.List;

/**
 *
 * @author Søren
 */
public interface DBFacadeInterface {
    
  public List<Person> getPersonsList();
  public Person getPersonByID(int id);
  public List<Company> getCompaniesList();
  public Company getCompanyByID(int id);
  public Person addPerson(Person person);
  public Company addCompany(Company company);
  public Person editPerson(Person person);
  public Company editCompany(Company company);
  
  
}
