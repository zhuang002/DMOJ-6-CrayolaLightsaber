/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crayolalightsaber;


import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class CrayolaLightsaber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        int[] markers=new int[6];
        String[] sAl=sc.nextLine().split(" ");
        for (int i=0;i<6;i++) markers[i]=0;
        for (int i=0;i<n;i++) {
            String color=sAl[i];
            switch (color) {
                case "yellow":
                    markers[0]+=1;
                    break;
                case "blue":
                    markers[1]+=1;
                    break;
                case "red":
                    markers[2]+=1;
                    break;
                case "black":
                    markers[3]+=1;
                    break;
                case "green":
                    markers[4]+=1;
                    break;
                case "orange":
                    markers[5]+=1;
                    break;
                default:
                    break;
                            
            }
                    
        }
        
        System.out.println(getStickLength(7,markers));
    }

    private static int getStickLength(int previousColor, int[] markers) {
        int maxLength=0;
        
        for (int color=0;color<6;color++) {
            if (color==previousColor) continue;
            int count=markers[color];
            if (count==0) continue;
            markers[color]=count-1;
            int length=getStickLength(color,markers)+1;
            if (maxLength<length) maxLength=length;
            markers[color]=count;
        }
        return maxLength;
    }
}
