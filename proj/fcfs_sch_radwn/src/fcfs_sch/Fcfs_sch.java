/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs_sch;
import java.util.* ;
/**
 *
 * @author Radwan
 */
public class Fcfs_sch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in=new Scanner (System.in);
        try {
        System.out.println("Please! Enter The number Of Process ");
        int num =in.nextInt();
            if (num <0){
                do {
                    System.out.println("Error! Please Enter possitive number of process ");
                    num =in.nextInt();
                }while(num <0); 
            }
            
        int A []=new int[num];  //arrival_time
        
        // For Enter the Arrival Time of process 
        for (int i=0 ;i<num ;i++){
            System.out.println("Enter the Arrival Time of process "+(i+1)+" : ");
            A [i]=in.nextInt();
            do {
                if (A[i] <0){
                    System.out.println("Error! Please Enter possitive Arrival time for process "+(i+1)+" : ");
                    A [i]=in.nextInt();
                }
            }while(A[i] <0);
        }
        
        int B []=new int[num]; //BURST_TIME
        //For Enter the Burst Time of process 
        for (int i=0 ;i<num ;i++){
            System.out.println("Enter the Burst Time of process "+(i+1)+" : ");
            B[i]=in.nextInt();
            do {
                if (B[i] <0){
                    System.out.println("Error! Please Enter possitive Burst time for process "+(i+1)+" : ");
                    B [i]=in.nextInt();
                }
            }while(B[i] <0);
        }
        
        int SA []=new int[num];   //to sort 
        int SAA []=new int[num]; // Because i will play in A[] 
        
        for (int i=0 ;i<num ;i++){
           SA[i]=A[i] ;
           SAA[i]=A[i] ;
        }
        
        int temp= 0 ;
        // if Arrival time is {2 ,5 ,3 ,1 ,0 } sort it to {0 ,1 ,2 ,3 ,5 }
        for (int i=0 ;i<num ;i++){
            for (int j=0 ;j<num-1 ;j++){
                if (SA[j]>=SA[j+1]){
                    temp=SA[j];
                    SA[j]=SA[j+1];
                    SA[j+1]=temp;                    
                }
            }
        }
        
        int wait =0 ;
        double total=0 ;
        int sb=SA[0] ,se=0 ; //Service Begin
        System.out.println("Process"+"\t\t\t "+"Arrival Time"+"\t\t\t"+"Burst Time"+"\t\t\t"+
                          "Service begin"+"\t\t\t"+"Service End"+"\t\t\t"+"Waiting time");
        
        for (int i=0 ;i<num ;i++){
            for(int j=0 ;j<num ;j++){      // B  [5 ,3 ,8 ,6 ,4 ]
                if (SA[i]==A[j]){          // A  [2 ,5 ,3 ,1 ,0 ]
                                           // SA [0 ,1 ,2 ,3 ,5 ]
                    if (i==0 ){   // First Put wait =0         
                        wait=0 ;
                        se =sb+B[j] ;   // 0(first served) + 4(Burst time of first ) = 4(end service )
                    }
                    else {
                        if (SA[i]<=se){     //For if cpu being idle in center of process
                            sb =se ;
                            wait=sb-SA[i] ;
                            se =sb+B[j] ;
                        }
                        else{
                            wait =0 ;
                            sb =SA[i] ;   // service begin = the time that process arrive 
                            se =sb+B[j] ; //service end = service begin + service(Burst) time
                        }
                    }                    
                    System.out.println(" P"+(j+1)+"\t\t\t "+SA[i]+"\t\t\t\t "+B[j]+"\t\t\t\t  "+
                                    sb+"\t\t\t\t  "+se+"\t\t\t\t  "+wait);     
                    A[j] -=1;     // no A[j] +=1   to not make error // make to if arrival time is equal 
                    total+=wait ;
                    break ;                    
                    }                    
                }
            }        
        System.out.println("The Averag = "+(total/num));
        }catch(Exception e){
            System.out.println("Error! Should Enter integer Number");
        }
        }
}
