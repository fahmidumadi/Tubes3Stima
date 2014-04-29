/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stringmatching;

/**
 *
 * @author Fahmi
 */
public class BM {
    public static int bmMatch(String text, String pattern)
    {
        int last[] = buildLast(pattern);
        int n = text.length();
        int m = pattern.length();
        int i = m-1;
        
        if(i > n-1)
            return -1; // no match if pattern is longer than text
        
        int j = m-1;
        do{
            if(pattern.charAt(j) == text.charAt(i))
                if(j == 0)
                    return i; //match
                else{ //looking-glass technique
                    i--;
                    j--;
                }
            
            else{ //character jump tehnique
                //System.out.print(last.length + " ");
                //System.out.println(text.codePointAt(i) + " " + text.charAt(i) + " " + i);
                int lo = last[text.charAt(i)]; //last occ
                i = i + m - Math.min(j, 1+lo);
                j = m - 1;
            }
        } while(i <= n-1);
        return -1; //no match
    }
    public static int[] buildLast(String pattern)
    /* Return array storing index of last occurence of
            each ASCII char in pattern. */
    {
        int last[] = new int[100000]; //ASCII char set
        
        for(int i = 0;i < 100000;i++)
            last[i] = -1; // initialize array
        
        for(int i = 0; i < pattern.length();i++)
            last[pattern.charAt(i)] = i;
        
        return last;
    }
}
