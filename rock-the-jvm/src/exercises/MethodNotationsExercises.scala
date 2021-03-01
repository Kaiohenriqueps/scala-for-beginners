package exercises

object MethodNotationsExercises extends App {
  /*
    1. Overload the + operator
      mary + "the rockstar" => new person "Mary (the rockstar)"

    2. Add an age to the Person class
       Add a unary + operator =: new person with the age + 1
       +mary => mary with the age incrementer

    3. Add a "learns" method in the Person Class => "Mary learns Scala"
       Add a learnScala method, calls learns method with "Scala"
       Use it in postfix notation

    4. Overload the apply method
       mary.apply(2) => "Mary watched inception 2 times"
   */

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name $nickname", age, favoriteMovie)
    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, age+1, favoriteMovie)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like ${favoriteMovie}"
    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
    def learns(course: String): String = s"$name learns $course"
    def learnsScala: String = learns("Scala")
  }

  val kaio = new Person("Kaio", 27, "Avatar")
  val juliana = new Person("Juliana", 24, "Titanic")

  println((kaio + "the rockstar").apply())
  println((+kaio).age)
  println(kaio + juliana)
  println(kaio learns "Method Notations")
  println(kaio.learnsScala)
  println(kaio(2))
}
