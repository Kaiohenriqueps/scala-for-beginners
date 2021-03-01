package exercises

object ExceptionsExercises extends App {

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash your program with an StackOverflowError
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw
        - OverflowException if add(x,y) exceds Int.MAX_VALUE
        - UnderflowException if subtract(x,y) exceds Int.MIN_VALUE
        - MatchCalculationException for divison by 0
   */

  // OOM
  //  val array = Array.ofDim(Int.MaxValue)
  // SO
  //  val infinite: Int = 1 + infinite
  //  val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero!")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y

      if(x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y

      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if(y == 0) throw new MathCalculationException
      else x/y
    }
  }

  //  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
