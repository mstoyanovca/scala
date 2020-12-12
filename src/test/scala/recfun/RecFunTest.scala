package recfun

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import recfun.RecFun._

class RecFunTest extends AnyFreeSpec with Matchers {
  "Given a factorial test" - {
    "When n = 3" - {
      "Then" in {
        factorial(3) shouldBe 6
      }
    }
  }

  "Given a Pascal's triangle test" - {
    "When col = 0, row = 2" - {
      "Then" in {
        pascal(0, 2) shouldBe 1
      }
    }
    "When col = 1, row = 2" - {
      "Then" in {
        pascal(1, 2) shouldBe 2
      }
      "When col = 1, row = 3" - {
        "Then" in {
          pascal(1, 3) shouldBe 3
        }
      }
    }
  }

  "Given a balance test" - {
    "When '(if (zero...'" - {
      "Then" in {
        balance("(if (zero? x) max (/ 1 x))".toList) shouldBe true
      }
      "When 'I told him...'" - {
        "Then" in {
          balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList) shouldBe true
        }
      }
      "When ':-)'" - {
        "Then" in {
          balance(":-)".toList) shouldBe false
        }
        "When '())('" - {
          "Then" in {
            balance("())(".toList) shouldBe false
          }
        }
      }
    }
  }

  "Given a count change test" - {
    "When countChange(4, List(1, 2))" - {
      "Then" in {
        countChange(4, List(1, 2)) shouldBe 3
      }
    }
    "When countChange(300, List(5,10,20,50,100,200,500))" - {
      "Then" in {
        countChange(300, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 1022
      }
    }
    "When countChange(301, List(5, 10, 20, 50, 100, 200, 500))" - {
      "Then" in {
        countChange(301, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 0
      }
    }
    "When countChange(300, List(500, 5, 50, 100, 20, 200, 10))" - {
      "Then" in {
        countChange(300, List(500, 5, 50, 100, 20, 200, 10)) shouldBe 1022
      }
    }
  }
}
