import java.io.IOException
import java.net.{MalformedURLException, URL}
import java.text.MessageFormat

object TestChapter2 {
  def main(args: Array[String]): Unit = {
    /**章节要点：
      * 1.if表达式有值
      * 2.块也有值--是它最后一个表达式的值
      * 3.scala的for循环就像是增强版的java for循环
      * 4.分号在绝大数情况下都不是必需的
      * 5.void类型是Unit
      * 6.避免在函数定义中使用return
      * 7.注意别在函数式定义中漏掉了=
      * 8.异常的工作方式和java或c++中基本一样，不同的是你在case中使用模式匹配
      * 9.scala没有受检异常
      */

    //1.条件表达式----------------------------------------------
    /*
    scala中的if/else语法跟java一样，但是在scala中这个表达式有值，
    这个值就是跟在if、else之后的表达式的值
     */
    //第一种：
    val s = if(1 > 0) 1 else -1 //类似于三元运算符
    println("val s:" + s)

    //第二种：效果相同也可以写作    这种写法ss必须为var
    var ss = 0
    if(1 > 0) ss = 1 else ss = -1
    println("var ss:" + ss)

    //混合类型表达式
    val a = if(2 > 3) "Positive" else 1
    println("混合表达式a:" + a)

    //在scala中每个表达式都应该有某种值，这个问题的解决方案是引入一个Unit类，写作（）
    val b = if(1 > 2) 2 //等同于
    val bb = if(1 > 2) 2 else ()
    val bbb = if(2 > 1) 2 else Unit
    println("b：" + b + "bb:" + bb + " bbb:" + bbb)


    //2:语句终止----------------------------------------------
    /*
    如果想在单行中写下多个语句，就需要用分号隔开
    scala程序员更倾向于使用Kernighan & Ritchie风格的花括号
      */
    var r:Int = 2;
    var n = 3;
    if( n > 0) { r = r * n; n -= 1}

    //3：块表达式和赋值----------------------------------------------
    /*
    在scala中，{}块包含一系列表达式，它的结果也是一个表达式。块中最后一个表达式的值就是块的值
     */
    val distance = { val dx = 10-2;val dy = 11-4;math.sqrt(dx*dx+dy*dy)}
    println(distance)
    /*
    在scala中赋值动作本身是没有值的--或者更严格的说，他们的值是Unit类型的
    注意：一个以赋值语句结束的块，比如{r=r*n; n-= 1}的值是Unit类型的。
     */


    //4：输入和输出----------------------------------------------
    //输出：print println 字符串printf函数
    printf("test printf")
    //输入 可以在方法前边加入Console Console.readLine()
    /*
    readLine读取一行输入，readInt readDouble readByte readShort readLong readFloat readBoolean readChar
    与其他方法不同，readLine可以带一个参数作为提示字符串
     */
    //val name = readLine("Your name:")
    //print("Your age:")
    //val age = readInt()
    //printf("Hello ,%s! Next year,your will be %d.\n",name,age+1)


    //5:循环----------------------------------------------
    /*
    scala中没有与for(初始化变量；检查变量是否满足条件；更新变量)对应的结构，
    如果要实现，有两个选择：while循环，二for语句
     */
    var nn = 4;
    var rr = 2;
    //while循环,与java类似
    while(nn > 0){
      rr = rr * nn
      nn -= 1
      println("while in nn:" + nn + "rr:" + rr)
    }
    print("while out nn:" + nn + "rr:" + rr)
    //for循环     for(i <- 表达式)
    nn = 4;
    rr = 2;
    for (i <- 1 to nn){
      rr = rr * i
      println("for内：" + rr)
    }
    println("for外：" + rr)

    /*
    scala并没有提供break或者continue语句来退出循环，如果要实现：
    1.使用Boolean型的控制变量
    2.使用嵌套函数，可以从函数当中return
    3.使用Breaks对象中的break方法...
      */

    //6:高级for循环和for推导式-----------------------------------------------------------
    /*
    变量<-表达式的形式可提供多个生成器，用分号隔开
      */
    for(i <- 1 to 3;j <- 1 to 3) print((10*i+j) + " ")
    println()
    /*
    每个生成器都可以带一个守卫，以if开头的Boolean表达式：注意在if前没有分号
      */
    for(i <- 1 to 3;j <- 1 to 3 if i != j) print((10*i+j) + " ")
    println()
    /*
    可以使用任意多的定义，引入可以在循环中使用的变量
     */
    for(i <- 1 to 3; from = 4 - i; j <- from to 3) print((10*i+j) + " ")
    println()

    //for推导式
    /**
    如果for循环的循环体以yield开始，则该循环会构造出一个集合，每次迭代生成集合中一个值
     */
    for(i <- 1 to 10) yield i % 3
    //for推导式生成的集合与他第一个生成器是类型兼容的
    val test = for(c <- "Hello";i<- 0 to 1) yield (c+i).toChar
    println(test)

    //7：函数-----------------------------------------------------------
    /**
      def 函数名称 （函数参数） = 函数体
      只要函数不是递归的，就不用指定返回类型
      */
    def abc(x:Double) = if(x>=0) x else -x
    println("函数abc:" +abc(200))

    //返回for循环后的r值
    def fac(n:Int) = {
      var r = 1
      for(i <- 1 to n) r = r*i
      r
    }
    //对于递归函数，必须指定返回类型
    def fac1(n:Int):Int = if(n <= 0) 1 else n * fac1(n-1)
    println("递归调用：" + fac1(3))

    //8:默认参数和带名参数 ---------------------------------------------------
    def decorate(str:String,left:String = "[",right:String="]") = left + str + right
    println(decorate("Hello"))
    println(decorate("Helllo",">>>"))

    //9:变长参数-----------------------------------------------------
    def sum(args:Int*) = {
      var result = 0
      for (arg <- args) result += arg
      result
    }
    val sss = sum(1,4,9,16,25)
    println("可变参数：" + sss)

    //如果函数被调用时候传入的是单个参数，那么该参数必须是单个整数，而不是一个整数区间
    //解决这个问题的办法是告诉编译器你希望这个参数被当做参数序列处理，追加：_*
    val sss2 = sum(1 to 5:_*)//将1 to 5 当做参数的序列处理
    println("一个区间当做参数_*:" + sss2)
    //用递归函数实现累加
    def recusiveSum(args:Int*):Int = {
      if(args.length == 0) 0
      else args.head + recusiveSum(args.tail:_*) //序列的head是它的首个元素，tail是其他元素
    }
    val sss3 = sum(1 to 5:_*)//将1 to 5 当做参数的序列处理
    println("递归调用实现累加：" + sss3)
    /*
    为什么基本类型需要手动转换为AnyRef？？？
    因为基本类型不是AnyRef类型的子类，而是AnyVal类型的子类
    asInstanceOf[AnyRef]
    需要转换的方法有：PrintStream printf  MessageFormat.format等
     */
    val strs = MessageFormat.format("The answer to {0} is {1}","everything",42.asInstanceOf[AnyRef])
    println(strs)


    //10：过程----------------------------------------------------------------
    /**
    scala对不返回值的函数有特殊表示法。如果函数体包含在花括号中，但没有前面的=号，那么返回值类型就是Unit
    这样的函数被称作过程（procedure）。
      过程不返回值，我们调用它仅仅是为了他的副作用
     */
    def box(sd:String){//没有=号
      var border = "-" * sd.length + "--\n"
      println(border + "|" + sd + "|\n" + border)
    }
    //建议显示声明返回类型Unit 加上等号
    def box1(sd:String):Unit={//没有=号
      var border = "-" * sd.length + "--\n"
      println(border + "|" + sd + "|\n" + border)
    }
    box("Hello")//调用过程
    box1("box1")


    //11：懒值--------------------------------------------------------------
    /**
      懒值：当val被声明为lazy时，它的初始化被推迟，直到我们首次对他取值
      懒值并不是没有额外开销，每次访问懒值，都有一个方法被调用，这个方法以线程安全的方式检查该值是否已经初始化
      */
      /*
    lazy val words = scala.io.Source.fromFile("/words").mkString//读取words中一行字符并拼接为一个字符串

    //在word1被定义的时候就被取值
    val word1 = scala.io.Source.fromFile("/words").mkString
    //在words被首次使用时取值
    lazy val word2 = scala.io.Source.fromFile("/words").mkString
    //在每一次words被使用时取值
    def word3 = scala.io.Source.fromFile("/words").mkString
    */

    //12:异常-----------------------------------------------
    /**
      1、当抛出异常，当前运算被终止，运行时系统查找可以接受 某 异常的异常接收器
      控制点将在离抛出点最近的处理器中恢复，如果没有找到符合要求的，那么程序退出
      2.scala没有受检异常--你不需要声明说函数或者方法可能会抛出什么异常
      3.throw表达式有特殊的类型Nothing,在ifelse表达式中很有用，
      如果一个分支的类型是Nothing,那么if/else表达式的类型就是另一个分支的类型
      4.错误分为try-catch try-final try-catch-final
      */
    val x = 15
    if(x > 0){math.sqrt(x)} //第一个分支是Double
    else throw new IllegalArgumentException("x should't be nagative") //第二个分支是Nothing 所以ifelse表达式类型为double

    //try-catch
    /*try{
      scala.sys.process(new URL("http://horstmann.com/fred-tiny.gif"))
    }catch{
      case _:MalformedURLException => println("Bad URL:" + url)
      case ex:IOException => ex.printStackTrace()
    }*/
    //try-finall
    /*var in = new URL("http://horstmann.com/fred-tiny.gif").openStream()
    try{
      process(in)
    } finally {
      in.close()
    }*/

  }
}
