package models.db

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx
import com.orientechnologies.orient.core.metadata.schema.OType
import com.orientechnologies.orient.core.record.impl.ODocument
import com.tinkerpop.blueprints.impls.orient.{OrientEdgeType, OrientVertexType, OrientGraphFactory, OrientGraph}


// circe
/**
  * Created by stephane on 18/07/2017.
  */


object Connection {

  val uri = "plocal:/Users/stephane/Downloads/orientdb-community-importers-2.2.24/databases/lychee"
  //val odb: ODatabaseDocumentTx = new ODatabaseDocumentTx("remote:localhost/lychee").open("root","test")
  val graphFactory = new OrientGraphFactory(uri)

  val graph = graphFactory.getTx

  val person: OrientVertexType = graph.createVertexType("PersonGG")
  person.createProperty("firstName", OType.STRING)
  person.createProperty("lastName", OType.STRING)

  val work: OrientEdgeType = graph.createEdgeType("WorkG")
  work.createProperty("startDate", OType.DATE)
  work.createProperty("endDate", OType.DATE)

  //val doc = new ODocument("User")
  //doc.field("name", "test")
  //doc.field("email","test@test.com")

  //doc.save()
  //odb.close()


}
