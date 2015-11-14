

import java.util.ArrayList
import org.junit.Test
import org.junit.Assert._
/**
 * athor  zengshl 2015-11-14
 */
class IpAddressIpv4Test {
  
  
   /*Ip类的单元测试*/ 
    
   @Test
   def  IpAddressTestCase(){
     
     var  ipv4Test = new IpAddressIpv4();
     assert(ipv4Test.checksum("4500 0042 3038 0000 4011 0000 c0a8 0afa af90 6ce9") == 
                "4500 0042 3038 6257 4011 6257 c0a8 0afa af90 6ce9");
     assert(ipv4Test.checksum("4500 0073 0000 4000 4011 0000 c0a8 0001 c0a8 00c7")==
                "4500 0073 0000 4000 4011 b861 c0a8 0001 c0a8 00c7");
     
   }
  
}