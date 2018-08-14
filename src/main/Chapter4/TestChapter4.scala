import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.JavaConversions.propertiesAsScalaMap
import scala.collection.JavaConversions.mapAsJavaMap
import java.awt.font.TextAttribute._//引入下面映射会用到的键

import scala.collection.mutable

object TestChapter4 {
  def main(args: Array[String]): Unit = {
    /**总结
数组Array    -----------------------可变
	      可变数组Array（2,3）
        可变数组ArrayBuffer

列表List -----------------------不可变
        不可变的列表List(2,3)
	      可变列表ListBuffer
元组 Tuple ----------------------不可变
          (99,"Luftballons")
Set集合  -----------------------不可变
        不可变集合Set("Boeing","Airbus")缺省的
	      可变集合scala.collection.mutable.Set
Map集合映射------------------
	      可变映射 nutable.Map[Int,String]()	treasureMap += (1 -> "Go to island.")
	      不可变映射 缺省的
      */

    /**
      映射是键值对偶的集合，在scala中叫做元组--n个对象的元组，不一定要相同类型
      */
    //1：构建映射----------------------------------------------------
    //不可变映射缺省的
    val scores = Map("Alice" -> 10, "Bob" -> 20)
    val scores1 = Map(("Alience",11),("bobb",22))//另一种写法不常用
    //可变映射，引入包
    val scores2 = scala.collection.mutable.Map("Qi" -> 10, "Tang" -> 20)
    //从一个空映射开始，需要选定一个映射实现并给出类型参数
    val scores3 = new scala.collection.mutable.HashMap[String,Int]

    //2:获取映射中的值-----------------------------------------------------
    val bobScore = scores("Bob")
    println(bobScore)
    //如果映射中不包含请求的键，抛出异常。
    //检查映射中是否有指定键，可以用contains
    val bobsScore = if(scores.contains("Bob")) scores("Bob") else 0
    println(bobsScore)
    //上述组合调用十分普遍，一下为快捷写法
    val bobsScoreQuick = scores.getOrElse("Bob",0)
    println(bobsScoreQuick)
    val testS = scores.get("bbb")//get返回值：对应的值或者是None
    print(testS)

    //3:更新映射中的值-----------------------------------------------------
    //可变映射： 这些方法不可变映射不能用
    scores2("Qi") = 77//可变映射可以这样修改
    scores2("Sun") = 33//添加映射中的值
    scores2 += ("A" ->1,"B" -> 22)//添加多个关系
    scores2 -= "A"
    println("update:" + scores2)

    //不可变映射：
    //不能更新一个不可变映射，但可以获取一个包含所需的更新的新映射
    val newScores = scores + ("A" ->1,"B" -> 22)
    val rScores = scores - "Alice"//移除某个键
    println("不可变添加：" + newScores + ":删除" + rScores)

    //4：迭代映射---------------------------------------------------
    //遍历所有的键值
    for((k,v) <- scores) println("key:" + k + "|value:" + v)
    //只访问key
    for(k <- scores.keys) println(k)
    for(k <- scores.keySet) println(k)//两种方式没有区别
    println("keySet:" + scores.keySet + "|keys:" + scores.keys)
    //访问value
    for(v <- scores.values) println(v)
    //反转一个映射--交换键值位置
    val yiscor = for((k,v) <- scores) yield (v,k)
    println(yiscor)


    //5：已排序映射-------------------------------------------------
    //不可变的树形映射而不是哈希值（没有可变树形映射）
    val sortScores = scala.collection.immutable.SortedMap("Alice" -> 10,"Fred" -> 7, "Bob" -> 3,"Cindy" -> 8)
    println(sortScores)
    //按照插入顺序访问所有键，使用LinkedHashMap
    val months = scala.collection.mutable.LinkedHashMap("January" -> 1,"February" -> 2,"March" ->3)

    //6:与java的互操作-----------------------------------------------
    //java映射转换成scala映射
    /*
    需要引入一下语句import scala.collection.JavaConversions.mapAsScalaMap
    然后通过scala映射类型来触发转换
     */
    val scors:scala.collection.mutable.Map[String,Int]  = new java.util.TreeMap[String,Int]()
    /*
    此外还可以得到从java.util.Properties到Map[String,String] 的转换
    需要引入import scala.collection.JavaConversions.propertiesAsScalaMap
     */
    val props:scala.collection.Map[String,String] = System.getProperties()
    println("java互操作java-scala：" + scors + ":" + props)

    //scala映射传递给预期java映射的方法（隐式转换）
    /*
    需要引入
    import scala.collection.JavaConversions.mapAsJavaMap
    import java.awt.font.TextAttribute._
     */
    val attrs = Map(FAMILY -> "Serif",SIZE -> 12)//scala映射
    val font = new java.awt.Font(attrs)//该方法预期一个java映射
    println("scala->java:" + font)

    //7元组tuple(不同类型值)----------------------------------------------------
    //元组的定义
    val t = (1,3.14,"Fred")
    //访问元组  用_1,_2 ..... 从1开始
    val t1 = t._2
    val t2 = t _3//效果相同
    //通常使用模式匹配类获取元组的组元
    val(first,second,third) = t //将first设置为1，second设置为3.14 third设置为“Fred”
    //如果并不是所有部件都需要，可以在不需要的位置加上_
    val(first1,second1,_) = t
    //元组可以用于函数需要返回不只一个值的情况
    //StringOps的partition方法返回的是一对字符串，分别包含满足某个条件和不满足某个条件的字符
    "New York".partition(_.isUpper)
    println("partition:" + "New York".partition(_.isUpper))

    //8：拉链操作-------------------------------------------
    /*
    使用元组的原因之一是把多个值绑在一起，便于他们能都被一起处理，一般用zip方法来完成
     */
    val symbols = Array("<","-",">")
    val counts = Array(2,10,2)
    val pair = symbols.zip(counts)//绑定
    println("zip:" + pair.toBuffer)
    //这些对偶的处理
    for((s,n)<-pair) Console.print(s*n)

    //toMap方法可以将对偶集合转换成映射
    val mapPair = pair.toMap
    println("toMap:" + mapPair)
  }
}
