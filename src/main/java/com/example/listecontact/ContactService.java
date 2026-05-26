package com.example.listecontact;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    public ContactService() {
        //donnees de test
        contacts.add(new Contact("Ousmane", "Dieye", "ouzdiarizo20@gmail.com", "781234567"));
        contacts.add(new Contact("Khady", "Niang", "khadyniang17@gmail.com", "712345678"));
        contacts.add(new Contact("Helena", "Sk", "lnask99@gmail.com", "703203400"));
        contacts.add(new Contact("Mass", "49", "mass49@gmail.com", "779001249"));
        contacts.add(new Contact("Bachir", "Wone", "bw2020@gmail.com", "770001234"));
    }

    public List<Contact> findAll() {return contacts; }

    public void add(Contact c) {contacts.add(c); }

    public void remove (Contact c) {contacts.remove(c); }
}
