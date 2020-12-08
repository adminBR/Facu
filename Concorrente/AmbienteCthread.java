/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambientecthread;

/**
 *
 * @author luisc
 */
public class AmbienteCthread {

    /**
     * @param args the command line arguments
     */
    public static boolean[] contAtivos = new boolean[5];
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i = 0; i < 5; i++){
            new ContadorThread(5,i).start();
        }
        boolean algumAtivos = true;
        while(algumAtivos){
            algumAtivos = false;
            System.out.println("Contadores ativos: ");
            
            for(int i = 0; i < 5; i++){
                System.out.println("Contador "+i+"esta: "+contAtivos[i]);
                if(contAtivos[i]){
                    algumAtivos = true;
                }
            }
            try{
            Runtime.getRuntime().exec("cls");
            //Thread.sleep(500);
            }catch(Exception e){System.out.println(""+e);}
            
        }
        
    }
    
}

class ContadorThread extends Thread{
    int tempo = 0;
    int id;
    public ContadorThread(int t, int ID){
        tempo = t;
        id = ID;
    }
    
    public void run(){
        AmbienteCthread.contAtivos[id] = true;
        while(tempo > 0){
            try{
            Thread.sleep(1000);
            tempo--;
            }catch(Exception e){}
        }
        AmbienteCthread.contAtivos[id] = false;
    }
}
