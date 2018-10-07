package objects

import io.circe.generic.auto._
import io.circe.parser._

object TweetReader {
  def getTweetData(user: String, json: String): List[Tweet] = decode[List[Tweet]](json).getOrElse(List())

  def toTweetSet(l: List[Tweet]): TweetSet = l.foldLeft(new Empty: TweetSet)(_.incl(_))

  private val gizmodoTweets = TweetReader.getTweetData("gizmodo", TweetData.gizmodo)
  private val techCrunchTweets = TweetReader.getTweetData("TechCrunch", TweetData.TechCrunch)
  private val engadgetTweets = TweetReader.getTweetData("engadget", TweetData.engadget)
  private val amazondealsTweets = TweetReader.getTweetData("amazondeals", TweetData.amazondeals)
  private val cnetTweets = TweetReader.getTweetData("CNET", TweetData.CNET)
  private val gadgetlabTweets = TweetReader.getTweetData("gadgetlab", TweetData.gadgetlab)
  private val mashableTweets = TweetReader.getTweetData("mashable", TweetData.mashable)

  private val sources: List[List[Tweet]] = List(gizmodoTweets, techCrunchTweets, engadgetTweets, amazondealsTweets, cnetTweets, gadgetlabTweets, mashableTweets)

  val tweetSets: List[TweetSet] = sources.map(tweets => toTweetSet(tweets))

  private def unionOfAllTweetSets(curSets: List[TweetSet], acc: TweetSet): TweetSet = {
    if (curSets.isEmpty) acc
    else unionOfAllTweetSets(curSets.tail, acc.union(curSets.head))
  }

  val allTweets: TweetSet = unionOfAllTweetSets(tweetSets, new Empty)
}
