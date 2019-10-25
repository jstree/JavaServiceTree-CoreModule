package tutorial

import org.junit.Test
import junit.framework.TestCase
import org.junit.Assert._

class PizzaTests extends TestCase {

  var pizza: Pizza = _

  override def setUp {
    pizza = new Pizza
  }

  def testChapter1: Unit = {
    println(1+2)
    val res0 = 1 //val res0: Int = 0
    println(res0 * 313)

    var msg: java.lang.String = "313 DEV GRP"
    println(msg)
    msg = "change"
    println(msg)
    println(max(313, 131))
    println(greeting())
  }

  def max(x:Int, y:Int): Int = {
    if(x>y)
      x
    else
      y
  }

  def greeting() = {
    println("hello scala")
  }

  def testOneTopping {
    pizza.addTopping(Topping("green olives"))
    assertEquals(pizza.getToppings.size, 1)
  }

  def testAddingAndRemovingToppings {
    pizza.addTopping(Topping("green olives"))
    pizza.removeTopping(Topping("green olives"))
    assertEquals(pizza.getToppings.size, 0)
  }

}