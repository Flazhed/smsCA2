/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.DBFacade;

/**
 *
 * @author Søren
 */
public class Test {
    
    public static void main(String[] args) {
        //Persistence.generateSchema("smsCA2PU", null);
//        EntityManager em = Persistence.createEntityManagerFactory("smsCA2PU").createEntityManager();
        DBFacade dbf = DBFacade.getInstance();
//        Person p1 = new Person("Jannik", "Green", "Per@lars.hans");
//        Person p2 = new Person("Per", "Hansen", "Per@asd.hans");
//        Company c1 = new Company("Snaps A/S", "Vi laver snaps", 24832, -7, 1337, "123@asd.dk");
//        Phone tlf1 = new Phone("12345678", "Hjemmenummer");
        
        //System.out.println(dbf.getPersonByPhoneNumber("22").getFirstName());
        //System.out.println(dbf.getCompanyByCVR(24832).getName());
        
//        em.getTransaction().begin();
//        em.persist(tlf1);
//        p1.addPhoneNumber(tlf1);
//        em.getTransaction().commit();
//        
//        dbf.editPerson(p1);
//        
//        System.out.println(dbf.getPersonsList().size());
        
//        
//        Phone tlf2 = new Phone("22", "Det gode nummer");
//        Person p4 = new Person("Claus", "Hansen", "claus@asd.dk");
//        
//        dbf.addPerson(p4);
//        dbf.addPhoneNumberToEntity(p4, tlf2);
//        em.getTransaction().begin();
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(c1);
//        em.getTransaction().commit();
        
//        c1.setName("Per og co.");
//        dbf.editCompany(c1);
//        dbf.addPerson(p1);
//        dbf.addPerson(p2);
        
        //System.out.println(dbf.getPersonByID(1).getFirstName());
        
        
//        Address address1 = new Address("Lyngby Hovedgade", "1. TH");
        //Hobby h2 = new Hobby("Bowling", "Strike eller ikke strike");
//        Person p4 = new Person("Morten", "Jensen", "morten@asd.dk");
//        Phone phone1 = new Phone("14444", "Mobil");
//        dbf.addHobby(h2);
//        dbf.addAdress(address1, 2800);
//        dbf.addPerson(p4);
//        dbf.addAddressToInfoEntity(address1, p4);
//        dbf.addPhoneNumberToEntity(p4, phone1);
//        dbf.addHobbyToPerson(h2, p4);
        //Person p5 = new Person("Søren", "Leet", "soren@1337.com");
//        dbf.addPerson(p5);
        //Person p1 = new Person("Jannik", "Green", "Per@lars.hans");
        
//        System.out.println(dbf.getHobbyById(303).getDescription());
//        System.out.println(dbf.getPersonByID(51).getFirstName());
//        System.out.println(dbf.getPersonByID(351).getFirstName());
        
//        dbf.addHobbyToPerson(dbf.getHobbyById(401), dbf.getPersonByID(51));
//        dbf.addHobbyToPerson(dbf.getHobbyById(401), dbf.getPersonByID(351));
        //System.out.println(dbf.getPersonsByHobby(dbf.getHobbyById(401)).size());
//        System.out.println(dbf.getPersonCountByHobby(dbf.getHobbyById(486)));
//        System.out.println(dbf.getAllCityInfos().size());
//        System.out.println(dbf.getCompaniesByEmployeeCount(4).size());
        System.out.println(dbf.getPersonsByNameSearch("Jan").size());
        System.out.println(dbf.getCompaniesBySearch("buzz").size());
    }
    
}
