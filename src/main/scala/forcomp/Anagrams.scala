package forcomp

object Anagrams {
  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]
  val dictionary: List[Word] = forcomp.loadDictionary

  def wordOccurrences(w: Word): Occurrences = w.toLowerCase.groupBy(c => c).toList.sortBy(_._1).map(e => (e._1, e._2.length))

  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString)

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = dictionary.groupBy(wordOccurrences)

  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.getOrElse(wordOccurrences(word), List())

  def combinations(occurrences: Occurrences): List[Occurrences] = {
    occurrences match {
      case List() => List(List())
      case head :: tail =>
        for {
          sublist <- combinations(tail)
          i <- 0 to head._2
        } yield (if (i == 0) sublist else (head._1, i) :: sublist).distinct
    }
  }

  def subtract(x: Occurrences, y: Occurrences): Occurrences = y.foldLeft(x.toMap)((acc: Map[Char, Int], e: (Char, Int)) => acc.updated(e._1, acc(e._1) - e._2)).filter(_._2 > 0).toList.sorted

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def loop(occurrences: Occurrences): List[Sentence] = {
      occurrences match {
        case Nil =>
          List(Nil)
        case _ =>
          for {
            combination <- combinations(occurrences)
            word <- dictionaryByOccurrences.getOrElse(combination, Nil)
            s <- loop(subtract(occurrences, wordOccurrences(word)))
            if combination.nonEmpty
          } yield word :: s
      }
    }

    loop(sentenceOccurrences(sentence))
  }
}
