/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.DBFacade;

/**
 *
 * @author MS
 */
public class MockData {
    
    static DBFacade dbf = DBFacade.getInstance();
    
    public static void main(String[] args) {
        
        //person setup
        Person p = new Person("Louise", "Martinez", "lmartinez0@msu.edu");
        Person p1 = new Person("Doris", "Ford", "dford1@illinois.edu");
        Person p2 = new Person("Rachel", "Alexander", "ralexander2@furl.net");
        Person p3 = new Person("Alice", "Carter", "acarter3@marketwatch.com");
        Person p4 = new Person("Donald", "Reynolds", "dreynolds4@sohu.com");
        Person p5 = new Person("Mildred", "Fuller", "mfuller5@google.de");
        Person p6 = new Person("Lillian", "Williams", "lwilliams6@mail.ru");
        Person p7 = new Person("Bruce", "Schmidt", "bschmidt7@studiopress.com");
        Person p8 = new Person("Shawn", "Evans", "sevans8@mlb.com");
        Person p9 = new Person("Martin", "Mason", "mmason9@etsy.com");
        
        //company setup
        Company c = new Company("Photojam", "google.co.jp", 113598, 30, 3083, "pmorris0@microsoft.com");
        Company c1 = new Company("Layo", "wordpress.com", 116250, 19, 5793, "dmontgomery1@admin.ch");
        Company c2 = new Company("Realblab", "oaic.gov.au", 177445, 28, 3434, "rcole2@wix.com");
        Company c3 = new Company("Gevee", "sina.com.cn", 153162, 15, 30015, "tsanchez3@addtoany.com");
        Company c4 = new Company("Meevee", "exblog.jp", 157658, 7, 6078, "hmason4@telegraph.co.uk");
        Company c5 = new Company("Zoovu", "china.com.cn", 170069, 3, 8463, "hstanley5@senate.gov");
        Company c6 = new Company("Centizu", "deviantart.com", 173392, 25, 6309, "jbaker6@springer.com");
        Company c7 = new Company("Shufflebeat", "baidu.com", 116812, 13, 69332, "pgardner7@springer.com");
        Company c8 = new Company("Buzzshare", "dot.gov", 194633, 6, 367, "ddavis8@samsung.com");
        Company c9 = new Company("Kazu", "yahoo.com", 158575, 17, 7589, "eellis9@indiegogo.com");
        
        //CitiInfo
        CityInfo ci = new CityInfo(2800, "Lyngby");
        CityInfo ci1 = new CityInfo(3000, "Helsingor");
        CityInfo ci2 = new CityInfo(2860, "Soborg");
        CityInfo ci3 = new CityInfo(2980, "Karlebo");
        
        //Addresses
        Address a = new Address("LyngbyHovedgade 10", "Stor kobenhavn");
        Address a1 = new Address("Koglevej 12", "..");
        Address a2 = new Address("Helsingevej 51", "..");
        Address a3 = new Address("Liljevej 89", "..");
        Address a4 = new Address("Egeskovvej 67", "..");
        Address a5 = new Address("Gummivej 17", "..");
        Address a6 = new Address("Dynamovej 617", "..");
        Address a7 = new Address("Jupitorvej 57", "..");
        Address a8 = new Address("Ullavej 667", "..");
        Address a9 = new Address("Roskildevej 103", "..");
        
        //Phone nr 
        
        Phone ph = new Phone("58938423", "Privat nummer");
        Phone ph1 = new Phone("32523532", "Privat nummer");
        Phone ph2 = new Phone("32512333", "Privat nummer");
        Phone ph3 = new Phone("15145355", "Privat nummer");
        Phone ph4 = new Phone("58768768", "Privat nummer");
        Phone ph5 = new Phone("57657568", "Privat nummer");
        Phone ph6 = new Phone("83736364", "Privat nummer");
        Phone ph7 = new Phone("72555443", "Privat nummer");
        Phone ph8 = new Phone("23532525", "Privat nummer");
        Phone ph9 = new Phone("88888888", "Privat nummer");
        Phone ph10 = new Phone("9963552", "Privat nummer");
        
        // Hobbies
        
        Hobby h = new Hobby("Stang Tennis", ":)");
        Hobby h1 = new Hobby("Ring Ridning", ":)");
        Hobby h2 = new Hobby("Svømning", ":)");
        Hobby h3 = new Hobby("Kendo", ":)");
        Hobby h4 = new Hobby("Billard", ":)");
        Hobby h5 = new Hobby("Kodning", ":)");
        
        
        // mockdata setup.

        //city
        dbf.addCity(ci);
        dbf.addCity(ci1);
        dbf.addCity(ci2);
        dbf.addCity(ci3);
        
        //add person
        dbf.addPerson(p);
        dbf.addPerson(p1);
        dbf.addPerson(p2);
        dbf.addPerson(p3);
        dbf.addPerson(p4);
        dbf.addPerson(p5);
        dbf.addPerson(p6);
        dbf.addPerson(p7);
        dbf.addPerson(p8);
        dbf.addPerson(p9);
        
        //add company
        dbf.addCompany(c);
        dbf.addCompany(c1);
        dbf.addCompany(c2);
        dbf.addCompany(c3);
        dbf.addCompany(c4);
        dbf.addCompany(c5);
        dbf.addCompany(c6);
        dbf.addCompany(c7);
        dbf.addCompany(c8);
        dbf.addCompany(c9);
        
        //add phones
        dbf.addPhoneNumber(ph);
        dbf.addPhoneNumber(ph1);
        dbf.addPhoneNumber(ph2);
        dbf.addPhoneNumber(ph3);
        dbf.addPhoneNumber(ph4);
        dbf.addPhoneNumber(ph5);
        dbf.addPhoneNumber(ph6);
        dbf.addPhoneNumber(ph7);
        dbf.addPhoneNumber(ph8);
        dbf.addPhoneNumber(ph9);
        dbf.addPhoneNumber(ph10);

        //add hobbies
        dbf.addHobby(h);
        dbf.addHobby(h1);
        dbf.addHobby(h2);
        dbf.addHobby(h3);
        dbf.addHobby(h4);
        dbf.addHobby(h5);
        
        //--- add too --- 
        
        //add addresse to city
        dbf.addAdress(a, ci1.getZipCode());
        dbf.addAdress(a1, ci1.getZipCode());
        dbf.addAdress(a2, ci1.getZipCode());
        dbf.addAdress(a3, ci2.getZipCode());
        dbf.addAdress(a4, ci2.getZipCode());
        dbf.addAdress(a5, ci2.getZipCode());
        dbf.addAdress(a6, ci2.getZipCode());
        dbf.addAdress(a7, ci3.getZipCode());
        dbf.addAdress(a8, ci3.getZipCode());
        dbf.addAdress(a9, ci3.getZipCode());
        
        //add phoneno to entity
        dbf.addPhoneNumberToEntity(p, ph);
        dbf.addPhoneNumberToEntity(p1, ph1);
        dbf.addPhoneNumberToEntity(p2, ph2);
        dbf.addPhoneNumberToEntity(p3, ph3);
        dbf.addPhoneNumberToEntity(p4, ph4);
        dbf.addPhoneNumberToEntity(c, ph6);
        dbf.addPhoneNumberToEntity(c1, ph7);
        dbf.addPhoneNumberToEntity(c2, ph8);
        dbf.addPhoneNumberToEntity(c3, ph9);
        
        //add hobbies to entity
        
        dbf.addHobbyToPerson(h1, p);
        dbf.addHobbyToPerson(h3, p1);
        dbf.addHobbyToPerson(h3, p2);
        dbf.addHobbyToPerson(h4, p3);
        dbf.addHobbyToPerson(h4, p4);
        dbf.addHobbyToPerson(h, p5);
        
        
        dbf.addAddressToInfoEntity(a1, p1);
        dbf.addAddressToInfoEntity(a2, p2);
        dbf.addAddressToInfoEntity(a3, p3);
        dbf.addAddressToInfoEntity(a4, p4);
        dbf.addAddressToInfoEntity(a5, p5);
        
        
    }
    
}
