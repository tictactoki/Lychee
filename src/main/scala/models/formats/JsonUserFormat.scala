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
  implicit val userFormat = jsonFormat4(User.apply)
}

sealed trait JsonTaskFormat { self: JsonFormat =>
  implicit val taskFormat = jsonFormat3(Task.apply)

  implicit object TaskCategoryFormat extends RootJsonFormat[TaskCategory.TaskCategory] {
    override def write(obj: TaskCategory): JsValue = JsString(obj.toString)

    override def read(json: JsValue): TaskCategory = json match {
      case JsString(value) => TaskCategory.withName(value)
      case _ => throw new DeserializationException("Enum string expected")
    }
  }

}

sealed trait JsonJodaTimeFormat { self: JsonFormat =>

  protected val formatter = ISODateTimeFormat.dateOptionalTimeParser()

  implicit object DateTimeFormat extends RootJsonFormat[DateTime] {
    override def write(obj: DateTime): JsValue = JsString(ISODateTimeFormat.basicDateTime.print(obj))

    override def read(json: JsValue): DateTime = json match {
      case JsString(value) => Try(formatter.parseDateTime(value)).getOrElse(error(value))
      case _ => error(json.toString())
    }

    protected def error(v: Any): DateTime = {
      deserializationError(
        s"""
           |'$v' is not a valid date value. Dates must be in format:
           |     * date-opt-time     = date-element ['T' [time-element] [offset]]
           |     * date-element      = std-date-element | ord-date-element | week-date-element
           |     * std-date-element  = yyyy ['-' MM ['-' dd]]
           |     * ord-date-element  = yyyy ['-' DDD]
           |     * week-date-element = xxxx '-W' ww ['-' e]
           |     * time-element      = HH [minute-element] | [fraction]
           |     * minute-element    = ':' mm [second-element] | [fraction]
           |     * second-element    = ':' ss [fraction]
           |     * offset            = 'Z' | (('+' | '-') HH [':' mm [':' ss [('.' | ',') SSS]]])
           |     * fraction          = ('.' | ',') digit+
        """.stripMargin
      )
    }

  }
}

trait JsonFormat extends SprayJsonSupport with DefaultJsonProtocol with JsonUserFormat with JsonTaskFormat with JsonJodaTimeFormat