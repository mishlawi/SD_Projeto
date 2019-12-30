import Exceptions.NomeNaoExisteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SoundCloud{

    private Map<String,User> users;
    private Map<Integer,Musica> musicas;
    private ReentrantLock lockSoundCloud = new ReentrantLock();
    private int lastID = 0;

    public SoundCloud() {
        this.users = new HashMap<>();
    }

    public void registarUser(String nome, String password){
        this.lockSoundCloud.lock();
        User u = new User(nome,password);
        this.users.put(nome,u);
        this.lockSoundCloud.unlock();
    }

    public boolean login(String nome, String password) throws NomeNaoExisteException{
        this.lockSoundCloud.lock();
        if(users.get(nome) == null) {
            this.lockSoundCloud.unlock();
            throw new NomeNaoExisteException("Nome não existe!");
        }
        else{
            this.lockSoundCloud.unlock();
            return users.get(nome).getPassword().equals(password);
        }
    }

    public void addMusica(String titulo, String interprete, String ano, List<String> etiquetas){
        this.lockSoundCloud.lock();
        int id = lastID++;
        Musica m = new Musica(id,titulo,interprete,ano,etiquetas);
        this.musicas.put(id,m);
        this.lockSoundCloud.unlock();
    }

    public String showUsers(){
        return this.users.toString();
    }

    public String showMusicas(){
        return this.musicas.toString();
    }
}
