object PracticeWork2 {
  def main(args: Array[String]): Unit = {
    /**
    1.如果一个数字为正数，则它的signum为1；如果是负数，则signum为-1；如果是0，则signum是0
    编写一个函数来计算这个值
     */
    def execise1(num:Int):Int={
      if(num > 0) 1 else if(num == 0) 0 else -1
    }

    /**2.一个空的块表达式的值是什么？它的类型是什么
          值是（）类型是Unit
      */
    /**3.指出Scala何种情况下赋值语句x=y=1是合法的
      在x的类型为Unit的情况下是合法的
      */
    /**4.针对下列java循环编写一个scala版
    for(int i=10;i>=0;i--)System.out.println(i)
      */
    def execise4_1()={
      for(i <- 0 to 10 reverse) println(i)
    }
    execise4_1()

    def execise4()={
      var i = 10
      while(i>0){
        println(i)
        i = i-1
      }
    }
    //execise4()

    /**5.编写一个过程countdown(n:Int)，打印从n到0的数字
      */
    def execise5_1(n:Int): Unit ={
      (0 to n reverse).foreach(i => println(i))
    }
    def execise5(n:Int)={
      var i = n
      while(i>0){
        println(i)
        i = i-1
      }
    }
    execise5_1(12)

    /** 6.编写一个for循环，计算字符串中所有字母的unicode代码的乘积
     举例来说，"Hello"中所有字母的字符成为9415087488L
      */
    def execise6(input:String):BigInt ={
      var sum:BigInt = 1
      val length = input.length()
      for(i <- 0 to length-1){
        sum = sum * input(i).toLong
      }
      sum
    }
    println(execise6("Hello"))

    /**7.同样是解决6的问题，但不能够用循环（提示，StringOps）
      */
    def execise7(input:String): BigInt ={
      var t:Long = 1
      input.foreach(t *= _.toLong)
      t
    }
    println("7:" + execise7("Hello"))

    /**
      *8.编写一个函数produce(s:String)计算前面练习提到的乘积
      */
    def produce(s:String):Long={
      var t:Long = 1
      for (ss <- s)
        t *= ss.toLong
      t
    }
    println("8:" + produce("Hello"))
    /**
      * 9.把前一个练习中的函数改为递归函数
      */
    def proceduce2(s:String):Long={
      if(s.length == 1) return s.charAt(0).toLong
      else s.take(1).charAt(0).toLong * proceduce2(s.drop(1))
    }
    println("9:" + proceduce2("Hello"))
    /**
      * 10 编写函数计算xn,其中n是整数，使用如下的递归定义:
      *
      * xn=y2,如果n是正偶数的话，这里的y=x(n/2)
      * xn = x*x(n-1),如果n是正奇数的话
      * x0 = 1
      * xn = 1/x(-n),如果n是负数的话
      * 不得使用return语句
      */
    def mi(x:Double,n:Int):Double={
      if(n == 0) 1
      else if(n>0 && n%2 == 0) mi(x,n/2)*mi(x,n/2)
      else if(n>0 && n%2 == 1) x*mi(x,n-1)
      else 1/mi(x,-n)
    }
    println("10:" + mi(2.0,2))
  }
}
