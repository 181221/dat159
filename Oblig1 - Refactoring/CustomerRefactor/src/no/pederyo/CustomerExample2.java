package no.pederyo;

import java.util.Enumeration;
import java.util.Vector;

public class CustomerExample2 {

    private String _name, _result;
    private Vector _rentals = new Vector();
    private int frequentRenterPoints, totalAmount;

    public CustomerExample2(String name) {
        _name = name;
        _result = "Rental Record for " + _name + "\n";
    }

    public String statement() {

        Enumeration rentals = _rentals.elements();

        while (rentals.hasMoreElements()) {

            RentalExample2 each = (RentalExample2) rentals.nextElement();

            double thisAmount = each.determineAmount(frequentRenterPoints++);

            totalAmount += thisAmount;

            _result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) + "\n";
        }

        resultfooterLineMessage();

        return _result;

    }

    private void resultfooterLineMessage() {
        _result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        _result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points" + "\n\n";
    }


    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }


    public String getName() {
        return _name;
    }
}