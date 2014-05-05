package models

/**
 * Created by JJ-Solutions on 29.4.14.
 */

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.{Date}

case class BlogPost(id: Long, title: String, content: String, created_at: Option[Date], updated_at: Option[Date], username: Option[String])
object BlogPost {

  val blogpost = {
      get[Long]("id") ~
      get[String]("title") ~
      get[String]("content") ~
      get[Option[Date]]("created_at") ~
      get[Option[Date]]("updated_at") ~
      get[Option[String]]("username") map {
      case id~title~content~created_at~updated_at~username => BlogPost(id, title, content, created_at, updated_at, username)
    }
  }

  def all(): List[BlogPost] = DB.withConnection { implicit c =>
    SQL("select * from blogpost").as(blogpost *)

  }

  def create(title: String, content: String, author: String) {
    DB.withConnection { implicit c =>
      SQL("insert into blogpost (title, content, created_at, updated_at) values ({title}, {content}, NOW(), NOW())").on(
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
