package structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

  private ListInterface<String> list;

  @Before
  public void setup() {
    list = new RecursiveList<String>();
  }

  @Test (timeout = 500)
  public void testInsertFirstIsEmptySizeAndGetFirst1() {
    assertTrue("Newly constructed list should be empty.", list.isEmpty());
    assertEquals("Newly constructed list should be size 0.", 0, list.size());
    assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
    assertFalse("List should now have elements.", list.isEmpty());
    assertEquals("List should now have 1 element.", 1, list.size());
    assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
    list.insertFirst("world");
    assertEquals(2, list.size());
    list.insertFirst("foo");
    assertEquals(3, list.size());
    assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
  }
  @Test
  public void testInserts() {
    list.insertAt(0, "hello");
    assertEquals("", "hello",list.get(0));

    list.insertAt(0, "hey");
    assertEquals("", "hey", list.get(0));
    assertEquals("", "hello",list.get(1));

    list.insertFirst("yo");
    assertEquals("", "yo", list.get(0));

    list.insertLast("heyo");
    assertEquals("", "heyo", list.get(list.size() - 1));

  }

  @Test
  public void testIndexOf() {
    list.insertFirst("green");
    list.insertLast("orange");
    list.insertLast("orange");
    list.insertLast("purple");

    assertEquals("", -1, list.indexOf("yellow"));
    assertEquals("", 1, list.indexOf("orange"));
    assertEquals("", 0, list.indexOf("green"));
    assertEquals("", list.size() - 1, list.indexOf("purple"));

    list.insertFirst("orange");
    assertEquals("", 0, list.indexOf("orange"));



  }

  @Test
  public void testRemoves() {
    list.insertFirst("green");
    list.insertLast("red");
    list.insertLast("orange");
    boolean remov = list.remove("green");
    System.out.println(remov);
    

  }

}
