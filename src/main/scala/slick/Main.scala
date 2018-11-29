package slick

import slick.CoffeeRepository.getCoffeeResult
import slick.dbio.DBIO
import slick.jdbc.H2Profile.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.backend.DatabasePublisher
import slick.driver.H2Driver.api._

object Main {
  val coffees: Seq[Coffee] = Seq(
    Coffee("Colombian", 101, 7.99, 0, 0),
    Coffee("French_Roast", 49, 8.99, 0, 0),
    Coffee("Espresso", 150, 9.99, 0, 0),
    Coffee("Colombian_Decaf", 101, 8.99, 0, 0),
    Coffee("French_Roast_Decaf", 49, 9.99, 0, 0)
  )

  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("h2mem")
    // val combined: Future[Seq[Int]] = db.run(DBIO.sequence(createCoffees +: coffees.map(insert) :+ findAll))
    val combined: Future[Int] = db.run(createCoffees andThen DBIO.sequence(coffees.map(insert)) andThen findAll)
    db.close
  }

  def createCoffees: DBIO[Int] =
    sqlu"""create table coffees(
    name varchar not null,
    sup_id int not null,
    price double not null,
    sales int not null,
    total int not null,
    foreign key(sup_id) references suppliers(id))"""

  def insert(c: Coffee): DBIO[Int] = {
    sqlu"insert into coffees values (${c.name}, ${c.supID}, ${c.price}, ${c.sales}, ${c.total})"
  }

  def findAll(): DBIO[Int] = {
    sqlu"select * from coffees"
  }
}
