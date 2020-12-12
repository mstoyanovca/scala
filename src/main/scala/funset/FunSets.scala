package funset

import scala.annotation.tailrec

object FunSets {
  type FunSet = Int => Boolean

  def contains(s: FunSet, elem: Int): Boolean = s(elem)

  def singletonSet(elem: Int): FunSet = Set(elem)

  def union(s: FunSet, t: FunSet): FunSet = {
    x: Int => contains(s, x) || contains(t, x)
  }

  def intersect(s: FunSet, t: FunSet): FunSet = {
    x: Int => contains(s, x) && contains(t, x)
  }

  def diff(s: FunSet, t: FunSet): FunSet = {
    x: Int => contains(s, x) && !contains(t, x)
  }

  def filter(s: FunSet, p: Int => Boolean): FunSet = {
    // x: Int => contains(s, x) && contains(p: Int => Boolean, x)
    intersect(s, p)
  }

  val bound = 1000

  def forall(s: FunSet, p: Int => Boolean): Boolean = {
    @tailrec
    def iter(a: Int): Boolean = {
      // if (a < bound && contains(s, a) && !contains(p: Int => Boolean, a)) false
      if (a < bound && contains(s, a) && !p(a)) false
      else if (a == bound) true
      else iter(a + 1)
    }

    iter(-bound)
  }

  def exists(s: FunSet, p: Int => Boolean): Boolean = {
    !forall(s, x => !p(x))
  }

  def map(s: FunSet, f: Int => Int): FunSet = {
    x: Int => exists(s, e => f(e) == x)
  }

  def toString(s: FunSet): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  def printSet(s: FunSet): Unit = {
    println(toString(s))
  }

  def main(args: Array[String]): Unit = {
    println(contains(singletonSet(1), 1))
  }
}
