package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import models._
import anorm._

object Application extends Controller {
  val form1 = Form(
    mapping(
        "id" -> ignored(NotAssigned:Pk[Long]),
        "name" -> text,
        "age" -> number,
        "address" -> text,
        "tel" -> text
    )(AddressBook.apply)(AddressBook.unapply)
  )

  def index = Action {
      // Ok(views.html.index("Your new application is ready."))
      // Ok(views.html.index("フォームを送信", form1))
      val book:List[AddressBook] = AddressBook.all
      Ok(views.html.index("住所録一覧", book))
  }

  def add = Action { implicit request =>
      val reform = form1.bindFromRequest
      val res = "住所録の登録"
      Ok(views.html.add(res, reform))
  }

  // def send = Action {implicit request =>
  //   var resform = form1.bindFromRequest
  //   var res = "you typed: " + resform.get.input
  //   Ok(views.html.index(res, resform))
  // }

  def send = Action { implicit request =>
    val reform = form1.bindFromRequest
    reform.get.add
    Redirect("/")
  }
}
