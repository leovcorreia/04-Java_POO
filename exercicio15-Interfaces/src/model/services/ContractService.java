package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		Double baseValue = contract.getTotalValue() / months;
		
		Integer installmentMonth = contract.getInstallments().size() + 1;	
		Double interestAmount = baseValue + paymentService.interest(baseValue, installmentMonth);
			
		Double installmentValue = interestAmount + paymentService.paymentFee(interestAmount);
		
		// DO THE RIGHT DATE RIGHT HERE
			
		Installment installment = new Installment(contract.getDate(), installmentValue);
		contract.addInstallment(installment);
		
	}

}
