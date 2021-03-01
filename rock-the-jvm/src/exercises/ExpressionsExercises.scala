package exercises

object ExpressionsExercises extends App {

  // 1. difference between "hello world" vs println("hello world")
  // "hello world" is a value and println("hello world") is an expression

  // 2. what's the value of...

  val someValue = {
    2 < 3
  }

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }

  // someValue = true
  // someOtherValue = 42
  println(someValue)
  println(someOtherValue)
}
