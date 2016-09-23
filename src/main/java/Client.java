import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Client {
  private int id;
  private String name;
  private String phone;
  private String email;
  private int stylist_id;

  public Client(String name, String phone, String email, int stylist_id) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.stylist_id = stylist_id;
  }

  public int getId() {
    return id;
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

  public int getStylist_id() {
    return stylist_id;
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
             this.getStylist_id() == newClient.getStylist_id();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO clients(name, email, phone, stylist_id) VALUES (:name, :email, :phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("email", this.email)
        .addParameter("phone", this.phone)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name, String phone, String email, int stylist_id, int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name, email = :email, phone = :phone, stylist_id = :stylist_id WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("phone", phone)
      .addParameter("email", email)
      .addParameter("stylist_id", stylist_id)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
