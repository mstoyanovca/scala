package recfun

import org.scalatest.{FreeSpec, Matchers}

class Pascal extends FreeSpec with Matchers {

  "Given a Pascal's Triangle" - {
    "When the column is 0" - {
      "Then it should return 1" in {
        Main.pascal(0, 2) shouldBe 1
      }
    }
  }

  "Given a Pascal's Triangle" - {
    "When the column is 1 and the row is 2" - {
      "Then it should return 2" in {
        Main.pascal(1, 2) shouldBe 2
      }
    }
  }

  "Given a Pascal's Triangle" - {
    "When the column is 1 and the row is 3" - {
      "Then it should return 3" in {
        Main.pascal(1, 3) shouldBe 3
      }
    }
  }
}
