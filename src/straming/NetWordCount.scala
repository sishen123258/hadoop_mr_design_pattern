package straming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by root on 1/3/16.
  */
object NetWordCount {


  def main(args: Array[String]) {

    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc=new StreamingContext(conf,Seconds(10))
    val lines=ssc.socketTextStream("localhost",9999)

    val words=lines.flatMap(_.split(" "))
    val map_word=words.map(w=>(w,1))
    val word_count=map_word.reduceByKey(_+_)

    word_count.print()
    word_count.saveAsTextFiles("tong")
    ssc.start()
    ssc.awaitTermination()

  }

}
