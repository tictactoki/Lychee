package models.tasks

/**
  * Created by stephane on 18/07/2017.
  */

object TaskCategory extends Enumeration {
  type TaskCategory = Value
  val Bill, Meeting, Counter, Work = Value
}

case class Task(title: String, description: Option[String])

