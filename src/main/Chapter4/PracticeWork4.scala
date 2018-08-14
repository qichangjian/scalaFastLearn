import scala.collection.mutable
import scala.io.Source

import scala.collection.immutable.SortedMap
import java.util
import scala.collection.JavaConversions.mapAsScalaMap


object PracticeWork4 {
  def main(args: Array[String]): Unit = {
    /*
    1.设计1个映射，其中包含你需要的一些装备，以及他们的价格
    然后构建另一组映射，采用同样的键，但在价格上打九折
     */
    def ex1()={
      val map1 = Map[String,Double]("青龙偃月刀" -> 2.33,"如意金箍棒"->7.8,"方天画戟"->3.33)
      for((k,v) <- map1) print("武器1：" + k + ":" + v +"  ")
      val map2 = new mutable.HashMap[String,Double]()//定义一个空映射
      for((k,v) <- map1) map2(k) = v*0.8//赋值
      for((k,v) <- map2) print("武器2：" + k + ":" + v +"  ")
    }
    ex1()
    /*
    2.编写一段程序，从文件中读取单词；用一个可变的映射清点单词出现的频率
     */
    def ex2(filepath:String): Unit ={
      val countMap = new mutable.HashMap[String,Int]()
      Source.fromFile(filepath).getLines().foreach(line => {
        for(word <- line.split(" ")){
          if(!countMap.keySet.contains(word.toLowerCase))
            countMap(word.toLowerCase) = 0
          countMap(word.toLowerCase) = countMap(word.toLowerCase) + 1
        }
      })
      for((word,wordcount) <- countMap){
        printf("word:%s,count:%d\n",word,wordcount)
      }
    }
    ex2("D:\\ScalaWorkSpace\\scalaFastLearn\\src\\main\\Chapter4\\test.txt")
    /*
    3.重复做前一个练习，这次用不可变映射????????????????????///
     */
    /*def ex3(filepath:String):Unit={
      var countMap = Map[String,Int]()
      Source.fromFile(filepath).getLines().foreach(line => {
        for(word <- line.split(" ")){
          if(countMap.keySet.contains(word.toLowerCase)){
            val newCountMap:Map[String,Int] = countMap + (word.toLowerCase -> 1)
            countMap = newCountMap
          }else{
            val count:Int = countMap(word.toLowerCase) + 1
            val newCountMap:Map[String,Int] = countMap - word.toLowerCase + (word.toLowerCase -> count)
            countMap = newCountMap
          }
        }
      })
      for((key,value) <- countMap)
        printf("key3:%s,value:%d\n",key,value)
    }
    ex3("D:\\ScalaWorkSpace\\scalaFastLearn\\src\\main\\Chapter4\\test.txt")*/

    /*
    4.重复前一个练习，这次用排序集合，使得单词可以以排序的方式输出
     */
    def ex4(filepath:String): Unit ={
      var countMap = SortedMap[String,Int]()
      Source.fromFile(filepath).getLines().foreach(line =>{
        for(word <- line.split(" ")){
          if(!countMap.keySet.contains(word.toLowerCase)){
            var newCount:SortedMap[String,Int] = countMap + (word.toLowerCase -> 1)
            countMap = newCount
          }else{
            val count:Int = countMap(word.toLowerCase) + 1
            var newCount:SortedMap[String,Int] = countMap - word.toLowerCase + (word.toLowerCase -> count)
            countMap = newCount
          }
        }
      })
      for((key,value) <- countMap)
        printf("key4:%s,value:%d\n",key,value)
    }
    ex4("D:\\ScalaWorkSpace\\scalaFastLearn\\src\\main\\Chapter4\\test.txt")
    /*
    5.重复前一个练习，这次用java.util.TreeMap，并使其适用于scala
     */
    def ex5(filepath:String): Unit ={
      var countMap = new util.TreeMap[String,Int]()
      Source.fromFile(filepath).getLines().foreach(line => {
        for(word <- line.split(" ")){
          if(!countMap.keySet().contains(word.toLowerCase))
            countMap(word.toLowerCase) = 0
          countMap(word.toLowerCase) = countMap(word.toLowerCase) + 1
        }
      })
      for((word,wordcount) <- countMap)
        printf("5Word:%s,wordCount:%d\n",word,wordcount)
    }
    ex5("D:\\ScalaWorkSpace\\scalaFastLearn\\src\\main\\Chapter4\\test.txt")
    /*
    7.打印java系统属性表格
     */
    def ex7(): Unit ={
      var sysMap = System.getProperties
      var maxlen:Int = 0
      for((key,value) <- sysMap if String.valueOf(key).length > maxlen) maxlen = String.valueOf(key).length
      for((key,value) <- sysMap) println(key+" "*(maxlen - String.valueOf(key).length) + "|" + value)
    }
    ex7()
    /*
    8.编写一个函数minmax(values:Array[Int]),返回数组中最大值和最小值的对偶
    Tuple2放两个数据
     */
    def minmax(values:Array[Int]):Tuple2[Int,Int]={
      Tuple2[Int,Int](values.min,values.max)
    }
    println(minmax(Array(1,3,5,76,3,6)))
    /*
    9.编写一个函数lteqgt(values:Array[Int],v:Int),返回数组中小于v，等于v和大于v的数量
    要求一起返回
     */
    def lteqgt(values:Array[Int],v:Int):Tuple3[Int,Int,Int]={
      var lcount = 0
      var ecount = 0
      var gcount = 0
      for(elem <- values){
        if(elem < v){
          lcount = lcount +1
        }else if(elem == v){
          ecount = ecount + 1
        }else{
          gcount = gcount + 1
        }
      }
      Tuple3[Int,Int,Int](lcount,ecount,gcount)
    }
    println(lteqgt(Array(1,2,3,4,5,6,7),4))
    /*
    10."Hello".zip("world")得到的是Vector(((H,w), (e,o), (l,r), (l,l), (o,d)))
     */
    println("hello".zip("world"))
  }
}
