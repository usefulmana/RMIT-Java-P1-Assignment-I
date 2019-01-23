package rmit.p1.Ticket;

public class TicketGenerator implements Ticket{
    /**This class generates an array with 6 random numbers from 0 to 45
     * and it implements the Ticket interface*/
    protected Ticket ticket; // Creating a ticket object

    public TicketGenerator(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketGenerator() {
    }

    public int[] generateTicket()
    {
        int[] ticketNumbers = new int[6];
        for (int i = 0; i < ticketNumbers.length; i++) {
            ticketNumbers[i] = (int) (Math.random() * (45 + 1)); // generate a random number and add it to the array
        }
        return ticketNumbers;
    }

    @Override
    public String toPrint()
    {/** Convert int[] ticket to String*/
        int[] ticket = generateTicket();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ticket.length; i++) {
            stringBuilder.append(ticket[i] + " ");
        }
        return stringBuilder.toString();
    }

    @Override
    public double getCost() {
        /** default cost of a ticket*/
        return 10000.00;
    }
}
