/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apiTest;

import entity.Company;
import entity.InfoEntity;
import entity.Person;
import facade.DBFacade;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MS
 */
public class tests {

    static DBFacade facade = DBFacade.getInstance();
    static List<Person> persons;
    static List<Company> companies;

    public tests() {
    }

    @BeforeClass
    public static void setUpClass() {

        //setup for persons
        persons = new ArrayList<>();
        companies = new ArrayList<>();

        Person person = new Person("Hans", "Hansen", "@mail.dk");
        Person person1 = new Person("Dorten", "Rasmussen", "@mail.dk");
        Person person2 = new Person("Michael", "Olesen", "@mail.dk");
        Person person3 = new Person("Stefan", "Vonnegut", "@mail.dk");

        facade.addPerson(person);
        facade.addPerson(person1);
        facade.addPerson(person2);
        facade.addPerson(person3);

        persons.add(person);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        //setup for company
        Company company = new Company("DBA", "Sælger din lort", 201010, 3, 100000, "dba@dk");
        Company company1 = new Company("Snaps A/S", "Vi laver snaps", 24832, -7, 1337, "123@asd.dk");

        facade.addCompany(company);
        facade.addCompany(company1);

        companies.add(company);
        companies.add(company1);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getSingleEntity() {

        Person dbUser = facade.getPersonByID(persons.get(1).getId());

        Person listUser = persons.get(1);

        assertEquals(listUser, dbUser);

    }

    @Test
    public void getAllEntities() {

        List<Person> dbPersons = facade.getPersonsList();

        assertEquals(persons, dbPersons);

    }

    @Test
    public void getSingleCompany() {

        Company dbCompany = facade.getCompanyByID(companies.get(1).getId());

        Company listCompany = companies.get(1);

        assertEquals(listCompany, dbCompany);

    }

    @Test
    public void getAllCompanies() {

        List<Company> dbCompanies = facade.getCompaniesList();

        assertEquals(companies, dbCompanies);

    }

}
