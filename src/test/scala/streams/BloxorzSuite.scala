package streams

import org.scalatest.{FreeSpec, Matchers}

class BloxorzSuite extends FreeSpec with Matchers {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
      * This method applies a list of moves `ls` to the block at position
      * `startPos`. This can be used to verify if a certain list of moves
      * is a valid solution, i.e. leads to the goal.
      */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) =>
        require(block.isLegal) // The solution must always lead to legal blocks
        move match {
          case Left => block.left
          case Right => block.right
          case Up => block.up
          case Down => block.down
        }
      }
  }

  trait Level1 extends SolutionChecker {
    /* terrain for level 1*/
    val level: String =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin

    val optSolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  "Given a level 1" - {
    "When it is instantiated" - {
      "Then check terrain positions" in {
        new Level1 {
          terrain(Pos(0, 0)) shouldBe true
          terrain(Pos(1, 1)) shouldBe true // start
          terrain(Pos(4, 7)) shouldBe true // goal
          terrain(Pos(5, 8)) shouldBe true
          terrain(Pos(5, 9)) shouldBe false
          terrain(Pos(4, 9)) shouldBe true
          terrain(Pos(6, 8)) shouldBe false
          terrain(Pos(4, 11)) shouldBe false
          terrain(Pos(-1, 0)) shouldBe false
          terrain(Pos(0, -1)) shouldBe false
        }
      }
    }
  }

  "Given a level 1" - {
    "When it is instantiated" - {
      "Then check start position" in {
        new Level1 {
          startPos == Pos(1, 1) shouldBe true
        }
      }
    }
  }

  "Given a level 1" - {
    "When it is instantiated" - {
      "Then check optimal solution" ignore {
        new Level1 {
          solve(solution) == Block(goal, goal) shouldBe true
        }
      }
    }
  }

  "Given a level 1" - {
    "When it is instantiated" - {
      "Then check optimal solution length" in {
        new Level1 {
          solution.length == optSolution.length shouldBe true
        }
      }
    }
  }
}
