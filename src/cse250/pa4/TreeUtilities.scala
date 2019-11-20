/**
 * cse250.pa4.TreeUtilities.scala
 *
 * Copyright 2019 Andrew Hughes (ahughes6@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 * Submission author
 * UBIT:smalbec 50280232
 * Person#:
 *
 * Collaborators (include UBIT name of each, comma separated):
 * UBIT:
 */
package cse250.pa4

import cse250.objects.{Empty, Node, Tree}
import sun.invoke.empty.Empty

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag



object TreeUtilities {

  val empty = cse250.objects.Empty

  def heapify[A](as: Array[A], i: Int): Node[A] ={


    val left = if((i*2)+1 < as.size){
      heapify(as, (i*2)+1)
    } else{
      empty
    }

    val right = if((i*2)+2 < as.size){
      heapify(as, (i*2)+2)
    } else{
      empty
    }

    val node = new Node(as(i), left, right)

    return node

  }




  def buildHeapTreeFromHeapArray[A](heapArray: Array[A]): Tree[A] = {

    var root = new Node(heapArray(0), heapify(heapArray, 1) , heapify(heapArray, 2))

    //TODO: Check if empty

    return root

  }

  def unheapify[A](arr: ArrayBuffer[Option[A]], que: mutable.Queue[Tree[A]]): ArrayBuffer[Option[A]] = {

    val currrent = que.dequeue

    que.addOne(current.left)

    que.addOne(current.right)

    arr.addOne(current.value)

    unheapify(arr,que)

    return arr

  }

  def flattenHeapTreeToHeapArray[A: ClassTag](root: Tree[A]): Array[A] = {

    var returnArr: ArrayBuffer[Option[Any]] = ArrayBuffer.empty

    var que: mutable.Queue[Tree[Any]] = mutable.Queue.empty

    que.addOne(root)

    unheapify(returnArr,que)

    Array()
  }

  def isValidBinaryHeap[A](root: Tree[A])(implicit comp: Ordering[A]): Boolean = {
    false
  }

  def applyTree[A](root: Tree[A], index: Int): Option[A] = {
    None
  }

  def updateHeap[A](root: Tree[A], index: Int, elem: A)(implicit comp: Ordering[A]): Tree[A] = {
    root
  }
}
