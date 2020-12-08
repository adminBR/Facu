//sistema multithread
import static java.lang.Thread.sleep;
import java.util.Random;

class GeradorReceptor extends Thread { //class thread que recebe um inteiro para identificar qual é o primeiro thread e qual é o segundo
    int id;
    
    public GeradorReceptor(int ID){
        id = ID;
    }
    public void run(){
        while(true){ 
            try{
                gerarMensagem();//uma vez que o thread é iniciado ele entra em loop para tentar sempre enviar uma mensagem
                lerMensagem();//e ler a mensagem enviada pelo outro thread
                sleep(1000);//a cada 1 segundo
            }catch (Exception e){}
        }
    }
    public synchronized void gerarMensagem() throws Exception{
        if("".equals(Q1.LerMensagem(id))){//o thread so é permitido enviar uma mensagem se a mensagem anterior ja foi recebida
            Random r = new Random();
            int temp = r.nextInt();
            System.out.println("Thread #"+id+":Mensagem ENVIADA  = "+temp+" :::"+new java.util.Date().toString());
            Q1.SalvarMensagem(id, ""+temp);//o thread gera uma mensagem com um valor aleatorio
        }
    }
    public synchronized void lerMensagem() throws Exception{
        int altId = 0;//essa variavel serve pra identificar o id do thread oposto ao atual
        if(id == 1)
            altId = 2;
        if(id == 2)
            altId = 1;
        
        if(!"".equals(Q1.LerMensagem(altId))){//para identificar sem tem mensagem o thread so imprime se for diferente de vazio
            String m = Q1.LerMensagem(altId);
            Q1.SalvarMensagem(altId, "");//e quando lê ele deixa a variavel vazia para definir que a mensagem foi lida
            System.out.println("Thread #"+id+":Mensagem RECEBIDA = "+m);
        }
    }
}

public class Q1 { //classe principal
    
    public static String mensagem1 = "";//variavel que recebe as mensagens do thread1
    public static String mensagem2 = "";//varivel que recebe as mensagens do thread2
    public static void main(String[] args) {
        GeradorReceptor gr1 = new GeradorReceptor(1);
        GeradorReceptor gr2 = new GeradorReceptor(2);
        gr1.start();
        gr2.start();
        // TODO code application logic here
    }
    public static void SalvarMensagem(int id, String mensagem){//função chamada pelos thread para escrita das variaveis de mensagem
        if(id == 1){
            mensagem1 = mensagem;
        }else if(id == 2){
            mensagem2 = mensagem;
        }
    }
    public static String LerMensagem(int id){//função chamada pelos threads para leitura das variaveis
        if(id == 1){
            return mensagem1;
        }else if(id == 2){
            return mensagem2;
        }
        return "err";
    }
    
}