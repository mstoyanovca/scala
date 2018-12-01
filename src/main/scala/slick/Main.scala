package slick

import java.util.concurrent.TimeUnit
import slick.dbio.DBIO
import slick.jdbc.GetResult
import slick.jdbc.H2Profile.api._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Success}

object Main {
  implicit val getCoffeeResult: AnyRef with GetResult[Coffee] = GetResult(r => Coffee(r.<<, r.<<, r.<<, r.<<, r.<<))
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global

  val coffees: Seq[Coffee] = Seq(
    Coffee("Colombian", 101, 7.99, 0, 0),
    Coffee("French_Roast", 49, 8.99, 0, 0),
    Coffee("Espresso", 150, 9.99, 0, 0),
    Coffee("Colombian_Decaf", 101, 8.99, 0, 0),
    Coffee("French_Roast_Decaf", 49, 9.99, 0, 0)
  )

  val suppliers: Seq[Supplier] = Seq(
    Supplier(101, "Acme Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
    Supplier(49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
    Supplier(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
  )

  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("h2mem")
    db.run(createSuppliers andThen createCoffees andThen DBIO.sequence(suppliers.map(insertSupplier)) andThen DBIO.sequence(coffees.map(insertCoffee)))
      .onComplete {
        case Success(value) =>
          val coffeesFromDB: Vector[Coffee] = Await.result(db.run(findAllCoffees()), FiniteDuration(1L, TimeUnit.SECONDS))
          coffeesFromDB.foreach(println)
        case Failure(exception) =>
          exception.printStackTrace()
      }
    Thread.sleep(2000)
    db.close
  }

  def createSuppliers: DBIO[Int] =
    sqlu"""create table suppliers(
    id int not null primary key,
    name varchar not null,
    street varchar not null,
    city varchar not null,
    state varchar not null,
    zip varchar not null)"""

  def createCoffees: DBIO[Int] =
    sqlu"""create table coffees(
    name varchar not null,
    sup_id int not null,
    price double not null,
    sales int not null,
    total int not null,
    foreign key(sup_id) references suppliers(id))"""

  def insertCoffee(c: Coffee): DBIO[Int] = {
    sqlu"insert into coffees values (${c.name}, ${c.supID}, ${c.price}, ${c.sales}, ${c.total})"
  }

  def insertSupplier(s: Supplier): DBIO[Int] = {
    sqlu"insert into suppliers values(${s.id}, ${s.name}, ${s.street}, ${s.city}, ${s.state}, ${s.zip})"
  }

  def findAllCoffees(): DBIO[Vector[Coffee]] = {
    sql"select * from coffees".as[Coffee](getCoffeeResult)
  }
}
