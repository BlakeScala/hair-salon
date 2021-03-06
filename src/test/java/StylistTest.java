import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
  Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
  assertEquals(true, stylist instanceof Stylist);
  }

  @Test
  public void getName_returnsName_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("Sue", stylist.getName());
  }

  @Test
  public void getEmail_returnsEmail_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("sue82@gmail.com", stylist.getEmail());
  }

  @Test
  public void getPhone_returnsPhone_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("888-328-3232", stylist.getPhone());
  }

  @Test
  public void getAvailability_returnsAvailability_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("MTWThF", stylist.getAvailability());
  }

  @Test
  public void getSpeciality_returnSpeciality_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("long hair", stylist.getSpeciality());
  }

  @Test
  public void equals_returnsTrueIfAttributesAreTheSame() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    Stylist stylistTwo = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertTrue(stylist.equals(stylistTwo));
  }

  @Test
  public void save_savesStylistToDatabase() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    assertTrue(Stylist.all().get(0).equals(stylist));
  }

  @Test
  public void all_returnsAllInstancesOfStylist() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    Stylist stylistTwo = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    stylistTwo.save();
    assertTrue(Stylist.all().get(0).equals(stylist));
    assertTrue(Stylist.all().get(1).equals(stylistTwo));
  }

  @Test
  public void getId_returnsCorrectId() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    assertTrue(stylist.getId() > 0);
  }

  @Test
  public void find_returnsObjectWithSameId() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    Stylist stylistTwo = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    stylistTwo.save();
    assertTrue(Stylist.find(stylistTwo.getId()).equals(stylistTwo));
  }

  @Test
  public void getClients_returnsAllClients() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    Client client = new Client("Jim", "724-373-2729", "jimmyjim@gmail.com", stylist.getId());
    Client clientTwo = new Client("Billy", "878-224-9237", "billy@gmail.com", stylist.getId());
    client.save();
    clientTwo.save();
    Client[] clients = new Client[] { client, clientTwo };
    assertTrue(stylist.getClients().containsAll(Arrays.asList(clients)));
  }

  @Test
  public void update_updatesStylist_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    stylist.update("Sue", "sue82@gmail.com", "555-555-3232", "MTWThFS", "long hair", stylist.getId());
    assertEquals(Stylist.find(stylist.getId()).getPhone(), "555-555-3232");
  }

  @Test
  public void delete_deletesStylist() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    stylist.save();
    stylist.delete(stylist.getId());
    assertEquals(null, Stylist.find(stylist.getId()));
  }
}
