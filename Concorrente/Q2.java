//algoritmo de alternancia estrita
class Depositar extends Thread{
    public void run(){
        while(true){
            try{
                sleep(500);
            }catch(Exception e){}
            
            while(Q2.Turn == 0){try{Thread.sleep(1);}catch(Exception e){}} //o thread aguarda a variavel de alternancia estrita liberar a RC
            
            Q2.contaBancaria += 20; //Entra na RC
            System.out.println("Deposito feito de R$"+20+", Saldo atual:"+Q2.contaBancaria);
            Q2.Turn = 0; //Sai da RC e permite o outro Thread
            
            
        }
    }
}
class Sacar extends Thread{
    public void run(){
        while(true){
            try{
                sleep(500);
            }catch(Exception e){}
            while(Q2.Turn == 1){try{Thread.sleep(1);}catch(Exception e){}} //o thread aguarda a variavel de alternancia estrita liberar a RC
            
            Q2.contaBancaria -= 20;//Entra na RC
            System.out.println("Saque feito de R$"+20+", Saldo atual:"+Q2.contaBancaria);
            Q2.Turn = 1; //Sai da RC e permite o outro Thread
            
        }
    }
}

public class Q2 {
    public static int Turn = 0; //variavel de alternancia estrita
    public static int contaBancaria = 100; //variavel para RC
    public static void main(String[] args) {
        Depositar d = new Depositar();
        Sacar s = new Sacar();
        d.start();
        s.start();
    }
    
}
