package com.netpod.domain

import slick.lifted.Tag
import slick.model.Table
import spray.http.HttpHeaders.Date

/**
 * Created by michaeldecourci on 25/07/15.
 */


case class Customer(id: Option[Long],
                    firstName: String,
                    lastName: String,
                    birthday: Option[java.util.Date])

object Customer {

  def unapply(json : String) : Option[(Long, String, String, Option[java.util.Date])] = {
    return Some(1l, "", "", Option.empty)
  }
}
