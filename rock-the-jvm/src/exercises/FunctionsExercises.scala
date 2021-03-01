package exercises

object FunctionsExercises extends App {
  /*
    1. A greeting function (name, age) -> "Hi, my name is $name and I am $age years old"
    2. Factorial function 1 * 2 * 3 * .. * n
    3. A Fibonacci function
       f(1) = 1
       f(2) = 1
       f(n) = f(n-1) + f(n-2)
    4. Tests if a number is prime
   */

  def greetings(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old"

  println(greetings("Kaio", 27))

  def factorialNumber(n: Int): Int = {
    if(n <= 0) 1
    else n * factorialNumber(n-1)
  }

  println(factorialNumber(5))
  println(factorialNumber(4))

  def fibonacciFunction(number: Int): Int = {
    if(number <= 2) 1
    else fibonacciFunction(number-1) + fibonacciFunction(number-2)
  }

  println(fibonacciFunction(8))

  def numberIsPrime(number: Int): Boolean = {
    if(number % 2 == 0) false
    else true
  }

  println(numberIsPrime(2))
  println(numberIsPrime(3))
  println(numberIsPrime(13))
  println(numberIsPrime(24))
}
