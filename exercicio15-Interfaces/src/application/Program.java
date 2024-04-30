package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter the contract data: ");
			System.out.print("Number: ");
			Integer contractNumber = sc.nextInt();
			System.out.print("Date (dd/MM/yyyy): ");
			Date contractDate = sdf.parse(sc.next());
			System.out.print("Contract value: ");
			Double contractTotalValue = sc.nextDouble();
			
			Contract contract = new Contract(contractNumber, contractDate, contractTotalValue);
			
			System.out.print("Enter the number of installments: ");
			Integer installmentsNumber = sc.nextInt();
			
			ContractService service = new ContractService(new PaypalService());
			int i = 0;
			while (i < installmentsNumber) {
				service.processContract(contract, installmentsNumber);
				i++;
			}
			
			System.out.println("Installments: ");
			for (Installment installment: contract.getInstallments()) {
				System.out.println(sdf.format(installment.getDueDate()) + " - " + String.format("%.2f", installment.getAmount()));
			}
			
		}
		catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		sc.close();
	}

}
