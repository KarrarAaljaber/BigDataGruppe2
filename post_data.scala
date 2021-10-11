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

case class FrontPageData(overallRank: String, country: String, score: String, gdp: String)

object HttpJsonPostTest extends App {

    val source = io.Source.fromFile("res/2019.csv")   
    val lines = source.getLines()
    
    println("Overall rank, Country, Score, GDP")

  
    for (line <- source.getLines) {
        val cols = line.split(",").map(_.trim)
        

        
        val overallRank = {cols(0)}
        val country = {cols(1)}
        val score = {cols(2)}
        val gdp = {cols(3)}


        //country.replaceAll("\\s", "-")
        val post = new HttpPost("http://127.0.0.1:2379/v2/keys/IncomeByCountry/" + overallRank)
        val frontPageData = new FrontPageData(overallRank, country, score,gdp)
        val fpjson = new Gson().toJson(frontPageData)

        //println(fpjson)
         val nameValuePairs = new ArrayList[NameValuePair]()
         nameValuePairs.add(new BasicNameValuePair("value", fpjson))
         post.setEntity(new UrlEncodedFormEntity(nameValuePairs))

         val client = new DefaultHttpClient
         val response = client.execute(post)
         println("--- HEADERS ---")
         response.getAllHeaders.foreach(arg => println(arg))

        
    }
    source.close

    
}
