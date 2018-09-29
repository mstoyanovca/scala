package recfun

import org.scalatest.{FreeSpec, Matchers}

class CountChange extends FreeSpec with Matchers {

  "Given change of 4" - {
    "When the list of coin denominations is (1)" - {
      "Then the ways to give change" in {
        Main.countChange(3, List(1)) shouldBe 1
      }
    }
  }

  "Given change of 3" - {
    "When the list of coin denominations is (1, 2)" - {
      "Then the ways to give change" in {
        Main.countChange(3, List(1, 2)) shouldBe 2
      }
    }
  }

  "Given change of 4" - {
    "When the list of coin denominations is (1, 2)" - {
      "Then the ways to give change" in {
        Main.countChange(4, List(1, 2)) shouldBe 3
      }
    }
  }

  "Given change of 400" - {
    "When the list of coin denominations is (5, 10, 20, 50, 100, 200, 500)" - {
      "Then the ways to give change" in {
        Main.countChange(300, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 1022
      }
    }
  }

  "Given change of 301" - {
    "When the list of coin denominations is (5, 10, 20, 50, 100, 200, 500)" - {
      "Then the ways to give change" in {
        Main.countChange(301, List(5, 10, 20, 50, 100, 200, 500)) shouldBe 0
      }
    }
  }

  "Given change of 300" - {
    "When the list of coin denominations is (500, 5, 50, 100, 20, 200, 10)" - {
      "Then the ways to give change" in {
        Main.countChange(300, List(500, 5, 50, 100, 20, 200, 10)) shouldBe 1022
      }
    }
  }
}
