package rmit.p1;

public class TicketNumberGenerator
{
    private int[] ticketNumbers = new int[6];

    public TicketNumberGenerator(int[] ticketNumbers)
    {
        this.ticketNumbers = ticketNumbers;
    }
    public void generateNumbers ()
    {
        for (int i = 0; i < ticketNumbers.length; i++)
        {
            ticketNumbers[i] = (int) (Math.random() * (45 + 1));
        }
    }

}
