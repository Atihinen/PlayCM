package models

/**
 * Created by JJ-Solutions on 29.4.14.
 */

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class BlogPost(id: Long, title: String, content: String)
object BlogPost {

  val blogpost = {
      get[Long]("id") ~
      get[String]("title") ~
      get[String]("content") map {
      case id~title~content => BlogPost(id, title, content)
    }
  }

  def all(): List[BlogPost] = DB.withConnection { implicit c =>
    SQL("select * from blogpost").as(blogpost *)

  }

  def create(title: String, content: String) {
    DB.withConnection { implicit c =>
      SQL("insert into blogpost (title, content) values ({title}, {content})").on(
        'title -> title,
        'content -> content
      ).executeUpdate()
    }
  }

  def delete(id: Long){
    DB.withConnection{ implicit c =>
      SQL("delete from blogpost where id = {id}").on(
        'id -> id
      ).executeUpdate()

    }
  }
}
