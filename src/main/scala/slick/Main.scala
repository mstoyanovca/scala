package slick

import slick.jdbc.H2Profile.api._
import slick.model.{Coffee, Supplier}
import slick.repository.{CoffeeRepository, SupplierRepository}
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

object Main {
  implicit val executor: ExecutionContextExecutor = ExecutionContext.global

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

    val result = for {
      _ <- SupplierRepository.createSuppliers andThen CoffeeRepository.createCoffees
      _ <- SupplierRepository.insertSuppliers(suppliers) andThen CoffeeRepository.insertCoffees(coffees)
      result <- CoffeeRepository.findAllCoffees()
    } yield result

    val f: Future[Vector[Coffee]] = db.run(result)

    Try(Await.result(f, Duration.Inf)) match {
      case Success(value) =>
        value.foreach(println(_))
      case Failure(exception) =>
        exception.printStackTrace()
    }

    db.close
  }
}
