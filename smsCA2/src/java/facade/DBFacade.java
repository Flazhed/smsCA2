package facade;

import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Søren
 */
public class DBFacade implements DBFacadeInterface {

    private static DBFacade instance;
    private EntityManager em;

    private DBFacade() {

        em = Persistence.createEntityManagerFactory("smsCA2PU").createEntityManager();

    }

    public static DBFacade getInstance() {

        if (instance == null) {
            instance = new DBFacade();
        }

        return instance;
    }

    @Override
    public List<Person> getPersonsList() {

        List<Person> persons = em.createNamedQuery("Person.findAll").getResultList();
        return persons;
    }

    @Override
    public Person getPersonByID(int id) {

        Person person = em.find(Person.class, id);
        if (person == null) {
            //room for error code
        }
        return person;

    }

    @Override
    public List<Company> getCompaniesList() {

        List<Company> companies = em.createNamedQuery("Company.findAll").getResultList();
        return companies;
    }

    @Override
    public Company getCompanyByID(int id) {

        Company company = em.find(Company.class, id);
        if (company == null) {
            //room for error code
        }
        return company;

    }

    @Override
    public Person addPerson(Person person) {
        
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        return em.find(Person.class, person.getId());
        
        
    }

    @Override
    public Company addCompany(Company company) {

        em.getTransaction().begin();
        em.persist(company);
        em.getTransaction().commit();
        return em.find(Company.class, company.getId());
    }

    @Override
    public Person editPerson(Person person) {

        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        return person;
    }

    @Override
    public Company editCompany(Company company) {
        
        em.getTransaction().begin();
        em.merge(company);
        em.getTransaction().commit();
        return company;
        
    }

}
