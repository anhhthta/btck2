package service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.ModelFriend;
import model.ModelSend;
import model.ModelUser;
import model.RequestFriend;

public class Client {
    private static Client instance;
    private Socket socket;
    private ModelUser user;
    private ObjectInputStream readerOj;
    private ObjectOutputStream writerOj1;
    private ObjectOutputStream writerOj;
    private PrintWriter writer;
    private BufferedReader reader;
    
    private List<ModelSend> history;
    
    private List<ModelFriend> friends;
    private List<RequestFriend> requests;
    
    public static Client getInstance() {
        if(instance == null) {
            instance = new Client();
        }
        
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void createSocket() throws IOException {
        this.socket = new Socket("localhost", 9999);
    }

    public ObjectInputStream getReaderOj() {
        return readerOj;
    }

    public ObjectOutputStream getWriterOj() {
        return writerOj;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReaderOj(ObjectInputStream readerOj) {
        this.readerOj = readerOj;
    }

    public void setWriterOj(ObjectOutputStream writerOj) {
        this.writerOj = writerOj;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }    
    
    public ModelUser getUser() {
        if(user == null) {
            return new ModelUser();
        }
        return user;
    }

    public ObjectOutputStream getWriterOj1() {
        return writerOj1;
    }

    public void setWriterOj1(ObjectOutputStream writerOj1) {
        this.writerOj1 = writerOj1;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public List<ModelSend> getHistory() {
        if(history == null) {
            return new ArrayList<>();
        }
        return history;
    }

    public void setHistory(List<ModelSend> history) {
        this.history = history;
    }

    public List<ModelFriend> getFriends() {
        return friends;
    }

    public void setFriends(List<ModelFriend> friends) {
        this.friends = friends;
    }

    public List<RequestFriend> getRequest() {
        return requests;
    }

    public void setRequest(List<RequestFriend> request) {
        this.requests = request;
    } 
}
