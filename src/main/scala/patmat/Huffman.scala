package patmat

import scala.annotation.tailrec

object Huffman {

  abstract class CodeTree

  case class Fork(left: CodeTree, right: CodeTree, chars: List[Char], weight: Int) extends CodeTree

  case class Leaf(char: Char, weight: Int) extends CodeTree

  def weight(tree: CodeTree): Int =
    tree match {
      case Fork(_, _, _, weight) => weight
      case Leaf(_, weight) => weight
    }

  def chars(tree: CodeTree): List[Char] =
    tree match {
      case Fork(_, _, chars, _) => chars
      case Leaf(char, _) => List(char)
    }

  def makeCodeTree(left: CodeTree, right: CodeTree) = Fork(left, right, chars(left) ::: chars(right), weight(left) + weight(right))

  def string2Chars(str: String): List[Char] = str.toList

  def times(chars: List[Char]): List[(Char, Int)] = {
    @tailrec
    def loop(chars: List[Char], acc: List[(Char, Int)]): List[(Char, Int)] = {
      if (chars.isEmpty) acc
      else if (!acc.map(e => e._1).contains(chars.head)) loop(chars.tail, acc :+ (chars.head, 1))
      else {
        acc.map(e => if (e._1 == chars.head) (chars.head, e._2 + 1) else e)
        loop(chars.tail, acc)
      }
    }

    loop(chars, List())
  }

  def makeOrderedLeafList(freqs: List[(Char, Int)]): List[Leaf] = {
    @tailrec
    def loop(freqs: List[(Char, Int)], acc: List[Leaf]): List[Leaf] = {
      if (freqs.isEmpty) acc
      else loop(freqs.tail, acc :+ Leaf(freqs.head._1, freqs.head._2))
    }

    loop(freqs.sortBy(_._2), List())
  }

  def singleton(trees: List[CodeTree]): Boolean = trees.size == 1

  def combine(trees: List[CodeTree]): List[CodeTree] = {
    trees match {
      case x :: y :: tail => (makeCodeTree(x, y) :: tail).sortWith((t1, t2) => weight(t1) < weight(t2))
      case _ => trees
    }
  }

  def until(p: List[CodeTree] => Boolean, f: List[CodeTree] => List[CodeTree])(trees: List[CodeTree]): List[CodeTree] = {
    if (p(trees)) trees
    else until(p, f)(f(trees))
  }

  def createCodeTree(chars: List[Char]): CodeTree = until(singleton, combine)(makeOrderedLeafList(times(chars))).head

  type Bit = Int // 0 or 1

  def decode(tree: CodeTree, bits: List[Bit]): List[Char] = {
    @tailrec
    def loop(tree: CodeTree, bits: List[Bit], acc: List[Char]): List[Char] = {
      tree match {
        case Fork(left, right, _, _) => bits match {
          case List() => acc
          case head :: tail => head match {
            case 0 => loop(left, tail, acc)
            case 1 => loop(right, tail, acc)
          }
        }
        case Leaf(char, _) => loop(tree, bits, char :: acc)
      }
    }

    loop(tree, bits, List())
  }

  val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279), Leaf('f', 16351), List('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), List('k', 'w'), 2492), List('z', 'k', 'w'), 4585), Leaf('y', 4725), List('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), List('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), List('z', 'k', 'w', 'y', 'h', 'q'), 41497), List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856), Leaf('u', 96785), List('m', 'p', 'u'), 188641), List('o', 'l', 'm', 'p', 'u'), 355071), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110), List('v', 'g', 'b'), 52085), List('c', 'v', 'g', 'b'), 102088), List('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915), List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575), List('e', 'i', 'a'), 458522), List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)
  val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  def decodedSecret: List[Char] = decode(frenchCode, secret)

  // not ending
  def encode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    @tailrec
    def loop(tree: CodeTree, text: List[Char], acc: List[Bit]): List[Bit] = {
      text match {
        case List() => acc
        case head :: tail => tree match {
          case Fork(left, _, _, _) => if (chars(left).contains(head)) loop(tree: CodeTree, text: List[Char], 0 :: acc) else loop(tree: CodeTree, text: List[Char], 1 :: acc)
          case Leaf(_, _) => loop(tree, tail, acc)
        }
      }
    }

    loop(tree, text, List())
  }

  type CodeTable = List[(Char, List[Bit])]

  def codeBits(table: CodeTable)(char: Char): List[Bit] = table.filter(e => e._1 == char).head._2

  def convert(tree: CodeTree): CodeTable = {
    def loop(tree: CodeTree, acc: List[Bit]): CodeTable = {
      tree match {
        case Fork(left, right, _, _) => mergeCodeTables(loop(left, 0 :: acc), loop(right, 1 :: acc))
        case Leaf(char, _) => List((char, acc))
      }
    }

    loop(tree, List())
  }

  def mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = a ::: b

  def quickEncode(tree: CodeTree)(text: List[Char]): List[Bit] = {
    val table = convert(tree)

    def loop(text: List[Char], table: CodeTable): List[Bit] = {
      text match {
        case List() => List()
        case head :: tail => codeBits(table)(head) ::: loop(tail, table)
      }
    }

    loop(text, table)
  }
}
