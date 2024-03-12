import java.io.PrintWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new LinkedList<>();
    }

    public void addContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                System.out.println("Contact already exists.");
                return;
            }
        }
        contacts.add(new Contact(name));
    }
    
    public void addEmail(String name, String email) {
        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contactFound = true;
                if (contact.getEmails().contains(email)) {
                    System.out.println("Email already exists for this contact.");
                    return;
                } else {
                    contact.addEmail(email);
                    System.out.println("Email added successfully.");
                    return;
                }
            }
        }
            if (!contactFound) {
                System.out.println("Contact not found.");
            }
    }

    public void deleteContact(String name) {
        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contactFound = true;
                contacts.remove(contact);
                System.out.println("Contact has been removed.");
                return;
            }
        }
            if (!contactFound) {
                System.out.println("Contact is clear.");
            }
    }
    
    public void deleteEmail(String name, String email) {
        boolean contactFound = false;
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contactFound = true;
                if (contact.getEmails().contains(email)) {
                    contact.deleteEmail(email);
                    System.out.println("Email has been removed.");
                    return;
                } else {
                    System.out.println("Email not found for this contact.");
                    return;
                }
            }
            return;
        }

        if (!contactFound) {
            System.out.println("Contact is empty.");
        }
    }
    public void printContacts() {
        if (contacts.isEmpty()) {
            System.out.println("There are no contacts in the book.");
            return;
        }

        // Will be re-write into the newest possible file
        try (PrintWriter writer = new PrintWriter("contacts.txt")) {
            for (Contact contact : contacts) {
                writer.println(contact);
            }

            // Write into contacts.txt file
            System.out.println("Contacts have been printed to contacts.txt");
            
            // Output also in the terminal
            System.out.println("Contacts:");
            for (Contact contact : contacts) {
                System.out.print(contact);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void searchContact(String name) {
        if (contacts.isEmpty()) {
            System.out.println("Contact list is empty");
            return;
        }

        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void searchEmail(String email) {
        if (contacts.isEmpty()) {
            System.out.println("Email list is empty");
            return;
        }

        for (Contact contact : contacts) {
            if (contact.getEmails().contains(email)) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("Email not found.");
    }

    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("(A) Add contact and email");
            System.out.println("(D) Delete contact and email");
            System.out.println("(E) Email search");
            System.out.println("(C) Contact search");
            System.out.println("(P) Print list of contact and email");
            System.out.println("(Q) Quit the program");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    contactBook.addContact(name);
                    contactBook.addEmail(name, email);
                    break;
                case "D":
                    System.out.print("Enter contact name: ");
                    String deleteContactName = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String deleteEmail = scanner.nextLine();
                    contactBook.deleteContact(deleteContactName);
                    contactBook.deleteEmail(deleteContactName, deleteEmail);
                    break;
                case "E":
                    System.out.print("Enter email: ");
                    String searchEmail = scanner.nextLine();
                    contactBook.searchEmail(searchEmail);
                    break;
                case "C":
                    System.out.print("Enter contact name: ");
                    String searchName = scanner.nextLine();
                    contactBook.searchContact(searchName);
                    break;
                case "P":
                    contactBook.printContacts();
                    break;
                case "Q":
                    System.out.println("Quitting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

class Contact {
    private String name;
    private List<String> emails;

    public Contact(String name) {
        this.name = name;
        this.emails = new LinkedList<>();
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public void deleteEmail(String email) {
        emails.remove(email);
    }

    public List<String> getEmails() {
        return emails;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", emails=" + emails +
                '}';
    }
}
