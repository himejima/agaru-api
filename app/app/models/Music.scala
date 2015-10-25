package models

import play.api.db._
import anorm._
import play.api.Play.current
import anorm.SqlParser._
import play.api.db.DB

//case class Music (id:Pk[Long], url:String, player_id:Int, condition:String, year:Int, type:String, created_at:DATETIME, updated_at:DATETIME) {
case class Music (id:Pk[Long], url:String) {
  def add {
    DB.withConnection { implicit c =>
      val id: Int = SQL("insert into music (url) values ({url})").
                    on('url->this.url).executeUpdate()
    }
  }
}

object Music {
  val data = {
    get[Pk[Long]]("id") ~
    get[String]("url") map {
      case id ~url => Music(id, url)
    }
  }

  def all: List[Music] = {
    DB.withConnection { implicit c =>
      val datas = SQL("Select * from music").as(Music.data *)
      return datas
    }
  }
  def delete(id: Long): Int = {
    DB.withConnection { implicit c =>
      val target_id: Int = SQL("delete from music where id = {id}").on('id->id).executeUpdate()
      return target_id
    }
  }
}
