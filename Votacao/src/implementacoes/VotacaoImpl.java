package implementacoes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.*;

import interfaces.VotacaoInterface;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VotacaoImpl extends UnicastRemoteObject implements VotacaoInterface {

    private static final long serialVersionUID = 1L;
    private List<CandidatoImpl> candidatos;
    private List<VotoImpl> votos = new ArrayList<>();
    private Map<Integer, Integer> quantidade = new HashMap<>();
    int totalVoto = 0;

    public VotacaoImpl(List<CandidatoImpl> candidatos) throws RemoteException {
        super();
        this.candidatos = candidatos;
    }

    @Override
    public List<CandidatoImpl> listarCandidatos() throws RemoteException {
        return this.candidatos;
    }

    @Override
    public int salvarVoto(int posicao) throws RemoteException {
        int verificar = 0;
        try {
            for (int i = 0; i < votos.size(); i++) {
                if (votos.get(i).getIdentificador().equals(InetAddress.getLocalHost().getHostAddress())) {
                    System.out.println("Voto ja regitrado");
                    verificar++;
                }
            }
            if (verificar == 0) {
                this.votos.add(new VotoImpl("InetAddress.getLocalHost().getHostAddress", this.candidatos.get(posicao)));
                System.out.println(InetAddress.getLocalHost().getHostAddress());
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(VotacaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalVoto++;
        return posicao;
    }

    @Override
    public void apuracao() {
        this.votos.forEach(voto -> {
            int count = (int) votos.stream().filter(p -> p.getCandidato().equals(voto.getCandidato())).count();
            quantidade.put(voto.getCandidato().getNumero(), count);
        });

        final String format = "O candidato %d possui %d votos";
        final Set<Integer> chaves = quantidade.keySet();
        System.out.println("Apuração dos votos:");
        for (final Integer chave : chaves) {
            System.out.println(String.format(format, chave, quantidade.get(chave)));
            float porcentagem;

            porcentagem=(float) ((quantidade.get(chave)/totalVoto*100));
            System.out.println(porcentagem+"%");
            
        }
    }
    
    @Override
    public List<VotoImpl>getVotos(){
        return this.votos;
    }
    
    @Override
    public int calcularVotos(int votos){ 
     String.valueOf(votos);
     return votos;
    }
    

    @Override
    public int buscarCandidato(String numero) throws RemoteException {
        for (int i = 0; i < this.candidatos.size(); i++) {
            if (this.candidatos.get(i).getNumero() == Integer.parseInt(numero)) {
                return this.salvarVoto(i);
            }
        }

        return -1;
    }

}
