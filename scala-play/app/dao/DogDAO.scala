package dao

import models.Cat
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class DogDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Cats = TableQuery[CatsTable]

  def all(): Future[Seq[Cat]] = db.run(Cats.result)

  def insert(cat: Cat): Future[Unit] = db.run(Cats += cat).map { _ => () }

  private class CatsTable(tag: Tag) extends Table[Cat](tag, "DOG") {

    def name = column[String]("NAME", O.PrimaryKey)
    def color = column[String]("COLOR")

    def * = (name, color) <> (Cat.tupled, Cat.unapply)
  }
}