package builder_service;

import java.util.ArrayList;
import java.util.List;

public class ClientContainer {
    private List<Client> clientList = new ArrayList<>();
    public void addClient(Client client) {
        clientList.add(client);
    }
    public void removeClient(Client client) {
        clientList.remove(client);
    }
    public List<Client> getClientList() {
        return clientList;
    }
}
