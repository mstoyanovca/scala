package slick.repository

import slick.dbio.DBIO
import slick.jdbc.GetResult
import slick.jdbc.H2Profile.api._
import slick.model.Coffee

object CoffeeRepository {
  implicit val getCoffeeResult: AnyRef with GetResult[Coffee] = GetResult(r => Coffee(r.<<, r.<<, r.<<, r.<<, r.<<))

  def createCoffees: DBIO[Int] =
    sqlu"""create table coffees(
    name varchar not null,
    sup_id int not null,
    price double not null,
    sales int not null,
    total int not null,
    foreign key(sup_id) references suppliers(id))"""

  def findAllCoffees(): DBIO[Vector[Coffee]] = {
    sql"select * from coffees".as[Coffee](getCoffeeResult)
  }

  def insertCoffee(c: Coffee): DBIO[Int] = {
    sqlu"insert into coffees values (${c.name}, ${c.supID}, ${c.price}, ${c.sales}, ${c.total})"
  }

  def insertCoffees(coffees: Seq[Coffee]): DBIO[Seq[Int]] = {
    DBIO.sequence(coffees.map(c => sqlu"insert into coffees values (${c.name}, ${c.supID}, ${c.price}, ${c.sales}, ${c.total})"))
  }
}
