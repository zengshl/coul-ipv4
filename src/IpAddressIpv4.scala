import java.lang._
/**
 * scala  Ipv4 头部数据校验
 * 
 * zengshl  2015-11-14
 * 
 */
class IpAddressIpv4 {
  
  
  /**
   * 16进制转换为2进制
   */
  def sixtyToSecondNum(hexString:String):String={
    //data  valication
     if (hexString == null || hexString.length() % 2 != 0)   null;  
     var bString = "";
     var tmp="";
     for (i <- 0 to  hexString.length()-1; from = i+1){  
          tmp = "0000"+ Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));  
          bString += tmp.substring(tmp.length() - 4);  
     }    
     bString;
  }
  
  /**
   * 2进制转换为16进制
   * for (i <- 1 to 3; j <- 1 to 3) print ((10 * i +j) + " ")

    // 将打印 11 12 13 21 22 23 31 32 33

        每个生成器都可以带一个守卫，以if开头的Boolean表达式：

   for (i <- 1 to 3; j <- 1 to 3  if i != j) print ((10 * i + j) + " ")

    // 将打印 12 13 21 23 31 32

       注意在if之前并没有分号。

    你可以使用任意多的定义，引入可以在循环中使用的变量：

  for (i <- 1 to 3; from = 4 - i; j <- from to 3) print ((10 * i + j) + " ")
   */
  def SecondToSixtyNum(bString:String):String={
      //data  valication
    if(bString == null || bString.equals("") || bString.length() % 8 != 0) null;
    var  tmp = new  StringBuffer();
    for( i <- 0 to bString.length()-1 by 4 ){
        var  iTmp = 0;
        for(j<- 0 to 3 ; form = j+1){
             iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1); 
        }
        tmp.append(Integer.toHexString(iTmp));  
    }
    tmp.toString();
  }
  
  
  /*
   *  多个16进制的数据加减
   * */
  def sixtyAdd(data :Array[String]):String={
    //1.进行数据校验
    if(data == null || data.length <=0) null;
    
    var result = "0000";
    
    for(str <- data){
      
      var x = Long.parseLong(str , 16);
      result = Long.toHexString(x+Long.parseLong(result , 16));
      
    }
    result;
    
  }
  
  /*
   *  多个2进制的数据加减
   * */
  def secondAdd(data :Array[String]):String={
    //1.进行数据校验
    if(data == null || data.length <=0) null;
    
    var result = "0000";
    
    for(str <- data){
      
      var x = Long.parseLong(str , 2);
      result = Long.toBinaryString(x+Long.parseLong(result , 2)); 
    }
    //2.对数据进行补0的操作
    if(result.length() % 4 == 0) result;
    if(result.length() % 4 == 1) result="000"+result;
    if(result.length() % 4 == 2) result="00"+result;
    if(result.length() % 4 == 3) result="0"+result;
    result;
    
  }
  
  
  def checksum(ipv4String : String):String={
    
     println("输入IPV4数据:"+ipv4String);
     var data = ipv4String.split(" ");
     var ipadress = new  IpAddressIpv4();
     //1.先获取16进制相加的数据
     var  hexData = ipadress.sixtyAdd(data);
     println("十六进制数据相加:"+hexData);
      //2.将16进制的数据转化为2进制的数据
     var  dscData = ipadress.sixtyToSecondNum(hexData);
     println("16进制的数据转化为2进制的数据:"+dscData);
     //3.两个2进制的数据相加
     var ydscData = new Array[String](2);
     ydscData(0) = dscData.substring(0, 4);
     ydscData(1) = dscData.substring(4, dscData.length);
     var  binData = ipadress.secondAdd(ydscData);
     println("两个2进制的数据相加:"+ipadress.secondAdd(ydscData));
     //4、将数据反转
     var  turnData =Integer.toBinaryString(~Integer.parseInt(binData , 2));
     turnData = turnData.substring(turnData.length()-binData.length())
     println("将数据反转:"+turnData);
     //5、在将2进制数据转换为16进制的数据
     var  checkData = ipadress.SecondToSixtyNum(turnData)
     println("在将2进制数据转换为16进制的数据:"+checkData);
     //6、替换结果未检测的数据(替换第二个数据)
     var replace = ipv4String.substring(0,15)+ipv4String.substring(15).replace("0000", checkData);
     println("校验结果:"+replace); 
     replace;
  }
  
}

object  IpAddressIpv4{
  
  var  result:String = "0000";
  
  def  main(arg : Array[String]):Unit={
    
     var str = "4500 0073 0000 4000 4011 0000 c0a8 0001 c0a8 00c7";  //==4500 0073 0000 4000 4011 b861 c0a8 0001 c0a8 00c7
     var str2 = "4500 0042 3038 0000 4011 0000 c0a8 0afa af90 6ce9"; //==4500 0042 3038 6257 4011 6257 c0a8 0afa af90 6ce9
     var ipadress = new  IpAddressIpv4();
     ipadress.checksum(str);
     ipadress.checksum(str2);
      
     
  }
  
  
}