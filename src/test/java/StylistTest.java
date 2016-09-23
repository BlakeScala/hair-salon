import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

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
  public void getAvailability_returnsAva_true() {
    Stylist stylist = new Stylist("Sue", "sue82@gmail.com", "888-328-3232", "MTWThF", "long hair");
    assertEquals("MTWThF", stylist.getAvailability());
  }
}
