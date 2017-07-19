import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl._
import model._
import models.db.Connection
import models.formats.JsonFormat
import models.tasks.{TaskCategory, Task}
import models.users.User
import server.Directives._

import scala.io.StdIn

/**
  * Created by stephane on 17/07/2017.
  */
object Lychee extends App with JsonFormat {

  implicit val system = ActorSystem("lychee")
  implicit val materializer = ActorMaterializer()
  // needed for future
  implicit val executionContext = system.dispatcher

  val route = path("Hello") {
    get {
      complete("Hellor Lychee")
    }
  }


  val binding = Http().bindAndHandle(route, "localhost", 8888)
  println("Server online")
  StdIn.readLine()
  binding.flatMap(_.unbind())
    .onComplete(_ => system.terminate())




}
