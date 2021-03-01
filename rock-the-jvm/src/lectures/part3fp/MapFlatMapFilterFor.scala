package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // print all combinations between 2 lists
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  // List("a1", "a2", ..., "d4")

  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // combinations between 3 lists
  val colors = List("black", "white")
  // interating
  val combinations2 = numbers.flatMap(num => chars.flatMap(ch => colors.map(c => "" + ch + num + "-" + c)))
  println(combinations2)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x*2
  }

  /*
    1. MyList supports for comprehensions?
        map(f: A => B) => MyGenericList[B]
        filter(p: A => Boolean) => MyGenericList[A]
        flatMap(f: A => MyGenericList[B]) => MyGenericList[B]
    2. A small collection of at most ONE element = Maybe[+T]
        - map, flatMap, filter
   */
}
