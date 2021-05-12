import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import xjc.generated.Client;
import xjc.generated.Clients;
import xjc.DAO.ClientDAO;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Lab5Test {

    @Test
    public void testUnmarshalling() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Clients.class);
            Unmarshaller u = jc.createUnmarshaller();

            FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("clients.xml").toURI()));

            Clients clients = (Clients) u.unmarshal(reader);

            Client client = clients.getClient().get(0);
            assertEquals("Lastname is not correct","Шейко", client.getLastname());
            assertEquals("Firstname is not correct","Алина", client.getFirstname());

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDAO() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Clients.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("clients.xml").toURI()));

            Clients clients = (Clients) u.unmarshal(reader);
            Client client = clients.getClient().get(0);

            ClientDAO dao = ClientDAO.getInstance();
            int id = dao.createClient(client);
            assertNotEquals("Insert failed", id, -1);

            client = dao.getClient(id);
            assertEquals("Lastname is not correct","Шейко", client.getLastname());
            assertEquals("Firstname is not correct","Алина", client.getFirstname());

            client.setMiddlename("Updated");
            dao.saveClient(client);
            assertEquals("Middlename is not correct","Updated", client.getMiddlename());

            dao.deleteClient(id);

            client = dao.getClient(id);
            assertNull("Delete failed", client);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMultiple() {
        try {
            ClientDAO dao = ClientDAO.getInstance();
            List<Client> list = dao.getClientsList();
            int initSize = list.size();
            int i = 0;
            int[] ids = new int[2];

            JAXBContext jc = JAXBContext.newInstance(Clients.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("clients.xml").toURI()));

            Clients clients = (Clients) u.unmarshal(reader);

            for (Client c :
                    clients.getClient()) {
                ids[i] = dao.createClient(c);
                i++;
            }

            list = dao.getClientsList();
            assertEquals(2, list.size() - initSize);

            Client client = dao.getClient(ids[1]);
            assertEquals("Email is not correct","dmitsin@gmail.com", client.getEmail());

            dao.deleteClient(ids[0]);
            dao.deleteClient(ids[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMarshalling() {
        try {
            List<Client> list = ClientDAO.getInstance().getClientsList();
            Clients clients = new Clients();
            clients.getClient().addAll(list);
            JAXBContext context = JAXBContext.newInstance(Clients.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(clients, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJSON() {
        try {

            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new FileReader(new File(getClass().getClassLoader().getResource("clients.json").toURI())));
            Client[] clients = gson.fromJson(reader, Client[].class);
            List<Client> list = Arrays.asList(clients);
            int id = -1;
            for (Client c :
                    list) {
                id = ClientDAO.getInstance().createClient(c);
            }

            list = ClientDAO.getInstance().getClientsList();
            System.out.println(gson.toJson(list));

            ClientDAO.getInstance().deleteClient(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}