import java.util.*;


public class TrieContacts {

    public static void main(String[] args) {
        Trie t = new Trie();
        t.add("abcd");
        t.add("abdd");
        t.add("acda");
        //t.printAll("");
        t.printAllContacts();
        t.find("ab");
        int count = t.count("a");
        
        /*below block for system input. Example:
         * add pearl
         * add mike
         * add mark
         * find pe
         * find m
         * */
        
        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            String func = sc.next();
            String s = sc.next();
            if(func.equals("add")){
                t.add(s);
            }
            if(func.equals("find")){
                System.out.println(t.count(s));
            }
        }*/
    }
}

class Trie{
    Trie[] childs = new Trie[26];
    boolean isLeaf = false;
    int entries = 0;
    void add(String s){
        //s = s.toLowerCase();
        entries++;
        if(s.equals("")){
            isLeaf = true;
        } else if(s.charAt(0) < 97){
            System.out.print("Invalid Character");
        } else if(childs[s.charAt(0)-97] != null){
            childs[s.charAt(0)-97].add(s.substring(1));
        } else {
            Trie temp = new Trie();
            childs[s.charAt(0)-97] = temp;
            temp.add(s.substring(1));
        }
    }
    void printAllContacts(){
        printAll("");
    }
    private void printAll(String s){
        if(isLeaf){
            System.out.println(s);
        }
        for(int i=0; i<childs.length; i++){
            char c = (char) (i+97);
            if(childs[i] != null){
                childs[i].printAll(s+c);
            }
            
        }
    }
    void find(String s){
        Trie temp = this;
        boolean found = true;
        for(int i=0; i<s.length(); i++){
            if(temp.childs[s.charAt(i)-97] != null){
                temp = temp.childs[s.charAt(i)-97];
            } else {
                found = false;
                break;
            }   
        }
        if(found){
            temp.printAll(s);
        } else {
            System.out.println("Contact not found");
        }
        
    }
    
    int count(String s){
        Trie temp = this;
        boolean found = true;
        
        for(int i = 0; i<s.length(); i++){
            if(temp.childs[s.charAt(i)-97] != null){
                temp = temp.childs[s.charAt(i)-97];
            } else {
                found = false;
            }
        }
        int count = 0;
        if(found){
            count = temp.countAll();
        }
        return count;
    }
    
    //below block if you want to count number of words query time.
    /*
    private int countAll(){
        int sum = 0;
        if(isLeaf){
            sum++;
        }
        for(Trie temp:childs){
            if(temp != null){
                sum += temp.countAll();
            }
        }
        return sum;
    }*/
    
    // below block if you want to count number of words addition time and store them.
    //More efficient for large contact lists and frequent queries
    
    private int countAll(){
        return entries;
    }
}