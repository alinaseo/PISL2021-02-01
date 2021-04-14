package com.xledbd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.xledbd.clients.Client;
import com.xledbd.clients.Clients;
import com.xledbd.dao.ClientDAO;
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

			FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("client.xml").toURI()));

			Clients clients = (Clients) u.unmarshal(reader);

			Client client = clients.getClient().get(0);
			assertEquals("Lastname is not correct","Иванов", client.getLastname());
			assertEquals("Firstname is not correct","Иван", client.getFirstname());

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
			FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("client.xml").toURI()));

			Clients clients = (Clients) u.unmarshal(reader);
			Client client = clients.getClient().get(0);

			ClientDAO dao = ClientDAO.getInstance();
			int id = dao.create(client);
			assertNotEquals("Insert failed", id, -1);

			client = dao.get(id);
			assertEquals("Lastname is not correct","Иванов", client.getLastname());
			assertEquals("Firstname is not correct","Иван", client.getFirstname());

			client.setMiddlename("Updated");
			dao.save(client);
			assertEquals("Middlename is not correct","Updated", client.getMiddlename());

			dao.delete(id);

			client = dao.get(id);
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
			List<Client> list = dao.getList();
			int initSize = list.size();
			int i = 0;
			int[] ids = new int[2];

			JAXBContext jc = JAXBContext.newInstance(Clients.class);
			Unmarshaller u = jc.createUnmarshaller();
			FileReader reader = new FileReader(new File(getClass().getClassLoader().getResource("clients.xml").toURI()));

			Clients clients = (Clients) u.unmarshal(reader);

			for (Client c :
					clients.getClient()) {
				ids[i] = dao.create(c);
				i++;
			}

			list = dao.getList();
			assertEquals(2, list.size() - initSize);

			Client client = dao.get(ids[1]);
			assertEquals("Email is not correct","vitalik0123@gmail.com", client.getEmail());

			dao.delete(ids[0]);
			dao.delete(ids[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMarshalling() {
		try {
			List<Client> list = ClientDAO.getInstance().getList();
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
				id = ClientDAO.getInstance().create(c);
			}

			list = ClientDAO.getInstance().getList();
			System.out.println(gson.toJson(list));

			ClientDAO.getInstance().delete(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
