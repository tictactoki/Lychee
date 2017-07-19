package models.db

import com.sksamuel.elastic4s.http.HttpClient
import com.sksamuel.elastic4s.{ElasticsearchClientUri, TcpClient}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.searches.RichSearchResponse
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy
import org.elasticsearch.common.settings.Settings
import com.sksamuel.elastic4s.jackson.ElasticJackson.Implicits._


// circe
import com.sksamuel.elastic4s.circe._
import io.circe.generic.auto._

/**
  * Created by stephane on 18/07/2017.
  */
object Connection {


  val uri = ElasticsearchClientUri("localhost", 9200)
  val client = TcpClient.transport(uri)

  client.execute {
    indexInto("bands" / "artists").fields("name" -> "coldplay").refresh(RefreshPolicy.IMMEDIATE)
  }.await

  val resp = client.execute {
    search("bands" / "artists") query "coldplay"
  }.await

  println(resp)

}
