package slick

import slick.jdbc.GetResult
import slick.jdbc.H2Profile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object CoffeeRepository {
  implicit val getCoffeeResult: AnyRef with GetResult[Coffee] = GetResult(r => Coffee(r.<<, r.<<, r.<<, r.<<, r.<<))

  def findAll(db: Database): Future[Vector[Coffee]] = {
    // val db = Database.forConfig("h2mem")
    val result = Try(db.run(sql"select * from coffees".as[Coffee](getCoffeeResult)))
    // db.close  // not working with an in memory DB :)
    result match {
      case Success(value) =>
        value
      case Failure(exception) =>
        println(exception)
        Future {
          Vector[Coffee]()
        }
    }
  }

  def findByName(db: Database, name: String): Future[Option[Coffee]] = {
    // val db = Database.forConfig("h2mem")
    val result = Try(db.run(sql"select * from coffees where name = $name}".as[Coffee].headOption))
    // db.close
    result match {
      case Success(value) =>
        value
      case Failure(exception) =>
        println(exception)
        Future {
          None
        }
    }
  }

  def insert(db: Database, c: Coffee): Unit = {
    // val db = Database.forConfig("h2mem")
    val result = Try(sql"insert into coffees values (${c.name}, ${c.supID}, ${c.price}, ${c.sales}, ${c.total})")
    // db.close
    result match {
      case Success(value) =>
      case Failure(exception) =>
        println(exception)
    }
  }

  def update(db: Database, c: Coffee): Unit = {
    // val db = Database.forConfig("h2mem")
    val result = Try(sql"update coffees set supID = ${c.supID}, price = ${c.price}, sales = ${c.sales}, total = ${c.total} where name = ${c.name}")
    // db.close
    result match {
      case Success(value) =>
      case Failure(exception) =>
        println(exception)
    }
  }

  def delete(db: Database, name: String): Unit = {
    // val db = Database.forConfig("h2mem")
    val result = Try(sql"delete from coffees where name = $name")
    // db.close
    result match {
      case Success(value) =>
      case Failure(exception) =>
        println(exception)
    }
  }
}
