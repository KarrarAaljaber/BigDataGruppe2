import java.io._
import org.apache.commons._

import org.apache.http.{ io => _, _ }
import org.apache.http.client._
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.util.ArrayList
import org.apache.http.message.BasicNameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import com.google.gson.Gson



case class NodeResponse(key : String, value: String)
case class Node(key : String, value: String, nodes: Array[NodeResponse])
case class Message(action: String, node: Node)


case class FrontPageData(overallRank: String, country: String, score: String, gdp: String)

object HttpJsonPostTest extends App {

 

    
}

case class Teacher(firstName: String, lastName: String, age: Int)

object HttpGetPostTest extends App {


    var index =0
    for(index <- 1 to 10){
        val url = "http://127.0.0.1:2379/v2/keys/IncomeByCountry/" + index 
        val result = scala.io.Source.fromURL(url).mkString

           
        val messageParsed = new Gson().fromJson( result, classOf[Message] );
        val valueParsed = new Gson().fromJson( messageParsed.node.nodes(0).value, classOf[FrontPageData] );

        println(valueParsed)
        
    

    }
  
    
}