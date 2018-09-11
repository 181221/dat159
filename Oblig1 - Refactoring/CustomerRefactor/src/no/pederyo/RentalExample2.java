package no.pederyo;

public class RentalExample2 {
    private Movie _movie;
    private int _daysRented;

    public RentalExample2(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public double determineAmount(int frequentRenterPoints) {
        double thisAmount = 0;
        switch (_movie.getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (_daysRented > 2)
                    thisAmount += (_daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                if(_daysRented > 1) frequentRenterPoints ++;
                thisAmount += _daysRented * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (_daysRented > 3)
                    thisAmount += (_daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDaysRented() {
        return _daysRented;
    }
}
