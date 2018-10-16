package forcomp

import forcomp.Anagrams._
import org.scalatest.{FreeSpec, Matchers}

class AnagramsTest extends FreeSpec with Matchers {

  "Given the Anagrams object" - {
    "When wordOccurrences: abcd" - {
      "Then" in {
        wordOccurrences("abcd") shouldBe List(('a', 1), ('b', 1), ('c', 1), ('d', 1))
      }
    }
  }

  "Given the Anagrams object" - {
    "When wordOccurrences: Robert" - {
      "Then" in {
        wordOccurrences("Robert") shouldBe List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1))
      }
    }
  }

  "Given the Anagrams object" - {
    "When sentenceOccurrences: abcd e" - {
      "Then" in {
        sentenceOccurrences(List("abcd", "e")) shouldBe List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1))
      }
    }
  }

  "Given the Anagrams object" - {
    "When dictionaryByOccurrences.get: eat" - {
      "Then" in {
        dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) shouldBe Some(Set("ate", "eat", "tea"))
      }
    }
  }

  "Given the Anagrams object" - {
    "When word anagrams: married" - {
      "Then" in {
        wordAnagrams("married").toSet shouldBe Set("married", "admirer")
      }
    }
  }

  "Given the Anagrams object" - {
    "When word anagrams: player" - {
      "Then" in {
        wordAnagrams("player").toSet shouldBe Set("parley", "pearly", "player", "replay")
      }
    }
  }

  "Given the Anagrams object" - {
    "When subtract: lard - r" - {
      "Then" in {
        val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
        val r = List(('r', 1))
        val lad = List(('a', 1), ('d', 1), ('l', 1))
        subtract(lard, r) shouldBe lad
      }
    }
  }

  "Given the Anagrams object" - {
    "When combinations: []" - {
      "Then" in {
        combinations(Nil) shouldBe List(Nil)
      }
    }
  }

  "Given the Anagrams object" - {
    "When combinations: abba" - {
      "Then" in {
        val abba = List(('a', 2), ('b', 2))
        val abbacomb = List(
          List(),
          List(('a', 1)),
          List(('a', 2)),
          List(('b', 1)),
          List(('a', 1), ('b', 1)),
          List(('a', 2), ('b', 1)),
          List(('b', 2)),
          List(('a', 1), ('b', 2)),
          List(('a', 2), ('b', 2))
        )
        assert(combinations(abba).toSet === abbacomb.toSet)
      }
    }
  }

  "Given the Anagrams object" - {
    "When sentence anagrams: []" - {
      "Then" in {
        val sentence = List()
        assert(sentenceAnagrams(sentence) === List(Nil))
      }
    }
  }

  "Given the Anagrams object" - {
    "When sentence anagrams: Linux rulez" - {
      "Then" in {
        val sentence = List("Linux", "rulez")
        val anas = List(
          List("Rex", "Lin", "Zulu"),
          List("nil", "Zulu", "Rex"),
          List("Rex", "nil", "Zulu"),
          List("Zulu", "Rex", "Lin"),
          List("null", "Uzi", "Rex"),
          List("Rex", "Zulu", "Lin"),
          List("Uzi", "null", "Rex"),
          List("Rex", "null", "Uzi"),
          List("null", "Rex", "Uzi"),
          List("Lin", "Rex", "Zulu"),
          List("nil", "Rex", "Zulu"),
          List("Rex", "Uzi", "null"),
          List("Rex", "Zulu", "nil"),
          List("Zulu", "Rex", "nil"),
          List("Zulu", "Lin", "Rex"),
          List("Lin", "Zulu", "Rex"),
          List("Uzi", "Rex", "null"),
          List("Zulu", "nil", "Rex"),
          List("rulez", "Linux"),
          List("Linux", "rulez")
        )
        assert(sentenceAnagrams(sentence).toSet === anas.toSet)
      }
    }
  }
}
