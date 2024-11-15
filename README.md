Question 1: Call Taxi Booking Application
Description:
Design a taxi booking application where customers can book taxis available at certain points in a linear route.

Problem Assumptions:
Taxi Count: Assume 4 taxis for simplicity, but it should scale to any number of taxis.
Points on Route: A, B, C, D, E, F (each 15 km apart)
Travel Time Between Points: 60 minutes
Charges:
Minimum Rs.100 for the first 5 km
Rs.10 per km thereafter
Initial Taxi Position: All taxis are stationed at A.
Booking Rules:
When a customer books a taxi:
A free taxi at the pickup point is allocated.
If no free taxi is available, the nearest taxi is allocated.
If two taxis are free at the same point, the one with lower earnings is allocated.
Taxis only charge from the pickup point to the drop point.
If no taxi is available, the booking is rejected.
Modules:
Call Taxi Booking

Sample Inputs and Outputs:

Input 1:

Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9
Output 1:

Taxi can be allotted.
Taxi-1 is allotted.
Input 2:

Customer ID: 2
Pickup Point: B
Drop Point: D
Pickup Time: 9
Output 2:

Taxi can be allotted.
Taxi-2 is allotted.
Input 3:

Customer ID: 3
Pickup Point: B
Drop Point: C
Pickup Time: 12
Output 3:

Taxi can be allotted.
Taxi-1 is allotted.
Display Taxi Details

Output Example:

Taxi-1    Total Earnings: Rs. 400
BookingID   CustomerID   From   To   PickupTime   DropTime   Amount
1           1            A      B    9           10         200
3           3            B      C    12          13         200

Taxi-2    Total Earnings: Rs. 350
BookingID   CustomerID   From   To   PickupTime   DropTime   Amount
2           2            B      D    9           11         350


Question 2: Food Delivery application.
 A food delivery company has 'n' number of delivery executives. For simplicity take the count as 5 but the program
 should work for any number of delivery executives (Let their names be identified as DE1, DE2....DE-n)
 There are only 5 restaurants in the city for pick-up and 5 drop locations (Each location can have multiple customers)
 After delivering a food package , the delivery executive waits there for devlivery allotment.
 Each customer is identified uniquely by a Customer-ID

 
 Write a program that does the following :
 Constraints :
 1.Delivery charge for every single order is Rs 50 for the delivery executive.
 2. If multiple orders (say n) are from the same delivery location within 15 mins period, combine orders to a maximum
 of 5 per delivery executive.
    In such case, the delivery charge will be base rate Rs.50 + Rs.5 for every other order (50+5 * (n-1)).
 3. An allowance of Rs.10 will be given for every trip made. Combined orders will be counted as a single trip.
 4. Assign the subsequent bookings giving preference to the executive who has earned the least delivery charge
 among the other available delivery executives excluding trip allowance.
 5. Every trip will take 30 mins to reach the destination.
 

 Input 1
 Customer ID: 1
 Restaurant: A
 Destionation Point : D
 Time : 9.00 AM
 Output
 Booking ID : 1
 Available Executives :
 Executive     Delivery Charge Earned
 DE1                      0
 DE2                      0
 DE3                      0
 DE4                      0
 DE5                      0
Allotted Delivery Executive: DE1

Input 2
 Customer ID: 2
 Restaurant : B
 Destination Point : A
 Time : 10.00 AM
 Output
 Booking ID : 2
 Available Executives :
 Executive     Delivery Charge Earned
 DE1                      50
 DE2                      0
 DE3                      0
 DE4                      0
 DE5                      0
 Allotted Delivery Executive: DE2
 
 Input 3
 Customer ID: 3
 Restaurant : B
 Destionation Point : A
 Time : 10.10 AM
 Output
 Booking ID : 3
 Available Executives :
 Executive     Delivery Charge Earned
 DE1                      50
 DE2                      50
 DE3                      0
 DE4                      0
 DE5                      0
 Allotted Delivery Executive: DE2 (because same location within 15mins)
 
 Input 4
 Customer ID: 3
 Restaurant : D
 Destionation Point : C
 Time : 10.35 AM
 Output
 Booking ID : 3
 
  Available Executives :
 Executive     Delivery Charge Earned
 DE1                      50
 DE2                      55
 DE3                      0
 DE4                      0
 DE5                      0
 Allotted Delivery Executive: DE3
 
![Screenshot 2024-11-15 134824](https://github.com/user-attachments/assets/000242ee-fb30-48f2-8467-fd3983f89dba)

 
 
