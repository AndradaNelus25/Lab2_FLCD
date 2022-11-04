import java.util.*;
import java.io.*;

public class Main {
    public static int integer_check(String s){
        try{
            Integer.parseInt(s);
            return 1;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    public static boolean reservedWord_check(String s) {
        return s.equals("For") || s.equals("if") || s.equals("otherwise") || s.equals("go") || s.equals("then") || s.equals("do") || s.equals("from") || s.equals("constant") || s.equals("integer") || s.equals("largeint") || s.equals("byte")  || s.equals("array")  || s.equals("chain")  || s.equals("bool")  || s.equals("char") || s.equals("begin")  || s.equals("end") || s.equals("VAR") ;
    }

    public static boolean separator_check(String s) {
        return s.equals("(") || s.equals(")") || s.equals("{") || s.equals("}") || s.equals(":") || s.equals(";") || s.equals(".") || s.equals(",");
    }

    public static boolean operator_check(String s) {
        return s.equals(".+") || s.equals("=") || s.equals(".-") || s.equals("./") || s.equals(".>") || s.equals(".<") || s.equals(".=>") || s.equals(".=<") || s.equals(".=") || s.equals("!!=") || s.equals("^&") || s.equals("^|");
    }

    public static boolean identifier_check(String s)
    {
        if (!((s.charAt(0) >= 'a' && s.charAt(0) <= 'z') || (s.charAt(0)>= 'A' && s.charAt(0) <= 'Z')))
            return false;
        for (int i = 1; i < s.length(); i++)
        {
            if (!((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')))
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        Map<String,Integer> SymbolMap = new HashMap<String, Integer>();//contains only identifiers and constants
        int n = 0;
        Map<String,List<Integer>> PIFMap = new HashMap<String, List<Integer>>();
        Map<String,Integer> TokensMap = new HashMap<String, Integer>();

        TokensMap.put("identifier",0);
        TokensMap.put("constant",1);
        TokensMap.put("integer",2);
        TokensMap.put("VAR",3);
        TokensMap.put("For",4);
        TokensMap.put("if",5);
        TokensMap.put("otherwise",6);
        TokensMap.put("go",7);
        TokensMap.put("then",8);
        TokensMap.put("do",9);
        TokensMap.put("from",10);
        TokensMap.put("largeint",11);
        TokensMap.put("byte",12);
        TokensMap.put("array",13);
        TokensMap.put("chain",14);
        TokensMap.put("bool",15);
        TokensMap.put("char",16);
        TokensMap.put("begin",17);
        TokensMap.put("end",18);
        TokensMap.put("(",19);
        TokensMap.put(")",20);
        TokensMap.put("{",21);
        TokensMap.put("}",22);
        TokensMap.put("[",23);
        TokensMap.put("]",24);
        TokensMap.put(":",25);
        TokensMap.put(";",26);
        TokensMap.put(".",27);
        TokensMap.put(".+",28);
        TokensMap.put(".-",29);
        TokensMap.put(".*",30);
        TokensMap.put("./",31);
        TokensMap.put(".<",32);
        TokensMap.put(".=<",33);
        TokensMap.put(".>",34);
        TokensMap.put(".=>",35);
        TokensMap.put(".=",36);
        TokensMap.put("!!=",37);
        TokensMap.put("^&",38);
        TokensMap.put("^|",39);
        TokensMap.put("=",40);
        TokensMap.put(",",41);


        try
        {
            File file=new File("/Users/andradanelus/Documents/Lab2_FLCD/src/fisier.txt");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
            String line;
            while((line=br.readLine())!=null) {
                String[] splited = line.split(" ");
                for (String s : splited) {
                    if (integer_check(s) == 1) {
                        if (!(TokensMap.containsKey(s))) {
                            if (!(SymbolMap.containsKey(s))) {
                                SymbolMap.put(s, n);
                                n++;
                            }
                        }
                    } else if (identifier_check(s)) {
                        if (!(TokensMap.containsKey(s))) {
                            if (!(SymbolMap.containsKey(s))) {
                                SymbolMap.put(s, n);
                                n++;
                            }
                        }
                    }
                    if (identifier_check(s) || (integer_check(s) == 1) || reservedWord_check(s) || separator_check(s) || operator_check(s)) {
                        List<Integer> pairList = new ArrayList<>();
                        pairList.add(SymbolMap.get(s) == null ? -1 : SymbolMap.get(s));
                        if (identifier_check(s)) {
                            pairList.add(0);
                        } else if (integer_check(s) == 1) {
                            pairList.add(1);
                        } else {
                            pairList.add(TokensMap.get(s));
                        }
                        PIFMap.put(s, pairList);
                    }
                    else System.out.println("the token " + s + " is not ok");
                }
            //fr.close();    //closes the stream and release the resources
                System.out.println();
            System.out.println("ST: ");
            for(Map.Entry<String,Integer> entry : SymbolMap.entrySet())
                System.out.println(entry.getKey() + " , " + entry.getValue());

            System.out.println(sb.toString());   //returns a string that textually represents the object
            System.out.println("PIF : ");
            for(Map.Entry<String,List<Integer>> entry : PIFMap.entrySet())
                System.out.println(entry.getKey() + " , " + entry.getValue());

            System.out.println();



    }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}

