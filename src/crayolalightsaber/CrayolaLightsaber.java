/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crayolalightsaber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author zhuan
 */
public class CrayolaLightsaber {

    static HashMap<MyKey, Integer> recursiveBackup = new HashMap();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] markers = new int[6];
        String[] sAl = sc.nextLine().split(" ");
        for (int i = 0; i < 6; i++) {
            markers[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            String color = sAl[i];
            switch (color) {
                case "yellow":
                    markers[0] += 1;
                    break;
                case "blue":
                    markers[1] += 1;
                    break;
                case "red":
                    markers[2] += 1;
                    break;
                case "black":
                    markers[3] += 1;
                    break;
                case "green":
                    markers[4] += 1;
                    break;
                case "orange":
                    markers[5] += 1;
                    break;
                default:
                    break;
            }

        }
        
        System.out.println(getStickLength(7, markers));
    }

    private static int getStickLength(int previousColor, int[] originalMarkers) {
        int[] markers=preprocessMarkers(originalMarkers);
        /*if (changedColor!=null) {
            int length=getStickLength(previousColor,markers);
            markers[changedColor[0]]+=changedColor[1];
            return length;
        }*/
        MyKey key = new MyKey(previousColor, markers);
        if (recursiveBackup.containsKey(key)) {
            return recursiveBackup.get(key);
        }

        int maxLength = 0;

        for (int color = 0; color < 6; color++) {
            if (color == previousColor) {
                continue;
            }
            int count = markers[color];
            if (count == 0) {
                continue;
            }
            markers[color] = count - 1;
            
            int length = getStickLength(color, markers) + 1;
            if (maxLength < length) {
                maxLength = length;
            }
            markers[color] = count;
        }

        recursiveBackup.put(key, maxLength);
        return maxLength;
    }

    private static int[] preprocessMarkers(int[] originalMarkers) {
        int[] markers=Arrays.copyOf(originalMarkers,originalMarkers.length);
        
        int maxColor=-1;
        int maxCount=-1;
        int totalMarkers=0;
        
        for (int i=0;i<markers.length;i++) {
            if (maxCount<markers[i]) {
                maxCount=markers[i];
                maxColor=i;
            }
            totalMarkers+=markers[i];
        }
        if (maxColor==-1) return null;
        int otherMarkers=totalMarkers-maxCount;
        if (maxCount>otherMarkers+1){
            markers[maxColor]=otherMarkers+1;
        }
        
        
        
        return markers;
    }

}

class MyKey {

    int previousColor;
    int[] ar;

    public MyKey(int color, int[] markers) {
        this.previousColor=color;
        this.ar=Arrays.copyOf(markers, markers.length);
    }

    @Override
    public boolean equals(Object obj) {
        MyKey key = (MyKey) obj;
        return (this.previousColor==key.previousColor &&
                this.ar[0] == key.ar[0]
                && this.ar[1] == key.ar[1]
                && this.ar[2] == key.ar[2]
                && this.ar[3] == key.ar[3]
                && this.ar[4] == key.ar[4]
                && this.ar[5] == key.ar[5]
        );
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + this.previousColor;
        hashCode = 31 * hashCode + java.util.Arrays.hashCode(ar);
        return hashCode;
    }
}
/* 
30
red red red red red red red red red red red red red red red red red red red red orange yellow green blue black orange yellow green blue black
*/
