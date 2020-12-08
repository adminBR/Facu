package q4;
//produtor consumidor sem semaforo
import java.util.Random;

class Produtor extends Thread{ //produtor tem uma lista que avisa quais buffers estão prontros para serem consumidos
    public boolean[] bufferCheio = {false,false,false,false,false,false,false,false,false,false};
    public void run(){
        while(true){
            produzir();
            
            try{ //aguarda por um tempo aleatorio para produzir novamente
                Random r = new Random();
                sleep(r.nextInt(200)+50);
            }catch(Exception e){}
        }
    }
    
    public void produzir(){//o produtor verifica no vetor quais buffers estão prontos para produzir e adiciona 10 ao buffer
        for(int i = 0; i < 10; i++){
            if(bufferCheio[i] == false){
                Q4.buffer[i] += 10;
                System.out.println("produzido: 10 no buffer: "+i+" = "+Q4.buffer[i]);
                bufferCheio[i] = true;//apos adicionar o valor o buffer avisa ao consumidor que o buffer está livre
                break;
            }
        }
        
    }
}

class Consumidor extends Thread{ //consumidor acessa a lista de buffers cheios do produtor para consumir
    Produtor pro;
    public Consumidor(Produtor p){
        pro = p;
    }
    public void run(){
        while(true){
            consumir();
            
            try{ //aguarda um tempo aleatorio para poder consumir novamente
                Random r = new Random();
                sleep(r.nextInt(500)+150);
            }catch(Exception e){}
        }
    }
    
    public void consumir(){ //consumidor verifica no vetor do produtor, quais buffers estão prontos e acessa o buffer na classe principal
        for(int i = 0; i < 10; i++){
            if(pro.bufferCheio[i] == true){
                Q4.buffer[i] -= 10;
                System.out.println("Consumido: 10 no buffer :"+i+" = "+Q4.buffer[i]);
                pro.bufferCheio[i] = false;//apos consumir o buffer avisa ao produtor que aquele buffer está pronto para produzir
            }
        }
    }
}

public class Q4 {
    public static int[] buffer = {0,0,0,0,0,0,0,0,0,0};//vetor com os buffer de produtos
    //public static int[] buffer = new int[10];
    public static void main(String[] args) {
        Produtor p = new Produtor();
        Consumidor c = new Consumidor(p);
        p.start();
        c.start();
    }
    
}
