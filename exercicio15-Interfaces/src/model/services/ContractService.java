package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		Double baseValue = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			Double interest = paymentService.interest(baseValue, i);
			Double fee = paymentService.paymentFee(baseValue + interest);
			Double installmentValue = baseValue + interest + fee;
			
			Installment installment = new Installment(dueDate, installmentValue);
			contract.getInstallments().add(installment);
		}
			
	}	

}
