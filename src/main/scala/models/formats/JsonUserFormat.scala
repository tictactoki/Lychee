package models.formats

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import models.tasks.TaskCategory.TaskCategory
import models.tasks.{TaskCategory, Task}
import models.tasks.TaskCategory.TaskCategory
import models.users.{User, SignUp}
import akka.http.scaladsl.server.Directives
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import spray.json._

import scala.util.Try

/**
  * Created by stephane on 18/07/2017.
  */
sealed trait JsonUserFormat { self: JsonFormat =>
  implicit val signUpFormat = jsonFormat3(SignUp.apply)
  implicit val userFormat = jsonFormat5(User.apply)
}

sealed trait JsonTaskFormat { self: JsonFormat =>

  implicit object TaskCategoryFormat extends RootJsonFormat[TaskCategory.TaskCategory] {
    override def write(obj: TaskCategory): JsValue = JsString(obj.toString)

    override def read(json: JsValue): TaskCategory = json match {
      case JsString(value) => TaskCategory.withName(value)
      case _ => throw new DeserializationException("Enum string expected")
    }
  }

  implicit val taskFormat = jsonFormat4(Task.apply)

}

trait JsonFormat extends SprayJsonSupport with DefaultJsonProtocol with JsonUserFormat with JsonTaskFormat