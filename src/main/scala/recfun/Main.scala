package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }


  def pascal(c: Int, r: Int): Int = ???


  def balance(chars: List[Char]): Boolean = ???

  
  def countChange(money: Int, coins: List[Int]): Int = ???
}
