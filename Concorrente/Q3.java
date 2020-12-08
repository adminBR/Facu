//Algoritmo de peterson

import java.util.Random;

class Loja1 extends Thread{
    public void run(){
        while(true){
            Q3.acesso[0] = true;//thread avisa que quer acesso a RC
            Q3.turn = 1;
            while(Q3.acesso[1] && Q3.turn == 1){try{Thread.sleep(1);}catch(Exception e){}} //enquanto requisitandoAcc2 estiver true, a RC ja esta sendo usada
            
            //entrando na RC
            Q3.EstoqueDeProdutos += 10;
            System.out.println("Loja1 acessou, estoque atual em:"+Q3.EstoqueDeProdutos);
            //saindo da RC
            
            Q3.acesso[0] = false;//thread liberar acesso a RC
            try{
                sleep(100);
            }catch(Exception e){}
        }
    }
    
}

class Loja2 extends Thread{
    public void run(){
        while(true){
            Q3.acesso[1] = true;//thread avisa que quer acesso a RC
            Q3.turn = 0;
            while(Q3.acesso[0] && Q3.turn == 0){try{Thread.sleep(1);}catch(Exception e){}} //enquanto requisitandoAcc1 estiver true, a RC ja esta sendo usada
            
            //entrando na RC
            Q3.EstoqueDeProdutos += 10;
            System.out.println("Loja2 acessou, estoque atual em:"+Q3.EstoqueDeProdutos);
            //saindo da RC
            
            Q3.acesso[1] = false;//thread liberar acesso a RC
            try{
                sleep(100);
            }catch(Exception e){}
        }
    }
    
}

public class Q3 {

    public static boolean[] acesso = new boolean[2];
    public static int turn = 0;
    public static int EstoqueDeProdutos = 500;
    public static void main(String[] args) {
        Loja1 l1 = new Loja1();
        Loja2 l2 = new Loja2();
        l1.start();
        l2.start();
    }
    
}
