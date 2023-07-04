/**
 * 
 */
package test;

/**
 * @author reinh
 *
 */
public class credit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double principal = 130000.00;
		double rate = 0.005;
//		rate = 0.02875;
		double time = 30;
		int numberOfPayments = (int) (12 * time);
		double outstandingPayment = 0;
		for (int i = 1; i <= numberOfPayments; i++) {
			// convert interest rate to monthly rate
			double monthlyRate = rate / 12;
			if (i == 3) {
				// change interest rate every three months
				rate = 0.00875;
			}
			if (i == 6) {
				// change interest rate every three months
				rate = 0.01875;
			}
			if (i == 9) {
				// change interest rate every three months
				rate = 0.02875;
			}
		    double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -(numberOfPayments - i + 1)));
		    double interest = principal * monthlyRate;
		    double valuta = monthlyPayment - interest;
		    principal = principal - valuta;
		     outstandingPayment = principal;
			System.out.println("Month " + i + ": Interest = " + interest + ", Valuta = " + valuta + ", Sum = "
					+ (valuta + interest) + ", Outstanding Payment = " + outstandingPayment);
		}
		System.out.println("Outstanding Payment :" + outstandingPayment);
	}
}
