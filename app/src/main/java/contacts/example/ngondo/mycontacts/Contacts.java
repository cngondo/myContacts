package contacts.example.ngondo.mycontacts;

/**
 * Created by ngondo on 12/9/15.
 */
public class Contacts {
    String cName;
    String cNumber;

    public Contacts() {
    }

    public Contacts(String cName, String cNumber) {
        this.cName = cName;
        this.cNumber = cNumber;
    }

    public String getcName() {
        return cName;
    }

    public String getcNumber() {
        return cNumber;
    }
}
