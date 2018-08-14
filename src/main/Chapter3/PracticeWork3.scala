import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object PracticeWork3 {
  def main(args: Array[String]): Unit = {
    //1.编写一段代码，将a设置为n个随机整数的数组，要求随机数位于[0,n)之间
    def execise1(n:Int):Array[Int] ={
      val random = new Random()
      val randomArr:Array[Int] = new Array[Int](n)
      for(i <- 0 until n) randomArr(i) = random.nextInt(n)
      randomArr
    }
    println(execise1(9).toBuffer)
    //2.编写一个程序，将整数数组中相邻的元素置换；例如，Array(1,2,3,4,5)置换后变为Array(2,1,4,3,5)
    def execise2(arr:Array[Int]):Array[Int]={
      for (i <- Range(0,arr.length/2 + 1,2)){
        val temp = arr(i)
        arr(i) = arr(i+1)
        arr(i+1) = temp
      }
      arr
    }
    val eArr = Array[Int](1,2,3,4,5)
    println("2:" + execise2(eArr).toBuffer)
    //3.重复前一个练习，不过这一次生成的新的值交换过的数组，用for/yeild

    /*
    4.给定一个整数数组，产生一个新的数组，包含原数组中的所有正值，按原有顺序排序
     之后的元素是所有的零或者负值，按原有顺序排序
     */
    val test4 = Array(1,-1,-3,0,4,-2,4,87,0,-10,9)
    def execise4(arr:Array[Int]): Array[Int] ={
      var newArr:Array[Int] = for(elem <- arr if elem > 0) yield elem
      val newArr1:Array[Int] = for(elem <- arr if elem <= 0) yield elem
      newArr ++= newArr1
      newArr
    }
    def execise41(arr:Array[Int]):Array[Int] = {
      val newArr = ArrayBuffer[Int]()
      val newArr1 = ArrayBuffer[Int]()
      arr.foreach(a => if(a > 0) newArr += a else newArr1 += a)
      newArr ++= newArr1
      newArr.toArray
    }
    println("习题四方法1：" + execise4(test4).toBuffer)
    println("习题四方法2：" + execise41(test4).toBuffer)
    //5.如何计算Array[Double]的平均值
    val test5 = Array(0.1,46.3,24.0,234.2,-4.2,24.0,234.2)
    def execise5(arr:Array[Double]):Double={
      arr.sum/arr.length
    }
    println("double平均值：" + execise5(test5))
    //7.写一个程序，输出数组中的所有值，去掉重复项
    def execise6(arr:Array[Double])= {
      arr.distinct.mkString(" ")
    }
    println("去掉重复项：" + execise6(test5))
    //9.创建一个java.util.TimeZone.getAvailableIDs返回的时区的集合，判断条件是它们在美洲；去掉"America/"前缀并排序
    def execise9()={
      var timezoneArr = java.util.TimeZone.getAvailableIDs()
      var filterArr = for(elem <- timezoneArr if !elem.startsWith("America/")) yield elem
      var sortedArr = filterArr.sorted
      for(ele <- sortedArr) println(ele)
    }
    execise9()
    /*
    10.引入java.awt.datatransfer._并构建一个类型为SystemFlavorMap的对象
    然后以DataFlavor.imageFlavor为参数调用getNativesForFlavor的方法，以Scala缓冲保留返回值
    为什么要用这样一个晦涩难懂的类？因为java标准类库中很难得到试用java.util.List的代码
     */

  }
}
