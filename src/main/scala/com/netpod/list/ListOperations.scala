package com.netpod.list

import java.util.NoSuchElementException

import scala.Nothing
import scala.collection.generic.SeqFactory

/**
  * Created by michaeldecourci on 08/02/2016.
  */
class ListOperations[T] {

  def last[T](xs: List[T]) : T =  xs match {
    case x :: Nil => x
    case x :: ys => last(ys)
  }

  def penultimate[T](xs: List[T]) : T =  xs match {
    case x :: y :: Nil => x
    case x :: ys => penultimate(ys)
  }

  def find(k: Int, xs: List[T]) : T =  (k, xs) match {
    case (1, x :: _ )=> x
    case (n , _ :: ys) => find(n - 1, ys)
    case _ => throw new NoSuchElementException
  }

  def length[T](xs: List[T]) : Int =  {
    def count(n: Int, xs: List[T]) : Int = {
      xs match {
        case Nil => 0
        case x :: Nil => n
        case x :: ys => count(n + 1, ys)
      }
    }
    count(1, xs)
  }

  /**
    * abcd => dcba
    * abcd => dbca => dcba
    *
    * @param xs
    * @tparam T
    * @return
    */
  def reverse[T](xs: List[T]) : List[T] =  {
    xs match {
      case Nil => Nil
      case x :: ys => reverse(ys) ::: List(x)
    }
  }

  def isPalindrome[T](xs: List[T]) : Boolean= {
    def check(xs: List[T], ys: List[T]) : Boolean = (xs, ys) match {
      case (Nil, Nil) => true
//      case (x :: xs1, Nil) => false
//      case (Nil, y :: ys1) => false
      case (x :: xs1, y :: ys1) => if (x.equals(y)) check(xs1, ys1) else false
      case _ => false
    }
    check(xs, reverse(xs))
  }

  def flatten(xs: Any) : List[Any] = {
    xs match {
      case List(x) => this.flatten(x)
      case x :: ys => flatten(x) ::: flatten(ys)
      case x => List(x)
      case Nil => Nil
    }
  }

  def compress(xs: List[T]) : List[T] = {
    xs match {
      case x::List() => List(x)
      case x :: ys if (x.equals(ys.head)) => compress(ys)
      case x :: ys => x :: compress(ys)
      case Nil => Nil
    }
  }

  /**
    * if current=next append next to current list
    * else add current to new list
    *
    * @param xs
    * @return
    */
  def pack(xs: List[T])  : List[List[T]] = {
    def _pack(res: List[List[T]], rem : List[T]) : List[List[T]] = {
        rem match {
          case Nil => res
          case x :: ys if (res.isEmpty || (res.last.head != x)) => _pack(res ::: List(List(x)), ys)
          case x :: ys => _pack(res.init ::: List(res.last ::: List(x)), ys)
        }
    }

    _pack(List(), xs)
  }

  def pack2(xs: List[T])  : List[List[T]] = {
    def _pack(res: List[List[T]], rem : List[T]) : List[List[T]] = {
      rem match {
        case Nil => res
        case ls => {
          val (s: List[T], r: List[T]) = rem span { _ == rem.head }
          _pack(res:::List(s), r)
        }
      }
    }

    _pack(List(), xs)
  }

//  def encode(xs: List[T]) : List[List[(Int, T)]] = {
//    def _encode(res: List[List[(Int, T)]], rem : List[T]) : List[List[(Int, T)]] = {
//      rem match {
//        case Nil => res
//        case x :: ys if (res.isEmpty || (res.last.head._2 != x)) => {
//          val e = (1, x)
//          _encode(res ::: List(List(e)), ys)
//        }
//        case x :: ys => {
//          val e = (res.last.last._1 + 1, x)
//          _encode(res.init ::: List(List(e)), ys)
//        }
//      }
//    }
//
//    _encode(List(), xs)
//  }

  def encode(xs: List[T]) : List[List[Any]] = {
    def _encode(res: List[List[Any]], rem : List[T]) : List[List[Any]] = {
      rem match {
        case Nil => res
        case x :: ys if (res.isEmpty || (res.last.last != x)) => {
          _encode(res ::: List(List(1, x)), ys)
        }
        case x :: ys => {
          val p : Int = res.last.head.asInstanceOf[Int]
          _encode(res.init ::: List(List(p+1, x)), ys)
        }
      }
    }

    _encode(List(), xs)
  }

}
