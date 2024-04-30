package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		try {
			System.out.println("Enter the contract data: ");
			System.out.print("Number: ");
			Integer contractNumber = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			System.out.print("Date (dd/MM/yyyy): ");
			LocalDate contractDate = LocalDate.parse(sc.nextLine(), fmt);
			System.out.print("Contract value: ");
			Double contractTotalValue = sc.nextDouble();
			
			Contract contract = new Contract(contractNumber, contractDate, contractTotalValue);
			
			System.out.print("Enter the number of installments: ");
			Integer installmentsNumber = sc.nextInt();
			
			ContractService service = new ContractService(new PaypalService());
			service.processContract(contract, installmentsNumber);
		
			System.out.println("Installments: ");
			for (Installment installment: contract.getInstallments()) {
				System.out.println(installment);
			}
			
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected error!");
		}
		
		sc.close();
	}

}
