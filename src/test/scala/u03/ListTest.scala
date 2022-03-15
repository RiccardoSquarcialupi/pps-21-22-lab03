package u03

import org.junit.*
import org.junit.Assert.*
import u03.Lists.*
import u03.Streams.*

class ListTest:

  import List.*

  private var l: List[Int] = Cons(10, Cons(20, Cons(30, Nil())))
  private var p: List[Person] = Cons(Person.Teacher("1", "ita"), Cons(Person.Teacher("2", "eng"), Cons(Person.Student("3", 1999), Nil())))
  private var lst: List[Int] = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))

  @Test def testSum(): Unit =
    assertEquals(0, sum(Nil()))
    assertEquals(60, sum(l))

  @Test def testMap(): Unit =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), map(l)(_ + 1))
    assertEquals(Cons("10", Cons("20", Cons("30", Nil()))), map(l)(_ + ""))

  @Test def testFilter(): Unit =
    assertEquals(Cons(20, Cons(30, Nil())), filter(l)(_ >= 20))
    assertEquals(Cons(10, Cons(30, Nil())), filter(l)(_ != 20))

  @Test def testDrop(): Unit =
    assertEquals(Cons(30, Nil()), drop(l, 2))
    assertEquals(Cons(20, Cons(30, Nil())), drop(l, 1))

  @Test def testAppend(): Unit =
    assertEquals(Cons(10, Cons(20, Cons(30, Cons(30, Cons(30, Nil()))))), append(l, Cons(30, Cons(30, Nil()))))
    assertEquals(Cons(30, Cons(30, Nil())), append(Nil(), Cons(30, Cons(30, Nil()))))

  @Test def testFlatMap(): Unit =
    assertEquals(Cons(11, Cons(21, Cons(31, Nil()))), flatMap(l)(v => Cons(v + 1, Nil())))
    assertEquals(Cons(11, Cons(12, Cons(21, Cons(22, Cons(31, Cons(32, Nil())))))), flatMap(l)(v => Cons(v + 1, Cons(v + 2, Nil()))))

  @Test def testMax(): Unit =
    assertEquals(Some(30), max(l))
    assertEquals(None, max(Nil()))

  @Test def testListOfCourses(): Unit =
    assertEquals(Cons("ita", Cons("eng", Nil())), listOfCourses(p))
    assertEquals(Nil(), listOfCourses(Cons(Person.Student("3", 1999), Nil())))

  @Test def testFoldRightAndLeft(): Unit =
    assertEquals(-16, foldLeft(lst)(0)(_ - _))
    assertEquals(-8, foldRight(lst)(0)(_ - _))
    assertEquals("Numero3715", foldLeft(lst)("Numero")(_ + _))