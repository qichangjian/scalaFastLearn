import scala.collection.mutable.ArrayBuffer

class NetWork {
  class Member(val name:String){ //Member类
    val contacts = new ArrayBuffer[Member]
  }

  //members字段
  private val members = new ArrayBuffer[Member]

  def join(name:String) = {
    val m = new Member(name)
    members += m
    m
  }
}
