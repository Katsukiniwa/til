package controllers.v1

import javax.inject.*
import dao.PersonDAO
import models.*
import play.api.libs.json.Json
import play.api.mvc.*

import scala.concurrent.ExecutionContext

class PersonController @Inject()(repo: PersonRepository,
                                 cc: MessagesControllerComponents,
                                 personDAO: PersonDAO,
                                )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  /**
   * The index action.
   */
  def index: Action[AnyContent] = Action.async { implicit request =>
    personDAO.all().map(cats =>  Ok(Json.toJson(cats)))
  }

  /**
   * The add person action.
   *
   * This is asynchronous, since we're invoking the asynchronous methods on PersonRepository.
   */
  def addPerson(): Action[AnyContent] = Action.async { implicit request =>
    val json = request.body.asJson.get
    val person = json.as[Person]
    repo.create(person.name, person.age).map { _ =>
      Ok(Json.obj("message" -> "success"))
    }
  }

  /**
   * A REST endpoint that gets all the people as JSON.
   */
  def getPersons: Action[AnyContent] = Action.async { implicit request =>
    repo.list().map { people =>
      Ok(Json.toJson(people))
    }
  }
}

/**
 * The create person form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreatePersonForm(name: String, age: Int)
object CreatePersonForm {
  def unapply(cpf: CreatePersonForm): Option[(String, Int)] = Some((cpf.name, cpf.age))
  def tupled: ((String, Int)) => CreatePersonForm = this.apply.tupled
}