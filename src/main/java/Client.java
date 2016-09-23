import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Client {
  private int id;
  private String name;
  private String phone;
  private String email;
  private int stylistId;

  public Client(String name, String phone, String email, int stylistId) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
