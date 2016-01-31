package straming

import org.apache.spark.streaming.{StreamingContext, Seconds}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by root on 1/7/16.
  */
object SqlNetWordCount {

  def main(args: Array[String]) {

    val conf=new SparkConf().setMaster("local[2]").setAppName("SqlNetWordCOunt")
    val ssc=new StreamingContext(conf,Seconds(10))

    val lines=ssc.socketTextStream("localhost",9999)

    val words=lines.flatMap(_.split(" "))

    words.foreachRDD(rdd=>{



      }

    )





  }




}
