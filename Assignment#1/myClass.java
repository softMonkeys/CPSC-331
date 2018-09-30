import java.util.Scanner;

/**
 * Java program which will address the problem of comparison, addition and subtraction
 * of extremely large numbers constructed from thousands of digits
 * @author Benjamin Chen
 *
 */
public class myClass {
	public static void main(String[] args){
		String FirstInput;			// User's second input
		String SecondInput;			// User's first input
		String[] partsOne;			// String array for splitting the first input between the "."
		String[] partsTwo;			// String array for splitting the second input between the "."
		String partOne1 = null;		// The string on the left of "." for the first input
		String partOne2 = null;		// The string on the right of "." for the first input
		String partTwo1 = null;		// The string on the left of "." for the second input
		String partTwo2 = null;		// The string on the right of "." for the second input
		String sum = "";			// The addition of two user inputs
		String sub = "";			// The subtraction of two user inputs
		short total = 0;
		boolean addOne = false;		// Boolean expression checks if string index is greater than 10
		boolean deleteOne = false;	// Boolean expression checks if string index is less than 0
		String largerNumber = "";	// String to store the larger user input

		// User Inputs
		/**
		 * Precondition P1: Input include
		 * 	FirstInput: a valid String
		 * 	SecondInput: a valid String
		 *
		 * Postcondition Q1:
		 * 	Output are two Strings FirstInput and SecondInput
		 * 	FirstInput and SecondInput have not changed
		 */
		Scanner object = new Scanner(System.in);
		System.out.println("Please enter your first number: ");
		FirstInput = object.nextLine();
		System.out.println("Please enter your second number: ");
		SecondInput = object.nextLine();

		// Checks if the number is an decimal or integer number.
		// If decimal, split the decimal into 2 parts.
		// If integer, add ".0" after.

		/**
		 * Precondition P2: Input include
		 * 	FirstInput: contains "."
		 *
		 * Postcondition Q2:
		 * 	Output are :
		 * 		partOne1
		 * 		partOne2
		 * 	each been splitted from FirstInput
		 * 	FirstInput has not changed
		 */
		// First user input
		if(FirstInput.contains(".")){
			partsOne = FirstInput.split("\\.", 2);
			partOne1 = partsOne[0];
			partOne2 = partsOne[1];
		}else{
			FirstInput = FirstInput + ".0";
			partsOne = FirstInput.split("\\.", 2);
			partOne1 = partsOne[0];
			partOne2 = partsOne[1];
		}

		/**
		 * Precondition P3: Input include
		 * 	SecondInput: contains "."
		 *
		 * Postcondition Q3:
		 * 	Output are :
		 * 		partTwo1
		 * 		partTwo2
		 * 	each been splitted from SecondInput
		 * 	SecondInput has not changed
		 */
		// Second user input
		if(SecondInput.contains(".")){
			partsTwo = SecondInput.split("\\.", 2);
			partTwo1 = partsTwo[0];
			partTwo2 = partsTwo[1];
		}else{
			SecondInput = SecondInput + ".0";
			partsTwo = SecondInput.split("\\.", 2);
			partTwo1 = partsTwo[0];
			partTwo2 = partsTwo[1];
		}

		/**
		 * Precondition P4: Input include
		 * 	partOne1: a valid String
		 * 	partTwo1: a valid String
		 *
		 * Postcondition Q4:
		 * 	new strings:
		 * 		partOne1
		 * 		partTwo1
		 */
		// Checks which integer's length on the left of "." is greater
		// If a is an integer which has more length than b, add zero(s) afrer b in order to make
		// a and b same length
		if(partOne1.length() < partTwo1.length()){
			for(partOne1.length(); partOne1.length() < partTwo1.length(); partOne1.length()){
				partOne1 = "0" + partOne1;
			}
		}else if(partOne1.length() > partTwo1.length()){
			for(partTwo1.length(); partTwo1.length() < partOne1.length(); partTwo1.length()){
				partTwo1 = "0" + partTwo1;
			}
		}

		/**
		 * Precondition P5: Input include
		 * 	partOne1: a valid String
		 * 	partTwo1: a valid String
		 *
		 * Postcondition Q5:
		 * 	new strings:
		 * 		partOne2
		 * 		partTwo2
		 */
		// Checks which integer's length on the right of "." is greater
		// If a has longer decimal places than b, add zero(s) after b in order to make a and b's
		// decimal place same length
		if(partOne2.length() < partTwo2.length()){
			for(partOne2.length(); partOne2.length() < partTwo2.length(); partOne2.length()){
				partOne2 = partOne2 + "0";
			}
		}else if(partOne2.length() > partTwo2.length()){
			for(partTwo2.length(); partOne2.length() > partTwo2.length(); partTwo2.length()){
				partTwo2 = partTwo2 + "0";
			}
		}

		// Combine the splitted strings to form the
		FirstInput = partOne1 + "." + partOne2;
		SecondInput = partTwo1 + "." + partTwo2;

		System.out.println("Results: ");

		/**
		 * Precondition P6: Input include
		 * 	partOne1: a valid String
		 * 	partTwo1: a valid String
		 *
		 * Postcondition Q6:
		 * 	SecondInput
		 * 	FirstInput
		 */
		// Checks which number is greater
		// Now we can loop thought each of two strings' index in order to check which number is bigger
		for(short a = 0; a < partOne1.length(); a++){
			if(partOne1.charAt(a) < partTwo1.charAt(a)){
				largerNumber = SecondInput;
				System.out.println("The larger number: " + SecondInput);
				break;
			}
			if(partOne1.charAt(a) > partTwo1.charAt(a)){
				largerNumber = FirstInput;
				System.out.println("The larger number: " + FirstInput);
				break;
			}
		}

		/**
		 * Precondition P7: Input include
		 * 	partOne1: a valid String
		 * 	partTwo1: a valid String
		 *
		 * Postcondition Q7:
		 * 	new strings:
		 * 		largerNUmber
		 */
		// Checks which number is grater if numbers on the left of the "." are same
		if(partOne1.equals(partTwo1)){
			for(short b = 0; b < partTwo2.length(); b++){
				if(partOne2.equals(partTwo2)){
					largerNumber = FirstInput;
					System.out.println("The numbers are the same");
					break;
				}else if(partOne2.charAt(b) < partTwo2.charAt(b)){
					largerNumber = SecondInput;
					System.out.println("The larger number: " + SecondInput);
					break;
				}else if(partOne2.charAt(b) > partTwo2.charAt(b)){
					largerNumber = FirstInput;
					System.out.println("The larger number: " + FirstInput);
					break;
				}
			}
		}

		// Overwrite the "FirstInput" and "SecondInput" for addition
		FirstInput = partOne1 + partOne2;
		SecondInput = partTwo1 + partTwo2;

		// Addition of the two numbers
		short totalSp = (short)partTwo1.length();			// the length of the integer in the second user input
		/**
		 * Precondition P8: Input include
		 * 	SecondInput: a valid String
		 *
		 * Postcondition Q8:
		 * 	new strings:
		 * 		sum
		 */
		for(int c = SecondInput.length() - 1; c >= 0; c--){	// SecondInput.length() - 1 in order to avoid java.lang.StringIndexOutOfBoundsException
			// checks if the number addition reach the first digit on the left of the decimal number,
			// then we need to add "." on the left of the sum
			if(c == (short)totalSp -1){
				sum = "." + sum;
			}
			total = 0;
			short FirstInputAdd = (short)Character.getNumericValue(FirstInput.charAt(c));
			short SecondInputAdd = (short)Character.getNumericValue(SecondInput.charAt(c));
			// add FirstInputAdd and SecondInputAdd to get the first result
			total = (short)(FirstInputAdd + SecondInputAdd);
			// checks if the result after addition is bigger than 9(which means we need to carry 1 to the left)
			if(addOne == false){
				// if addition bigger than 9, we need to carry one to the left, so addOne = true
				if(total > 9){
					addOne = true;
				}
				// if it is less than 10, it means we are not out of the carry one range
				if(total <= 9){
					addOne = false;
					sum = Short.toString(total) + sum; // combine the previous result(s)
				}
				// if addition bigger than 9, we need to carry one to the left, so addOne = true,
				// then we minus total by 10
				if(total > 9){
					addOne = true;
					total = (short)(total - 10);
					sum = Short.toString(total) + sum; // combine the previous result(s)
				}
			}else if(addOne == true){				   // addOne is true, it mean we need carry one
				total = (short)(FirstInputAdd + SecondInputAdd + 1);	// carry one
				// if it is less than 10, it means we are not out of the carry one range
				if(total <= 9){
					addOne = false;
					sum = Short.toString(total) + sum; // combine the previous result(s)
				}
				// if addition bigger than 9, we need to carry one to the left, so addOne = true
				if(total > 9){
					addOne = true;
					total = (short)(total -10);
					sum = Short.toString(total) + sum; // combine the previous result(s)
				}
			}

		}
		// Print out the result of addition
		System.out.println("Sum: " + sum);

		/**
		 * Precondition P9: Input include
		 * 	FirstInput: a valid String
		 * 	SecondInput: a valid String
		 *
		 * Postcondition Q9:
		 * 	new strings:
		 * 		largerNUmber
		 */
		// Subtraction of two numbers
		for(short e = 0; e < FirstInput.length(); e++)
	    {
			// Checks which user inputs is larger, then store the larger number into string
			// variable "largerNumber" for future uses.
	    	if(FirstInput.charAt(e) > SecondInput.charAt(e))
	    	{
	    		largerNumber = FirstInput;
	    		break;
	    	}
	    	if(FirstInput.charAt(e) < SecondInput.charAt(e))
	    	{
	    		largerNumber = SecondInput;
	    		break;
	    	}
	    }

		/**
		 * Precondition P10: Input include
		 * 	FirstInput: a valid String
		 *
		 * Postcondition Q10:
		 * 	new strings:
		 * 		sub
		 */
		for(short d = (short)(FirstInput.length() - 1); d >= 0; d--){
				// checks if the number subtraction reach the first digit on the left of the decimal number,
				// then we need to add "." on the left of the sub.
				if(d == totalSp -1){
					sub = "." + sub;
				}
				short FirstInputAdd = (short)Character.getNumericValue(FirstInput.charAt(d));
				short SecondInputAdd = (short)Character.getNumericValue(SecondInput.charAt(d));
				// if the second user input is the larger number, use second input minus first input
				if(largerNumber == SecondInput){
					// minus SecondInputAdd and FirstInputAdd to get the first result
					total = (short)(SecondInputAdd - FirstInputAdd);
					// if string index is greater than 0
					if(deleteOne == false){
						if(total >= 0){
							sub = Short.toString(total) + sub;	// combine the previous result(s)
							deleteOne = false;
						}
						// if string index is less than 0, make deleteOne = true and plus the total by 10
						else if(total < 0){
							deleteOne = true;
							total = (short)(total + 10);
							sub = Short.toString(total) + sub;	// combine the previous result(s)
						}
					}
					// if string index is less than 0
					else if(deleteOne == true){
						total = (short)(total -1);				// delete one
						// if result is not less than 0, then we combing the answer with the previous result(s)
						if(total >= 0){
							sub = Short.toString(total) + sub;	// combine the previous result(s)
							deleteOne = false;
						}
						// if string index is less than 0, make deleteOne = true and plus the total by 10
						else if(total < 0){
							deleteOne = true;
							total = (short)(total + 10);
							sub = Short.toString(total) + sub;	// combine the previous result(s)
						}
					}
				}
				// if the first user input is the larger number, use first input minus second input
				else if (largerNumber == FirstInput){
					// minus FirstInputAdd and SecondInputAdd to get the first result
					total = (short)(FirstInputAdd - SecondInputAdd);
					// if string index is greater than 0
					if(deleteOne == false){
						if(total >= 0){
							sub = Short.toString(total) + sub;	// combine the previous result(s)
							deleteOne = false;
						}else if(total < 0){
							deleteOne = true;
							total = (short)(total + 10);
							sub = Short.toString(total) + sub;  // combine the previous result(s)
						}
					}
					// if string index is less than 0
					else if(deleteOne == true){
						total = (short)(total -1);				// delete one
						// if result is not less than 0, then we combing the answer with the previous result(s)
						if(total >= 0){
							sub = Short.toString(total)+ sub;   // combine the previous result(s)
							deleteOne = false;
						}
						// if string index is less than 0, make deleteOne = true and plus the total by 10
						else if(total < 0){
							deleteOne = true;
							total = (short)(total + 10);
							sub = Short.toString(total) + sub;	// combine the previous result(s)
					}
				}
			}
		}
		// Print out the result of subtraction
		System.out.println("Difference: " + sub);
	}
}
