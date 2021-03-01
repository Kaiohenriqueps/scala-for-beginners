package exercises

object OOExercises extends App {
  val writer = new Writer("Kaio", "Silva", 1993)
  val anotherWriter = new Writer("Juliana", "Monaco", 1996)
  writer.fullName()

  val novel = new Novel("My Scala class", 2021, writer)
  novel.isWrittenBy(writer)
  novel.isWrittenBy(anotherWriter)
  novel.authorAge()
  val newCopy = novel.copy(2022)

  val counter = new Counter(200)
  counter.currentCount()
  counter.increment()
  counter.decrement()
  counter.increment(10)
  counter.decrement(10)
}

/*
  Writer: first name, surname, year
    - method fullname
 */
class Writer(firstName: String, surName: String, val year: Int) {
  def fullName(): Unit = println(s"$firstName $surName")
}

/*
  Novel: name, year of release, author
    - method authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of Novel
 */
class Novel(name: String, yearRelease: Int, author: Writer) {
  def authorAge(): Unit = println(s"author age: ${yearRelease - author.year}")

  def isWrittenBy(author: Writer): Unit = println(author == this.author)

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

/*
    Counter class
      receives an int value
      - method current count
      - method to increment/decrement -> new Counter
      - overload inc/dec to receive an amount
   */
class Counter(value: Int) {
  def currentCount(): Unit = println(value)

  def increment(): Unit = {
    val newValue = new Counter(value+1)
    newValue.currentCount()
  }

  def increment(amount: Int): Unit = {
    val newValue = new Counter(value+amount)
    newValue.currentCount()
  }

  def decrement(): Unit = {
    val newValue = new Counter(value-1)
    newValue.currentCount()
  }

  def decrement(amount: Int): Unit = {
    val newValue = new Counter(value-amount)
    newValue.currentCount()
  }
}
