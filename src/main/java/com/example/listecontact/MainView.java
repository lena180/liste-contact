package com.example.listecontact;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;

@Route("")
public class MainView extends VerticalLayout{
    private final ContactService service;
    private final Grid<Contact> grid = new Grid <>(Contact.class, false);

    public MainView(ContactService service ){
        this.service = service;

        H1 titre = new H1("Liste de contacts");
        //les colonnes de la grid
        grid.addColumn(Contact::getPrenom).setHeader("Prénom");
        grid.addColumn(Contact::getNom).setHeader("Nom");
        grid.addColumn(Contact::getEmail).setHeader("Email");
        grid.addColumn(Contact::getTelephone).setHeader("Telephone");
        grid.addComponentColumn(contact -> {
            Button modifierBtn = new Button("Modifier", e -> ouvrirModification(contact));
            return modifierBtn;
        }).setHeader("Actions");
        grid.setItems(service.findAll());

        TextField filtre = new TextField("Rechercher");
        filtre.setValueChangeMode(ValueChangeMode.EAGER);
        filtre.addValueChangeListener(e->{
            String val = e.getValue().toLowerCase();
            grid.setItems(service.findAll().stream()
                    .filter(c->c.getNom().toLowerCase().contains(val)
                        || c.getPrenom().toLowerCase().contains(val))
                    .toList());
        });
        //bouton pour ouvrir le formulaire
        Button ajouterBtn = new Button("+ Ajouter un contact", e-> ouvrirFormulaire());


        Button supprimer = new Button ("Supprimer", click -> {
            grid.getSelectedItems().forEach(service::remove);
            grid.setItems(service.findAll());

        });

        grid.addSelectionListener(e-> {
            e.getFirstSelectedItem().ifPresent(contact -> afficherFiche(contact));
        });

        add(titre, filtre, grid, ajouterBtn,supprimer);
        setSizeFull();
        }

        private void ouvrirFormulaire(){
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Nouveau contact");

            TextField prenom = new TextField("Prénom");
            TextField nom = new TextField("Nom");
            TextField email = new TextField("Email");
            TextField telephone = new TextField("Téléphone");

            Button sauvegarder = new Button ("Sauvegarder", e-> {
                Contact c = new Contact (
                        prenom.getValue(),
                        nom.getValue(),
                        email.getValue(),
                        telephone.getValue()
                );
                service.add(c);
                grid.setItems(service.findAll()); // on rafraichit la liste
                dialog.close();
            });
            Button annuler = new Button("Annuler", e-> dialog.close());

            dialog.add(new VerticalLayout(prenom, nom, email, telephone));
            dialog.getFooter().add(annuler, sauvegarder);

            dialog.open();
        }
//
private void ouvrirModification(Contact contact) {
    Dialog dialog = new Dialog();
    dialog.setHeaderTitle("Modifier le contact");

    TextField prenom = new TextField("Prénom");
    prenom.setValue(contact.getPrenom());

    TextField nom = new TextField("Nom");
    nom.setValue(contact.getNom());

    TextField email = new TextField("Email");
    email.setValue(contact.getEmail());

    TextField telephone = new TextField("Téléphone");
    telephone.setValue(contact.getTelephone());

    Button sauvegarder = new Button("Sauvegarder", e -> {
        contact.setPrenom(prenom.getValue());
        contact.setNom(nom.getValue());
        contact.setEmail(email.getValue());
        contact.setTelephone(telephone.getValue());
        grid.setItems(service.findAll()); // rafraîchir
        dialog.close();
    });

    Button annuler = new Button("Annuler", e -> dialog.close());

    dialog.add(new VerticalLayout(prenom, nom, email, telephone));
    dialog.getFooter().add(annuler, sauvegarder);
    dialog.open();
}
//
//    add(filtre, grid, supprimer);
//    setSizeFull();
//    }
    private void afficherFiche(Contact contact) {
        Dialog fiche = new Dialog();
        fiche.setHeaderTitle("Fiche contact");

        VerticalLayout contenu = new VerticalLayout();
        contenu.add(new H2(contact.getPrenom() + " " +  contact.getNom()));
        contenu.add(new H3("Email : " +  contact.getEmail()));
        contenu.add(new H3("Telephone : " +  contact.getTelephone()));

        Button fermer = new Button ("Fermer", e -> fiche.close());

        fiche.add(contenu);
        fiche.getFooter().add(fermer);
        fiche.open();
    }

}
