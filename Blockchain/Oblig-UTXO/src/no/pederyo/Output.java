package no.pederyo;

import java.util.Objects;

public class Output {

    //Simplified compared to Bitcoin - The address should be a script
	private long value;
	private String address;
	
	public Output(long value, String address) {
	    this.value = value;
	    this.address = address;
	}

	public long getValue() {
		return value;
	}

	public String getAddress() {
		return address;
	}


	@Override
	public String toString() {
		return "Output{" +
				"value=" + value +
				", address='" + address + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Output output = (Output) o;
		return value == output.value &&
				Objects.equals(address, output.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, address);
	}
}
