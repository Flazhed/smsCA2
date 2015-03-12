/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Søren
 */
@Entity
@NamedQueries({
@NamedQuery(name ="Hobby.findPersonByHobby", query="SELECT h FROM Hobby h WHERE h.name = :hobbyName"),
@NamedQuery(name ="Hobby.findPersonCountByHobby", query="SELECT count(p) FROM Hobby h JOIN h.persons p WHERE h.id = :hobbyID")})
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons = new ArrayList<>();
// String q = "SELECT count(p) FROM Hobby p WHERE p.persons"; // select count(o) from Comic c join c.owners o
    public Hobby() {
        
       
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        
        person.getHobbies().add(this);
        persons.add(person);
    }

}
