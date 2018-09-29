package funsets

import org.scalatest.{FreeSpec, Matchers}

class FunSetsTest extends FreeSpec with Matchers {

  trait TestSets {
    val s1: FunSets.Set = FunSets.singletonSet(1)
    val s2: FunSets.Set = FunSets.singletonSet(2)
    val s3: FunSets.Set = FunSets.singletonSet(3)

    val s4: FunSets.Set = FunSets.singletonSet(3)
  }

  "Given the FunSets Set type" - {
    "When contains is implemented" - {
      "Then" in {
        FunSets.contains(_ => true, 100) shouldBe true
      }
    }
  }

  "Given the FunSets Set type" - {
    "When singletonSet is implemented" - {
      "Then" in {
        new TestSets {
          FunSets.contains(s1, 1) shouldBe true
        }
      }
    }
  }

  "Given the FunSets Set type" - {
    "When union is implemented" - {
      "Then union contains all elements of each set" in {
        new TestSets {
          private val s = FunSets.union(s1, s2)
          FunSets.contains(s, 1) shouldBe true
          FunSets.contains(s, 2) shouldBe true
          FunSets.contains(s, 3) shouldBe false
        }
      }
    }
  }

  "Given the FunSets Set type" - {
    "When intersect is implemented" - {
      "Then intersect returns the intersection of the two sets" in {
        new TestSets {
          private val s = FunSets.intersect(s3, s4)
          FunSets.contains(s, 3) shouldBe true
        }
      }
    }
  }

  "Given the FunSets Set type" - {
    "When diff is implemented" - {
      "Then diff returns the the difference of the two sets" in {
        new TestSets {
          private val s = FunSets.diff(s1, s2)
          FunSets.contains(s, 1) shouldBe true
          FunSets.contains(s, 2) shouldBe true
        }
      }
    }
  }

  "Given the FunSets Set type" - {
    "When filter is implemented" - {
      "Then it returns the subset of `s` for which `p` holds" in {
        new TestSets {
          private val s = FunSets.filter(s1, (x: Int) => x == 1)
          FunSets.contains(s, 1) shouldBe true
        }
      }
    }
  }

  "Given the FunSets Set type" - {
    "When map is implemented" - {
      "Then it returns a set transformed by applying `f` to each element of `s`" in {
        new TestSets {
          private val s = FunSets.map(s3, (x: Int) => x * 2)
          FunSets.contains(s, 6) shouldBe true
          println(FunSets.toString(s))
        }
      }
    }
  }
}
