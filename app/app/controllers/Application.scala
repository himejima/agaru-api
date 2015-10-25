package controllers

import play.api._
import play.api.mvc._

import play.api.data.Form
import play.api.data.Forms._
import models._
import anorm._

import play.api.libs.json._

object Application extends Controller {
  val form1 = Form(
    mapping(
      "id" -> ignored(NotAssigned:Pk[Long]),
      "url" -> text
    )(Music.apply)(Music.unapply)
  )

  def index = Action {
    val data:List[Music] = Music.all
    Ok(views.html.index("ddd", data))
  }

  // def create = Action { implicit request =>
  //   var resform = form1.bindFromRequest
  //   var res = ""
  // }
  def create = Action { implicit request =>
    val reform = form1.bindFromRequest
    Logger.info(reform.data.toString)
    reform.get.add

    // val json = Map(
    //   // "code" -> Json.toJson(200),
    //   "result" -> Seq(
    //     Json.toJson(
    //       Map(
    //         "name" -> Json.toJson("Bob"),
    //         "age" -> Json.toJson(31),
    //         "email" -> Json.toJson("bob@gmail.com")
    //       )
    //     ),
    //     Json.toJson(
    //       Map(
    //         "name" -> Json.toJson("Kiki"),
    //         "age" -> Json.toJson(25),
    //         "email" -> JsNull
    //       )
    //     )
    //   )
    // )
    val json = Map(
        "code" -> Json.toJson(201)
    )
    Ok(Json.toJson(json))
  }

  def update = TODO

  def delete (id: Long) = Action {
    Logger.info(id.toString)

    val test = Music.delete(id)
    Logger.info(test.toString)

    val json = Map(
      "code" -> Json.toJson(200)
    )
    Ok(Json.toJson(json))
  }

  def read = Action {
    val data:List[Music] = Music.all
    // val t = Map("dd" -> Json.toJson("dddd"))
    // val t = Map("dd" -> "http://ddfafa")
    // Logger.info(Json.toJson(t).toString)
    Logger.info(data.size.toString)
    Logger.info(data.toString)
    val rows = data.map( e => Map("id" -> Json.toJson(e.id.toString), "url" -> Json.toJson(e.url)) )
    Logger.info(rows.toString)

    var json = Map(
      "code" -> Json.toJson(200),
      // "result" -> Seq(
      //     Json.toJson(
      //         Map(
      //             "id" -> Json.toJson(999),
      //             "url" -> Json.toJson("http://google.com")
      //         )
      //     )
      // )
      "result" -> Json.toJson(rows)
    )
    // Logger.info(Json.toJson(json).toString)
    Ok(Json.toJson(json))
  }

  def add = Action { implicit request =>
    val reform = form1.bindFromRequest
    val res = "regist"
    Ok(views.html.add(res, reform))
  }

  def demo = Action {
    // val json = Seq("hoge", "fuga")
    // val json = List(1,2,3)
    // val json = Map(
    //   "a" -> 1, "b" -> 2
    // )
    val result = Map(
      "id" -> 999,
      "url" -> "http://google.com"
    )
    // val json = Map(
    //   "code" -> 201,
    //   // "message" -> None,
    //   "result" -> "ree"
    // )
    // val jsonObject = Json.toJson(
    //   Map(
    //     "users" -> Seq(
    //       Json.toJson(
    //         Map(
    //           "name" -> Json.toJson("Bob"),
    //           "age" -> Json.toJson(31),
    //           "email" -> Json.toJson("bob@gmail.com")
    //         )
    //       )
    //       ,
    //       Json.toJson(
    //         Map(
    //           "name" -> Json.toJson("Kiki"),
    //           "age" -> Json.toJson(25),
    //           "email" -> JsNull
    //         )
    //       )
    //     )
    //   )
    // )
    // // Logger.info(json.toString)
    // // Ok(Json.toJson(json))
    // Ok(jsonObject)
    val json = Map(
      "users" -> Seq(
        Json.toJson(
          Map(
            "name" -> Json.toJson("Bob"),
            "age" -> Json.toJson(31),
            "email" -> Json.toJson("bob@gmail.com")
          )
        ),
        Json.toJson(
          Map(
            "name" -> Json.toJson("Kiki"),
            "age" -> Json.toJson(25),
            "email" -> JsNull
          )
        )
      )
    )
    Ok(Json.toJson(json))
  }
}
