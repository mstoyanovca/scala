package recfun

import scala.annotation.tailrec

object RecFun {
  def factorial(n: Int): Int = {
    // if (n == 0) 1 else n * factorial(n - 1)
    @tailrec
    def loop(n: Int, acc: Int): Int = {
      if (n == 0) acc else loop(n - 1, n * acc)
    }

    loop(n, 1)
  }

  // factorial(3)
  // 3 * 1 * factorial(2)
  // 3 * 1 * 2 * factorial(1)
  // 3 * 1 * 2 * 1 * factorial(0)
  // 3 * 1 * 2 * 1 * 1
  // 6

  def pascal(c: Int, r: Int): Int = {
    // if (c == 0 || c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)
    if (c == 0 || c == r) return 1

    @tailrec
    def loop(c: Int, r: Int, acc: Seq[Int]): Int = {
      if (r == 0) acc(c)
      else loop(c, r - 1, (0 +: acc :+ 0).sliding(2).toList.map(_.sum))
    }

    loop(c, r, List(1))
  }

  //   1
  //  1 1
  // 1 2 1
  // pascal(1, 2)
  // pascal(0, 1) + pascal(1, 1)
  // 1 + 1
  // 2

  // c = 1, r = 2, acc = List(1) -> 0, 1, 0 -> (0, 1), (1, 0) -> 1, 1
  // c = 1, r = 1, acc = List(1, 1) -> 0, 1, 1, 0 -> (0, 1), (1, 1), (1, 0) -> 1, 2, 1
  // c = 1, r = 0, acc = List(1, 2, 1) returns acc(1)

  def balance(chars: List[Char]): Boolean = {
    /*var acc: Int = 0

    chars.foreach(c =>
      if (acc == 0 && c.equals(')')) {
        return false
      } else if (c.equals('(')) {
        acc += 1
      } else if (c.equals(')')) {
        acc -= 1
      }
    )

    acc == 0*/

    @tailrec
    def loop(chars: List[Char], acc: Int): Boolean = {
      if (chars.isEmpty) return acc == 0
      if (acc == 0 && chars.head.equals(')')) return false
      if (chars.head.equals('(')) loop(chars.tail, acc + 1)
      else if (chars.head.equals(')')) loop(chars.tail, acc - 1)
      else loop(chars.tail, acc)
    }

    if (chars.nonEmpty) loop(chars, 0) else false
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    /*if (money == 0) 1
    else if (money > 0 && coins.nonEmpty) countChange(money - coins.head, coins) + countChange(money, coins.tail)
    else 0*/

    def loop(money: Int, coins: List[Int]): Int = {
      if (money == 0) 1
      else if (money > 0 && coins.nonEmpty) countChange(money - coins.head, coins) + countChange(money, coins.tail)
      else 0
    }

    loop(money, coins)
  }

  // 1, 1, 1, 1
  // 1, 1, 2
  // 2, 2

  // f(4, (1, 2)) f(4, 2)
  // f(3, (1, 2)) f(3, 2)
  // f(2, (1, 2)) f(2, 2)
  //              f(0, 2)
  //                 1
  // f(1, (1, 2)) f(1, 2)
  // f(0, (1, 2)) f(0, 2)
  //      1          1

  def main(args: Array[String]): Unit = {
    println("factorial(3) = " + factorial(3))
    println("pascal(1, 2) = " + pascal(1, 2))
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row) print(s"${
        pascal(col, row)
      } ")
      println()
    }
    println("balance('a(b)c'.toList) = " + balance("a(b)c".toList))
    println("countChange(4, List(1, 2)) = " + countChange(4, List(1)))
  }
}
