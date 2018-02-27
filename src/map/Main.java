package map;

public class Main {

     public static void main ( String[] args){
         SimpleHashMap<Integer,Integer> map = new SimpleHashMap<>();

         for (int i=0;i<11;i++){
             map.put(i, i);
         }
         for (int i=0;i<11;i++){
             map.put(-i, i);
         }
         System.out.println(map.show());
         System.out.print(map.size());
     }
}
