package controllers.v1

import dao.CatDAO
import dao.DogDAO

import javax.inject.Inject
import models.Cat
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

class ArticleController @Inject() (
                              catDao: CatDAO,
                              dogDao: DogDAO,
                              mcc: MessagesControllerComponents
                            )(implicit executionContext: ExecutionContext) extends MessagesAbstractController(mcc) {

  private val logger = Logger(getClass)
  
  def index: Action[AnyContent] = Action.async { implicit request =>
    catDao.all().zip(dogDao.all()).map {
      case (cats, dogs) =>  Ok(Json.toJson(cats))
    }
  }

//  def insertCat = Action.async { implicit request =>
//    val cat: Cat = catForm.bindFromRequest().get
//    catDao.insert(cat).map(_ => Redirect(routes.Application.index))
//  }
//
//  def insertDog = Action.async { implicit request =>
//    val dog: Dog = dogForm.bindFromRequest().get
//    dogDao.insert(dog).map(_ => Redirect(routes.Application.index))
//  }
}