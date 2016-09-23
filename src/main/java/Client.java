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

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public int getStylistId() {
    return stylistId;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getEmail().equals(newClient.getEmail()) &&
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }
}
