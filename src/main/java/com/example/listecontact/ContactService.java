package com.example.listecontact;
import org.springframework.stereotype.Service;
//import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {

        this.repository = repository;
//        //donnees de test
//        contacts.add(new Contact("Ousmane", "Dieye", "ouzdiarizo20@gmail.com", "781234567"));
//        contacts.add(new Contact("Khady", "Niang", "khadyniang17@gmail.com", "712345678"));
//        contacts.add(new Contact("Helena", "Sk", "lnask99@gmail.com", "703203400"));
//        contacts.add(new Contact("Mass", "49", "mass49@gmail.com", "779001249"));
//        contacts.add(new Contact("Bachir", "Wone", "bw2020@gmail.com", "770001234"));
    }

    public List<Contact> findAll() {return repository.findAll(); }

    public void add(Contact contact) {repository.save(contact); }

    public void remove (Contact contact) {repository.delete(contact); }
}
