package objects

class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
      "Text: " + text + " [" + retweets + "]"
}

abstract class TweetSet {
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, new Empty)

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  def union(that: TweetSet): TweetSet

  def mostRetweeted: Tweet

  def descendingByRetweet: TweetList = {
    if (isEmpty) Nil
    else {
      val mostRet = mostRetweeted
      new Cons(mostRet, remove(mostRet).descendingByRetweet)
    }
  }

  def incl(tweet: Tweet): TweetSet

  def remove(tweet: Tweet): TweetSet

  def contains(tweet: Tweet): Boolean

  def foreach(f: Tweet => Unit): Unit

  def isEmpty: Boolean
}

class Empty extends TweetSet {
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

  override def union(that: TweetSet): TweetSet = that

  def mostRetweeted: Tweet = throw new NoSuchElementException

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, new Empty, new Empty)

  def remove(tweet: Tweet): TweetSet = this

  def contains(tweet: Tweet): Boolean = false

  def foreach(f: Tweet => Unit): Unit = ()

  def isEmpty: Boolean = true
}

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    if (p(elem)) left.filterAcc(p, right.filterAcc(p, acc.incl(elem)))
    else left.filterAcc(p, right.filterAcc(p, acc))
  }

  override def union(that: TweetSet): TweetSet = {
    ((left union right) union that).incl(elem)
  }

  def mostRetweeted: Tweet = {
    def withMoreRetweets(tweet1: Tweet, tweet2: Tweet): Tweet = if (tweet1.retweets > tweet2.retweets) tweet1 else tweet2

    if (left.isEmpty && right.isEmpty) elem
    else if (right.isEmpty) withMoreRetweets(left.mostRetweeted, elem)
    else if (left.isEmpty) withMoreRetweets(right.mostRetweeted, elem)
    else withMoreRetweets(left.mostRetweeted, withMoreRetweets(left.mostRetweeted, elem))
  }

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }

  def isEmpty: Boolean = false
}

trait TweetList {
  def head: Tweet

  def tail: TweetList

  def isEmpty: Boolean

  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")

  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")

  def isEmpty = true
}

class Cons(val head: Tweet, val tail: TweetList) extends TweetList {
  def isEmpty = false
}

object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  lazy val googleTweets: TweetSet = TweetReader.allTweets.filter(t => google.exists(s => t.text.contains(s)))
  lazy val appleTweets: TweetSet = TweetReader.allTweets.filter(t => apple.exists(s => t.text.contains(s)))

  /**
    * A list of all tweets mentioning a keyword from either apple or google,
    * sorted by the number of retweets.
    */
  lazy val trending: TweetList = googleTweets.union(appleTweets).descendingByRetweet
}

object Main extends App {
  GoogleVsApple.trending foreach println
}
