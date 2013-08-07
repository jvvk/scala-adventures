package forcomp

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val ll = List(List())                           //> ll  : List[List[Nothing]] = List(List())
  val ll2 = ll ++ List(1,2)                       //> ll2  : List[Any] = List(List(), 1, 2)
  (List(('a',2),('c',3))::List(List(('a',1),('b',2)))).flatten.groupBy(e => e._1).map(((e)) => (e._1, e._2.length)).toList.sorted
                                                  //> res0: List[(Char, Int)] = List((a,2), (b,1), (c,1))
  println((List(('i',1), ('l',1), ('n',1)) :: List(List(('l',1)))).flatten.groupBy(e => e._1).map(((e)) => (e._1, e._2.map(_._2).sum)).toList.sorted)
                                                  //> List((i,1), (l,2), (n,1))
  
  
}