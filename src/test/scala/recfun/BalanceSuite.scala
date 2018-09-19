package recfun

import org.scalatest.FreeSpec

class BalanceSuite extends FreeSpec {

  "Given" - {
    "When" - {
      "Then" in {
        assert(Main.balance("(if (zero? x) max (/ 1 x))".toList))
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(Main.balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(!Main.balance(":-)".toList))
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(!Main.balance("())(".toList))
      }
    }
  }
}
