/**
 * Created by vamshi on 25/02/15.
 */

import scala.collection.immutable.SortedSet
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{HashMap => mHashMap}

package com.vamshi {

object Apriori {
  def apply(database: Map[String, List[String]], min_sup: Int): List[List[SortedSet[String]]] = {
    val db = database.values.map(_.to[SortedSet]).toList

    //find frequent 1-item sets
    var L = {
      val counts = new mHashMap[SortedSet[String], Int].withDefaultValue(0)
      db.foreach(_.foreach(item => counts(SortedSet(item)) += 1))
      (counts filter (_._2 >= min_sup)).keys.toList
    }

    //frequent itemsets that will be returned
    var freqItemSets = ListBuffer(L)

    while (!L.isEmpty) {
      //generate candidate set Ck from Lk-1
      val C = {
        var Ck = ListBuffer[SortedSet[String]]()
        for (l1 <- L) {
          for (l2 <- L) {
            if ((l1.zip(l2).init forall (pair => pair._1 == pair._2)) && (l1.last < l2.last)) {
              //join
              val c = l1 + l2.last
              //prune candidate set
              if (c.subsets(L.head.size).exists(!L.contains(_))) Ck -= c else Ck += c
            }
          }
        }
        Ck
      }
      val counts = new mHashMap[SortedSet[String], Int].withDefaultValue(0)
      for (t <- db) {
        for (c <- C) {
          if (c subsetOf t) counts(c) += 1
        }
      }
      L = counts.filter(_._2 >= min_sup).keys.toList
      if (!L.isEmpty) freqItemSets += L
    }
    freqItemSets.toList
  }
}

}