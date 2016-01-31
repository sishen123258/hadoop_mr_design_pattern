package straming

import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds
import org.apache.spark.SparkConf



/**
  * Created by root on 12/29/15.
  */
object Streaming {

  def main(args: Array[String]) {
    //本地模式下使用２个ｃｐｕ内核运行
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    //初始化StreamingContext 设置每隔一秒钟产生一个Dstream
    val ssc = new StreamingContext(conf, Seconds(1))
    //listen to localhost:9999
    val lines = ssc.socketTextStream("localhost", 9999)
    //统计消息中出现ｅｒｒｏｒ的ｌｉｎｅ
    val errorLines = lines.filter(_.contains("error"))
    errorLines.print()//print
    // 开始任务
    ssc.start()
    ssc.awaitTermination()


  }

}
