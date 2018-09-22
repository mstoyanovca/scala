package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  // non tail recursive version:
  def pascalNTR(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1 else pascalNTR(c - 1, r - 1) + pascalNTR(c, r - 1)
  }

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) return 1

    @tailrec
    def loop(c: Int, r: Int, acc: List[Int]): Int = {
      if (r == 0)
        acc(c) // c =< r
      else
        loop(c, r - 1, (0 +: acc :+ 0).take(c + 2).sliding(2).toList.map(_.sum))
    }

    // do not calculate beyond c/2 due to symmetry:
    if (c < r / 2) loop(c, r, List(1)) else loop(r - c, r, List(1))

  }

  def balance(chars: List[Char]): Boolean = ???

  def countChange(money: Int, coins: List[Int]): Int = ???
}
