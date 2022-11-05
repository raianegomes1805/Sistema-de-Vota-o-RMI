package votacao;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.IsEvenImpl;
import implementacoes.VotacaoImpl;
import implementacoes.VotoImpl;
import interfaces.VotacaoInterface;
import java.util.Scanner;
import static votacao.Cliente.porta;

public class Servidor {
	
	static List<CandidatoImpl> candidatos;
	static VotacaoInterface votacaoImpl;
	static int porta = 1099;
        static int portaSer = 1097;
    
	public static void main(String[] args) {
         try {
            	String objNameServ = "rmi://localhost:" + portaSer + "/server";
		VotacaoInterface votacao = (VotacaoInterface) Naming.lookup(objNameServ);
                
                String objName = "rmi://localhost:" + porta + "/server";
			
            System.out.println("Registrando o objeto no RMIRegistry...");
            LocateRegistry.createRegistry(porta);

            // Lista de Candidatos
            candidatos = new ArrayList<>();
            candidatos.add(new CandidatoImpl(10, "Luiz Picolo"));
            candidatos.add(new CandidatoImpl(20, "Leticia Enz"));
            candidatos.add(new CandidatoImpl(30, "Fabio Andrade"));
            candidatos.add(new CandidatoImpl(40, "Renato Garcia"));
            candidatos.add(new CandidatoImpl(50, "Patricia Barbosa"));
            candidatos.add(new CandidatoImpl(60, "Fernando Balbino"));
            candidatos.add(new CandidatoImpl(70, "Wesley Tessaro"));

            votacaoImpl = new VotacaoImpl(candidatos);

            Naming.rebind(objName, votacaoImpl);
            //Naming.rebind(objName, x);

            //votacao.apuracao();

            System.out.println("Aguardando Clientes na porta " + porta + "!");
            
            while(true){
                System.out.println("Tecle enter para ver a apuração");
                Scanner entrada = new Scanner(System.in);
                String numero_candidato = entrada.nextLine();
                
                    List<VotoImpl> votos = votacaoImpl.getVotos();
                
                System.out.println(votacao.calcularVotos(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}