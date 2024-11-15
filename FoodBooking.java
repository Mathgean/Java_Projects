package hello;
import java.util.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class FoodBooking {
	public static double convertToDecimal(String timeStr) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = LocalTime.parse(timeStr, inputFormat);
        int hour = time.getHour();
        int minute = time.getMinute();
        return hour + (minute / 60.0);
    }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Del_Ex[] dx_arr = new Del_Ex[5];
		for(int i = 0; i<5; i++) {
			dx_arr[i] = new Del_Ex(i+1);
		}
		ArrayList<Book> bookings = new ArrayList<>();
		int i = 0;
		while(i<100) {
			System.out.println("Enter your choice: 1 to Order Food and 2 to Show Details");
			int choice = scanner.nextInt();
			
			switch(choice) {
			case 1:
				System.out.println("Enter Order Id: ");
				int booking_id = scanner.nextInt();
				System.out.println("Enter Customer Id: ");
				int customer_id = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter Restarant name: ");
				char pickup_point = scanner.next().charAt(0);
				System.out.println("Enter Delivery Address: ");
				char delivery_point = scanner.next().charAt(0);
				System.out.println("Enter the pickup time in format of HH:MM (AM or PM) (e.g., 9:30 AM): ");
				scanner.nextLine();
		        String timeInput = scanner.nextLine();
		        double pickup_time = convertToDecimal(timeInput);
				
				Book b1 = new Book(booking_id,customer_id,pickup_point,delivery_point,pickup_time);
				if(b1.handle_booking(dx_arr, bookings) == -1) {
					System.out.println("This Booking is added to previous order.");
				}
				else bookings.add(b1);
				break;
				
			case 2:
				for(Book booking: bookings) {
					System.out.printf("%-5d %-5d %-5s %-5c %-5c %-5d %-5.2f %-5.2f %-5d%n",booking.booking_id, booking.customer_id,"DE"+ booking.ex_id, booking.pickup_point, booking.delivery_point, booking.no_of_orders, booking.pickup_time, booking.delivery_time ,booking.delivery_charge - 10);
				}
				for(Del_Ex dx: dx_arr) {
					System.out.printf("%-5s %-5d %-5.2f %-5.2f%n", "DE" + dx.ex_id,10, (dx.total_earnings <= 0)? dx.total_earnings:dx.total_earnings-10 - 10,dx.total_earnings);
				}
				break;
			}
			i++;
			}
		scanner.close();
		}    
	}


class Del_Ex{
	int ex_id;
	double total_earnings = 0;
	double busy_till = 0;
	
	public Del_Ex(int ex_id) {
		this.ex_id = ex_id;
	}
	
	public boolean is_free(double time) {
		if(this.busy_till < time) {
			return true;
		}
		return false;
	}
}

class Book{
	int booking_id;
	int customer_id;
	int ex_id;
	char pickup_point;
	char delivery_point;
	double pickup_time;
	double delivery_time;
	int delivery_charge;
	int no_of_orders;
	static int previous_book_id;
	
	public Book(int booking_id, int customer_id, char pickup_point, char delivery_point, double pickup_time){
		this.booking_id = booking_id;
		this.customer_id = customer_id;
		this.pickup_point = pickup_point;
		this.delivery_point = delivery_point;
		this.pickup_time = pickup_time;
		this.delivery_time = pickup_time + 0.75;
	}
	
	public int handle_booking(Del_Ex[] dx_arr, ArrayList<Book> bookings) {
		for(Book booking: bookings) {
			if(booking.booking_id == previous_book_id) {
				if(booking.pickup_point == this.pickup_point && booking.delivery_point == this.delivery_point && this.pickup_time <= booking.pickup_time+0.25) {
					for(Del_Ex dx: dx_arr) {
						if(dx.ex_id == booking.ex_id) {
							dx.total_earnings += 5;
							booking.no_of_orders += 1;
							booking.delivery_charge += 5;
							System.out.println("Booking Id: "+this.booking_id);
							System.out.println("Alloted Delivery Executive: DE"+ dx.ex_id);
							return -1;
						}
					}
				}
			}
		}
		int count = 0;
		for(Del_Ex dx: dx_arr) {
			if(dx.is_free(this.pickup_time)) {
				count += 1;
			}
		}
		int index = 0;
		Del_Ex[] free_dx = new Del_Ex[count];
		for(Del_Ex dx: dx_arr) {
			if(dx.is_free(this.pickup_time)) {
				free_dx[index] = dx;
				index += 1;
			}
		}
		double lower_earn = free_dx[0].total_earnings;
		for(Del_Ex dx: free_dx) {
			if(dx.total_earnings < lower_earn) {
				lower_earn = dx.total_earnings;
			}
		}
		
		for(Del_Ex dx: free_dx) {
			if(dx.total_earnings == lower_earn) {
				this.ex_id = dx.ex_id;
				this.delivery_charge = 50 + 10;
				this.no_of_orders += 1;
				previous_book_id = this.booking_id;
				dx.busy_till = this.delivery_time;
				dx.total_earnings = this.delivery_charge;
				System.out.println("Booking Id: "+this.booking_id);
				System.out.println("Alloted Delivery Executive: DE"+ dx.ex_id);
				return this.booking_id;
			}
		}
		return 100000;
	}	
}