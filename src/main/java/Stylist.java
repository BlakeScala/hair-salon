import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Stylist {
  private int id;
  private String name;
  private String email;
  private String phone;
  private String availability;
  private String speciality;
  private List<Client> clients;

  public Stylist(String name, String email, String phone, String availability, String speciality) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.availability = availability;
    this.speciality = speciality;
    clients = new ArrayList<Client>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getAvailability() {
    return availability;
  }

  public String getSpeciality() {
    return speciality;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getEmail().equals(newStylist.getEmail()) &&
             this.getPhone().equals(newStylist.getPhone()) &&
             this.getAvailability().equals(newStylist.getAvailability()) &&
             this.getSpeciality().equals(newStylist.getSpeciality());
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists(name, email, phone, availability, speciality) VALUES (:name, :email, :phone, :availability, :speciality)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("email", this.email)
        .addParameter("phone", this.phone)
        .addParameter("availability", this.availability)
        .addParameter("speciality", this.speciality)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

  public void update(String name, String email, String phone, String availability, String speciality, int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name = :name, email = :email, phone = :phone, availability= :availability, speciality= :speciality WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("email", email)
        .addParameter("phone", phone)
        .addParameter("availability", availability)
        .addParameter("speciality", speciality)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
