package rmit.p1.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerFileCareTaker {
    /** This class is a caretaker class for CustomerFile class.
     * It will save all the previous filename into a list and retrieve any saved filename*/
    private List<CustomerFileMemento> mementoList = new ArrayList<>();

    public void add(CustomerFileMemento customerFileMemento)
    {
        mementoList.add(customerFileMemento);
    }

    public CustomerFileMemento get(int index)
    {
        return mementoList.get(index);
    }

}
