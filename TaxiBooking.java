package hello;
import java.util.*;

public class TaxiBooking {
	public static void main(String[] args) {
		System.out.println("Hello");
		Scanner scan = new Scanner(System.in);
		Taxi_Details[] taxi_array = new Taxi_Details[4];
		for(int j = 1; j < 5; j++) {
			taxi_array[j-1] = new Taxi_Details(j);// it creates n no of taxi
			
		}
		ArrayList<Booking> bookings = new ArrayList<>();
		int i = 0;
		while(i < 100) {
			System.out.println("Enter your choice: 1 to Book Call Taxi and 2 to Show Taxi Details");
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter Booking Id: ");
				int booking_id = scan.nextInt();
				System.out.println("Enter Customer Id: ");
				int customer_id = scan.nextInt();
				scan.nextLine();
				System.out.println("Enter Pickup point: ");
				char pickup_point = scan.next().charAt(0);
				System.out.println("Enter Drop point: ");
				char drop_point = scan.next().charAt(0);
				System.out.println("Enter Pickup time: ");
				double pickup_time = scan.nextInt(); 
				Booking b1 = new Booking(booking_id,customer_id,pickup_point,drop_point,pickup_time);
				b1.book_taxi(taxi_array);
				bookings.add(b1);
				break;
				
			case 2:
				for(Taxi_Details td: taxi_array) {
					System.out.println("Taxi - "+ td.get_id()+"              Total Earnings : "+ td.get_total_earnings());
					for(Booking booking: bookings) {
						if(booking.taxi_no == td.get_id()) {
							System.out.printf("%-5d %-5d %-5c %-5c %-5.2f %-5.2f %-5.2f%n",booking.booking_id, booking.customer_id, booking.pickup_point, booking.drop_point, booking.pickup_time, booking.drop_time ,booking.amount);
						}
					}
				}
				break;
			}
			i++;
			}
		scan.close();
		}
}

class Taxi_Details{
	private int id;
	private double total_earnings = 0.0;
	private char current_location = 'A';
	private double busy_till = 0;
	
	public Taxi_Details(int id) {
		this.id = id;
	}
	
	public int get_id() {
		return this.id;
	}
	
	public double get_total_earnings(){
		return this.total_earnings;
	}
	
	public char get_current_location(){
		return this.current_location;
	}
	
	public double get_busy_till(){
		return this.busy_till;
	}
	
	
	public void set_total_earnings(double total_earnings) {
		this.total_earnings += total_earnings;
	}
	
	public void set_current_location(char location) {
		this.current_location = location;
	}

	public void set_busy_till(double time) {
		this.busy_till = time;
	}
	
	public boolean is_free(double pickup_time, char pickup_location) {
		int distance = Math.max((int) pickup_location ,(int) this.current_location) - Math.min((int) pickup_location ,(int) this.current_location);
		if(pickup_time >= this.busy_till + distance) return true;
		else return false;
	}
	
}




class Booking {
	int booking_id;
	int customer_id;
	char pickup_point;
	char drop_point;
	double pickup_time;
	int taxi_no;
	double amount;
	double drop_time;
	
	public Booking(int booking_id, int customer_id, char pickup_point, char drop_point, double pickup_time) {
		this.booking_id = booking_id;
		this.customer_id = customer_id;
		this.pickup_point = pickup_point;
		this.drop_point = drop_point;
		this.pickup_time = pickup_time;
			
	}
	
	int kilometers_of_travelling() {
		return (Math.max((int)pickup_point, (int)drop_point) - Math.min((int)pickup_point, (int)drop_point));
	}
	
	double calculate_price() {
		return 100.0 + (this.kilometers_of_travelling()*15-5) * 10;
		
	}
	
	void book_taxi(Taxi_Details[] taxi_array) {
		int count = 0;
		for(Taxi_Details td: taxi_array) {
			if(td.is_free(this.pickup_time, this.pickup_point)) {
				count += 1;
			}
		}
		if(count == 0) {
			System.out.println("Sorry, All the taxies are busy at that time. So, Your booking is rejected.");
		}
		else if(count == 1) {
			for(Taxi_Details td: taxi_array) {
				if(td.is_free(this.pickup_time, this.pickup_point)) {
					System.out.println("Taxi booking Confirmed");
					System.out.println("Taxi - "+td.get_id()+" is alloted for you");
					td.set_busy_till(this.pickup_time + this.kilometers_of_travelling());
					td.set_current_location(this.drop_point);
					this.amount = this.calculate_price();
					td.set_total_earnings(this.amount);
					this.taxi_no = td.get_id();
					this.drop_time = this.pickup_time + this.kilometers_of_travelling();
					break;
				}
			}
		}
		else {
			Taxi_Details free_taxi[] = new Taxi_Details[count];
			int pos = 0;
			for(Taxi_Details td: taxi_array) {
				if(td.is_free(this.pickup_time, this.pickup_point)) {
					free_taxi[pos] = td;
					pos += 1;
				}
			}
			int minimum = 10;
			for(Taxi_Details i: free_taxi) {
				int distance = Math.max((int)(i.get_current_location()),(int) this.pickup_point)-Math.min((int)(i.get_current_location()),(int) this.pickup_point);
				if(distance < minimum) {
					minimum = distance;
				}
			}
			int total = 0;
			for(Taxi_Details i: free_taxi) {
				int distance = Math.max((int)(i.get_current_location()),(int) this.pickup_point)-Math.min((int)(i.get_current_location()),(int) this.pickup_point);
				if(distance == minimum) {
					total += 1;
				}
			}
			
			if(total == 1) {
				for(Taxi_Details i: free_taxi) {
					int distance = Math.max((int)(i.get_current_location()),(int) this.pickup_point)-Math.min((int)(i.get_current_location()),(int) this.pickup_point);
					if(distance == minimum){
						System.out.println("Taxi booking Confirmed");
						System.out.println("Taxi - "+i.get_id()+" is alloted for you");
						i.set_busy_till(this.pickup_time + this.kilometers_of_travelling());
						i.set_current_location(this.drop_point);
						this.amount = this.calculate_price();
						i.set_total_earnings(this.amount);
						this.taxi_no = i.get_id();
						this.drop_time = this.pickup_time + this.kilometers_of_travelling();
						break;
					}
				}
			}
			else {
				Taxi_Details[] array = new Taxi_Details[total];
				int index = 0;
				for(Taxi_Details i: free_taxi) {
					int distance = Math.max((int)(i.get_current_location()),(int) this.pickup_point)-Math.min((int)(i.get_current_location()),(int) this.pickup_point);
					if(distance == minimum) {
						array[index] = i;
						index += 1;
					}
				}
				
				double lower_earning = array[0].get_total_earnings();
				for(Taxi_Details i: array) {
					if(i.get_total_earnings() < lower_earning) {
						lower_earning = i.get_total_earnings();
					}
				}
				for(Taxi_Details i: array) {
					if(i.get_total_earnings() == lower_earning) {
						System.out.println("Taxi booking Confirmed");
						System.out.println("Taxi - "+i.get_id()+" is alloted for you");
						i.set_busy_till(this.pickup_time + this.kilometers_of_travelling());
						i.set_current_location(this.drop_point);
						this.amount = this.calculate_price();
						i.set_total_earnings(this.amount);
						this.taxi_no = i.get_id();
						this.drop_time = this.pickup_time + this.kilometers_of_travelling();
						break;
					}
				}
			}
		}
	}
}