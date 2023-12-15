package application;

import java.util.Scanner;
import java.util.Locale;

public class Program {

	public static void main(String[] args) {
		
		 Locale.setDefault(Locale.US);
	     Scanner sc = new Scanner (System.in);
	       
	     double rendaAnualSalario, rendaAnualServico, rendaAnualCapital, salarioMensal;
		 double gastosMedicos, gastosEducacionais;
		 double impostoSalario, impostoServico, impostoCapital;
		 double maximoDedutivel, gastosDedutiveis;
		 double impostoBruto, abatimento, impostoLiquido;
	       
	     System.out.println("Renda anual com salario: ");
	     rendaAnualSalario = sc.nextDouble();
	     System.out.println("Renda anual com prestaçao de serviço: ");
	     rendaAnualServico = sc.nextDouble();
	     System.out.println("Renda anual com ganho de capital: ");
	     rendaAnualCapital = sc.nextDouble();
	     System.out.println("Gastos médicos: ");
	     gastosMedicos = sc.nextDouble();
	     System.out.println("Gastos educacionais: ");
	     gastosEducacionais = sc.nextDouble();
	       
	     System.out.println("\nRELATORIO DE IMPOSTO DE RENDA\n");
	     System.out.println("CONSOLIDADO DE RENDA: ");
	     salarioMensal = rendaAnualSalario / 12.0;
	     
	     if (salarioMensal < 3000.0) {
	         impostoSalario = 0.0;
	     } else if (salarioMensal <= 5000) {
	         impostoSalario = rendaAnualSalario * 0.1;
	     } else {
	         impostoSalario = rendaAnualSalario * 0.2;
	     }
	       
	     impostoServico = rendaAnualServico * 0.15;
	     impostoCapital = rendaAnualCapital * 0.2;
	       
	     System.out.printf("Imposto sobre salario: %.2f\n", impostoSalario);
	     System.out.printf("Imposto sobre servico: %.2f\n", impostoServico);
	     System.out.printf("Imposto sobre ganho de capital: %.2f\n", impostoCapital);
	      
	     System.out.println("\nDEDUÇOES: ");
	     impostoBruto = impostoSalario + impostoServico + impostoCapital;
	     maximoDedutivel = impostoBruto * 0.3;
	     gastosDedutiveis = gastosMedicos + gastosEducacionais;
	       
	     if (gastosDedutiveis < maximoDedutivel) {
	         abatimento = gastosDedutiveis;
	     } else {
	         abatimento = maximoDedutivel;
	     }
	        
	     impostoLiquido = impostoBruto - abatimento;
	        
	     System.out.printf("Maximo dedutivel: %.2f", maximoDedutivel);
	     System.out.printf("\nGastos dedutiveis: %.2f\n", gastosDedutiveis);  
	     System.out.println("\nRESUMO: ");
	     System.out.printf("Imposto bruto total: %.2f\n", impostoBruto);
	     System.out.printf("Abatimento: %.2f\n", abatimento);
	     System.out.printf("Imposto devido: %.2f", impostoLiquido);	     
	        
	     sc.close();  

	}

}
