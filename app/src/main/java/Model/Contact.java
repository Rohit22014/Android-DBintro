package Model;

public class Contact
{
    private int id;
    private String name;
    private String phnumber;

    public Contact() {

    }

    public Contact(int id, String name, String phnumber) {
        this.id = id;
        this.name = name;
        this.phnumber = phnumber;
    }

    public Contact(String name, String phnumber) {
        this.name = name;
        this.phnumber = phnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }
}
