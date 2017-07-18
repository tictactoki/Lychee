package models.formats

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import models.users.{User, SignUp}
import akka.http.scaladsl.server.Directives
import spray.json.DefaultJsonProtocol

/**
  * Created by stephane on 18/07/2017.
  */
trait JsonUserFormat { self: JsonFormat =>
  implicit val signUpFormat = jsonFormat3(SignUp.apply)
  implicit val userFormat = jsonFormat4(User.apply)
}


trait JsonFormat extends SprayJsonSupport with DefaultJsonProtocol with JsonUserFormat {

}