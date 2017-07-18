package models.users

import java.util.Date

/**
  * Created by stephane on 18/07/2017.
  */

final case class SignUp(name: String, email: String, password: String)
final case class User(name: String, email: String, password: String, signDate: Option[Date])
