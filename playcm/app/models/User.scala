package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class User(id: Long, username: String, password: String, email: String, biography: String)

object User {

  val simple = {
    get[Long]("user.id") ~
    get[String]("user.username") ~
    get[String]("user.password") ~
    get[String]("user.email") ~
    get[String]("user.biography")  map {
      case id~username~password~email~biography => User(id, username, password, email, biography)
    }
  }

  def findByUsername(username: String): Option[User] = {
    DB.withConnection { implicit c =>
      SQL("select * from user where username = {username}").on(
        'username -> username
      ).as(User.simple.singleOpt)

    }
  }

  def all(): List[User] = DB.withConnection { implicit c =>
    SQL("select * from user").as(User.simple *)

  }

  def findAll: Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user").as(User.simple *)
    }
  }

  def authenticate(username: String, password: String): Option[User] = {
    DB.withConnection{ implicit c =>
      SQL("select * from user where username = {username} and password = {password}").on(
        'username -> username,
        'password -> password
      ).as(User.simple.singleOpt)
    }
  }
}