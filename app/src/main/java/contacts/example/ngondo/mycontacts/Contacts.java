package contacts.example.ngondo.mycontacts;

/**
 * Created by ngondo on 12/9/15.
 */
public class Contacts {
    String Name;
    String Number;

    public Contacts() {
    }

    public Contacts(String name, String number) {
        this.Name = name;
        this.Number = number;
    }

    public String getName() {
        return Name;
    }

    public String getNumber() {
        return Number;
    }
}
