package lectures.part1basics

object Recursion extends App {

  def factorial(n: Int): Int =
    if(n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }

  println(factorial(10))

  def anotherFactorial(n: Int): BigInt = {
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)

    factHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10*1)
    = factHelper(8, 9*10*1)
    = factHelper(7, 8*9*10*1)
    = ...
    = factHelper(2, 3*4*...*9*10*1)
    = factHelper(1, 1*2*3*4*...*9*10)
   */
  println(anotherFactorial(10))
  println(anotherFactorial(5000))
  println(anotherFactorial(20000))
}
