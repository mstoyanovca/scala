package funset

import funset.FunSets.{singletonSet, _}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class FunSetTest extends AnyFreeSpec with Matchers {

  "Given" - {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(1)

    "When contains is implemented" - {
      "Then" in {
        contains(_ => true, 100) shouldBe true
      }
    }

    "When singleton set one contains one" - {
      "Then" in {
        contains(s1, 1) shouldBe true
      }
    }

    "When union contains all elements of each set" - {
      val s = union(s1, s2)
      printSet(s)
      "Then" in {
        contains(s, 1) shouldBe true
        contains(s, 2) shouldBe true
      }
    }

    "When intersect contains common elements of both sets" - {
      val s = intersect(s1, s3)
      printSet(s)
      "Then" in {
        contains(s, 1) shouldBe true
      }
    }

    "When diff contains elements from the first set not in the second" - {
      val s = diff(s1, s2)
      printSet(s)
      "Then" in {
        contains(s, 1) shouldBe true
        contains(s, 2) shouldBe false
      }
    }

    "When filter contains elements which comply to a predicate" - {
      val s = filter(union(s1, s2), (p: Int) => p == 1)
      printSet(s)
      "Then" in {
        contains(s, 1) shouldBe true
        contains(s, 2) shouldBe false
      }
    }

    "When forall contains elements which comply to a predicate" - {
      val s = union(s1, s2)
      var r = forall(s, (p: Int) => p > 0)
      "Then" in {
        r shouldBe true
        r = forall(s, (p: Int) => p > 1)
        r shouldBe false
      }
    }

    "When exists checks for an element which complies to a predicate" - {
      val s = union(s1, s2)
      var r = exists(s, (p: Int) => p == 1)
      "Then" in {
        r shouldBe true
        r = exists(s, (p: Int) => p < 0)
        r shouldBe false
      }
    }

    "When map transforms each element of a set" - {
      val r = map(union(s1, s2), (x: Int) => x + 2)
      printSet(r)
      "Then" in {
        contains(r, 3) shouldBe true
        contains(r, 4) shouldBe true
      }
    }
  }
}
