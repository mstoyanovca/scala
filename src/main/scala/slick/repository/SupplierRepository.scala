package slick.repository

import slick.dbio.DBIO
import slick.jdbc.H2Profile.api._
import slick.model.Supplier

object SupplierRepository {

  def createSuppliers: DBIO[Int] =
    sqlu"""create table suppliers(
    id int not null primary key,
    name varchar not null,
    street varchar not null,
    city varchar not null,
    state varchar not null,
    zip varchar not null)"""

  def insertSupplier(s: Supplier): DBIO[Int] = {
    sqlu"insert into suppliers values(${s.id}, ${s.name}, ${s.street}, ${s.city}, ${s.state}, ${s.zip})"
  }

  def insertSuppliers(suppliers: Seq[Supplier]): DBIO[Seq[Int]] = {
    DBIO.sequence(suppliers.map(s => sqlu"insert into suppliers values(${s.id}, ${s.name}, ${s.street}, ${s.city}, ${s.state}, ${s.zip})"))
  }
}
