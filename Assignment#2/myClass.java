import java.util.ArrayList;
import java.util.Scanner;

/**
 * Java program which will address the problem of multiplication
 * between large natural numbers(𝑛 ∈𝑁, 𝑛 >1) constructed from thousands
 * of digits.
 * 	A. Input: Your program should ask the user to enter two different natural numbers,
 *	B. Output: Your program should print the following:
 *		a. The outcome of the multiplication between the two natural numbers
 * 		b. All intermediate calculations (partial products) of the multiplication algorithm
 * @author Benjamin Chen
 */
public class myClass{
	public static void main(String[] args){
		long start = System.currentTimeMillis();

		String firstInput;												// first user input
		String secondInput;												// second user input
		ArrayList<String> intermediateList = new ArrayList<String>();	// array list for storing the intermediate values
		boolean greater = false;										// checks if two numbers multiplies >= 10
		short mul;														// result for multiplication
		String result = "0";											// result for addition
		short carry = 0;												// carry number when the multiplication is bigger then 10
		short zero = 0;												    // 0s added after each multiplication depends on the indexs
		String number = "";												// intermediate calculation
		short count = 1;												// counter for intermediate calculation

		/**
		 * Part 1: Ask for user inputs
		 * Precondition P1:
		 * 	1. Input include:
		 * 		-firstInput: a valid string
		 * 		-secondInput: a valid string
		 * 	2. Inputs are numbers not less than 1 or foreign symbol
		 *
		 * PostCondition Q1:
		 * 	1. Output include:
		 * 		-firstInput: string
		 * 		-secondInput: string
		 * 	2. The strings have not changed
		 */
		Scanner object = new Scanner(System.in);
		System.out.println("Please enter your first number: ");
		firstInput = object.nextLine();
		System.out.println("Please enter your second number: ");
		secondInput = object.nextLine();

		/**
		 * Part 2: Calculate intermediate values(outer loop)
		 * Precondition P2:
		 *  1. The numbers in strings are not less than 1 or foreign symbol
		 *  2. 0 <= short 'a' < secondInput.length
		 *  3. 0 <= short 'd' < zero
		 *
		 * Postcondition Q2:
		 * 	1. The strings have not changed
		 * 	2. The intermediate values have been written into the List
		 */
		// Create a nested loop to multiply the numbers in 2 Strings one by one
		for(short a = (short) (secondInput.length() - 1); a >= 0; a--){
			// add zero(s) starting from the second loop of the multiplication
			for(short d = 0; d < zero; d++){
				number = number + "0";
			}
			/**
			 * Part 3: Calculate intermediate values(inner loop)
			 * Precondition P3:
			 *  1. The numbers in strings are not less than 1 or foreign symbol
			 *  2. 0 <= short 'b' < firstInput.length
			 *
			 * Postcondition Q3:
			 * 	1. The strings have not changed
			 */
			// inside loop to increment the count in first input
			for(short b = (short) (firstInput.length() - 1); b >= 0; b--){
				short firstInputDig = (short) Character.getNumericValue(firstInput.charAt(b));	 // convert char to short
				short secondInputDig = (short) Character.getNumericValue(secondInput.charAt(a)); // convert char to short
				mul = (short) (firstInputDig * secondInputDig);		// multiply 2 short numbers together to get the result
				// 	 if statement for checking the result of multiplication is greater than or equal to 10, we initialize the flag to false
				// in order to not jump over the if statement below
				if(greater == false){
					if(mul > 9){		// if result greater than or equal to 10, it means we have carry
						greater = true;
					}
					if(mul < 10){		// if result less than 10, it means we have no carry
						greater = false;
						mul = (short) (carry + mul);		// add carry to result from last multiplication
					    number = Short.toString(mul) + number;		// combine the result from this loop with the result from last loop
					}
					if(mul > 9){		// if result greater than or equal to 10, it means we have carry
						greater = true;
						//   when number is greater than or equal to 10, we take the last
						// digit as the result of this loop, and first digit as the carry
						number = Short.toString(mul).substring(1, 2) + number;
						carry = Short.parseShort(Short.toString(mul).substring(0, 1));
					}
				}else if(greater = true){		//   the else statement may be running after the first loop depends on whether the multiplication
												// in the last loop is greater than 9 or not
					mul = (short) (carry + mul);		// add carry to result from last multiplication
					if(mul < 10){		// if result less than 10, it means we have no carry
						greater = false;
					    number = Short.toString(mul) + number;		// combine the result from this loop with the result from last loop
					    carry = 0;		// initialize carry back to 0
					}
					if(mul > 9){
						greater = true;
						number = Short.toString(mul).substring(1, 2) + number;
						carry = Short.parseShort(Short.toString(mul).substring(0, 1));

						if(b == 0){
							number = Short.toString(carry) + number;		// combine the result from this loop with the carry from last loop
							carry = 0;		// initialize carry back to 0
						}
					}
				}
			}
			intermediateList.add(number);		// add the intermediate value into the list
			number = "";		// initialize the number back to empty in order to get ready for the next loop
			zero++;		// next multiplication result will have one more 0 in the end
		}

		/**
		 * Part 4: add the intermediate calculations together
		 * Precondition P4:
		 * 	1. 0 <= short 'f' < intermediateList.size
		 *
		 * Postcondition Q4:
		 * 	1. string result for addition of all intermediate values
		 */
		for(short f = 0; f < (short) intermediateList.size(); f++){
			String str = intermediateList.get(f);
			result = add(str, result);
		}
		System.out.println("\nResults: \nProduct: " + result + "\n");
		System.out.println("Intermediate Calculation: \n");
		/**
		 * Part 5: Print out all the intermediate calculations
		 * Precondition P5:
		 * 	1. 0 <= short 'c' < intermediateLIst.size
		 *
		 * Postcondition Q5:
		 * 	1. Output include:
		 * 		-count: short
		 * 		-intermediate calculations: string
		 */
		for(short c = 0; c < (short)intermediateList.size(); c++){
			System.out.println("  " + count + ") " + intermediateList.get(c));
			count++;
		}

		long elapsed = System.currentTimeMillis() - start;
		System.out.println("\nTime elaped: " + elapsed);
	}

