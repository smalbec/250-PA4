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

    if(heapArray.isEmpty){
      return empty
    }

    var root = new Node(heapArray(0), heapify(heapArray, 1) , heapify(heapArray, 2))

    //TODO: Check if empty

    return root

  }

  def unheapify[A](arr: ArrayBuffer[A], que: mutable.Queue[Tree[A]]): Array[A] = {

    val current = que.dequeue

    if(current == cse250.objects.Empty){
      return arr.asInstanceOf[Array[A]]
    }

    que.addOne(current.left.head)

    que.addOne(current.right.head)

    arr.addOne(current.value.head)

    unheapify(arr,que)
  }

  def flattenHeapTreeToHeapArray[A: ClassTag](root: Tree[A]): Array[A] = {

    var emmm: Array[A] = Array.empty

    if(root == empty){
      return emmm
    }

    var returnArr: ArrayBuffer[A] = ArrayBuffer.empty

    var que: mutable.Queue[Tree[A]] = mutable.Queue.empty

    que.addOne(root)

    val returnarr = unheapify(returnArr,que)

    returnArr.toArray

  }



//  def isValidBinaryHeap[A](root: Tree[A])(implicit comp: Ordering[A]): Boolean = {
//
//    //TODO: Check if tree is proper
//
//    val arr = flattenHeapTreeToHeapArray(root)
//
//    for(i<- 0 to arr.length){
//
//      if((i*2)+1 > arr.length-1){
//        return true
//      }
//        //if the element at the index is smaller than either children, then return false
//      if((comp.lteq(arr(i), arr((i*2)+1))) || (comp.lteq(arr(i), arr((i*2)+2)))){
//        return false
//      }
//
//    }
//
//    false
//  }

  def applyTree[A](root: Tree[A], index: Int): Option[A] = {

    var q:mutable.Queue[Tree[A]] = mutable.Queue.empty

    q.addOne(root)

    var current: Tree[A] = root

    var counter = 0

    while(current != empty){
      current = q.dequeue()
      if(counter == index){
        return current.value
      }
      if(current == empty){
        return None
      }
      q.addOne(current.left.head)
      q.addOne(current.right.head)
      counter += 1
    }

    None

  }

  def updateHeap[A](root: Tree[A], index: Int, elem: A)(implicit comp: Ordering[A]): Tree[A] = {

    var q:mutable.Queue[Tree[A]] = mutable.Queue.empty

    var arr:ArrayBuffer[Tree[A]] = ArrayBuffer.empty

    q.addOne(root)
    arr.addOne(root)

    var current: Tree[A] = root

    var counter = 0

    while(current != empty){
      current = q.dequeue()
      if(counter == index){
        current = new Node(elem, current.left.head,current.right.head)
        return  root
      }
      if(current == empty){
        return root
      }
      q.addOne(current.left.head)
      q.addOne(current.right.head)
      counter += 1
    }

    root
  }
}
