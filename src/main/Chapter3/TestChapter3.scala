import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

object TestChapter3 {
  /**
    * 本章重点：
    * 1.若长度固定使用Array.长度可能有变化使用ArrayBuffer
    * 2.提供初始值时不要使用new
    * 3.用（）访问元素
    * 4.用for(elem <- arr)来遍历元素
    * 5.用for（elem <- arr）... yield ...来将原数组转型为新数组
    * 6.scala和java数组可以互操作，用ArrayBuffer，使用scala.collection.javaConversions中的转换函数
    */
  def main(args: Array[String]): Unit = {
    //1.定长数组 Array-------------------------------------------------
    val nums = new Array[Int](10)//10个整数的数组，所有元素初始化为0
    val test = new Array[String](10)//初始化为null

    val s = Array("Hello","World")//长度为2的Array[String]类型是推断出来的：已经提供了初始值就不需要new
    s(0) = "Goodbye" //使用（）来访问元素，从0开始

    //2.变长数组：数组缓冲 ArrayBuffer--------------------------------------------------------------
    val b = ArrayBuffer[Int]()//一个空的数组缓冲，准备存储整数
    val bb = new ArrayBuffer[Int]// 两种方式效果相同
    //在尾部操作元素   ---高效
    //1。添加元素的三种方式：
    b += 1//用+=在尾部添加元素
    print(b)//打印
    b += (1,3,5,6)//在尾部添加多个元素，用（）括起来
    print(b)
    b ++= Array(8,9,78)//使用++=操作符追加任何集合
    print(b)
    //2。移除元素
    b.trimEnd(5)//移除最后五个元素
    println(b)

    //在任意位置插入和移除元素  ---不那么高效
    b.insert(1,111)//第2个位置插入111
    print(b)
    b.insert(1,22,33,44,55,66)//在第二个位置插入任意多的元素
    print(b)
    b.remove(2)//删除第三个元素
    print(b)
    b.remove(2,3)//从第三个开始移除三个元素，第二个参数是移除多少
    println(b)

    //数组和数组缓冲的转换
    //toArray :有时候需要构建Array数组，但是不知道装多少元素，可以先建立数组缓冲，然后调用toArray
    b.toArray//将b转换为一个数组
    b.toArray.toBuffer//将数组转换为一个数组缓冲


    //3:遍历数组和数组缓冲-----------------------------------------------
    //0 until 10   相当于  0.until(10)
    for(i <- 0 until b.length)//util是RichInt类的方法，返回<上限的数字
      println(i + "：" + b(i))

    //每个元素一跳
    for(i <- 0 until (b.length,2)) println(i + ":" + b(i))

    //从数组的尾端开始，遍历写法
    for(i <- (0 until b.length).reverse) println(i + ":" + b(i))

    //不需要数组下标，也可访问数组元素。类似于增强for
    for(bb <- b)
      println(bb)

    //4：数组转换（不会修改原始数组，而是产生一个全新的数组）---------------------------------------------------
    val a = Array(1,4,7,9,11,22)
    val results = for(aa <- a) yield 2*aa  //创建了一个新集合
    println("for推导式：" + results)
    for(result <- results) print(result + " ")

    /*
    遍历一个集合时候，通常只是处理满足特定条件的元素
    这个需求可以通过守卫：for中的if来实现
     */
    val results2 = for(aa <- a if aa % 2 ==0) yield 2 * aa//去掉奇数，偶数翻倍
    for(result <- results2) print(result + " ")

    //第一个负数做标记，其余负数移除
    val c = ArrayBuffer(1,-4,7,9,-11,22)//这个必须是变长数组
    var first = true
    //first在第一个负数判断的时候为true，进入后修改为false,第二个负数，first是false ，数《= 0，所以进不去循环，所及记下了第一个负数和其他正数
    val indexes = for(i <- 0 until c.length if first || c(i) >= 0) yield {//收集要保留的下标
      if(c(i) < 0)
        first = false;
      i
    }
    print("indexes:" + indexes)
    for(j <- 0 until indexes.length) c(j) = c(indexes(j))
    c.trimEnd(c.length - indexes.length)//去掉多余的负数的长度
    println(c)

    //5:常用算法---------------------------------------------
    //求和
    /**要适用sum方法，元素类型必须是数值类型：要么整形，要么浮点数或者BigInteger/BigDecimal*/
    println("sum（）：" + Array(1,2,3,4,5).sum)//对ArrayBuffer同样适用
    //min输出数组和数组缓冲中最小的元素
    println("min" + ArrayBuffer("mary","had","a","Little","Lamb").min)
    //max
    println("max:" + ArrayBuffer("mary","had","a","Little","Lamb").max)

    val d = ArrayBuffer(1,4,36,9)
    //排序sortBy ：对一个属性或多个属性进行排序，通过它的类型。
    println(d.sortBy(d=>d))//升序（对数组缓存有效）
    println(d.sortBy(d=>d).reverse)//降序
    //sortWith:基于函数的排序，通过一个comparator函数，实现自定义排序的逻辑。
    println(d.sortWith(_<_))//升序（对数组缓存有效）
    println(d.sortWith(_>_))//降序
    //sorted方法将数组和数组缓冲排序，并返回经过排序的数组或数组缓冲，过程不会修改原版本
    print("sorted升序:" + d.sorted)//升序
    println("sorted降序：" + d.sorted.reverse)//降序
    print("test:::::" + a.sorted)//对数组使用该方法都是返回[I@337d0578
    //可以直接对数组排序，但是不能对数组缓冲排序
    println("quickSort对数组排序：" + scala.util.Sorting.quickSort(d.toArray))

    //mkString：显示数组和数组缓冲的内容，允许定义元素之间的分割符
    println("mkString数组缓冲的内容：" + d.mkString(" and "))
    println(d.mkString("<",",",">"))//重载版本，能指定前缀和后缀

    println("数组toString：" + a.toString)//这里被调用的是来自Java毫无意义的toString方法
    println("数组缓存toString：" + d.toString)//toString 报告了类型，便于调试


    //6:解读Scaladoc-----------------------------------------------------------------------

    //7:多维数组--------------------------------------------------
    /*
    多维数组是通过数组的数组来实现的。
    举例来说：Double的二维数组 .通过ofDim创建
     */
    //规则的二维数组
    val matrix = Array.ofDim[Double](2,3)//两行三列
    matrix(1)(1) = 42//赋值
    for(i <- 0 until matrix.length; j <- 0 until matrix(i).length) println(i+":" + j + "|" + matrix(i)(j))
    //不规则的二维数组
    val triangle = new Array[Array[Int]](10)
    for(i <- 0 until triangle.length) triangle(i) = new Array[Int](i+1)
    triangle(2)(2) = 99
    for(i <- 0 until triangle.length;j <- 0 until triangle(i).length)
      println(i + ":" + j + "|" + triangle(i)(j) + "|" + triangle(i).length + "|" + triangle(i).toBuffer)

    //8:与java的互操作-----------（不是太明白）-----------------------------------
    /*
    需要导入包
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConversions.bufferAsJavaList
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer
     */
    val command = ArrayBuffer("ls","-al","/home/cay")
    val pd = new ProcessBuilder(command)//Scala到java的转换
    val cmd: Buffer[String] = pd.command()//java到scala的转换
    //不能使用ArrayBuffer--包装起来的对象仅能保证是个Buffer
  }
}
