package fi.kapsi.kosmik.sfti

object Chapter14 {

  /**
    * Using pattern matching, write a function swap that receives a pair of integers
    * and returns the pair with the components swapped.
    */
  object Ex02 {
    def swap(pair: (Int, Int)): (Int, Int) =
      pair match {
        case (a, b) => (b, a)
      }
  }

  /**
    * Using pattern matching, write a function swap that swaps the first two elements
    * of an array provided its length is at least two.
    */
  object Ex03 {
    def swap(array: Array[Int]): Array[Int] =
      array match {
        case Array(a, b, res@_*) => Array(b, a) ++ res
        case _ => array.clone()
      }
  }

  /**
    * Add a case class Multiple that is a subclass of the Item class. For example,
    * Multiple(10, Article("Blackwell Toaster", 29.95)) describes ten toasters. Of course,
    * you should be able to handle any items, such as bundles or multiples, in
    * the second argument. Extend the price function to handle this new case.
    */
  object Ex04 {

    abstract class Item

    case class Article(description: String, price: Double) extends Item

    case class Bundle(description: String, discount: Double, items: Item*) extends Item

    case class Multiple(quantity: Int, item: Item) extends Item

    def price(it: Item): Double = it match {
      case Article(_, p) => p
      case Bundle(_, disc, its@_*) => its.map(price).sum - disc
      case Multiple(q, i) => q * price(i)
    }
  }

  /**
    * One can use lists to model trees that store values only in the leaves. For
    * example, the list ((3 8) 2 (5)) describes the tree
    * <pre>
    * #     •
    * #    /|\
    * #   • 2 •
    * #  / \  |
    * #  3 8  5
    * </pre>
    * However, some of the list elements are numbers and others are lists. In Scala,
    * you cannot have heterogeneous lists, so you have to use a List[Any] . Write a
    * leafSum function to compute the sum of all elements in the leaves, using pattern
    * matching to differentiate between numbers and lists.
    */
  object Ex05 {
    def leafSum(tree: List[Any]): Int = {
      def rec(subTree: Any): Int =
        subTree match {
          case singleton: Int => singleton
          case x :: xs => rec(x) + rec(xs)
          case _ => 0
        }

      tree.foldLeft(0)(_ + rec(_))
    }
  }

}
