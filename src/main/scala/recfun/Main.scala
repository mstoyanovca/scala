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

  def pascalNonTailRecursive(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1 else pascalNonTailRecursive(c - 1, r - 1) + pascalNonTailRecursive(c, r - 1)
  }

  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) return 1

    @tailrec
    def loop(c: Int, r: Int, acc: Seq[Int]): Int = {
      if (r == 0)
        acc(c) // c =< r
      else
        loop(c, r - 1, (0 +: acc :+ 0).take(c + 2).sliding(2).toList.map(_.sum))
    }

    // do not calculate beyond c/2 for optimization, possible due to symmetry:
    if (c < r / 2) loop(c, r, List(1)) else loop(r - c, r, List(1))

  }

  def balance(chars: Seq[Char]): Boolean = {
    if (chars.length < 2) return false

    @tailrec
    def loop(chars: Seq[Char], opened: Int): Boolean = {
      if (chars.isEmpty) opened == 0
      else if (chars.head == '(') loop(chars.tail, opened + 1)
      else if (chars.head == ')' && opened > 0) loop(chars.tail, opened - 1)
      else if (chars.head == ')' && opened <= 0) false
      else loop(chars.tail, opened)
    }

    loop(chars, 0)
  }

  // non tail recursive:
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money > 0 && coins.nonEmpty) countChange(money - coins.head, coins) + countChange(money, coins.tail)
    else 0
  }
}
