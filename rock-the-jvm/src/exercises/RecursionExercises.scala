package exercises

object RecursionExercises extends App {

  /*
    1. Concatenate a string n times
    2. isPrime function tail recursive
    3. Fibonacci function, tail recursive
   */

  def concatenateStringNTimes(myString: String, n: Int): String =
    if (n <= 1) myString
    else myString + concatenateStringNTimes(myString, n-1)

  println(concatenateStringNTimes("Kaio", 2))
  println(concatenateStringNTimes("Kaio", 3))

  def isPrimeTailRecursive(n: Int): Boolean = {
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n/2, true)
  }

  println(isPrimeTailRecursive(2003))
  println(isPrimeTailRecursive(24))

  def fibonacciTailRecursive(n: Int): Int = {
    def fibHelper(x: Int, last: Int, nextLast: Int): Int =
      if (x >= n) last
      else fibHelper(x + 1, last + nextLast, last)

    if (n <= 2) 1
    else fibHelper(2, 1, 1)
  }

  /*
    fibonacciTailRecursive(8) = fibHelper(2, 1, 1)
    = fibHelper(3, 2, 1)
    = fibHelper(4, 3, 2)
    = fibHelper(5, 5, 3)
    = fibHelper(6, 8, 5)
    = fibHelper(7, 13, 8)
    = fibHelper(8, 21, 13)
   */

  println(fibonacciTailRecursive(8))
}
