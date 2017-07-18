package models.tasks

import models.tasks.TaskCategory.TaskCategory

/**
  * Created by stephane on 18/07/2017.
  */

object TaskCategory extends Enumeration {
  type TaskCategory = Value
  val Bill, Meeting, Counter, Work = Value
}

case class Task(title: String, category: TaskCategory.Value, description: Option[String] = None)

