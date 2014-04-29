/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stringmatching;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Fahmi
 */
public class Categorizing {
    private ArrayList<String> Tweets;
    private String Macet;
    private String Kecelakaan;
    private String Tutup;
    public Categorizing(String DaftarTweet) throws IOException{
            Tweets = new ArrayList<>();
            BufferedReader inFile = new BufferedReader (new FileReader(DaftarTweet));
            String inputLine;

            while ((inputLine = inFile.readLine())!=null) { 
            Tweets.add(inputLine.toLowerCase());
            }
        }
    public void SetKatMacet(String S){
        Macet = S;
    }
    public void SetKatKecelakaan(String S){
        Kecelakaan = S;
    }
    public void SetKatTutup(String S){
        Tutup = S;
    }
    public void UseBM() throws IOException{
        SetKatMacet("macet");
        SetKatKecelakaan("celaka");
        SetKatTutup("padat");
        int i = 0;
        int patternposn;
        String delimiter=";";
        PrintWriter fmacet = new PrintWriter("Macet.txt");
        PrintWriter fkecelakaan = new PrintWriter("Kecelakaan.txt");
        PrintWriter ftutup = new PrintWriter("Tutup.txt");
        PrintWriter funknown = new PrintWriter("Unknown.txt");
        
        for(String tweet : Tweets){
            String CurrentKategori = "Unknown";
            int CurrentPos = tweet.length();
            i++;
            for(String pattern : Macet.split(delimiter)){
                patternposn = BM.bmMatch(tweet, pattern);
                if(patternposn != -1){
                    System.out.println("Kategori macet ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "macet";
                }
                //else System.out.println("tidak macet");
            }
            for(String pattern : Kecelakaan.split(delimiter)){
                patternposn = BM.bmMatch(tweet, pattern);
                if((patternposn != -1) && (patternposn < CurrentPos)){
                    //System.out.println("Kata kecelakaan ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "kecelakaan";
                }
                //else System.out.println("tidak celaka");
            }
            for(String pattern : Tutup.split(delimiter)){
                patternposn = BM.bmMatch(tweet, pattern);
                if((patternposn != -1) && (patternposn < CurrentPos)){
                    //System.out.println("Kata tutup ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "tutup";
                }
                //else System.out.println("tidak tutup");
            }
            System.out.println("TWEET " + i + " TELAH DIPERIKSA");
            switch(CurrentKategori){
                case "macet":
                    fmacet.println(tweet);
                    break;
                case "kecelakaan":
                    fkecelakaan.println(tweet);
                    break;
                case "tutup":
                    ftutup.println(tweet);
                    break;
                default:
                    funknown.println(tweet);
                    break;
            }
        }
        fmacet.close();
        fkecelakaan.close();
        ftutup.close();
        funknown.close();
    }

    public void UseKMP() throws IOException{
        SetKatMacet("macet");
        SetKatKecelakaan("celaka");
        SetKatTutup("padat");
        int i = 0;
        int patternposn;
        String delimiter=";";
        PrintWriter fmacet = new PrintWriter("Macet.txt");
        PrintWriter fkecelakaan = new PrintWriter("Kecelakaan.txt");
        PrintWriter ftutup = new PrintWriter("Tutup.txt");
        PrintWriter funknown = new PrintWriter("Unknown.txt");
        
        for(String tweet : Tweets){
            String CurrentKategori = "Unknown";
            int CurrentPos = tweet.length();
            i++;
            for(String pattern : Macet.split(delimiter)){
                patternposn = KMP.kmpMatch(tweet, pattern);
                if(patternposn != -1){
                    //System.out.println("Kategori macet ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "macet";
                }
                //else System.out.println("tidak macet");
            }
            for(String pattern : Kecelakaan.split(delimiter)){
                patternposn = KMP.kmpMatch(tweet, pattern);
                if((patternposn != -1) && (patternposn < CurrentPos)){
                    //System.out.println("Kata kecelakaan ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "kecelakaan";
                }
                //else System.out.println("tidak celaka");
            }
            for(String pattern : Tutup.split(delimiter)){
                patternposn = KMP.kmpMatch(tweet, pattern);
                if((patternposn != -1) && (patternposn < CurrentPos)){
                    //System.out.println("Kata tutup ditemukan pada tweet ke " + i );
                    CurrentPos = patternposn;
                    CurrentKategori = "tutup";
                }
                //else System.out.println("tidak tutup");
            }
            //System.out.println("TWEET " + i + " TELAH DIPERIKSA");
            switch(CurrentKategori){
                case "macet":
                    fmacet.println(tweet);
                    break;
                case "kecelakaan":
                    fkecelakaan.println(tweet);
                    break;
                case "tutup":
                    ftutup.println(tweet);
                    break;
                default:
                    funknown.println(tweet);
                    break;
            }
        }
        fmacet.close();
        fkecelakaan.close();
        ftutup.close();
        funknown.close();
    }
}