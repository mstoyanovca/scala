package recfun

import org.scalatest.{FreeSpec, Matchers}

class Balance extends FreeSpec with Matchers {

  "Given a string as an argument" - {
    "When the balance function is run" - {
      "Then it should return true" in {
        Main.balance("(if (zero? x) max (/ 1 x))".toList) shouldBe true
      }
    }
  }

  "Given a second string as an argument" - {
    "When the balance function is run" - {
      "Then it should return true" in {
        Main.balance("I told him (that it's not (yet) done).\n(But he wasn't listening)") shouldBe true
      }
    }
  }

  "Given a third string as an argument" - {
    "When the balance function is run" - {
      "Then it should return false" in {
        Main.balance(":-)".toList) shouldBe false
      }
    }
  }

  "Given the last string as an argument" - {
    "When the balance function is run" - {
      "Then it should return false" in {
        Main.balance("())(".toList) shouldBe false
      }
    }
  }
}
