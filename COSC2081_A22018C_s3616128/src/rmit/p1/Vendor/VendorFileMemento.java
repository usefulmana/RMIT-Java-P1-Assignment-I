package rmit.p1.Vendor;

public class VendorFileMemento {
    /** This is a memento class for CustomerFile class
     * It will save a file name*/
    private String vendorFileName;

    public VendorFileMemento(String vendorFileName) {
        this.vendorFileName = vendorFileName;
    }

    public String getVendorFileName() {
        return vendorFileName;
    }

}
