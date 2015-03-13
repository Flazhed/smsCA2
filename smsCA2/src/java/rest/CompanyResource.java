package rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.Company;
import entity.exceptions.CompanyNotFoundException;
import facade.DBFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Stefan Duro <stefduro@gmail.com>
 */
@Path("company")
public class CompanyResource {

    @Context
    private UriInfo context;

    private static DBFacade dbf;
    private final Gson gson;

    public CompanyResource() {
        dbf = DBFacade.getInstance();
        gson = new Gson();
    }

    @GET
    @Path("complete")
    @Produces("application/json")
    public String getAllCompanies() {

        JsonArray companies = new JsonArray();

        for (Company company : dbf.getCompaniesList()) {

            JsonObject jo = new JsonObject();

            jo.addProperty("id", company.getId());
            jo.addProperty("cvr", company.getCvr());
            jo.addProperty("name", company.getName());
            jo.addProperty("description", company.getDescription());
            jo.addProperty("email", company.getEmail());
            jo.addProperty("employees", company.getNumEmployees());
            jo.addProperty("marketValue", company.getMarketValue());

            companies.add(jo);

        }

        return gson.toJson(companies);
    }

    @GET
    @Path("complete/{cvr}")
    @Produces("application/json")
    public String getCompanyByCVR(@PathParam("cvr") int cvr) throws CompanyNotFoundException {

        Company company = dbf.getCompanyByCVR(cvr);

        JsonObject jo = new JsonObject();

        jo.addProperty("id", company.getId());
        jo.addProperty("cvr", company.getCvr());
        jo.addProperty("name", company.getName());
        jo.addProperty("description", company.getDescription());
        jo.addProperty("email", company.getEmail());
        jo.addProperty("employees", company.getNumEmployees());
        jo.addProperty("marketValue", company.getMarketValue());

        return gson.toJson(jo);
    }

    @POST
    @Consumes("application/json")
    @Path("find")
    @Produces("application/json")
    public String searchByName(String content) throws CompanyNotFoundException {

        JsonArray persons = new JsonArray();

        for (Company c1 : dbf.getCompaniesBySearch(content)) {

            JsonObject jo = new JsonObject();

            jo.addProperty("id", c1.getId());
            jo.addProperty("name", c1.getName());
            jo.addProperty("description", c1.getDescription());
            jo.addProperty("cvr", c1.getCvr());
            jo.addProperty("numEmployees", c1.getNumEmployees());
            jo.addProperty("marketValue", c1.getMarketValue());

        }

        return gson.toJson(persons);

    }

}
