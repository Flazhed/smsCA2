package rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import entity.exceptions.PersonNotFoundException;
import facade.DBFacade;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Stefan Duro <stefduro@gmail.com>
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private static DBFacade dbf;
    private final Gson gson;

    public PersonResource() {
        dbf = DBFacade.getInstance();
        gson = new Gson();
    }

    
    
    @GET
    @Path("getPersonsByHobby/{id}")
    @Produces("application/json")
    public String getPersonsByHobby(@PathParam("id") int id){
        
        JsonArray persons = new JsonArray();

        for (Person p : dbf.getPersonsByHobby(dbf.getHobbyById(id))) {

           JsonObject jo = new JsonObject();

            jo.addProperty("id", p.getId());
            jo.addProperty("firstName", p.getFirstName());
            jo.addProperty("lastName", p.getLastName());
            jo.addProperty("email", p.getEmail());

            //Missing part, uncommented because it gives nullpointers from the DB
            // As they are not set in the db.
            if (p.getAddress()!=(null)) {
                jo.addProperty("street", p.getAddress().getStreet());
                jo.addProperty("city", p.getAddress().getCityInfo().getCity());
                jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
            }
            if (p.getHobbies()!=(null)) {
                JsonArray hobbies = new JsonArray();
                for (Hobby hobby : p.getHobbies()) {

                    JsonObject h = new JsonObject();
                    h.addProperty("id", hobby.getId());
                    h.addProperty("description", hobby.getDescription());
                    h.addProperty("name", hobby.getName());
                    hobbies.add(h);
                }
                jo.add("hobbies", hobbies);
            }
            if(p.getPhoneNumbers()!=null){
            JsonArray phones = new JsonArray();
            for (Phone phone : p.getPhoneNumbers()) {

                JsonObject h = new JsonObject();
                h.addProperty("phoneNumber", phone.getNumber());
                phones.add(h);
            }
            jo.add("phones", phones);
            }
            persons.add(jo);
            
        }

        return gson.toJson(persons);
        
        
    }
    
    @GET
    @Path("zipCode/{zip}")
    @Produces("application/json")
    public String getPersonsByZipCode(@PathParam("zip") int zip) {

        JsonArray persons = new JsonArray();

        for (Person p : dbf.getPersonsByZipCode(zip)) {

           JsonObject jo = new JsonObject();

            jo.addProperty("id", p.getId());
            jo.addProperty("firstName", p.getFirstName());
            jo.addProperty("lastName", p.getLastName());
            jo.addProperty("email", p.getEmail());

            //Missing part, uncommented because it gives nullpointers from the DB
            // As they are not set in the db.
            if (p.getAddress()!=(null)) {
                jo.addProperty("street", p.getAddress().getStreet());
                jo.addProperty("city", p.getAddress().getCityInfo().getCity());
                jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
            }
            if (p.getHobbies()!=(null)) {
                JsonArray hobbies = new JsonArray();
                for (Hobby hobby : p.getHobbies()) {

                    JsonObject h = new JsonObject();
                    h.addProperty("id", hobby.getId());
                    h.addProperty("description", hobby.getDescription());
                    h.addProperty("name", hobby.getName());
                    hobbies.add(h);
                }
                jo.add("hobbies", hobbies);
            }
            if(p.getPhoneNumbers()!=null){
            JsonArray phones = new JsonArray();
            for (Phone phone : p.getPhoneNumbers()) {

                JsonObject h = new JsonObject();
                h.addProperty(phone.getNumber(), phone.getDescription());
                phones.add(h);
            }
            jo.add("phones", phones);
            }
            persons.add(jo);
            
        }

        return gson.toJson(persons);

    }

    @GET
    @Path("complete")
    @Produces("application/json")
    public String getAllPersons() {

        JsonArray persons = new JsonArray();

        for (Person p : dbf.getPersonsList()) {

            JsonObject jo = new JsonObject();

            jo.addProperty("id", p.getId());
            jo.addProperty("firstName", p.getFirstName());
            jo.addProperty("lastName", p.getLastName());
            jo.addProperty("email", p.getEmail());

            //Missing part, uncommented because it gives nullpointers from the DB
            // As they are not set in the db.
            if (p.getAddress()!=(null)) {
                jo.addProperty("street", p.getAddress().getStreet());
                jo.addProperty("city", p.getAddress().getCityInfo().getCity());
                jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
            }
            if (p.getHobbies()!=(null)) {
                JsonArray hobbies = new JsonArray();
                for (Hobby hobby : p.getHobbies()) {

                    JsonObject h = new JsonObject();
                    h.addProperty("id", hobby.getId());
                    h.addProperty("description", hobby.getDescription());
                    h.addProperty("name", hobby.getName());
                    hobbies.add(h);
                }
                jo.add("hobbies", hobbies);
            }
            if(p.getPhoneNumbers()!=null){
            JsonArray phones = new JsonArray();
            for (Phone phone : p.getPhoneNumbers()) {

                JsonObject h = new JsonObject();
                h.addProperty("phoneNumber", phone.getNumber());
                phones.add(h);
            }
            jo.add("phones", phones);
            }
            persons.add(jo);
        }

        return gson.toJson(persons);
    }

    @GET
    @Path("complete/{person_id}")
    @Produces("application/json")
    public String getAllPersonById(@PathParam("person_id") int id) throws PersonNotFoundException {

        Person p1 = dbf.getPersonByID(id);

        JsonObject jo = new JsonObject();

        jo.addProperty("id", p1.getId());
        jo.addProperty("firstName", p1.getFirstName());
        jo.addProperty("lastName", p1.getLastName());
        jo.addProperty("email", p1.getEmail());

        //Missing part, uncommented because it gives nullpointers from the DB
        // As they are not set in the db.
//        jo.addProperty("street", p.getAddress().getStreet());
//        jo.addProperty("city", p.getAddress().getCityInfo().getCity());
//        jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
//        JsonArray hobbies = new JsonArray();
//        for (Hobby hobby : p.getHobbies()) {
//            
//            JsonObject h = new JsonObject();
//            h.addProperty(hobby.getName(), hobby.getDescription());
//        }
//        jo.add("hobbies", hobbies);
        JsonArray phones = new JsonArray();
        for (Phone phone : p1.getPhoneNumbers()) {

            JsonObject h = new JsonObject();
            h.addProperty(phone.getNumber(), phone.getDescription());
            phones.add(h);
        }
        jo.add("phones", phones);

        return gson.toJson(jo);
    }

    @GET
    @Path("contactinfo")
    @Produces("application/json")
    public String getAllPersonsContactInfo() {

        JsonArray persons = new JsonArray();

        for (Person p1 : dbf.getPersonsList()) {

            JsonObject jo = new JsonObject();

            jo.addProperty("firstName", p1.getFirstName());
            jo.addProperty("lastName", p1.getLastName());
            jo.addProperty("email", p1.getEmail());

            //Missing part, uncommented because it gives nullpointers from the DB
            // As they are not set in the db.
//        jo.addProperty("street", p.getAddress().getStreet());
//        jo.addProperty("city", p.getAddress().getCityInfo().getCity());
//        jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
//        JsonArray hobbies = new JsonArray();
//        for (Hobby hobby : p.getHobbies()) {
//            
//            JsonObject h = new JsonObject();
//            h.addProperty(hobby.getName(), hobby.getDescription());
//        }
//        jo.add("hobbies", hobbies);
            JsonArray phones = new JsonArray();
            for (Phone phone : p1.getPhoneNumbers()) {

                JsonObject h = new JsonObject();
                h.addProperty(phone.getNumber(), phone.getDescription());
                phones.add(h);
            }
            jo.add("phones", phones);

            persons.add(jo);
        }

        return gson.toJson(persons);
    }

    @GET
    @Path("contactinfo/{person_id}")
    @Produces("application/json")
    public String getAllPersonsContactInfoById(@PathParam("person_id") int id) throws PersonNotFoundException {

        Person p1 = dbf.getPersonByID(id);

        JsonObject jo = new JsonObject();

        jo.addProperty("firstName", p1.getFirstName());
        jo.addProperty("lastName", p1.getLastName());
        jo.addProperty("email", p1.getEmail());

        //Missing part, uncommented because it gives nullpointers from the DB
        // As they are not set in the db.
//        jo.addProperty("street", p.getAddress().getStreet());
//        jo.addProperty("city", p.getAddress().getCityInfo().getCity());
//        jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
//        JsonArray hobbies = new JsonArray();
//        for (Hobby hobby : p.getHobbies()) {
//            
//            JsonObject h = new JsonObject();
//            h.addProperty(hobby.getName(), hobby.getDescription());
//        }
//        jo.add("hobbies", hobbies);
        JsonArray phones = new JsonArray();
        for (Phone phone : p1.getPhoneNumbers()) {

            JsonObject h = new JsonObject();
            h.addProperty(phone.getNumber(), phone.getDescription());
            phones.add(h);
        }
        jo.add("phones", phones);

        return gson.toJson(jo);
    }

    @POST
    @Consumes("application/json")
    public void addPerson(String content) {

        
        
        JsonObject jo = new JsonParser().parse(content).getAsJsonObject();
        
        Phone p = new Phone(jo.get("phones").getAsString(), "Fastnet");
        
        dbf.addPhoneNumber(p);

        Person tempPerson = new Person(
                jo.get("firstName").getAsString(),
                jo.get("lastName").getAsString(),
                jo.get("email").getAsString()
        );

        dbf.addPerson(tempPerson);
        
        dbf.addPhoneNumberToEntity(tempPerson, p);
    }

    @PUT
    @Consumes("application/json")
    public void editPerson(String content) {

        Type type = new TypeToken<Person>() {
        }.getType();

        Person person = gson.fromJson(content, type);
        System.out.println(person.getFirstName());
        System.out.println("");;
        System.out.println(person.getHobbies().size());
        dbf.editPerson(person);
    }

    @POST
    @Consumes("application/json")
    @Path("hobby/{person_id}")
    public void addHobbyById(@PathParam("person_id") int id, String content) throws PersonNotFoundException {

        Person person = dbf.getPersonByID(id);

        Type type = new TypeToken<Hobby>() {
        }.getType();

        Hobby hobby = gson.fromJson(content, type);

        dbf.addHobbyToPerson(hobby, person);

    }

    @DELETE
    @Consumes("application/json")
    public void deletePerson(String content) throws PersonNotFoundException {

        JsonObject jo = new JsonParser().parse(content).getAsJsonObject();

        int id = jo.get("id").getAsInt();

        Person tempPerson = dbf.getPersonByID(id);

        dbf.deletePerson(tempPerson);
    }

    @POST
    @Consumes("text/plain")
    @Path("find")
    @Produces("application/json")
    public String searchByName(String content) throws PersonNotFoundException {

        JsonArray persons = new JsonArray();

        for (Person p1 : dbf.getPersonsByNameSearch(content)) {

            JsonObject jo = new JsonObject();

            jo.addProperty("id", p1.getId());
            jo.addProperty("firstName", p1.getFirstName());
            jo.addProperty("lastName", p1.getLastName());
            jo.addProperty("email", p1.getEmail());

            //Missing part, uncommented because it gives nullpointers from the DB
            // As they are not set in the db.
//        jo.addProperty("street", p.getAddress().getStreet());
//        jo.addProperty("city", p.getAddress().getCityInfo().getCity());
//        jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
//        JsonArray hobbies = new JsonArray();
//        for (Hobby hobby : p.getHobbies()) {
//            
//            JsonObject h = new JsonObject();
//            h.addProperty(hobby.getName(), hobby.getDescription());
//        }
//        jo.add("hobbies", hobbies);
            JsonArray phones = new JsonArray();
            for (Phone phone : p1.getPhoneNumbers()) {

                JsonObject h = new JsonObject();
                h.addProperty(phone.getNumber(), phone.getDescription());
                phones.add(h);
            }
            jo.add("phones", phones);

            persons.add(jo);
        }

        return gson.toJson(persons);

    }

    @POST
    @Consumes("text/plain")
    @Path("phone/find")
    @Produces("application/json")
    public String searchByPhone(String content) throws PersonNotFoundException {

        Person p1 = dbf.getPersonByPhoneNumber(content);

        JsonObject jo = new JsonObject();

        jo.addProperty("id", p1.getId());
        jo.addProperty("firstName", p1.getFirstName());
        jo.addProperty("lastName", p1.getLastName());
        jo.addProperty("email", p1.getEmail());

        //Missing part, uncommented because it gives nullpointers from the DB
        // As they are not set in the db.
//        jo.addProperty("street", p.getAddress().getStreet());
//        jo.addProperty("city", p.getAddress().getCityInfo().getCity());
//        jo.addProperty("zipCode", p.getAddress().getCityInfo().getZipCode());
//        JsonArray hobbies = new JsonArray();
//        for (Hobby hobby : p.getHobbies()) {
//            
//            JsonObject h = new JsonObject();
//            h.addProperty(hobby.getName(), hobby.getDescription());
//        }
//        jo.add("hobbies", hobbies);
        JsonArray phones = new JsonArray();
        for (Phone phone : p1.getPhoneNumbers()) {

            JsonObject h = new JsonObject();
            h.addProperty(phone.getNumber(), phone.getDescription());
            phones.add(h);
        }
        jo.add("phones", phones);

        return gson.toJson(jo);

    }

}
