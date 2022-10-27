import java.util.*;
class Problem1 {
    public String[] expand(String s) {
        List<List<String>>l= new ArrayList<>();
        int flag=0;
        
        
        for(int i=0;i<s.length();i++){
             
            char c = s.charAt(i);
            
            if(c=='{'){
                
                if(l.isEmpty()||(!l.get(l.size()-1).isEmpty())){
               
                    l.add(new ArrayList<String>());
                
                   
                }
                 flag=1;
            }
            
            else if(c==','){
                continue;
                
            }else if(c=='}'){
                 
                if(i==s.length()-1){
                    
                }else{
                 l.add(new ArrayList<String>());
                }
                flag=0;
                
            }else{ 
           
                if(l.isEmpty()){ l.add(new ArrayList<String>());}
                
                if(flag==0){
                    String temp="";
                    while(i<s.length() && s.charAt(i)!='{'){
                         
                        temp=temp+s.charAt(i);
                        i++;
                    }i--;
                    System.out.println(temp);
                 l.get(l.size()-1).add(temp);   
                }
                  else{ 
                       l.get(l.size()-1).add(""+c); 
                      
                  }
                
              
             
            }
            
        }
       System.out.println(l);
        
        List<String> ans= new ArrayList<>();
        StringBuilder ss= new StringBuilder();
        dfs(0,l,ans,ss);
        String[]an=new String[ans.size()];
        an=ans.toArray(an);
        Arrays.sort(an);
        return an;
    }
    
    
    public void dfs(int x, List<List<String>>l, List<String> ans, StringBuilder ss ){

        if(x>=l.size()){  ans.add(ss.toString());return;}
        
        for( int i=0;i<l.get(x).size();i++){
       
            ss=ss.append(l.get(x).get(i));    
            dfs(x+1,l,ans,ss);    
            ss.delete(ss.length()-l.get(x).get(i).length(),ss.length());
            
        }
        
        
        
    }