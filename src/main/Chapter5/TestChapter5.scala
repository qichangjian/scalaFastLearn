import scala.beans.BeanProperty

object TestChapter5 {
  def main(args: Array[String]): Unit = {
    /**
      类
      类中的字段自动带有setter和getter方法
      可以用定制的getter/setter方法替换掉字段的定义，而不必修改使用类的客户端--这就是所谓的统一访问原则
      用@BeanProperty注释来生成JavaBeans的getXxx/setXxx方法
      每个类都有一个主要的构造器，这个构造器和类定义“交织”在一起，它的参数直接成为类的字段，主构造器之赐你个类体重所有的语句
      辅助构造器是可选的，他们叫做this
      */

    //1简单类和无参方法——————————————————————
    val myCounter = new Counter//或者new Counter（）
    myCounter.increment()
    myCounter.increment//带不带括号都行，但是一般默认对改值器带括号，取值器不带括号
    println(myCounter.current)

    //2带getter/setter的属性-------------------------------
    //scala中的getter和setter分别叫做age和age_=
    /*
    如果字段是私有的，则setget方法也是私有的
    如果字段是val,则只有getter方法生成
    如果不需要任何getset，可以将字段声明为private[this]
     */
    val myPerson = new Person
    myPerson.age
    myPerson.age_=(13)
    println(myPerson.age)

    //3只带getter的属性----------------------------------------------
    //一个私有字段，加上getter方法（对val字段而言）
    //或者加上setter getter方法（对var而言）

    //4对象私有字段---------------------------------------------------
    //方法可以访问该类所有对象的私有字段

    //5.Bean属性--------------------------------------
    //将字段标注为@BeanProperty时，方法自动生成set.get样式

    //6辅助构造器-------------------------------------
    /*
    辅助构造器的命名为this
    每一个构造器都必须以一个对先前已定义的其他辅助构造器，或者主构造器调用开始的
     */
    //如果一个类没有显示定义构造器，则自动拥有一个无参的主构造器
    val p1 = new Person2//主构造器
    val p2 = new Person2("Fred")//第一个辅助构造器
    val p3 = new Person2("Dad",42)//第二个辅助构造器

    //7主构造器--------------------------------------
    /*
    针对主构造器参数生成的字段和方法

    主构造器参数         生成的字段和方法
    name:String         对象私有字段。如果没有方法使用name，则没有该字段
    private val/var name:String  私有字段，私有的setget方法
    val/var name:String          私有字段，共有的setget方法
    @BeanProperty val/var name:String  私有四段，共有的scala版和JavaBean版的setget方法
     */
    val p5 = new person5("name","12","nan","noodlish")
    println(p5)

    //8嵌套类--------------------------------------------------------------
    //几乎可以自任何语法结构中内嵌任何语法结构
    //类中定义类
    val chatter = new NetWork
    val myFace = new NetWork//每个实例都有自己的Member类
    new chatter.Member("tangqi")//构建新的内部对象
    val qixiaoning = chatter.join("qixiaoning")
    val qichangjian = chatter.join("qichangjian")
    val boBs = myFace.join("Bobs")
    qichangjian.contacts += qixiaoning//ok
    println(qichangjian.contacts)

    //qichangjian.contacts += boBs
    //不可以这样做--不能讲一个myFace.Member添加到chatter.Member元素缓冲中
    /**
      * 如果不想是这种效果，有两种解决方式：
      * 将Member移到别处，一个不错的对象是NetWork的伴生对象
      * object Network{
      *   class Member(val name:String){
      *   ...
      *   }
      * }
      *
      * class Network{
      *   ...
      * }
      *
      * 或者可以使用类型投影Network#Member,其含义是“任何Network的Member,”
      */
  }

}
class Counter{
  private var value = 0//必须初始化字段
  val timestamp = new java.util.Date//构建完成，就不在改变 val
  def increment(){value += 1}//改值器   方法默认是共有的
  def current()=value//取值器
  def currents = value//与上一行相同
}

class Person{
  private var privateAge = 0//私有并改名

  def age = privateAge //getter
  def age_=(newValue:Int): Unit ={
    if(newValue > privateAge) privateAge = newValue;//set不能变年轻
  }
}

class Counters{
  private var value = 0
  def increment(){value += 1}
  def isLess(other:Counters) = value < other.value //可以访问另一个对象的私有属性 因为otherCount也是Counterd对象

  //对象私有的
  private[this] var value2 = 0//类似某个对象，.value这样的访问将不被允许
}

class Persons{
  @BeanProperty var name:String = _
  /*
  将会生成四个方法：
  name:String
  name_=(newValue:String):Unit
  getName():String
  setName(newValue:String):Unit
   */
}
//主构造器参数
class Personss(@BeanProperty var name:String)

class Person2{
  private var name = ""
  private var age = 0

  def this(name:String){//一个辅助构造器
    this()//调用主构造器
    this.name = name
  }

  def this(name:String,age:Int){//另一个辅助构造器
    this(name)//调用前一个辅助构造器
    this.age = age
  }
}

class Person3(val name:String = "",val age:Int){
  //(...)中的内容就是主构造器的参数
  //主构造器会一次执行类定义中的所有语句
  //val name:String = ""在主构造器中使用默认参数，避免过多的使用辅助构造器
  println("Just Constructed another person!")
  def description = name + " is " + age + " years old"
}

class person4 private(val id:Int){}

class person5(name:String,val age:String,private var sex:String,@BeanProperty var likeFood:String){
  def testName = name + "!!!"
}