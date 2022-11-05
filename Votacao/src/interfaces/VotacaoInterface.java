package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.VotoImpl;

public interface VotacaoInterface extends Remote {
	public List<CandidatoImpl> listarCandidatos() throws RemoteException;
	public int salvarVoto(int posicao) throws RemoteException;
	public int buscarCandidato(String numero) throws RemoteException;
	public void apuracao() throws RemoteException;
        public int calcularVotos(int votos) throws RemoteException;
        public List<VotoImpl> getVotos() throws RemoteException;
}
