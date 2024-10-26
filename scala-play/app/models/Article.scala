package models

import play.api.libs.json._

case class Article(id: Option[Long], content: String, completed: Boolean)

object Article {
  implicit val articleFormat: OFormat[Article] = Json.format[Article]
}