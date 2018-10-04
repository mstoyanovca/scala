package objsets

import objects.{Empty, Tweet, TweetList, TweetSet}
import org.scalatest.{FreeSpec, Matchers}

class TweetSetTest extends FreeSpec with Matchers {

  trait TestSets {
    val set1 = new Empty
    val set2: TweetSet = set1.incl(new Tweet("a", "a body", 20))
    val set3: TweetSet = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c: TweetSet = set3.incl(c)
    val set4d: TweetSet = set3.incl(d)
    val set5: TweetSet = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  "Given the TweetSet class" - {
    "When filter: on empty set is applied" - {
      "Then" in {
        new TestSets {
          size(set1.filter(tw => tw.user == "a")) shouldBe 0
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When filter: a on set5 is applied" - {
      "Then" in {
        new TestSets {
          size(set5.filter(tw => tw.user == "a")) shouldBe 1
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When filter: 20 on set5 is applied" - {
      "Then" in {
        new TestSets {
          size(set5.filter(tw => tw.retweets == 20)) shouldBe 2
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When union: set4c and set4d is applied" - {
      "Then" in {
        new TestSets {
          size(set4c.union(set4d)) shouldBe 4
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When union: with empty set (1) is applied" - {
      "Then" in {
        new TestSets {
          size(set5.union(set1)) shouldBe 4
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When union: with empty set (2) is applied" - {
      "Then" in {
        new TestSets {
          size(set1.union(set5)) shouldBe 4
        }
      }
    }
  }

  "Given the TweetSet class" - {
    "When descending: set5 is applied" - {
      "Then" in {
        new TestSets {
          val trends: TweetList = set5.descendingByRetweet
          trends.isEmpty shouldBe false
          trends.head.user == "a" || trends.head.user == "b" shouldBe true
        }
      }
    }
  }
}
