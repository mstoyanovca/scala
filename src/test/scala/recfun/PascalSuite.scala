package recfun

import org.scalatest.FreeSpec

class PascalSuite extends FreeSpec {

  "Given" - {
    "When" - {
      "Then" in {
        assert(Main.pascal(0, 2) === 1)
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(Main.pascal(1, 2) === 2)
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(Main.pascal(1, 3) === 3)
      }
    }
  }
}
