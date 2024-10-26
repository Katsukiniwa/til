package models

import play.api.libs.json.{Json, OFormat}

case class Cat(name: String, color: String)
object Cat {
  def unapply(cat: Cat): Option[(String, String)] = Some((cat.name, cat.color))
  def tupled: ((String, String)) => Cat = (this.apply _).tupled
  implicit val catFormat: OFormat[Cat] = Json.format[Cat]
}

case class Dog(name: String, color: String)
object Dog {
  def unapply(dog: Dog): Option[(String, String)] = Some((dog.name, dog.color))
  def tupled: ((String, String)) => Dog = (this.apply _).tupled
  implicit val catFormat: OFormat[Dog] = Json.format[Dog]
}