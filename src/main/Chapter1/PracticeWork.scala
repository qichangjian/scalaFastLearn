object PracticeWork {
  def main(args: Array[String]): Unit = {
    //在Scala REPL 中键入3，然后按Tab键，有哪些方法？
    /**
!=   <     abs            equals       isInstanceOf    isWhole     shortValue       toFloat         unary_-
##   <<    asInstanceOf   floatValue   isNaN           longValue   signum           toHexString     unary_~
%    <=    byteValue      floor        isNegInfinity   max         synchronized     toInt           underlying
&    ==    ceil           formatted    isPosInfinity   min         to               toLong          until
*    >     compare        getClass     isValidByte     ne          toBinaryString   toOctalString   wait
+    >=    compareTo      hashCode     isValidChar     notify      toByte           toRadians       |
-    >>    doubleValue    intValue     isValidInt      notifyAll   toChar           toShort         →
->   >>>   ensuring       isInfinite   isValidLong     round       toDegrees        toString
/    ^     eq             isInfinity   isValidShort    self        toDouble         unary_+
      */

    //res变量是val 还是 var ：
    /**
      是val不能改变
     */

    //Scala允许用数字*字符串，
    /**
    “ crazy” * 3 结果是：
      结果为 res: String = crazycrazycrazy
    注意：
    但是不能写作 3 * “crazy”
      */

    //10 max 2 的含义是什么？
    /**
      10 max 2
      10.max(2)
      math.max(10,2)效果相同
      */

    //用BigInt计算2的1024次方
    /**
      BigInt("2").pow(1024)
      BigInt(2).pow(1024)
      */

    //生成一个随机的BigInt，然后将其转换成三十六进制，输出类似“qsnvtomc3k”这个的字符串
    /**
    BigInt(util.Random.nextInt).toString(36)
      */

    //在scala中如何获取字符串的首字符和尾字符
    println("Hello"(0))
    println("Hello".take(1))//从1开始
    println("Hello".reverse(0))
    println("Hello".takeRight(1))//从1开始

    //take,drop,takeRight,dropRight这些字符串函数都是做什么用的？和substring相比，它的优缺点有哪些
    /**
      take是从字符串开始获取字符串，drop是从字符串开始到坐标去除字符串，takeRight,dropRight是从字符串尾部开始操作

      这四个方法都是单向的，如果我们想要取字符串中间的字段，那么我们需要同时调用take,takeRight或者调用substring方法
      */
    println("Hello".drop(2))
    println("Hello".dropRight(2))
    println("Hello".take(1))
    println("Hello".takeRight(1))
  }
}
