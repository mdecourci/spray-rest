package com.netpod.list

import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by michaeldecourci on 08/02/2016.
  */
class ListOperationsTest extends FlatSpec with Matchers {

  "The last function" should "return the last element of a list" in {
    val list = new ListOperations[Int]

    list.last(List(1, 1, 2, 3, 5, 8)) should be (8)
  }

  "The penultimate function" should "return the last but one element of a list" in {
    val list = new ListOperations[Int]

    list.penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
  }

  "The find function" should "return the kth element of a list" in {
    val list = new ListOperations[Int]

    list.find(2, List(1, 1, 2, 3, 5, 8)) should be (1)
    list.find(6, List(1, 1, 2, 3, 5, 8)) should be (8)
    list.find(1, List(1, 1, 2, 3, 5, 8)) should be (1)
  }

  it should "throw NoSuchElementException if finding the 0th element of a list" in {
    val list = new ListOperations[Int]

    a [NoSuchElementException] should be thrownBy {
      list.find(0, List(1, 1, 2, 3, 5, 8))
    }
  }

  "The length function" should "return the length of a list" in {
    val list = new ListOperations[Int]

    list.length(List(1, 1, 2, 3, 5, 8)) should be (6)
    list.length(List(1)) should be (1)
  }

  "The length function" should "return 0 length for an empty list" in {
    val list = new ListOperations[Int]

    list.length(List()) should be (0)
  }

  "The reverse function" should "reverse the contents of a list" in {
    val list = new ListOperations[Int]

    list.reverse(List(1, 1, 2, 3, 5, 8)) should be (List(8, 5, 3, 2, 1, 1))
  }

  "The palindrome function" should "indicate that the reverse contents of a list is the same as the original order" in {
    val list = new ListOperations[Int]

    list.isPalindrome(List(1, 2, 3, 2, 1)) should be (true)
  }

  "The palindrome function" should "indicate that the reverse contents of a list is not the same as the original order" in {
    val list = new ListOperations[Int]

    list.isPalindrome(List(1, 2, 3, 5, 2, 1)) should be (false)
  }

  "The flatten function" should "flatten the contents of a list containing lists" in {
    val list = new ListOperations[Int]

    list.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
  }

  "The compress function" should "compress the contents of a list by removing duplicates" in {
    val list = new ListOperations[Int]

    list.compress(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) should be (List('a', 'b', 'c', 'a', 'd', 'e'))
  }

  "The pack function" should "get repeated elements in a list and place them in separate sublists." in {
    val list = new ListOperations[Int]

    list.pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) should be (List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e')))
  }

  "The pack2 function" should "get repeated elements in a list and place them in separate sublists." in {
    val list = new ListOperations[Int]

    list.pack2(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) should be (List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e')))
  }

  "The encode function" should "gets consecutive duplicates of elements and encodes them as tuples (N, E) where N is the number of duplicates of the element E placed them in separate sublists." in {
    val list = new ListOperations[Int]

    list.encode(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) should be (List(List(4, 'a'), List(1, 'b'), List(2, 'c'), List(2, 'a'), List(1, 'd'), List(4, 'e')))
  }

}
