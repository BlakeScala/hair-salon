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
  
}
