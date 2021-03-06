import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("add-stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/add-stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phone = request.queryParams("phone");
      String availability = request.queryParams("availability");
      String speciality = request.queryParams("speciality");
      Stylist stylist = new Stylist(name, email, phone, availability, speciality);
      stylist.save();
      model.put("stylists", Stylist.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      model.put("clients", stylist.getClients());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      stylist.delete(stylist.getId());
      model.put("template", "templates/delete-stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("template", "templates/update-stylist.vtl");
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/update/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phone = request.queryParams("phone");
      String availability = request.queryParams("availability");
      String speciality = request.queryParams("speciality");
      stylist.update(name, email, phone, availability, speciality, stylist.getId());
      model.put("template", "templates/update-stylist-success.vtl");
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/add-client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("template", "templates/add-client.vtl");
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phone = request.queryParams("phone");
      Client client = new Client(name, phone, email, stylist.getId());
      client.save();
      model.put("template", "templates/stylist.vtl");
      model.put("clients", stylist.getClients());
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/client/:client_id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("template", "templates/client.vtl");
      model.put("client", client);
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("stylist/:id/client/:client_id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("template", "templates/update-client.vtl");
      model.put("client", client);
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id/client/:client_id/update/success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      String name = request.queryParams("name");
      String email = request.queryParams("email");
      String phone = request.queryParams("phone");
      client.update(name, phone, email, stylist.getId(), client.getId());
      model.put("stylist", stylist);
      model.put("template", "templates/update-client-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("stylist/:id/client/:client_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("template", "templates/delete-client.vtl");
      client.delete(client.getId());
      model.put("stylist", stylist);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
