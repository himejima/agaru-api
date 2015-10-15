package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

object Application extends Controller {
  case class FormData(input:String)

  val form1 = Form(
    mapping("input" -> text)(FormData.apply)(FormData.unapply)
  )


  def index = Action {
      // Ok(views.html.index("Your new application is ready."))
      Ok(views.html.index("フォームを送信", form1))
  }

  def send = TODO

}
