package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    @Override
    public Person getPersonByPhoneNumber(String phoneNumber) {
        Person p;
        Query q = em.createNamedQuery("Phone.findByNumber");
        q.setParameter("phoneNumber", phoneNumber);
        Phone ph = (Phone) q.getSingleResult();
        return (Person) ph.getInfoEntity();//Cast crime?

    }

    @Override
    public Phone addPhoneNumber(Phone phone) {

        em.getTransaction().begin();
        em.persist(phone);
        em.getTransaction().commit();
        return phone;
    }

    @Override
    public InfoEntity addPhoneNumberToEntity(InfoEntity infoEntity, Phone phone) {
        em.getTransaction().begin();
        em.persist(phone);
        infoEntity.addPhoneNumber(phone);
        em.merge(infoEntity);
        em.getTransaction().commit();
        return infoEntity;
    }

    @Override
    public Company getCompanyByCVR(int cvr) {

        Query q = em.createNamedQuery("Company.findByCVR");
        q.setParameter("cvr", cvr);
        Company c = (Company) q.getSingleResult();
        if (c == null) {
            //ERRORCODE
        }
        return c;
    }

    @Override
    public Hobby addHobby(Hobby hobby) {

        em.getTransaction().begin();
        em.persist(hobby);
        em.getTransaction().commit();
        return hobby;
    }

    @Override
    public Hobby editHobby(Hobby hobby) {
        em.getTransaction().begin();
        em.merge(hobby);
        em.getTransaction().commit();
        return hobby;
    }

    @Override
    public Address addAdress(Address address, int zipCode) {
        Query q = em.createNamedQuery("CityInfo.findByZip", CityInfo.class);
        q.setParameter("zipCode", zipCode);
        CityInfo city = (CityInfo) q.getSingleResult();
        address.setCityInfo(city);
        city.addAdress(address);
        em.getTransaction().begin();
        em.persist(address);
        em.merge(city);
        em.getTransaction().commit();
        return address;
    }

    @Override
    public CityInfo addCity(CityInfo cityInfo) {

        em.getTransaction().begin();
        em.persist(cityInfo);
        em.getTransaction().commit();
        return cityInfo;

    }

    @Override
    public Person addHobbyToPerson(Hobby hobby, Person person) {

        person.addHobby(hobby);
        em.getTransaction().begin();
        em.getTransaction().commit();

        return person;
    }

    @Override
    public InfoEntity addAddressToInfoEntity(Address address, InfoEntity infoEntity) {

        infoEntity.setAddress(address);
        address.addInfoEntity(infoEntity);
        em.getTransaction().begin();
        em.merge(infoEntity);
        em.merge(address);
        em.getTransaction().commit();
        return infoEntity;
    }

    @Override
    public List<Person> getPersonsByHobby(Hobby hobby) {

        Query q = em.createNamedQuery("Hobby.findPersonByHobby");
        q.setParameter("hobbyName", hobby.getName());
        Hobby requstedHobby = (Hobby) q.getSingleResult();
        List<Person> persons = requstedHobby.getPersons();
        return persons;

    }

    @Override
    public Hobby getHobbyById(int id) {
        Hobby hobby = em.find(Hobby.class, id);
        if (hobby == null) {
            //room for error code
        }
        return hobby;
    }

    @Override
    public List<Person> getPersonsByZipCode(int zipCode) {

        List<Person> persons = new ArrayList<>();
        Query q = em.createNamedQuery("CityInfo.findByZip", CityInfo.class);
        q.setParameter("zipCode", zipCode);
        CityInfo city = (CityInfo) q.getSingleResult();
        if (city != null) {
            for (Address address : city.getAdresses()) {
                for (InfoEntity infoEntity : address.getInfoEntities()) {
                    if (infoEntity.getClass() == Person.class) {
                        persons.add((Person) infoEntity);
                    }
                }
            }
        }

        return persons;
    }

    @Override
    public long getPersonCountByHobby(Hobby hobby) {

        Query q = em.createNamedQuery("Hobby.findPersonCountByHobby");
        q.setParameter("hobbyID", hobby.getId());
        long result = (long) q.getSingleResult();
        return result;

    }

    @Override
    public List<CityInfo> getAllCityInfos() {

        List<CityInfo> cityInfos = em.createNamedQuery("CityInfo.findAll").getResultList();
        return cityInfos;
    }

    @Override
    public List<Company> getCompaniesByEmployeeCount(int empCount) {

        Query q = em.createNamedQuery("Company.findByEmployeeCount");
        q.setParameter("empCount", empCount);
        List<Company> companies = q.getResultList();
        return companies;

    }

    @Override
    public Person deletePerson(Person person) {

        em.getTransaction().begin();
        em.remove(person);
        em.getTransaction().commit();
        return person;

    }

    @Override
    public List<Person> getPersonsByNameSearch(String search) {

        search = "%" + search.toUpperCase() + "%";
        Query q = em.createNamedQuery("Person.findByName");
        q.setParameter("search", search);
        List<Person> persons = q.getResultList();
        return persons;

    }

    @Override
    public List<Company> getCompaniesBySearch(String search) {
        
        
        search = "%" + search.toUpperCase() + "%";
        Query q = em.createNamedQuery("Company.findByNameSearch");
        q.setParameter("search", search);
        List<Company> companies = q.getResultList();
        return companies;
    }

}
