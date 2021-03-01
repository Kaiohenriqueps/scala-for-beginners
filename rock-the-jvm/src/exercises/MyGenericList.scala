package exercises

abstract class MyGenericList[+A] {
  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyGenericList[B]
  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B]
  def filter(predicate: A => Boolean): MyGenericList[A]

  // concatenation
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyGenericList[A]
  def zipWith[B, C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object GenericEmpty extends MyGenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyGenericList[B] = new GenericCons(element, GenericEmpty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyGenericList[B] = GenericEmpty
  def flatMap[B](transformer: Nothing => MyGenericList[B]): MyGenericList[B] = GenericEmpty
  def filter(predicate: Nothing => Boolean): MyGenericList[Nothing] = GenericEmpty

  def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = GenericEmpty
  def zipWith[B, C](list: MyGenericList[B], zip: (Nothing, B) => C): MyGenericList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else GenericEmpty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class GenericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  def head: A = h
  def tail: MyGenericList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyGenericList[B] = new GenericCons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements


  /*
    [1,2,3].filter(n%2==0) =
      = [2,3].filter(n%2==0)
      = new Cons(2, [3].filter(n%2==0)
      = new Cons(2, Empty.filter(n%2==0))
      = new Cons(2, Empty)
   */
  def filter(predicate: A => Boolean): MyGenericList[A] =
    if (predicate(h)) new GenericCons(h, t.filter(predicate))
    else t.filter(predicate)


  /*
    [1,2,3].map(n*2)
      = new Cons(2, [2,3].map(n*2))
      = new Cons(2, new Cons(4, [3].map(n*2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n*2))))
      = new Cons(2, new Cons(4, new Cons(6)))
   */
  def map[B](transformer: A => B): MyGenericList[B] =
    new GenericCons(transformer(h), t.map(transformer))

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */
  def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new GenericCons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyGenericList[B]): MyGenericList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyGenericList[A] = {
    def insert(x: A, sortedList: MyGenericList[A]): MyGenericList[A] = {
      if(sortedList.isEmpty) new GenericCons(x, GenericEmpty)
      else if(compare(x, sortedList.head) <= 0) new GenericCons(x, sortedList)
      else new GenericCons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyGenericList[B], zip: (A, B) => C): MyGenericList[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new GenericCons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(1)(+) =
    = [3].fold(3)(+) =
    = [].fold(6)(+)
    = 6
   */
  def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start, h))(operator)
}

/*
    1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3. MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      [1,2,3].map(n*2) = [2,4,6]
      [1,2,3,4].filter(n%2) = [2,4]
      [1,2,3].flatMap(n > [n, n+1]) = [1,2,2,3,3,4]
   */


object GenericListTest extends App {
  val listOfIntegers: MyGenericList[Int] = new GenericCons(1, new GenericCons(2, new GenericCons(3, GenericEmpty)))
  val cloneListOfIntegers: MyGenericList[Int] = new GenericCons(1, new GenericCons(2, new GenericCons(3, GenericEmpty)))
  val anotherListOfIntegers: MyGenericList[Int] = new GenericCons(4, new GenericCons(5, GenericEmpty))
  val listOfStrings: MyGenericList[String] = new GenericCons("Hello", new GenericCons("Scala", GenericEmpty))
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(elem => elem * 2).toString)
  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(element => new GenericCons(element, new GenericCons(element+1, GenericEmpty))).toString)

  println(cloneListOfIntegers == listOfIntegers)

  /*
      - foreach method A => Unit
        [1,2,3].foreach(x => println(x))
   */
  listOfIntegers.foreach(println)

  /*
      - sort function((A, A) => Int) => MyList
        [1,2,3].sort((x, y) => y-x) => [3,2,1]
   */
  println(listOfIntegers.sort((x, y) => y - x))

  /*
      - zipWith (list, (A, A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x*y) => [1*4, 2*5, 3*6] = [4, 10, 18]
   */
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  /*
      - fold(start)(function) => a value
        [1,2,3].fold(0)(x+y) = 6
   */
  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(combinations)
}