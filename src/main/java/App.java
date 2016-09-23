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
  }
}
