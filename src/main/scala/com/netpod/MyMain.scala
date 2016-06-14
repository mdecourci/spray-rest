package com.netpod

/**
  * Created by michaeldecourci on 05/11/2015.
  */
object MyMain {
  var doIt = false
  def main(args: Array[String]): Unit = {
    doIt = true
    run(f = x)
  }

  def x(): Unit = {
    println("Hello world")
  }
  def run(f : () => Unit): Unit = {
      if (doIt) {
        f.apply()
      }
  }
}
