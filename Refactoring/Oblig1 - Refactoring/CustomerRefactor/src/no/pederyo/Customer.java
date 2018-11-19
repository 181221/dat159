package no.pederyo;
import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String _name;
    private Vector _rentals = new Vector();
    private String _result;
    private int frequentRenterPoints;
    private int totalAmount;

    public Customer(String name) {
        _name = name;

    }

    public String statement() {

        _result = "Rental Record for " + _name + "\n";

        Enumeration rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {

            Rental each = (Rental) rentals.nextElement();

            double thisAmount = each.determineAmount();

            addRenterPoints(each);

            totalAmount += thisAmount;

            _result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) + "\n";
        }

        resultfooterLineMessage();

        return _result;

    }

    private void addRenterPoints(Rental each) {
        frequentRenterPoints ++;
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) frequentRenterPoints ++;
    }

    private void resultfooterLineMessage() {
        _result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        _result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
    }


    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }


    public String getName() {
        return _name;
    }
}
