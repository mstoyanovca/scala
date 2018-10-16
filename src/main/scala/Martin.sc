import forcomp.Anagrams.{Occurrences, Word}

val s: String = "Aaabbcd uugf kl"
val l = List("Martin", "Stoyanov")
s.toLowerCase.groupBy(c => c).toList.sortBy(_._1).map(e => (e._1, e._2.length))

def wordOccurrences(w: Word): Occurrences = w.toLowerCase.groupBy(c => c).toList.sortBy(_._1).map(e => (e._1, e._2.length))
l.mkString

val o = List(('a', 2), ('b', 1))
o.toMap