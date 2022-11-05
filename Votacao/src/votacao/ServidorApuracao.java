package votacao;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.IsEvenImpl;
import implementacoes.VotacaoImpl;
import interfaces.VotacaoInterface;
import static votacao.Servidor.candidatos;
import static votacao.Servidor.votacaoImpl;

public class ServidorApuracao {
        
	static int porta = 1097;
    
	public static void main(String[] args) {
           
           
        try {
        	//IsEvenImpl x = new IsEvenImpl();
            String objNameServ = "rmi://localhost:" + porta + "/server";
			
            System.out.println("Registrando o objeto no RMIRegistry...");
			LocateRegistry.createRegistry(porta);
            
			
			votacaoImpl = new VotacaoImpl(candidatos);
			
			Naming.rebind(objNameServ, votacaoImpl);
			//Naming.rebind(objName, x);
                        
			
            System.out.println("Aguardando Clientes na porta " + porta + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}