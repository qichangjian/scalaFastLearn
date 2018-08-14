import scala.collection.immutable.Range
import scala.math._
object TestChapter1 {
  def main(args: Array[String]): Unit = {
    //1:scala解释器----------------------------------------------
    /*
    Scala解释器：
      Scala解释器读到一个表达式，对它进行求和，将它打印出来，接着继续读取下一个表达式
      这个过程被称作：读取-求值-打印-循环，即REPL
      scala程序并不是一个解释器，输入的内容被快速编译为字节码，然后交由jvm执行。
     */


    //2:声明值和变量----------------------------------------------
    /*
      val定义的值实际上是一个变量，无法改变他的内容
      var定义的值是一个可变的变量。

      注意：
      可以不用声明值和变量的类型，可以通过初始化表达式方式推断出来
      声明值和变量但不做初始化会报错
      （特别注意：）在scala中，变量和函数的类型总是写在变量和函数名称的后面
     */
    val name = "qichangjian"//不可改变
    var counter = 0
    counter = 2//可以改变
    //必要的时候可以指定类型
    val greeting:String = null
    //对于Any和AnyVal，只有在编译的时候，scala才会将它们视为Object。换句话说，在编译阶段Any和AnyVal会被类型擦除为Object。
    val greeting1:Any = "Hello"
    var greeting2,message:String = null //greeting2和message都是字符串，被初始化为null


    //3:常用类型----------------------------------------------
    /*
    Scala中有七种数值类型：Byte,Char,Short,Int,Long,Float和Double以及一个Boolean类型
    跟java不同，这些类型都是类
    scala并不可以区分基本类型和引用类型。
     */
    print(1.toString())
    /*
    RichInt,RichDouble,RichChar提供Int,Double,Char中所不具备的快捷方法
    BigInt和BigDecimal类，用于任意大小（但又穷）的数字，分别对应的是java.math.BigInteger和java.math.BigDecimal
    下例中：Int值首先被转换为RichInt，然后再应用to方法
     */
    println(1.to(10))//产出Range（1,2,3，4,5,6,7,8,9,10） RichInt类中的方法，提供快捷方法
    /*
    在scala中，我们用方法，而不是强制类型转换，来做数值类型之间的转换
    Scala用底层的java.lang.String类来表示字符串。它通过StringOps类给字符串追加了上百种操作
    下例中：对象被隐式的转换成了一个StringOps对象，接着StringOps类的intersect方法被应用
     */
    println("Hello".intersect("World")) //输出两个字符串中共通的一组字符


    //4:算术和操作符重载----------------------------------------------
    /*
    Scala的算术操作符合java或C++中预期的效果是一样的
    不同的是 这些操作符实际上是方法。例如：a+b 是a.+(b)的简写
    重要： a.方法（b） 可以写成 a 方法 b
          a.+(b) --> a + b    "Hell".intersect("eldd") --> "Hell" intersect "eldd"

    scala没有提供++ --操作符，我们需要使用+=1或者-=1 原因：Int类是不可变的，此方法不能改变某整数类型的值
     */
    println("Hello " intersect "hhllo")
    println("hello".intersect("ddodd")) //效果相同


    //5:使用函数和方法----------------------------------------------
    /*
    Scala中使用数字函数（比如min或者pow）更为简单
    使用以scala开头的包时，可以省去scala
     */
    math.sqrt(2)  //将产出1.4142135623730951
    math.pow(2,4)//将产出16.0
    println(math.min(3,5))  //将产出3.0
    sqrt(2)//需要import scala.math._ 不然就10 max要加上math.    _字符是通配符。类似java的*
    /*
    （重要：）不带参数不改变当前对象的scala方法通常不使用（）。
    例如：StringOps类的API显示它有一个distinct方法，不带（）
      */
    "Hello".distinct   //获取字符串中不重复的字符

    /*
    通常，一个类对应一个伴生对象，其方法就跟java中的静态方法一样。
    举例来说，BigInt类的BigInt伴生对象有一个生成指定随机素数的方法probablePrime
     */

    //6:apply方法----------------------------------------------
    /*
    使用伴生对象的apply方法是scala中构建对象的常用手法，
      例如Array(1,4,9)返回一个数组，用的就是Array的伴生对象的apply方法

    "Hello"(4)可以把这种语法当做是（）操作符的重载形式，它背后的实现原理是
      一个名为apply的方法 def apply(n:Int):Char, 也就是说可以写成“Hello”.apply(4)
     */
    println("Hello"(4))
    println("hello".apply(4))
    println(BigInt("11") * BigInt.apply("2"))


    //7:Scaladoc -------------------------------------------------------
    /*
    可以在https://www.scala-lang.org/api在线浏览scaladoc.也可以下载下来再本地看
    每个类名旁边的O和G，分别连接到对应的类（C）和伴生对象（G）
    如果使用数值类型，记得看看RichInt，RichDouble等，字符串看SpringOps
    数学函数位于scala.math包中，而不是位于某个类中
    ..................
     */
  }
}
