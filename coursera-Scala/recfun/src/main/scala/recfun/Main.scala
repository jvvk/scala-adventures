package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    
    print("Testing balance")
    println()
    print(balance("())(".toList))
    println()
    print(countChange(100,List(1,5,10,25,50)))
        
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
	if (c == 0 || r == c) 1 else pascal (c, r-1) + pascal (c-1, r-1)  
  } 

  /**
   * Exercise 2
  */ 
  def balance(chars: List[Char]): Boolean = {
	def _balance(chars:List[Char], parstack:List[Char]):Boolean = {
		if (chars.isEmpty) if (parstack.isEmpty) true else false  
		else if (chars.head == '(') _balance (chars.tail,'('::parstack)
		else if (chars.head != ')') _balance (chars.tail, parstack)
		else if (parstack.isEmpty) false else _balance(chars.tail,parstack.drop(1))
	}
	_balance(chars, List())
  }

  /**
   * Exercise 3 
  */
  def countChange(money: Int, coins: List[Int]): Int = { 
      if (money < 0 || coins.isEmpty) 0
      else if (money == 0) 1 else countChange(money, coins.tail) + countChange(money-coins.head, coins)     
  }
}