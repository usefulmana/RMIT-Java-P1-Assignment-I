package rmit.p1.Ticket;

public class TicketDecorator implements Ticket {
    /** This class is a decorator class for Ticket. it will return a ticket string in ASCII art*/
    protected Ticket ticket;

    public TicketDecorator(Ticket ticket)
    {
        this.ticket = ticket;
    }


    @Override
    public double getCost() {
        /** Special ticket cost 5000 more*/
        return ticket.getCost() + 5000.00;
    }

    @Override
    public String toPrint() {
        /** This methods will return a ticket as an ASCII art*/
        try{
        ASCIIArtGenerator asciiArtGenerator = new ASCIIArtGenerator(); // Call ASCII art object
        return asciiArtGenerator.printTextArt(ticket.toPrint(), ASCIIArtGenerator.ART_SIZE_SMALL);}
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
