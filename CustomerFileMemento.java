package rmit.p1.Customer;

public class CustomerFileMemento {

    /** This is a memento class for CustomerFile class
     * It will save a file name*/
    private String customerFilename;

    public CustomerFileMemento(String customerFilename) {
        this.customerFilename = customerFilename;
    }

    public String getSavedCustomerFileName()
    {
        return customerFilename;
    }
}
