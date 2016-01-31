package straming

import java.io.PrintWriter
import java.net.ServerSocket
import java.util.Random

import scala.collection.mutable.ListBuffer


/**
  * Created by root on 1/3/16.
  */
object LoggerSimulation {

  def index={
    val rdm=new Random
    rdm.nextInt(7)
  }

  def generateContent(index: Int) :String={

    val charList=ListBuffer[Char]()
    for (i<-65 to 90){
      charList+=i.toChar
    }
    val charArray=charList.toArray
    charArray(index).toString

  }
  def main(args: Array[String]) {

    if(args.length!=2){
      println("Error")
      System.exit(1)
    }

    val listener=new ServerSocket(args(0).toInt)
    while (true){
      println("while")
      val socket=listener.accept()
      println("accept")
      new Thread() {

        override def run(): Unit = {
          println("Got client connection from:" +socket.getInetAddress)
          val out=new PrintWriter(socket.getOutputStream(),true)
          while (true){
            Thread.sleep(args(1).toLong)
            val content=generateContent(index)
            println(content)
            out.write(content + "\n")
            out.flush()
          }
          socket.close()


        }
      }.start()



    }




  }


}
