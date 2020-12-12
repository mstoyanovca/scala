package patmat

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import patmat.Huffman._

class HuffmanTest extends AnyFreeSpec with Matchers {
  private val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
  private val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then weight of a larger tree" in {
        weight(t1) shouldBe 5
      }
    }
  }

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then chars of a larger tree" in {
        chars(t2) shouldBe List('a', 'b', 'd')
      }
    }
  }

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then string2chars(\"hello, world\")" in {
        string2Chars("hello, world") shouldBe List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd')
      }
    }
  }

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then makeOrderedLeafList for some frequency table" in {
        makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) shouldBe List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3))
      }
    }
  }

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then combine of some leaf list" in {
        val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
        combine(leaflist) shouldBe List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4))
      }
    }
  }

  "Given the TestTrees trait" - {
    "When an instance is created" - {
      "Then decode and encode a very short text should be identity" in {
        decode(t1, encode(t1)("ab".toList)) shouldBe "ab".toList
      }
    }
  }
}
