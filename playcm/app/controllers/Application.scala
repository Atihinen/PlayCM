package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.BlogPost
import models.User


object Application extends Controller {

  val blogpostForm = Form(
    tuple(
      "title" -> nonEmptyText,
      "content" -> nonEmptyText
    )
  )

  def index = Action {
    Ok("Hello world")
  }

  def blogposts = Action {
    Ok(views.html.index(BlogPost.all(), User.all(), blogpostForm))
  }

  def newBlogpost = Action { implicit  request =>
    blogpostForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(BlogPost.all(), User.all(), errors)),
      {
        case (title, content) => {
          BlogPost.create(title, content)
          Redirect(routes.Application.blogposts)
        }
      }
      /*title => {
        BlogPost.create(title, "some content")
        Redirect(routes.Application.blogposts)
      }*/
    )

  }

  def deleteBlogpost(id: Long) = TODO

}