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
}
