/*Running Time Complexity:O(k^n) where k is Character in each block and n block 
Space complexity: O(n*k)
Run and compiled on online compiler
*/
import java.util.*;
public class Main
{
    static List<String> result;
	public static void main(String[] args) {
	    String s = "{a,b}ef{d}";
	    result = new ArrayList<>();
	    List<List<Character>> l = new ArrayList<>();
	    int i = 0;
	    while(i<s.length()){
	        List<Character> temp = new ArrayList<>();
	        if(s.charAt(i) == '{') {
	           i++;
	           while(s.charAt(i) != '}'){
	               if(s.charAt(i)!=',') {
	                    temp.add(s.charAt(i));    
	               }
	               i++;
	           }
	        }
	        else{
	            temp.add(s.charAt(i));
	        }
	        l.add(temp);
	        i++;
	    }
	   Backtrack(l,0,new StringBuilder());
	   String[] arr = new String [result.size()];
	   for(int k = 0;k<result.size();k++){
	       arr[k] = result.get(k);
	   }
	   Arrays.sort(arr);
	   for(int z = 0;z<arr.length;z++){
	        System.out.println(arr[z]);    
	   }
	   
	}
	public static void Backtrack(List<List<Character>> l, int index, StringBuilder sb){
	    //Base
	    if(sb.length()==l.size()){
	        result.add(sb.toString());
	        return;
	    }
	    //Logic 
	    List<Character> temp = l.get(index);
	    for(int i = 0;i<temp.size();i++){
	        //action
	        sb.append(temp.get(i));
	        //Recursion
	        Backtrack(l,index+1,sb);
	        //Backtrack
	        sb.setLength(sb.length()-1);
	    }   
	    
	}
}
