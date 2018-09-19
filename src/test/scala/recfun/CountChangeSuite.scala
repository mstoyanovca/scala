package recfun

import org.scalatest.FreeSpec

class CountChangeSuite extends FreeSpec {

  import Main.countChange

  "Given" - {
    "When" - {
      "Then" in {
        assert(countChange(4, List(1, 2)) === 3)
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(countChange(300, List(5, 10, 20, 50, 100, 200, 500)) === 1022)
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(countChange(301, List(5, 10, 20, 50, 100, 200, 500)) === 0)
      }
    }
  }

  "Given" - {
    "When" - {
      "Then" in {
        assert(countChange(300, List(500, 5, 50, 100, 20, 200, 10)) === 1022)
      }
    }
  }
}