	private static String add(String str, String result) {
		short total = 0;			// result of addition between two chars
		String sum = "";			// result of addition between two intermediate calculations
		boolean addOne = false;		// checks if two numbers addition >= 10

		/**
		 * Part 6: Making two intermediate calculations in the same length
		 * Precondition P6:
		 * 	1. str and result are valid string
		 *
		 * Postcondition Q6:
		 * 	1. str or result: valid string
		 *
		 */
		if(str.length() < result.length()){
			for(str.length(); str.length() < result.length(); str.length()){
				str = "0" + str;		// add 0s in front of the shorter number until it reaches the same length as the longer number
			}
		}else if(str.length() > result.length()){
			for(result.length(); result.length() < str.length(); result.length()){
				result = "0" + result;	// add 0s in front of the shorter number until it reaches the same length as the longer number
			}
		}

		/**
		 * Part 7: Addition of two numbers
		 * Precondition P7:
		 * 	1. 0 <= short 'g' < str.length
		 * Postcondition Q7:
		 * 	1. sum: valid string
		 */
		// for loop or adding two numbers together
		for(short g = (short) (str.length() - 1); g >= 0; g--){
			short firstAdd = (short) Character.getNumericValue(str.charAt(g));		// convert char to short
			short secondAdd = (short) Character.getNumericValue(result.charAt(g));	// convert char to short
			total = (short) (firstAdd + secondAdd);		// result of addition between two numbers
			//   if statement for checking the result of addition is greater than or equal to 10, we initialize the flag to false in order
			// to not jump over the if statement below
			if(addOne == false){
				if(total > 9){		// if result greater than or equal to 10, it means we have carry
					addOne = true;
				}
				if(total < 10){		// if result less than 10, it means we have no carry
					addOne = false;
					sum = Short.toString(total) + sum;		// combine the result from this loop with the result from last loop
				}
				if(total > 9){
					addOne = true;
					total = (short) (total - 10);		// subtract total by 10 in order to get current result
					sum = Short.toString(total) + sum;		// combine the result from this loop with the result from last loop
				}
			}else if(addOne == true){
				total = (short) (firstAdd + secondAdd + 1); // result of addition of two numbers and carry 1
				if(total < 10){		// if result less than 10, it means we have no carry
					addOne = false;
					sum = Short.toString(total) + sum;		// combine the result from this loop with the result from last loop
				}
				if(total > 9){		// if result greater than or equal to 10, it means we have carry
					addOne = true;
					total = (short)(total - 10);		// subtract total by 10 in order to get current result
					sum = Short.toString(total) + sum;		// combine the result from this loop with the result from last loop
				}
			}
		}
		return sum;
	}
}
