package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.BlogPost
import models.User


object Application extends Controller with Secured {

  val blogpostForm = Form(
    tuple(
      "title" -> nonEmptyText,
      "content" -> nonEmptyText
    )
  )

  def index = Action {
    Ok(views.html.index(BlogPost.all(), User.all(), blogpostForm))
  }

  def blogposts = Action {
    Ok(views.html.index(BlogPost.all(), User.all(), blogpostForm))
  }

  def newBlogpost = IsAuthenticated { username => _ =>
    User.findByUsername(username).map { user =>
      Ok(views.html.blogpost.item(blogpostForm))
    }.getOrElse(Forbidden)
  }

  def createBlogpost = IsAuthenticated { username => implicit  request =>
    blogpostForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(BlogPost.all(), User.all(), errors)),
      {
        case (title, content) => {
          BlogPost.create(title, content)
          Redirect(routes.Application.blogposts)
        }
      }
    )

  }

  def deleteBlogpost(id: Long) = TODO

}