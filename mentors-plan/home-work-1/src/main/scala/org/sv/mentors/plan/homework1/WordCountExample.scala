package org.sv.mentors.plan.homework1

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object WordCountExample {

  def parseFileName(seq: Seq[String]): String = seq match {
    case Seq(x) => x
    case Seq(x, xs@_*) => x
    case _ => throw new Exception("Please pass file name as a first parameter")
  }

  def main(args: Array[String]) {

    val filename: String = parseFileName(args)

    Logger.getLogger("org").setLevel(Level.ERROR)
//        val conf = new SparkConf().setAppName("wordCounts").setMaster("local")
//        val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
//        val conf = new SparkConf().setAppName("wordCounts").setMaster("*")
        val conf = new SparkConf().setAppName("wordCounts").setMaster("spark://spark-master:7077")
//    val conf = new SparkConf().setAppName("wordCounts").setMaster("spark://localhost:7077")
      //      .setSparkHome("/opt/spark/")
//      .setSparkHome("./spark-apps/")
    //  .getOrCreate()
    val sc = new SparkContext(conf)
    //    val sc = SparkSession.builder()
    //      .appName("SparkSample")
    //      .master("spark://ec2-54-245-111-320.compute-1.amazonaws.com:7077")
    //      .getOrCreate()

//    val lines = sc.parallelize(List("any strings ddd\n\n\n\n    here"))
    val lines = sc.textFile(s"/opt/spark-data/${filename}")
    val words = lines.flatMap(line => line.split("\\W")).filter(_.nonEmpty)

    println(s" ===> ${filename} contains ${words.count()} words")
//    words.foreach {
//      println
//    }
//
//    val wordCounts = words.countByValue()
//    for ((word, count) <- wordCounts) println(word + " : " + count)
  }

}

