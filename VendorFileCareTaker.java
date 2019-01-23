package rmit.p1.Vendor;


import java.util.ArrayList;
import java.util.List;

public class VendorFileCareTaker {

    /** This class is a caretaker class for VendorFile class.
     * It will save all the previous filename into a list and retrieve any saved filename*/
    private List<VendorFileMemento> mementoList = new ArrayList<>();

    public void add(VendorFileMemento vendorFileName)
    {
        mementoList.add(vendorFileName);
    }

    public VendorFileMemento get(int index)
    {
        return mementoList.get(index);
    }
}
